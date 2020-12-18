<template>
    <div>
        <el-row>
            <el-col :span="12" style="height: 540px">
                <el-table height="540"
                          :data="roleList"
                          v-loading="tableLoading"
                          @current-change="onSelectRole"
                          highlight-current-row >
                    <el-table-column label="编号" prop="id" width="80px" align="center"></el-table-column>
                    <el-table-column label="名称" prop="name" width="100px"></el-table-column>
                    <el-table-column label="类别" prop="type" width="100px">
                        <template slot-scope="scope">
                            <el-popover>
                                <p>{{roleTypes[scope.row.type]}}</p>
                                <template slot="reference">
                                    <el-tag :type="scope.row.type" effect="dark" >{{scope.row.type}}</el-tag>
                                </template>
                            </el-popover>
                        </template>
                    </el-table-column>
                    <el-table-column align="right" >
                        <template slot="header" slot-scope="scope" >
                            <el-input
                                    size="small"
                                    v-model="search.value"
                                    placeholder="输入关键字搜索">
                                {{scope.id}}
                            </el-input>
                        </template>
                        <template>
                            <el-button type="danger" size="small">删除</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </el-col>
            <el-col :offset="1" :span="10" style="height: 540px">
                <el-card style="height: 100%">
                    <template v-if="page==='add'">
                        <el-page-header @back="page='controller'" slot="header" content="添加页面"></el-page-header>
                        <el-form ref="roleAddForm" :model="roleAddForm" :rules="roleAddFormRules" label-position="left" label-width="80px" >
                            <el-form-item label="名称" prop="name">
                                <el-input v-model="roleAddForm.name" ></el-input>
                            </el-form-item>
                            <el-form-item label="类别" prop="type">
                                <el-select v-model="roleAddForm.type" style="width: 100%" >
                                    <el-option v-for="(value,name) in roleTypes"
                                               :key="name"
                                               :label="name"
                                               :value="name" >
                                        <span>{{name}}</span>
                                        <el-tag :type="name" style="float: right" >{{name}}</el-tag>
                                    </el-option>
                                </el-select>
                            </el-form-item>
                            <el-form-item label="备注" prop="remark" >
                                <el-input type="textarea" v-model="roleAddForm.remark"  ></el-input>
                            </el-form-item>
                            <el-form-item>
                                <el-button style="float: right" type="primary" @click="doRoleAdd" >添加</el-button>
                            </el-form-item>
                        </el-form>
                    </template>
                    <template v-else-if="page==='update'">
                        <el-page-header @back="page='controller'" slot="header" content="更改页面"></el-page-header>
                        <el-form ref="roleUpdateForm" :model="roleUpdateForm" label-position="left" label-width="80px" >
                            <el-form-item label="编号" prop="id">
                                {{roleUpdateForm.id}}
                            </el-form-item>
                            <el-form-item label="名称" prop="name">
                                <el-input v-model="roleUpdateForm.name" ></el-input>
                            </el-form-item>
                            <el-form-item label="类别" prop="type">
                                <el-select v-model="roleUpdateForm.type" style="width: 100%" >
                                    <el-option v-for="(value,name) in roleTypes"
                                               :key="name"
                                               :label="name"
                                               :value="name" >
                                        <span>{{name}}</span>
                                        <el-tag :type="name" style="float: right" >{{name}}</el-tag>
                                    </el-option>
                                </el-select>
                            </el-form-item>
                            <el-form-item label="权限" prop="perm">
                                <el-button type="text" @click="drawerProp.visible = true" >修改</el-button>
                            </el-form-item>
                            <el-form-item label="备注" prop="remark" >
                                <el-input type="textarea" v-model="roleUpdateForm.remark"  ></el-input>
                            </el-form-item>
                            <el-form-item>
                                <el-button style="float: right" type="primary" @click="doRoleUpdate" >更改</el-button>
                            </el-form-item>
                        </el-form>
                    </template>
                    <template  v-else-if="page==='controller'">
                        <div slot="header">
                            <span style="font-size: 18px;color: #303133;line-height: 24px">操作</span>
                        </div>
                        <el-row :gutter="20">
                            <el-col :span="12">
                                <el-button style="width: 100%;" type="primary" @click="page='add'" >添加</el-button>
                            </el-col>
                            <el-col :span="12">
                                <el-button style="width: 100%;" type="warning" @click="shwoUpdatePage"  :disabled="roleInfo.name==null">更改</el-button>
                            </el-col>
                        </el-row>
                        <el-row :gutter="20">
                            <el-col :span="6"><span class="info_label">名称：</span> </el-col>
                            <el-col :span="18" class="info_value" >{{roleInfo.name}}</el-col>
                        </el-row>
                        <el-row :gutter="20">
                            <el-col :span="6"><span class="info_label">类别：</span></el-col>
                            <el-col :span="18" class="info_value" ><el-tag v-if="roleInfo.type" :type="roleInfo.type">{{roleInfo.type}}</el-tag></el-col>
                        </el-row>
                        <el-row :gutter="20">
                            <el-col :span="6"><span class="info_label">权限：</span>
                            </el-col>
                            <el-col :span="18" class="info_value" >
                                <el-tag v-for="perm in roleInfo.perms" :type="perm.type" :key="perm.id" >{{perm.name}}</el-tag>
                            </el-col>
                        </el-row>
                        <el-row :gutter="20">
                            <el-col :span="6"><span class="info_label">备注：</span></el-col>
                            <el-col :span="18" class="info_value" >
                                {{roleInfo.remark}}
                            </el-col>
                        </el-row>
                    </template>

                </el-card>
            </el-col>
        </el-row>
        <el-drawer
                :visible.sync="drawerProp.visible"
                :direction="drawerProp.direction"
        >

        </el-drawer>
    </div>
</template>

<script>

    const roleTypes = {
        default: "默认的",
        danger: '危险的，管理重要资源的',
        success: "安全的，不会造成系统崩溃的",
        info: "访客，不可更改资源的",
        warning: "需要注意的，可能会造成崩溃的",
    }

    export default {
        name: "roleManagement",
        data(){
            return{
                roleList: [],
                roleTypes,
                tableLoading:true,
                search: {
                    type: "id",
                    value: "",
                },
                roleInfo: {},
                roleAddForm: {type:"success"},
                roleAddFormRules:{
                    name: [
                        {required: true,message:"请输入角色名"}
                    ],
                    type: [
                        {required:true,message:"请输入角色类型"}
                    ]
                },
                roleUpdateForm: {},
                page:"controller",
                drawerProp:{
                    visible: false,
                    direction: 'rtl'
                }
            }
        },
        methods: {
            loadingTable(){
                let _this = this;
                _this.tableLoading=true
                let Axios = _this.$axios;
                Axios.get("/application/role/list.action").then(response=>{
                    _this.roleList = response.data;
                    _this.tableLoading = false
                })
            },
            onSelectRole(currentRow){
                if(currentRow) {
                    this.roleInfo = JSON.parse(JSON.stringify(currentRow));
                }else {
                    this.roleInfo = {}
                }

            },
            doRoleAdd(){
                let _this = this;
                this.$refs["roleAddForm"].validate(valid=>{
                    if(valid){
                        const loading = _this.$loading({
                            lock: true,
                            text: "Loading",
                            spinner: 'el-icon-loading',
                            background: 'rgba(0, 0, 0, 0.7)'
                        });
                        this.$axios.post("/application/role/add.action",_this.roleAddForm).then(response=>{
                            if(response.data){
                                _this.$message({
                                    message: "添加成功！！！",
                                    type: "success"
                                });
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

            shwoUpdatePage(){
                this.page='update';
                this.roleUpdateForm = JSON.parse(JSON.stringify(this.roleInfo));
            },
            doRoleUpdate(){
                let _this = this;
                _this.$refs["roleUpdateForm"].validate(valid=>{
                    if(valid){
                        const loading = _this.$loading({
                            lock: true,
                            text: "Loading",
                            spinner: 'el-icon-loading',
                            background: 'rgba(0, 0, 0, 0.7)'
                        });
                        this.$axios.post("/application/role/update.action",_this.roleUpdateForm).then(response=>{
                            if(response.data){
                                _this.$message({
                                    message: "更改成功！！！",
                                    type: "success"
                                });
                                _this.loadingTable();
                            }else {
                                _this.$message({
                                    message:"更改成功",
                                    type: "error"
                                });
                            }
                            loading.close();
                        }).catch(()=>{
                            _this.$message({
                                message:"更改成功",
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
                });
            }

        },
        created(){
            this.loadingTable();
        }
    }
</script>

<style scoped>
    .el-row {
        margin-bottom: 20px
    }
    .info_label{
        font-size: 14px;
        color: #606266;
        line-height: 40px;
    }
    .info_value{
        color: #606266;
        line-height: 40px;
    }
</style>