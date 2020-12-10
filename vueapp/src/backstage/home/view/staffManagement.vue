<template>
    <div>
        <el-table :data="userList" border v-loading="loading">
            <el-table-column label="账号名称" property="name" width="150">
            </el-table-column>
            <el-table-column label="所属部门" property="department" width="200">
            </el-table-column>
            <el-table-column label="头像" property="portrait" width="100">
                <el-image src="#" style="width: 64px;height: 64px;"></el-image>
            </el-table-column>
            <el-table-column label="角色" property="null" width="200">
                <template>
                    <el-tag>角色1</el-tag>
                    <el-tag>角色2</el-tag>
                    <el-tag>+1</el-tag>
                </template>
            </el-table-column>
            <el-table-column
                align="right">
                <template slot="header">
                    <el-row>
                        <el-col :span="5">
                            <el-button>添加</el-button>
                        </el-col>
                        <el-col :span="18" :offset="1">
                            <el-input v-model="search" placeholder="输入账号关键字搜索"></el-input>
                        </el-col>
                    </el-row>
                </template>
                <template slot-scope="scope">
                    <el-button @click="handleEdit(scope.$index,scope.row)">
                        编辑
                    </el-button>
                    <el-button
                        @click="handleDelete(scope.$index,scope.row)"
                        type="danger">
                        删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-dialog :visible.sync="dialogBox.visible" width="30%">
            <template slot="title">
                EDIT: zhang3
            </template>
            <template slot="default">
                <el-form :model="dialogBox.from">
                    <el-form-item label="账户名称">
                        <el-input v-model="dialogBox.from.name"></el-input>
                    </el-form-item>
                    <el-form-item label="账户密码">
                        <el-checkbox label="重置密码" v-model="dialogBox.from.resetpwd"></el-checkbox>
                    </el-form-item>
                    <el-form-item label="用户头像">
                        <el-upload ></el-upload>
                    </el-form-item>
                    <el-form-item label="所属部门">
                        <el-input v-model="dialogBox.from.department"></el-input>
                    </el-form-item>
                </el-form>
            </template>
            <template slot="footer">
                <el-button type="success">确认</el-button>
                <el-button type="info">取消</el-button>
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
                search: "",
                loading: true,
                dialogBox: {
                    visible: false,
                    from: {},
                }
            }
        },
        methods:{
            handleEdit:function (index, row) {
                this.dialogBox.visible = true;
                console.log(index, row);
            },
            handleDelete:function (index, row) {
                console.log(index, row);
            }
        },
        created: function () {
            let _this = this;
            Axios.get("/application/user/listUser.action").then((request)=>{
                _this.userList = request.data;
                this.loading = false;
            })
        }
    }
</script>

<style scoped>

</style>