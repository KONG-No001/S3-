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



      <el-main>
        <span>自提地址：{{sps.shanghu.address}}</span>
        <el-divider></el-divider>
        <h3>收货人：</h3>
        <el-input v-model="sps.user.name" style="width: 150px;" readonly="readonly"></el-input>
        <el-input v-model="sps.user.phone" style="width: 150px;" readonly="readonly"></el-input>
        <el-divider></el-divider>
        <el-row :gutter="40">
          <el-col style="margin-top: 20px" :span="24" v-for="(sp,i) in sps.dingDanWarehouses" :key="i">
          <img :src="sp.warehouseVo.goodsVo.goodsImg" style="width: 100px;height: 100px">
          <span >[{{sp.warehouseVo.goodsVo.goodsBrand}}]{{sp.warehouseVo.name}}</span>
          <span style="margin-left: 20px">x {{sp.count}}</span>
          <span style="margin-left: 200px">￥{{sp.warehouseVo.goodsOutPrice}}</span>

          </el-col>
        </el-row>




        <span>总计:<span style="font-size:30px;color: red;">￥{{sps.sum}}</span></span>
        <el-divider></el-divider>
        <el-button type="success" style="width: 200px;">确认支付</el-button>
      </el-main>


  </div>
</template>

<script>
export default {
  name: "dingdan",
  data() {
    return {
      sps:[

      ]
    }

  },
  created() {
    this.getsp();
  },
  methods: {
    getsp(){
      var _this = this;
      var params = new URLSearchParams();
      params.append('id',_this.$route.params.id);
      this.
      $axios.post('/application/dingdan/queryById.action',params).
      then(function(result) {
        _this.sps = result.data;
        console.log(_this.sps)
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