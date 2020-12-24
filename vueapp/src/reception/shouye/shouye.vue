<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="2">
                <img src="@/assets/蟹老板.png" class="image" style="height: 80px;width: 120px">
            </el-col>
            <div style="line-height:80px">
                <el-col :span="10" :offset="10">
                    <el-input v-model="input" placeholder="请输入要查找的商品" ></el-input>
                </el-col>
                <el-col :span="2">
                    <el-button type="primary">搜&emsp;索</el-button>
                </el-col>
            </div>
        </el-row>
<!--        轮播图-->
        <div class="block">
            <el-carousel trigger="click" height="500px" id="lunbo">
                <el-carousel-item v-for="(item,key) in imgs" :key="key">
                    <img style="width: 100%;height: 100%" :src="item.url">
                </el-carousel-item>
            </el-carousel>
        </div>
<!--        分类菜单-->
        <div  id="fenlei">
            <el-row class="fenlei" >
                <el-col>
                    <el-link :underline="false" style="margin-top: 30px">水果馆</el-link>
                </el-col>
            </el-row>
            <el-row class="fenlei">
                <el-link :underline="false">蔬菜馆</el-link>
            </el-row>
            <el-row class="fenlei">
                <el-link :underline="false">肉肉馆</el-link>
            </el-row>
            <el-row class="fenlei">
                <el-link :underline="false">家禽馆</el-link>
            </el-row>
            <el-row class="fenlei">
                <el-link :underline="false">水产馆</el-link>
            </el-row>
            <el-row class="fenlei">
                <el-link :underline="false">蛋蛋馆</el-link>
            </el-row>
            <el-row class="fenlei">
                <el-link :underline="false">豆腐馆</el-link>
            </el-row>
            <el-row class="fenlei">
                <el-link :underline="false">纸品馆</el-link>
            </el-row>
            <el-row class="fenlei">
                <el-link :underline="false">美妆馆</el-link>
            </el-row>
            <el-row class="fenlei">
                <el-link :underline="false">牛奶馆</el-link>
            </el-row>
        </div>
        <div>
            <el-row>
                <el-col :span="6" :offset="10">
                    <span id="hotname">热门商品</span>
                </el-col>
            </el-row>
        </div>
        <div>
            <el-row>
                <el-col>
                    <img id="hotshop1" class="hotshop" src="@/assets/蟹老板.png">
                </el-col>
                <el-col>
                    <img id="hotshop2" class="hotshop" src="@/assets/蟹老板.png">
                </el-col>
                <el-col>
                    <img id="hotshop3" class="hotshop" src="@/assets/蟹老板.png">
                </el-col>
                <el-col>
                    <img id="hotshop4" class="hotshop" src="@/assets/蟹老板.png">
                </el-col>
            </el-row>
            <el-row>
                <el-col>
                    <img id="hotshop5" class="hotshop" src="@/assets/蟹老板.png">
                </el-col>
                <el-col>
                    <img id="hotshop6" class="hotshop" src="@/assets/蟹老板.png">
                </el-col>
                <el-col>
                    <img id="hotshop7" class="hotshop" src="@/assets/蟹老板.png">
                </el-col>
                <el-col>
                    <img id="hotshop8" class="hotshop" src="@/assets/蟹老板.png">
                </el-col>
            </el-row>
        </div>
    </div>
</template>

<script>
    export default {
        name: "shouye",
        data(){
            return {
                imgs:[
                    {url:require('@/assets/01.jpg')},
                    {url:require('@/assets/02.jpg')},
                    {url:require('@/assets/03.jpg')},
                    {url:require('@/assets/04.jpg')},
                    {url:require('@/assets/05.jpg')}
                ],
                input: ''
            }
        }
    }
</script>

<style scoped>
    .el-carousel__item h3 {
        color: #475669;
        font-size: 14px;
        opacity: 0.75;
        line-height: 150px;
        margin: 0;
    }

    .el-carousel__item:nth-child(2n) {
        background-color: #99a9bf;
    }

    .el-carousel__item:nth-child(2n+1) {
        background-color: #d3dce6;
    }
    .fenlei{
        margin-top: 17px;
        margin-left:30px;
    }
    #lunbo{
        width: 1113px;
        position: absolute;
        left: 228px;
        top: 89px;
    }
    #fenlei{
        position: absolute;
        width: 220px;
        height: 500px;
        background-color:gainsboro;
    }

    #hotname{
        position: absolute;
        left: 610px;
        top: 534px;
        font-size: 30px;
    }
    .hotshop{
        width: 250px;
        height: 150px;
    }
    #hotshop1{
        position: absolute;
        left: 140px;
        top: 609px;
    }
    #hotshop2{
        position: absolute;
        left: 410px;
        top: 609px;
    }
    #hotshop3{
        position: absolute;
        left: 680px;
        top: 609px;
    }
    #hotshop4{
        position: absolute;
        left: 950px;
        top: 609px;
    }
    #hotshop5{
        position: absolute;
        left: 140px;
        top: 779px;
    }
    #hotshop6{
        position: absolute;
        top: 779px;
        left: 410px;
    }
    #hotshop7{
        position: absolute;
        top: 779px;
        left: 680px;
    }
    #hotshop8{
        position: absolute;
        top: 779px;
        left: 950px;
    }

</style>