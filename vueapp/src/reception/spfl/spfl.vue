<template>
    <div>
  <el-container>
      <!--    顶部菜单栏    -->
    <el-header class="el-header">
      <el-menu default-active="1"
               background-color="#545c64"
               text-color="#fff"
               active-text-color="#ffd04b" class="el-menu-demo" mode="horizontal">
        <el-menu-item index="1">
          <template slot="title">
<!--            <el-image-->
<!--                    style="width: 100px; height: 100px"-->
<!--                    :src="pjyx.PNG"-->
<!--                    :fit="cover"></el-image>-->
            <span>首页</span>
          </template>
        </el-menu-item>
        <el-menu-item index="2">商品分类</el-menu-item>
        <el-submenu index="3">
          <template slot="title">我的</template>
          <el-menu-item index="3-1">个人信息</el-menu-item>
          <el-menu-item index="3-2">注册商户</el-menu-item>
          <el-menu-item index="3-3">我的订单</el-menu-item>
          <el-menu-item index="3-4">注销账号</el-menu-item>
        </el-submenu>
        <el-menu-item index="4">购物车</el-menu-item>
      </el-menu>
    </el-header>
    <el-container class="page">
<!--    左部菜单栏    -->
      <el-aside width="200px" class="el-aside">
          <el-row class="tac">
              <el-col :span="12">
                  <h5 style="margin-left: 22px">商品分类</h5>
                  <el-menu
                          default-active="2"
                          class="el-menu-vertical-demo"
                            >
                      <el-menu-item index="1">
                          <i class="el-icon-menu"></i>
                          <span slot="title">商品分类</span>
                      </el-menu-item>
                      <el-menu-item index="2">
                          <i class="el-icon-menu"></i>
                          <span slot="title">商品分类</span>
                      </el-menu-item>
                      <el-menu-item index="3">
                          <i class="el-icon-document"></i>
                          <span slot="title">商品分类</span>
                      </el-menu-item>
                      <el-menu-item index="4">
                          <i class="el-icon-setting"></i>
                          <span slot="title">商品分类</span>
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
          <el-button type="primary" icon="el-icon-search">搜索</el-button>
        </div>

          <el-row :gutter="20">
              <el-col :span="5" v-for="o in 50" :key="o">
                  <el-card :body-style="{ padding: '0px' }" shadow="hover" class="sp">
                      <img src="@/assets/logo.png" class="image">
                      <div style="padding: 14px;">
                          <span>商品名称</span>
                          <div class="bottom clearfix">
                              <span>价格：$12</span>
                              <el-button type="text" class="button">操作按钮</el-button>
                          </div>
                      </div>
                  </el-card>
              </el-col>
          </el-row>
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
  export default {
    data() {
      return {
        input: ''
      }
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
        top: 60px;
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