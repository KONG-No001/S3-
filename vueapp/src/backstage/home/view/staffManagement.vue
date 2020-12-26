<template>
    <div>
        <el-table :data="staffList" v-loading="loading" height="500">
            <el-table-column label="NO" property="id" width="50" align="center">
            </el-table-column>
            <el-table-column label="账号名称" property="name" width="150">
            </el-table-column>
            <el-table-column label="所属部门" property="department" width="200">
            </el-table-column>
            <el-table-column label="头像" property="portrait" :width="150">
                <el-image slot-scope="{row}" :src="row.portrait" style="width:32mm;height:32mm;"></el-image>
            </el-table-column>
            <el-table-column label="角色" property="null" width="225">
                <template slot-scope="scope" >
                    <el-tag class="col_role_tag" v-for="role in scope.row.roles.slice(0,3)" :key="role.id" :type="role.type">{{role.name}}</el-tag>
                    <el-tag class="col_role_tag" v-if="scope.row.roles.length>3" type="info" >+{{scope.row.roles.length-3}}</el-tag>
                </template>
            </el-table-column>
            <el-table-column align="right">
                <template slot="header" slot-scope="scope">
                    <el-row>
                        <el-col :span="5">
                            <el-button size="small" @click="handleAdd" type="primary" >添加</el-button>
                        </el-col>
                        <el-col :span="18" :offset="1">
                            <el-input
                                    size="small"
                                    v-model="search.value"
                                    placeholder="输入关键字搜索">
                                {{scope.id}}
                                <el-select v-model="search.type" slot="prepend" style="width: 100px" >
                                    <el-option v-for="type in types" :label="type.text" :value="type.value" :key="type.value"></el-option>
                                </el-select>
                            </el-input>
                        </el-col>
                    </el-row>
                </template>
                <template slot-scope="scope">
                    <el-button
                            @click="roleEdit(scope.$index,scope.row)"
                            type="" size="small">
                        角色
                    </el-button>
                    <el-button @click="handleEdit(scope.$index,scope.row)" type="info" size="small">
                        编辑
                    </el-button>
                    <el-button
                        @click="handleDelete(scope.$index,scope.row)"
                        type="danger" size="small">
                        删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-dialog :visible.sync="staffDialogBox.visible" width="360px">
            <template slot="title">
                <span v-if="staffDialogBox.formType==='edit'" >Edit: No:{{staffForm.id}}</span>
            </template>
            <template slot="default">
                <el-form ref="staffForm" :rules="staffRules" :model="staffForm" label-position="right" label-width="80px" size="small">
                    <el-form-item label="账户名称" prop="name" >
                        <el-input v-model="staffForm.name"></el-input>
                    </el-form-item>
                    <template v-if="staffDialogBox.formType==='edit'" >
                        <el-form-item label="账户密码">
                            <el-checkbox label="重置密码" v-model="staffForm.resetpwd"></el-checkbox>
                        </el-form-item>
                    </template>
                    <el-form-item label="用户头像" prop="portrait">
                        <el-upload
                                class="avatar-uploader"
                                ref="portraitUploader"
                                action="/application/FileUpdate/directUpdate.action"
                                :show-file-list="false"
                                :auto-upload="false"
                                :on-change="handlePortraitChange"
                                >
                            <img v-if="staffForm.portrait" class="avatar" :src="staffForm.portrait">
                            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                        </el-upload>
                    </el-form-item>
                    <el-form-item label="所属部门" prop="department" >
                        <el-input v-model="staffForm.department"></el-input>
                    </el-form-item>
                </el-form>
            </template>
            <template slot="footer">
                <el-button v-if="staffDialogBox.formType==='edit'" size="small" type="success" @click="staffEditSub" >确认</el-button>
                <el-button v-if="staffDialogBox.formType==='add'" size="small" type="success" @click="staffAddSub" >确认</el-button>
                <el-button size="small" type="info" @click="staffDialogBox.visible=false" >取消</el-button>
            </template>
        </el-dialog>
        <el-dialog :visible.sync="roleDialogBox.visible" width="627px" >
            <template slot="title">
                角色
            </template>
            <template slot="default">
                <el-transfer
                        :data="roleList"
                        v-model="currentRole"
                        :titles="['未使用角色','已使用角色']"
                        :props="{
                            key: 'id',
                            label: 'name',
                        }"
                >
                    <el-tag slot-scope="{option}" :type="option.type" size="small" >{{option.name}}</el-tag>
                </el-transfer>

            </template>
            <template slot="footer">
                <el-button size="small" >取消</el-button>
                <el-button type="primary" size="small" @click="onUpdateRoleRelation" >确认</el-button>
            </template>
        </el-dialog>
    </div>
</template>

<script>
    import Axios from "axios";

    export default {
        name: "staffManagement",
        data: function(){
            return {
                staffList: [],
                search: {
                    type: "id",
                    value: "",
                },
                loading: true,
                staffDialogBox: {
                    formType: "edit",
                    visible: false,
                },
                roleDialogBox:{
                    visible: false,
                },
                staffForm: {},
                avatarUpdate:{
                    file:null,
                },
                staffRules: {
                    name: [
                        {required:true,message:"请输入用户名称"}
                    ],
                    department: [
                        {required:true,message:"请输入所属部门"}
                    ]
                },
                roleList:[],
                currentRole:[],
                currentStaff:0,
                types: [
                    {text:"NO",value:"id"},
                    {text:"职员名称",value:"name"},
                    {text:"所属部门",value:"department"},
                ],

            }
        },
        methods:{
            loadingTable(){
                let _this = this;
                _this.loading = true;
                Axios.get("/application/staff/listStaff.action",{
                    params: _this.search
                }).then((request)=>{
                    _this.staffList = request.data;
                    _this.loading = false;
                })
            },
            handleEdit:function (index, row) {
                this.staffDialogBox.visible = true;
                this.staffDialogBox.formType = "edit";
                // 将row转变为无属性对象，不然会与staffList的item冲突
                this.staffForm = JSON.parse(JSON.stringify(row));
                this.avatarUpdate.file = null;
            },
            handleDelete:function (index, row) {
                let _this =  this;

                _this.$confirm("此操作将永久删除该条记录, 是否继续?",'确认删除',{
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(()=>{

                    const loading = _this.$loading({
                        lock: true,
                        text: "Loading",
                        spinner: 'el-icon-loading',
                        background: 'rgba(0, 0, 0, 0.7)'
                    });

                    Axios.get("/application/staff/remove.action",{
                        params: {id: row.id}
                    }).then((response)=>{
                        if(response.data){
                            this.$message({
                                type: 'success',
                                message: '删除成功!'
                            });
                            _this.loadingTable();
                        }else{
                            this.$message({
                                type: 'error',
                                message: '删除失败!'
                            });
                        }
                        loading.close();
                    }).catch(()=>{
                        this.$message({
                            type: 'error',
                            message: '删除异常!'
                        });
                    })
                }).catch(()=>{
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                })

                console.log(index, row);

            },
            roleEdit(index,row){
                let _this = this;
                this.roleDialogBox.visible = true;
                _this.currentRole = [];
                _this.currentStaff = row.id;
                Axios.get("/application/role/show.action").then(response=>{
                    _this.roleList = response.data;
                });
                row.roles.forEach(role=>{
                    _this.currentRole.push(role.id);
                })
            },
            async staffEditSub(){
                let _this = this;
                let formData = new FormData()

                const loading = _this.$loading({
                    lock: true,
                    text: "Loading",
                    spinner: 'el-icon-loading',
                    background: 'rgba(0, 0, 0, 0.7)'
                });
                formData.append("staffUpdate",JSON.stringify(_this.staffForm))
                if(_this.avatarUpdate.file)formData.append("avatarUpdate",_this.avatarUpdate.file.raw)
                Axios.post("/application/staff/update.action",formData,{headers:{'content-type':'multipart/form-data;'}}).then(response=>{
                    if(response.data===true){
                        _this.$message({
                            message: "更改成功！！！",
                            type: "success"
                        });
                        _this.staffDialogBox.visible=false;
                        _this.loadingTable();
                    }else {
                        _this.$message({
                            message:"更改失败",
                            type: "error"
                        });
                    }
                    loading.close();
                }).catch(()=>{
                    _this.$message({
                        message:"更改异常",
                        type: "error"
                    });
                    loading.close();
                });

            },
            handleAdd(){
                this.staffDialogBox.visible = true;
                this.staffDialogBox.formType = "add";
                this.staffForm = {};
            },
            staffAddSub(){
                let _this = this;
                this.$refs["staffForm"].validate(valid=>{
                    if(valid){
                        const loading = _this.$loading({
                            lock: true,
                            text: 'Loading',
                            spinner: 'el-icon-loading',
                            background: 'rgba(0, 0, 0, 0.7)'
                        });
                        Axios.post("/application/staff/add.action",_this.staffForm).then(response=>{
                            if(response.data){
                                _this.$message({
                                    message: "添加成功！！！",
                                    type: "success"
                                });
                                _this.staffDialogBox.visible=false;
                                _this.loadingTable();
                            }else {
                                _this.$message({
                                    message:"添加失败",
                                    type: "error"
                                });
                            }
                            loading.close();
                        }).catch(()=>{
                            _this.$message({
                                message:"添加异常",
                                type: "error"
                            });
                            loading.close();
                        });
                    }else {
                        _this.$message({
                            message:"Warning：请将必要信息填写完整！！！",
                            type: "warning"
                        });
                    }
                })
            },
            onUpdateRoleRelation(){
                let _this = this;
                const loading = _this.$loading({
                    lock: true,
                    text: 'Loading',
                    spinner: 'el-icon-loading',
                    background: 'rgba(0, 0, 0, 0.7)'
                });
                Axios.post("/application/staff/updateRoleRelation.action", {
                    staffId: this.currentStaff,
                    roleIds:this.currentRole
                }).then(response=>{
                    if(response.data){
                        _this.$message({
                            message: "添加成功！！！",
                            type: "success"
                        });
                        _this.roleDialogBox.visible=false;
                        _this.loadingTable();
                    }else {
                        _this.$message({
                            message:"添加失败",
                            type: "error"
                        });
                    }
                    loading.close();
                }).catch(()=>{
                    _this.$message({
                        message:"添加异常",
                        type: "error"
                    });
                    loading.close();
                });
            },
            handlePortraitChange(file){
                this.staffForm = JSON.parse(JSON.stringify(Object.assign(
                    this.staffForm,
                    {portrait:URL.createObjectURL(file.raw)}
                )));
                this.avatarUpdate.file = file;
            },
        },
        created: function () {
            this.loadingTable();
        },
        watch:{
            "search.value": function () {
                this.loadingTable();
            }
        },
    }
</script>

<style scoped>
    .avatar-uploader{
        border: 2px dashed #d9d9d9;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
        width: 32mm;
        height: 32mm;
    }
    .avatar-uploader:hover {
        border-color: #409EFF;
    }
    .avatar-uploader-icon {
        font-size: 20px;
        color: #8c939d;
        width: 32mm;
        height: 32mm;
        line-height: 32mm;
        text-align: center;
    }
    .avatar-uploader-icon:hover {
        color: #409EFF;
    }
    .avatar {
        width: 32mm;
        height: 32mm;
        border-radius: 6px;
        display: block;
    }

    .col_role_tag{
        margin: 5px;
    }

</style>