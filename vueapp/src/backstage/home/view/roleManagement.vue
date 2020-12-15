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
                        <el-form :model="roleAddForm" label-position="left" label-width="80px" >
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
                            <el-form-item label="权限" prop="perm" ></el-form-item>
                        </el-form>
                    </template>
                    <template v-else-if="page==='update'">
                        <el-page-header @back="page='controller'" slot="header" content="添加页面"></el-page-header>
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
                                <el-button style="width: 100%;" type="warning" @click="page='update'"  :disabled="roleInfo.name==null">更改</el-button>
                            </el-col>
                        </el-row>
                        <el-row :gutter="20">
                            <el-col :span="6">名称：</el-col>
                            <el-col :span="18">{{roleInfo.name}}</el-col>
                        </el-row>
                        <el-row :gutter="20">
                            <el-col :span="6">类别：</el-col>
                            <el-col :span="18"><el-tag v-if="roleInfo.type" :type="roleInfo.type">{{roleInfo.type}}</el-tag></el-col>
                        </el-row>
                        <el-row :gutter="20">
                            <el-col :span="6">权限：</el-col>
                            <el-col :span="18">
                                <el-tag v-for="perm in roleInfo.perms" :type="perm.type" :key="perm.id" >{{perm.name}}</el-tag>
                            </el-col>
                        </el-row>
                        <el-row :gutter="20">
                            <el-col :span="6">备注：</el-col>
                            <el-col :span="18">
                                {{roleInfo.remark}}
                            </el-col>
                        </el-row>
                    </template>

                </el-card>
            </el-col>
        </el-row>
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
                roleAddForm: {},
                roleUpdateForm: {},
                page:"controller"
            }
        },
        methods: {
            listRole(){
                let _this = this;
                _this.tableLoading=true
                let Axios = _this.$axios;
                Axios.get("/application/role/list.action").then(response=>{
                    _this.roleList = response.data;
                    _this.tableLoading = false
                })
            },
            onSelectRole(currentRow){
                this.roleInfo = currentRow
            }
        },
        created(){
            this.listRole();
        }
    }
</script>

<style scoped>
    .el-row {
        margin-bottom: 20px
    }
</style>