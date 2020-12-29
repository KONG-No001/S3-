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
          <router-link to="/mygrzx"> 我的</router-link>
        </el-menu-item>
        <el-menu-item style="margin-left: 900px;" index="5" route="true" v-if="uid==null">
          <router-link to="/login"> 登录</router-link>
        </el-menu-item>
        <el-menu-item style="margin-left: 900px;" index="6" route="true" v-else>
          <strong  style="color: orange;">{{name}}</strong>登录了
        </el-menu-item>
      </el-menu>
    </el-header>
    <el-container class="page">
<!--    左部菜单栏    -->
      <el-aside style="margin-left: 20px;margin-top: -20px;width: 240px;" class="el-aside">
          <el-row class="tac">
              <el-col :span="20">
                  <h5 style="margin-left: 22px">商品分类</h5>
                  <el-menu
                      @select="xz"
                      active-text-color="#ffd04b"
                          :default-active="flid"
                          class="el-menu-vertical-demo"
                          >
                    <el-menu-item index="0" style="margin-left: 26px;margin-top: -14px;">
                      <template slot="title">全部</template>
                    </el-menu-item>
                    <div style="margin-left: 26px;" v-for="(sp,i) in spfl" :key="i" >
                      <el-menu-item :index="(i+1).toString()">
                        <template slot="title">{{sp.goodsTypeName}}</template>
                      </el-menu-item>
                    </div>
                  </el-menu>
              </el-col>
          </el-row>
      </el-aside>
        <!--    商品展示    -->
      <el-main class="el-main">
        <div style="margin-left: 300px;margin-top: -20px">

        <el-input style="width: 500px"
                       placeholder="请输入商品名称"
                       prefix-icon="el-icon-search"
                       v-model="input" clearable>
        </el-input>&nbsp;
          <el-button type="primary" icon="el-icon-search"
                     @click="chaXuan">搜索</el-button>
        </div>

          <el-row style="margin-left: 20px;margin-top: 20px" :gutter="20">
              <el-col style="margin-left: 30px" :span="5" v-for="(sp,i) in sps" :key="i">
                  <el-card  :body-style="{ padding: '0px' }" shadow="hover" class="sp">
                      <img style="width: 230px;height: 200px" :src="sp.goodsVo.goodsImg" class="image">
                      <div style="padding: 14px;">
                          <span>[{{sp.goodsVo.goodsBrand}}]{{sp.name}}</span>
                          <div class="bottom clearfix">
                            <span>价格：<span style="color: red">${{sp.goodsOutPrice}}</span></span>
                            <!-- 带参数跳转 -->
                            <el-button type="text" class="button" @click="gm(sp.id)">查看详情</el-button>
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
            :page-sizes="[10, 15, 20,30]"
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
        flid:'0',
        total: 0,
        pages: 0,
        row: 12,
        page: 1,
        uid:'',
        name:''
      }
    },
    created() {
      this.getdata();
      this.getsp();
      this.uid=sessionStorage.getItem('uid');
      this.name=sessionStorage.getItem('name');
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
        params.append('name',_this.input);
        params.append('page',_this.page);
        params.append('row',_this.row);
        params.append('goodsTypeId',_this.flid=='0'?"":_this.flid);
        this.
        $axios.post('/application/warehouse/fenYe.action',params).
        then(function(result) {
          console.log(result)
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
        this.getsp();
      },

      //页数变化
      handleCurrentChange(val) {
        this.page = val;
        this.getsp();
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