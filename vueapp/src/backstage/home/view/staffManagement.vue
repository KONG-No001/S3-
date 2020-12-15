<template>
    <div>
        <el-table :data="userList" v-loading="loading" height="500">
            <el-table-column label="NO" property="id" width="50" align="center">
            </el-table-column>
            <el-table-column label="账号名称" property="name" width="150">
            </el-table-column>
            <el-table-column label="所属部门" property="department" width="200">
            </el-table-column>
            <el-table-column label="头像" property="portrait" :width="120">
                <el-image src="#" style="width: 26mm;height: 32mm;"></el-image>
            </el-table-column>
            <el-table-column label="角色" property="null" width="200">
                <template>
                    <el-tag>角色1</el-tag>
                    <el-tag>角色2</el-tag>
                    <el-tag>+1</el-tag>
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
        <el-dialog :visible.sync="userDialogBox.visible" width="360px">
            <template slot="title">
                <span v-if="userDialogBox.formType==='edit'" >Edit: No:{{userForm.id}}</span>
            </template>
            <template slot="default">
                <el-form ref="userForm" :rules="userRules" :model="userForm" label-position="right" label-width="80px" size="small">
                    <el-form-item label="账户名称" prop="name" >
                        <el-input v-model="userForm.name"></el-input>
                    </el-form-item>
                    <template v-if="userDialogBox.formType==='edit'" >
                        <el-form-item label="账户密码">
                            <el-checkbox label="重置密码" v-model="userForm.resetpwd"></el-checkbox>
                        </el-form-item>
                    </template>
                    <el-form-item label="用户头像" prop="portrait">
                        <el-upload
                                class="avatar-uploader"
                                action="#"
                                :show-file-list="false">
                            <img v-if="userForm.portrait" class="avatar" :src="userForm.portrait" >
                            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                        </el-upload>
                    </el-form-item>
                    <el-form-item label="所属部门" prop="department" >
                        <el-input v-model="userForm.department"></el-input>
                    </el-form-item>
                </el-form>
            </template>
            <template slot="footer">
                <el-button v-if="userDialogBox.formType==='edit'" size="small" type="success" @click="userEditSub" >确认</el-button>
                <el-button v-if="userDialogBox.formType==='add'" size="small" type="success" @click="userAddSub" >确认</el-button>
                <el-button size="small" type="info" @click="userDialogBox.visible=false" >取消</el-button>
            </template>
        </el-dialog>
        <el-dialog :visible.sync="roleDialogBox.visible" width="627px" >
            <template slot="title">
                角色
            </template>
            <template slot="default">
                <el-transfer :data="roleList"></el-transfer>
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
                userList: [],
                search: {
                    type: "id",
                    value: "",
                },
                loading: true,
                userDialogBox: {
                    formType: "edit",
                    visible: false,
                },
                roleDialogBox:{
                    visible: false,
                },
                userForm: {
                },
                userRules: {
                    name: [
                        {required:true,message:"请输入用户名称"}
                    ],
                    department: [
                        {required:true,message:"请输入所属部门"}
                    ]
                },
                roleList:[

                ],

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
                Axios.get("/application/user/listUser.action",{
                    params: _this.search
                }).then((request)=>{
                    _this.userList = request.data;
                    _this.loading = false;
                })
            },
            handleEdit:function (index, row) {
                this.userDialogBox.visible = true;
                this.userDialogBox.formType = "edit";
                // 将row转变为无属性对象，不然会与userList的item冲突
                this.userForm = JSON.parse(JSON.stringify(row));
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

                    Axios.get("/application/user/remove.action",{
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
                this.roleDialogBox.visible = true;
                console.log(index, row);
            },
            userEditSub(){
                let _this = this;

                const loading = _this.$loading({
                    lock: true,
                    text: "Loading",
                    spinner: 'el-icon-loading',
                    background: 'rgba(0, 0, 0, 0.7)'
                });

                Axios.post("/application/user/update.action",_this.userForm).then(response=>{
                    if(response.data===true){
                        _this.$message({
                            message: "更改成功！！！",
                            type: "success"
                        });
                        _this.userDialogBox.visible=false;
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
                this.userDialogBox.visible = true;
                this.userDialogBox.formType = "add";
                this.userForm = {};
            },
            userAddSub(){
                let _this = this;
                this.$refs["userForm"].validate(valid=>{
                    if(valid){
                        const loading = _this.$loading({
                            lock: true,
                            text: 'Loading',
                            spinner: 'el-icon-loading',
                            background: 'rgba(0, 0, 0, 0.7)'
                        });
                        Axios.post("/application/user/add.action",_this.userForm).then(response=>{
                            if(response.data){
                                _this.$message({
                                    message: "添加成功！！！",
                                    type: "success"
                                });
                                _this.userDialogBox.visible=false;
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
        border: 1px dashed #DDDDDD;
        border-radius: 1px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
        width: 26mm;
        height: 32mm;
    }
    .avatar-uploader:hover{
        border-color: #409EFF;;
    }
    .avatar-uploader-icon{
        font-size: 20px;
        color: #8c939d;
        width: 26mm;
        height: 32mm;
        line-height: 32mm;
        text-align: center;
    }

</style>