<template>
    <div>
        <!--    顶部菜单栏    -->
        <el-header >
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

<!--     展示商品数据   -->
        <el-main>
            <div class="block" style="width: 400px; background-color: #99a9bf;" >
                <span class="demonstration">默认 Hover 指示器触发</span>
                <el-carousel height="150px" >
                    <el-carousel-item v-for="item in imgs" :key="item">
                       <img style="width: 100%;height: 100%" :src="item.url">
                    </el-carousel-item>
                </el-carousel>
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
                    {url:require('../public/logo.png')},
                    {url:require('../public/logo.png')},
                    {url:require('../public/logo.png')},
                    {url:require('../public/logo.png')}
                ]
            }
        }
    }
</script>

<style scoped>
</style>