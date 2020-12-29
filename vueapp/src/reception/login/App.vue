<template>
    <div id="app">
      <el-card class="login">
        <el-form :model="form" :rules="loginRules" ref="loginForm" label-position="right" label-width="70px" >
          <div slot="header">
            <span>登录</span>
          </div>
          <el-form-item prop="username" style="margin-left: -70px" >
            <el-input placeholder="用户名" v-model="form.username" clearable style="width: 360px">
            </el-input>
          </el-form-item>
          <el-form-item prop="password" style="margin-left: -70px">
            <el-input  placeholder="密码" v-model="form.password" type="password" clearable style="width: 360px;">
            </el-input>
          </el-form-item>
            <el-button class="block" @click="subLogin('loginForm')">登录</el-button><br/><br/>
            <el-button class="block" @click="addUser">注册</el-button>
        </el-form>

        <el-dialog title="注册" :visible.sync="userAddDialogVisible" width="50%" @close="closeRegister">
          <el-form :model="form" status-icon  :rules="addRules"  ref="registerForm" label-width="100px" class="demo-ruleForm">
            <el-row type="flex" class="row-bg">
              <el-col :span="10">
                <el-form-item label="用户名" prop="username">
                  <el-input type="text" v-model="form.username" autocomplete="off"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="10">
                <el-form-item label="密码" prop="password">
                  <el-input type="password" v-model="form.password" autocomplete="off"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row type="flex" class="row-bg">
              <el-col :span="10">
                <el-form-item label="性别" prop="sex">
                  <el-radio-group v-model="form.sex">
                    <el-radio :label="sex[0]" >男</el-radio>
                    <el-radio :label="sex[1]" >女</el-radio>
                  </el-radio-group>
                </el-form-item>
<!--                <el-form-item label="性别" prop="sex">-->
<!--                  <el-input type="text" v-model="form.sex" autocomplete="off"></el-input>-->
<!--                </el-form-item>-->
              </el-col>
              <el-col :span="10">
                <el-form-item label="电话" prop="phone">
                  <el-input type="number" v-model="form.phone" autocomplete="off"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="10" >
                <el-form-item label="请收货地址" prop="phone">
                  <el-select v-model="form.shop" placeholder="请选择">
                    <el-option
                        v-for="item in shopDate"
                        :key="item.id"
                        :label="item.address"
                        :value="item.id">
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>

            <el-form-item>
              <el-button type="primary" @click="submitForm('registerForm')">提交</el-button>
              <el-button @click="resetForm('registerForm')">重置</el-button>
            </el-form-item>

          </el-form>
        </el-dialog>
    </el-card>
  </div>
</template>

<script>
  export default {
    name: "UserList",
    data(){
      var cheCkphone = (rule,value,callback) =>{
        if(!value){
          return callback(new Error('电话不能为空'));
        }

        if(value.length==11){
          return callback();
        }else{
          return callback(new Error('请输入正确的手机号'));
        }
      };
      return {
        userAddDialogVisible:false,
        form:{
          username:'',
          password:'',
          sex:'',
          phone:'',
          shop:''
        },
        sex:['男','女'],
        addRules:{
          username:[{required:true,message:'用户名不能为空',trigger:'blur'}],
          password:[{required:true,message:'密码不能为空',trigger:'blur'}],
          sex:[{required:true,message:'请选择性别',trigger:'change'}],
          phone:[{validator: cheCkphone, trigger: 'blur'}]
        },
        loginRules:{
          username:[{required:true,message:'用户名不能为空',trigger:'blur'}],
          password:[{required:true,message:'密码不能为空',trigger:'blur'}],
        },
        shopDate:[]
      }

    },
    methods:{
      getShop(){
        var _this = this;
        this.
        $axios.post('/application/queryShanghuAll.do').
        then(function(result) {
          _this.shopDate = result.data;
        }).
        catch(function(error) {
          alert(error)
        });
      },
      addUser(){
        this.userAddDialogVisible = true;
        for(let key in this.form){
          this.form[key] = '';
        }
      },
      resetForm(form){
        this.$refs[form].resetFields();
      },
      subLogin(formName){
        console.log(formName)
        this.$refs[formName].validate((valid) => {
          if(valid){
            var params = new URLSearchParams();
            params.append("name",this.form.username);
            params.append("password",this.form.password);

            var _this = this;
            this.$axios.post('/application/queryUser.action',params).then(function (result) {
              _this.$message({
                message: result.data.id!=''?'登陆成功':'登录失败',
                type: "info"
              });
              if(result.data.id!=''){
                let uid=result.data.id;
                _this.$router.push({path:"/shouye"});
                sessionStorage.setItem("uid",uid);
                sessionStorage.setItem("name",result.data.name);
                sessionStorage.setItem("sh",result.data.shop);
              }

            })
          }
        })
      },
      submitForm(formName){
        this.$refs[formName].validate((valid) => {
          if(valid){
            var params = new URLSearchParams();
            params.append("name",this.form.username);
            params.append("password",this.form.password);
            params.append("sex",this.form.sex);
            params.append("phone",this.form.phone);
            params.append("shop",this.form.shop);

            var _this = this;
            this.$axios.post('/application/addUser.action',params).then(function (result) {
              _this.$message({
                message: result.data,
                type: "info"
              });
              _this.userAddDialogVisible = false;
              _this.getData();
            })
          }
        })
      },
      closeRegister(){
        this.resetForm('registerForm');
      }
    },
    created() {
      this.getShop();
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
