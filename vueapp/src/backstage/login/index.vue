<template>
    <div>
        <el-card class="login">
            <div slot="header">
                <span>登录</span>
            </div>
            <div slot="default">
                <el-form :model="form" :rules="rules" ref="loginForm" label-position="right" label-width="70px" size="mini" >
                    <el-form-item label="账号" prop="account">
                        <el-input v-model="form.account"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" prop="password">
                        <el-input show-password v-model="form.password" ></el-input>
                    </el-form-item>
                    <el-form-item label="记住我">
                        <el-checkbox
                                v-model="form.rememberMe"
                        ></el-checkbox>
                    </el-form-item>
                    <el-button class="block" @click="subLogin">登录</el-button>
                </el-form>
            </div>

        </el-card>
    </div>
</template>

<script>
    import Axios from "axios";
    export default {
        name: "login",
        data: function () {
            return {
                form:{
                    account: "",
                    password: "",
                    rememberMe: false
                },
                rules:{
                    account: [
                        { required: true, message: '账户不能为空！！！', trigger: 'blur' },
                    ],
                    password: [
                        {required:true,message:"密码不能为空！！！",trigger:'blur'}
                    ]
                }
            }
        },
        methods: {
            subLogin(){
                let _this = this;
                this.$refs["loginForm"].validate(valid=>{
                    if (valid){
                        const loading = _this.$loading({
                            lock: true,
                            text: 'Loading',
                            spinner: 'el-icon-loading',
                            background: 'rgba(0, 0, 0, 0.7)'
                        });
                        Axios.get("/application/login.action",{
                            params: {
                                name: _this.form.account,
                                password: _this.form.password
                            }
                        }).then((response)=>{
                            if(response.data === true){
                                location.assign("/backstage/home")
                            }else {
                                _this.$message({
                                    message:"登录失败！！！检查账户和密码是否匹配！！！",
                                    type: "error"
                                });
                            }
                            loading.close();
                        }).catch(()=>{
                            _this.$message({
                                message:"登录异常！！！请联系管理员！！！",
                                type: "error"
                            });
                            loading.close();
                        })
                    }else {
                        alert("FALSE")
                        return false;
                    }
                })
            },
        }
    }
</script>

<style scoped>
    .login{
        width: 400px;
        margin: 100px auto 0 auto;
    }
    .block{
        width: 100%;
    }
</style>