<template>
    <div>
<!--        导航栏-->
        <el-row :gutter="20">
            <el-col :span="2">
                <img src="@/assets/蟹老板.png" class="image" style="height: 80px;width: 120px">
            </el-col>
            <div style="line-height:80px">
                <el-col :span="10" :offset="10">
                    <el-input v-model="goodsName"  placeholder="请输入要查找的商品" ></el-input>
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
        <el-row class="tac" style="width: 500px">
            <el-col :span="12">
                <div id="fenlei">
                    <ul  v-for="(g,i) in goodsType" :key="i"
                        :index="(i+1).toString()">
                        <li style="list-style-type:none"><a href="#">{{g.goodsTypeName}}</a></li>
                    </ul>
                </div>
            </el-col>
        </el-row>

<!--        热门商品区-->
        <div>
        <div>
            <el-row>
                <el-col :span="6" :offset="10">
                    <span id="hotname">热门商品</span>
                </el-col>
            </el-row>
        </div>
        <div>
            <el-row >
                <el-col>
                    <template>
                        <div id="hotshop1" class="remen">
                        <a href="#">
                        <img  class="hotshop" :src="tableData[1].goodsVo.goodsImg" />
                            <span style="font-size: 15px;margin-left: 30px">{{tableData[1].goodsVo.goodsName}}</span>
                            <span style="font-size: 30px;margin-left: 160px;margin-top:30px">{{tableData[1].goodsOutPrice}}</span>
                        </a>
                        </div>
                    </template>
                </el-col>
                <el-col>
                    <template>
                        <div id="hotshop2" class="remen">
                            <a href="#">
                                <img  class="hotshop" :src="tableData[2].goodsVo.goodsImg" />
                                <span style="font-size: 15px;margin-left: 30px">{{tableData[2].goodsVo.goodsName}}</span>
                                <span style="font-size: 30px;margin-left: 160px;">{{tableData[2].goodsOutPrice}}</span>
                            </a>
                        </div>
                    </template>
                </el-col>
                <el-col>
                    <template>
                        <div id="hotshop3" class="remen">
                            <a href="#">
                                <img  class="hotshop" :src="tableData[3].goodsVo.goodsImg" />
                                <span style="font-size: 15px;margin-left: 30px">{{tableData[3].goodsVo.goodsName}}</span>
                                <span style="font-size: 30px;margin-left: 160px;">{{tableData[3].goodsOutPrice}}</span>
                            </a>
                        </div>
                    </template>
                </el-col>
                <el-col>
                    <template>
                        <div id="hotshop4" class="remen">
                            <a href="#">
                                <img  class="hotshop" :src="tableData[4].goodsVo.goodsImg" />
                                <span style="font-size: 15px;margin-left: 30px;">{{tableData[4].goodsVo.goodsName}}</span>
                                <span style="font-size: 30px;margin-left: 160px;margin-top:30px">{{tableData[4].goodsOutPrice}}</span>
                            </a>
                        </div>
                    </template>
                </el-col>
                <el-col>
                    <template>
                        <div id="hotshop5" class="remen">
                            <a href="#">
                                <img  class="hotshop" :src="tableData[5].goodsVo.goodsImg" />
                                <span style="font-size: 15px;margin-left: 30px">{{tableData[5].goodsVo.goodsName}}</span>
                                <span style="font-size: 30px;margin-left: 160px;margin-top:30px">{{tableData[5].goodsOutPrice}}</span>
                            </a>
                        </div>
                    </template>
                </el-col>
                <el-col>
                    <template>
                        <div id="hotshop6" class="remen">
                            <a href="#">
                                <img  class="hotshop" :src="tableData[6].goodsVo.goodsImg" />
                                <span style="font-size: 15px;margin-left: 30px;">{{tableData[6].goodsVo.goodsName}}</span>
                                <span style="font-size: 30px;margin-left: 160px;margin-top:30px">{{tableData[6].goodsOutPrice}}</span>
                            </a>
                        </div>
                    </template>
                </el-col>
                <el-col>
                    <template>
                        <div id="hotshop7" class="remen">
                            <a href="#">
                                <img  class="hotshop" :src="tableData[7].goodsVo.goodsImg" />
                                <span style="font-size: 15px;margin-left: 30px;">{{tableData[7].goodsVo.goodsName}}</span>
                                <span style="font-size: 30px;margin-left: 160px;margin-top:30px">{{tableData[7].goodsOutPrice}}</span>
                            </a>
                        </div>
                    </template>
                </el-col>
                <el-col>
                    <template>
                        <div id="hotshop8" class="remen">
                            <a href="#">
                                <img  class="hotshop" :src="tableData[8].goodsVo.goodsImg" />
                                <span style="font-size: 15px;margin-left: 30px">{{tableData[8].goodsVo.goodsName}}</span>
                                <span style="font-size: 30px;margin-left: 160px;margin-top:30px">{{tableData[8].goodsOutPrice}}</span>
                            </a>
                   </div>
                    </template>
                </el-col>
            </el-row>
        </div>
    </div>

    </div>
</template>

<script>
    import Axios from "axios";

    export default {
        data() {
            return {
                tableData:[],
                goodsType:[],
                flid:'1',
                imgs:[
                    {url:require('@/assets/01.jpg')},
                    {url:require('@/assets/02.jpg')},
                    {url:require('@/assets/03.jpg')},
                    {url:require('@/assets/04.jpg')},
                    {url:require('@/assets/05.jpg')}
                ],
                goodsName:''
            }
        },

        methods: {
            /*获取数据*/
            getDate:function() {
                var _this = this;
                //商品查询
                this.$axios.post("/application/warehouse/queryAll.action ")
                    .then(function (result) {  //成功  执行then里面的方法
                    _this.tableData = result.data;
                }).catch(function (e) { //失败 执行catch方法
                    alert(e);
                });
            },
            //获取商品类型下拉框数据
            getGoodsType: function () {
                let _this = this;
                Axios.get('/application/goodsType/queryAll.action')
                    .then(function (result) {
                        _this.goodsType = result.data;
                    }).catch(function (error) {
                    alert(error)
                });
            },

            //查询
            getGoodsFenYe(){
                Axios({
                    url: '/application/goods/fenYe.action',
                    method: "get",
                    params: {
                        goodsName: this.goodsName,
                        page: this.page, row: this.row
                    },
                }).then((result) => {
                    this.total = result.data.total;
                    this.pages = result.data.pages;
                    this.tableData = result.data.records;
                }).catch((error) => {
                    alert(error);
                })
            }

        },

        created() {
            this.getDate();
            this.getGoodsType();
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
        height: 180px;
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
        top: 874px;
    }
    #hotshop6{
        position: absolute;
        top: 874px;
        left: 410px;
    }
    #hotshop7{
        position: absolute;
        top: 874px;
        left: 680px;
    }
    #hotshop8{
        position: absolute;
        top: 874px;
        left: 950px;
    }
    li:hover{
       color: lightblue;
    }
    li{
        margin-top: 25px;
    }
    .hotshop:hover{
        filter:alpha(Opacity=80);
        -moz-opacity:0.8;opacity: 0.8;
    }
    .remen{
        width: 250px;
        height: 245px;
        border: 1px solid #A9A9A9;
    }
    a{
        text-decoration: none;
    }
</style>