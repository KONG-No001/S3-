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
        <el-menu-item index="4" route="true">
          <router-link to="/gwc"> 购物车</router-link>
        </el-menu-item>
        <el-menu-item index="3">

        </el-menu-item>
        <el-menu-item style="margin-left: 900px;" index="5" route="true" v-if="uid==null">
          <router-link to="/login"> 登录</router-link>
        </el-menu-item>
        <el-menu-item style="margin-left: 900px;" index="5" route="true" >
          <strong style="color: orange;">{{name}}</strong>登录了
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
        <el-button @click="pay" type="success" style="width: 200px;">确认支付</el-button>
      </el-main>

<div id="mydiv">

</div>
  </div>
</template>

<script>
export default {
  name: "dingdan",
  data() {
    return {
      sps:[

      ],
      uid:'',
      name:''
    }

  },
  created() {
    this.getsp();
    this.uid=sessionStorage.getItem('uid');
    this.name=sessionStorage.getItem('name');
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
    },
    pay(){
      var params = new URLSearchParams();
      params.append("tradeno",this.sps.id);  //订单号
      params.append("price",this.sps.sum);    //价格
      params.append("tradename",'付款订单');  //订单名字

      this.$axios.post("/application/pay.action",params).then(function (result) {

        var bodystr = result.data;  //后端返回的支付页面代码
        console.log(bodystr)
        bodystr=bodystr.substr(0,bodystr.indexOf("<script>"));
        console.log(bodystr)
        document.getElementById("mydiv").innerHTML=bodystr;
        document.forms[0].submit();   //返回代码中需要表单提交得到真实的支付页面

      }).catch();
    }
  }
}
</script>

<style scoped>

</style>