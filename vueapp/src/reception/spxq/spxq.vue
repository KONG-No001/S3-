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
              <router-link to="/shouye"> 首页</router-link>
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
                <el-col :sp="12" class="block" style="width: 350px; background-color: #99a9bf;" >
                    <el-carousel height="350px" >
                        <el-carousel-item v-for="(item,key) in imgs" :key="key">
                           <img style="width: 100%;height: 100%" :src="item">
                        </el-carousel-item>
                    </el-carousel>
                </el-col>

                <el-row :sp="12" style="left: 20px">
                  <span>[{{sp.goodsVo.goodsBrand}}]{{sp.name}}</span><br><br>
                  <span>商品规格:&nbsp;{{sp.goodsVo.goodsGuige}}</span><br><br>
                  <span>商品价格:&nbsp;{{sp.goodsOutPrice}}</span><br><br>
                  <span>生产日期:&nbsp;{{sp.goodsVo.goodsTime}}</span><br><br>
                  <span>厂家:&nbsp;{{sp.goodsVo.goodsFactory}}</span><br><br>
                  <span>产地:&nbsp;{{sp.goodsVo.goodsAddress}}</span><br><br>
                  数量：<el-input-number size="mini" v-model="num" :min="1" :max="sp.count" label="数量"></el-input-number><br><br>
                  <el-button type="primary" @click="addCar(sp.id)">加入购物车</el-button>
                  <el-button style="left: 150px;" @click="gm" type="primary">立即购买</el-button>
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

                ],
              sp:[],
              num:1
            }
        },
      created() {
        this.getdata();
      },
      methods :{
        getdata(){
          var _this = this;
          var params = new URLSearchParams();
          params.append("id",this.$route.params.id);

          this.
          $axios.post('/application/warehouse/queryById.action',params).
          then(function(result) {
            console.log(result)
            _this.sp = result.data;
            _this.imgs.push(result.data.goodsVo.goodsImg);
            _this.imgs.push(result.data.goodsVo.goodsImg2);
            _this.imgs.push(result.data.goodsVo.goodsImg3);
            _this.imgs.push(result.data.goodsVo.goodsImg4);
          }).
          catch(function(error) {
            alert(error)
          });
        },
        addCar(id){
          var _this = this;
          var params = new URLSearchParams();
          params.append("uid",1);
          params.append("WarehouseVo.id",id);
          params.append("shu",_this.num);
          this.
          $axios.post('/application/goodscar/addGoodsCar.action',params).
          then(function(result) {
            console.log(result)
            _this.$message({
              message: result.data ,
              type: 'success'
            });
          }).
          catch(function(error) {
            alert(error)
          });
        },
        gm(){
          var _this = this;
          var params = new URLSearchParams();
          params.append("User.id",1);
          params.append("Shanghu.id",1);
          params.append("count",1);
          params.append("sum",_this.num*_this.sp.goodsOutPrice);
          params.append("shu",_this.num);
          params.append("wid",_this.sp.id);
          this.
          $axios.post('/application/dingdan/addDingDan.action',params).
          then(function(result) {
            _this.$router.push({name:"dingdan",params:{id:result.data}});
          }).
          catch(function(error) {
            alert(error)
          });
        }
      }
    }
</script>

<style scoped>
</style>