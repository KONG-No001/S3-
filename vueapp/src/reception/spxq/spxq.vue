<template>
    <div>
      <!--    顶部菜单栏    -->
      <el-header class="el-header">
        <el-menu
                 background-color="#545c64"
                 text-color="#fff"
                 active-text-color="#ffd04b" class="el-menu-demo" mode="horizontal">
          <el-menu-item index="1">
            <template slot="title">
              <span>首页</span>
            </template>
          </el-menu-item>
          <el-menu-item index="2">
            <router-link to="/spfl">商品分类</router-link>
          </el-menu-item>
          <el-submenu index="3">
            <template slot="title">我的</template>
            <el-menu-item index="3-1">个人信息</el-menu-item>
            <el-menu-item index="3-2">注册商户</el-menu-item>
            <el-menu-item index="3-3">我的订单</el-menu-item>
            <el-menu-item index="3-4">注销账号</el-menu-item>
          </el-submenu>
          <el-menu-item index="4" route="true">
            <router-link to="/gwc"> 购物车</router-link>
          </el-menu-item>
        </el-menu>
      </el-header>

<!--     展示商品数据   -->
        <el-main>
            <div>
<!--     商品图片展示           -->
              <el-row>
                <el-col :sp="12" class="block" style="width: 400px; background-color: #99a9bf;" >
                    <el-carousel height="300px" >
                        <el-carousel-item v-for="(item,key) in imgs" :key="key">
                           <img style="width: 100%;height: 100%" :src="item.url">
                        </el-carousel-item>
                    </el-carousel>
                </el-col>

                <el-row :sp="12" style="left: 20px">
                  <span style="">[{{sp.goodsBrand}}]{{sp.goodsName}}</span><br><br>
                  <span>商品规格:{{sp.goodsGuige}}</span><br><br>
                  <span>商品价格:{{sp.goodsInPrice}}</span><br><br>
                  <span>商品公司:{{sp.goodsFactory}}</span><br><br>
                  <span>产地:{{sp.goodsAddress}}</span><br><br><br><br>
                  <el-button type="primary">加入购物车</el-button>
                  <el-button style="left: 150px;" type="primary">立即购买</el-button>
                </el-row>
              </el-row>

            </div>
        </el-main>
    </div>
</template>

<script>
    export default {
        name: "spxq",
        data() {
            return {
                imgs:[
                    {url:require('@/assets/logo.png')},
                    {url:require('@/assets/logo.png')},
                    {url:require('@/assets/logo.png')},
                    {url:require('@/assets/logo.png')}
                ],
              sp:[]
            }
        },
      created() {
        this.getdata();
      },
      methods :{
        getdata(){
          var _this = this;
          var params = new URLSearchParams();
          params.append("goodsId",this.$route.params.id);

          this.
          $axios.post('/application/goods/queryById.action',params).
          then(function(result) {
            console.log(result.data)
            _this.sp = result.data;
          }).
          catch(function(error) {
            alert(error)
          });
        },
      }
    }
</script>

<style scoped>
</style>