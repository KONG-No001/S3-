<template>
    <div style="padding: 0 10px;" >
        <el-row :gutter="30" >
            <el-col :span="15">
                <el-card style="width: 100%;height: 540px" body-style="height:445px;padding:0">
                    <template slot="header">
                        <span style="font-size: 18px;color: #303133;line-height: 24px">权限列表</span>
                        <span style="float: right">
                            <el-button icon="el-icon-refresh-right" :loading="tableLoading" @click="listPerm" size="small">刷新</el-button>
                            <el-button icon="el-icon-menu" size="small"></el-button>
                        </span>
                    </template>
                    <el-table
                            :data="permList"
                            v-loading="tableLoading"
                            height="445"
                            highlight-current-row
                            border>
                        <el-table-column label="编号" prop="id" width="50px" align="center"></el-table-column>
                        <el-table-column label="名称" prop="name" width="80px"></el-table-column>
                        <el-table-column label="资源" prop="resources" width="200px"></el-table-column>
                        <el-table-column align="right">
                            <template slot="header" slot-scope="scope">
                                <el-row :gutter="10">
                                    <el-col :span="5">
                                        <el-button size="small" type="primary" @click="showAddPermDialog" >添加</el-button>
                                    </el-col>
                                    <el-col :span="18">
                                        <el-input
                                                size="small"
                                                v-model="search.value"
                                                placeholder="输入关键字搜索">
                                            {{scope.id}}
                                        </el-input>
                                    </el-col>
                                </el-row>
                            </template>
                            <template slot-scope="scope">
                                <el-button type="warning" size="small" @click="showUpdatePermDialog(scope.row)">更改</el-button>
                                <el-button type="danger" size="small" @click="deletePermById(scope.row.id)" >删除</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                    <el-pagination
                            style="float: right;color: #303133"
                            layout="prev, pager, next"
                            :total="1000">
                    </el-pagination>
                </el-card>
            </el-col>
            <el-col :span="9" >
                <el-card style="width: 100%;height: 540px" body-style="height:445px" >
                    <div slot="header">
                        <span style="font-size: 18px;color: #303133;line-height: 24px">菜单授权</span>
                        <span style="float: right">
                            <el-button type="warning" size="small">授权</el-button>
                        </span>
                    </div>
                    <el-scrollbar slot="default" style="height: 100%;" wrap-style="overflow: hidden scroll;">
                        <div>

                        </div>
                    </el-scrollbar>
                </el-card>
            </el-col>
        </el-row>
        <el-dialog :visible.sync="permInfoDialog.visible" width="400px">
            <template slot="title">
                {{permInfoDialog.title}}
            </template>
            <template>
                <el-form label-position="left" label-width="80px" :model="permInfoForm" size="small" >
                    <el-form-item label="编号" v-if="permInfoDialog.type==='edit'" >
                        {{permInfoForm.id}}
                    </el-form-item>
                    <el-form-item label="名称">
                        <el-input size="small" v-model="permInfoForm.name"></el-input>
                    </el-form-item>
                    <el-form-item label="资源">
                        <el-input  size="small" v-model="permInfoForm.resources"></el-input>
                    </el-form-item>
                </el-form>
            </template>
            <template slot="footer" >
                <el-button size="small" @click="permInfoDialog.visible = false">取消</el-button>
                <el-button v-if="permInfoDialog.type==='edit'" size="small" type="primary" @click="doUpdatePerm" >更改</el-button>
                <el-button v-else size="small" type="primary" @click="doAddPerm">添加</el-button>
            </template>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "permManagement",
        data(){
            return {
                permList:[],
                tableLoading: false,
                search:{
                    value:""
                },
                permInfoForm:{

                },
                permInfoDialog:{
                    type:"add",
                    title:"",
                    visible: false

                }
            }
        },
        methods:{
            listPerm(){
                let _this = this;
                _this.tableLoading=true
                _this.$axios.get("/application/perm/list.action").then(response=>{
                    _this.permList = response.data;
                    _this.tableLoading=false
                })
            },

            deletePermById(id){
                let _this = this;
                _this.$confirm("此操作将永久删除该记录, 是否继续?","确认删除",{
                    confirmButtonText: "确认",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(()=>{
                    const loading = _this.$loading({
                        lock: true,
                        text: "Loading",
                        spinner: 'el-icon-loading',
                        background: 'rgba(0, 0, 0, 0.7)'
                    });

                    _this.$axios.post("/application/perm/delete.action",{id}).then(response=>{
                        if(response.data){
                            this.$message({
                                type: 'success',
                                message: '删除成功!'
                            });
                            _this.listPerm();

                        }else {
                            this.$message({
                                type: 'warning',
                                message: '删除失败!'
                            });
                        }
                        loading.close();
                    }).catch(() => {
                        this.$message({
                            type: 'danger',
                            message: '执行异常!'
                        });
                        loading.close();
                    });

                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },

            showUpdatePermDialog(row){
                let _this = this;
                _this.permInfoDialog.visible = true;
                _this.permInfoDialog.title = "更改";
                _this.permInfoDialog.type = "edit";
                _this.permInfoForm = JSON.parse(JSON.stringify(row));
            },
            showAddPermDialog(){
                let _this = this;
                _this.permInfoDialog.visible = true;
                _this.permInfoDialog.title = "添加";
                _this.permInfoDialog.type = "add";
                _this.permInfoForm = {};
            },

            doAddPerm(){
                let _this = this;
                const loading = _this.$loading({
                    lock: true,
                    text: "Loading",
                    spinner: 'el-icon-loading',
                    background: 'rgba(0, 0, 0, 0.7)'
                });
                _this.$axios.post("/application/perm/add.action",_this.permInfoForm).then(response=>{
                    if(response.data){
                        this.$message({
                            type: 'success',
                            message: '添加成功!'
                        });
                        _this.listPerm();
                        _this.permInfoDialog.visible = false;
                    }else {
                        this.$message({
                            type: 'warning',
                            message: '添加失败!'
                        });
                    }
                    loading.close();
                }).catch(() => {
                    this.$message({
                        type: 'danger',
                        message: '执行异常!'
                    });
                    loading.close();
                });
            },

            doUpdatePerm(){
                let _this = this;
                const loading = _this.$loading({
                    lock: true,
                    text: "Loading",
                    spinner: 'el-icon-loading',
                    background: 'rgba(0, 0, 0, 0.7)'
                });
                _this.$axios.post("/application/perm/update.action",_this.permInfoForm).then(response=>{
                    if(response.data){
                        this.$message({
                            type: 'success',
                            message: '更改成功!'
                        });
                        _this.listPerm();
                        _this.permInfoDialog.visible = false;
                    }else {
                        this.$message({
                            type: 'warning',
                            message: '更改失败!'
                        });
                    }
                    loading.close();
                }).catch(() => {
                    this.$message({
                        type: 'danger',
                        message: '更改异常!'
                    });
                    loading.close();
                });
            },

        },
        created(){
            this.listPerm();
        }
    }
</script>

<style scoped>

</style>