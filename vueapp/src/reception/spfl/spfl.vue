<template>
    <div>
  <el-container>
      <!--    顶部菜单栏    -->
    <el-header class="el-header">
      <el-menu default-active="2"
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
    <el-container class="page">
<!--    左部菜单栏    -->
      <el-aside width="200px" class="el-aside">
          <el-row class="tac">
              <el-col :span="12">
                  <h5 style="margin-left: 22px">商品分类</h5>
                  <el-menu
                      @select="xz"
                      active-text-color="#ffd04b"
                          :default-active="flid"
                          class="el-menu-vertical-demo"
                          v-for="(sp,i) in spfl" :key="i" >
                      <el-menu-item :index="(i+1).toString()">
                        <template slot="title">{{sp.goodsTypeName}}</template>
                      </el-menu-item>
                  </el-menu>
              </el-col>
          </el-row>
      </el-aside>
        <!--    商品展示    -->
      <el-main class="el-main">
        <div>

        查询商品：<el-input style="width: 500px"
                       placeholder="请输入商品名称"
                       prefix-icon="el-icon-search"
                       v-model="input" clearable>
        </el-input>&nbsp;
          <el-button type="primary" icon="el-icon-search"
                     @click="chaXuan">搜索</el-button>
        </div>

          <el-row :gutter="20">
              <el-col :span="5" v-for="(sp,i) in sps" :key="i">
                  <el-card :body-style="{ padding: '0px' }" shadow="hover" class="sp">
                      <img src="@/assets/logo.png" class="image">
                      <div style="padding: 14px;">
                          <span>[{{sp.goodsBrand}}]{{sp.goodsName}}</span>
                          <div class="bottom clearfix">
                              <span>价格：${{sp.goodsInPrice}}</span>
                            <!-- 带参数跳转 -->
                            <el-button type="text" class="button" @click="gm(sp.goodsId)">查看详情</el-button>
                          </div>
                      </div>
                  </el-card>
              </el-col>
          </el-row>

        <!-- 分页-->
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="page"
            :page-sizes="[5, 10, 15, 20]"
            :page-size="row"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total">
        </el-pagination>

<!--        返回顶部-->
          <el-backtop target=".page .el-main" :bottom="100">
              <div
                      style="{
        height: 100%;
        width: 100%;
        background-color: #f2f5f6;
        box-shadow: 0 0 6px rgba(0,0,0, .12);
        text-align: center;
        line-height: 40px;
        color: #1989fa;
      }"
              >
                  UP
              </div>
          </el-backtop>
      </el-main>
    </el-container>
  </el-container>

    </div>


</template>

<script>
// import Axios from "axios";
  export default {
    data() {
      return {
        input: '',
        spfl:[],
        sps:[],
        flid:'1',
        total: 0,
        pages: 0,
        row: 5,
        page: 1
      }
    },
    created() {
      this.getdata();
      this.getsp();
    },
    methods :{
      getdata(){
        var _this = this;
        this.
        $axios.post('/application/goodsType/queryAll.action').
        then(function(result) {
          _this.spfl = result.data;
        }).
        catch(function(error) {
          alert(error)
        });
      },
      getsp(){
        var _this = this;
        var params = new URLSearchParams();
        params.append('page',_this.page);
        params.append('row',_this.row);
        params.append('goodsTypeId',_this.flid);
        params.append('goodsName',_this.input);
        this.
        $axios.post('/application/goods/fenYe.action',params).
        then(function(result) {
          _this.total = result.data.total;
          _this.pages = result.data.pages;
          _this.sps = result.data.records;
        }).
        catch(function(error) {
          alert(error)
        });
      },
      xz(i){
        this.flid=i;
        this.getsp();
      },
      chaXuan(){
        this.getsp();
      },
      gm(id){
        this.$router.push(
            {
              name:"spxq",
              params:{id:id}
            }
        );
      },

      //每页条数变化
      handleSizeChange(val) {
        this.row = val;
        this.getData();
      },

      //页数变化
      handleCurrentChange(val) {
        this.page = val;
        this.getData();
      },
    }
  }

</script>
<style>
    .el-header {
        position: relative;
        width: 100%;
        height: 60px;
    }
    .el-aside {
        display: block;
        position: absolute;
        left: 0;
        top: 80px;
        bottom: 0;
    }
    .el-main {
        position: absolute;
        left: 200px;
        right: 0;
        top: 80px;
        bottom: 0;
        overflow-y: scroll;
    }
    .image {
        width: 100%;
        display: block;
    }
    .sp {
        margin-bottom: 20px;
    }
</style>