<template>
    <div style="width: 100%">
        <div style="width: 100%;height: 110px">
            <el-row>
                <div style="width: 100%;text-align: center">
                    <span>个人中心</span>
                </div>

            </el-row>
            <el-row style="margin-left: 1300px">
                <el-button type="primary" icon="el-icon-setting" @click="dialogMy = true" circle></el-button>
            </el-row>
            <el-dialog
                    title="设置"
                    :visible.sync="dialogMy"
                    width="30%">
                平平无奇的设置
                <el-button type="primary" round>登录</el-button>
                <el-button type="danger" round>注销</el-button>
            </el-dialog>
        </div>
        <div>
            <el-tabs type="border-card">
                <el-tab-pane label="待付款">
                    <el-table
                            :data="daifukuan"
                            height="250"
                            border
                            style="width: 100%">
                        <el-table-column
                                prop="user.name"
                                label="用户"
                                width="180">
                        </el-table-column>
                        <el-table-column
                                label="商品信息">
                            <template slot-scope="scope">
                                <el-col style="margin-top: 20px" :span="24" v-for="(sp,i) in scope.row.dingDanWarehouses" :key="i">
                                    <img :src="sp.warehouseVo.goodsVo.goodsImg" style="width: 100px;height: 100px">
                                    <span >[{{sp.warehouseVo.goodsVo.goodsBrand}}]{{sp.warehouseVo.name}}</span>
                                    <span style="margin-left: 20px">x {{sp.count}}</span>
                                    <span style="margin-left: 200px">￥{{sp.warehouseVo.goodsOutPrice}}</span>

                                </el-col>
                            </template>
                        </el-table-column>
                        <el-table-column
                                prop="shanghu.address"
                                label="商户">
                        </el-table-column>
                        <el-table-column
                                prop="time"
                                label="订单时间">
                        </el-table-column>
                        <el-table-column
                                prop="sum"
                                label="总价">
                        </el-table-column>
                        <el-table-column
                                prop="count"
                                label="商品数量">
                        </el-table-column>
                        <el-table-column prop="" label="操作" width="180">
                            <template slot-scope="scope">
                                <el-button type="primary" icon="el-icon-edit" @click="fukuan(scope.row)">付款</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-tab-pane>

                <el-tab-pane label="待发货">
                    <el-table
                            :data="daifahuo"
                            height="250"
                            border
                            style="width: 100%">
                        <el-table-column
                                prop="user.name"
                                label="用户"
                                width="180">
                        </el-table-column>
                        <el-table-column
                                label="商品信息">
                            <template slot-scope="scope">
                                <el-col style="margin-top: 20px" :span="24" v-for="(sp,i) in scope.row.dingDanWarehouses" :key="i">
                                    <img :src="sp.warehouseVo.goodsVo.goodsImg" style="width: 100px;height: 100px">
                                    <span >[{{sp.warehouseVo.goodsVo.goodsBrand}}]{{sp.warehouseVo.name}}</span>
                                    <span style="margin-left: 20px">x {{sp.count}}</span>
                                    <span style="margin-left: 200px">￥{{sp.warehouseVo.goodsOutPrice}}</span>

                                </el-col>
                            </template>
                        </el-table-column>
                        <el-table-column
                                prop="shanghu.address"
                                label="商户">
                        </el-table-column>
                        <el-table-column
                                prop="time"
                                label="订单时间">
                        </el-table-column>
                        <el-table-column
                                prop="sum"
                                label="总价">
                        </el-table-column>
                        <el-table-column
                                prop="count"
                                label="商品数量">
                        </el-table-column>
                        <el-table-column prop="" label="操作" width="180">
                            <template slot-scope="scope">
                                <el-button type="primary" icon="el-icon-edit" @click="tixin(scope.row)">付款</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-tab-pane>

                <el-tab-pane label="待提货">
                    <el-table
                            :data="daitihuo"
                            height="250"
                            border
                            style="width: 100%">
                        <el-table-column
                                prop="user.name"
                                label="用户"
                                width="180">
                        </el-table-column>
                        <el-table-column
                                label="商品信息">
                            <template slot-scope="scope">
                                <el-col style="margin-top: 20px" :span="24" v-for="(sp,i) in scope.row.dingDanWarehouses" :key="i">
                                    <img :src="sp.warehouseVo.goodsVo.goodsImg" style="width: 100px;height: 100px">
                                    <span >[{{sp.warehouseVo.goodsVo.goodsBrand}}]{{sp.warehouseVo.name}}</span>
                                    <span style="margin-left: 20px">x {{sp.count}}</span>
                                    <span style="margin-left: 200px">￥{{sp.warehouseVo.goodsOutPrice}}</span>

                                </el-col>
                            </template>
                        </el-table-column>
                        <el-table-column
                                prop="shanghu.address"
                                label="商户">
                        </el-table-column>
                        <el-table-column
                                prop="time"
                                label="订单时间">
                        </el-table-column>
                        <el-table-column
                                prop="sum"
                                label="总价">
                        </el-table-column>
                        <el-table-column
                                prop="count"
                                label="商品数量">
                        </el-table-column>
                        <el-table-column prop="" label="操作" width="180">
                            <template slot-scope="scope">
                                <el-button type="primary" icon="el-icon-edit" @click="tihuo(scope.row)">付款</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-tab-pane>

                <el-tab-pane label="已提货">
                    <el-table
                            :data="yitihuo"
                            height="250"
                            border
                            style="width: 100%">
                        <el-table-column
                                prop="user.name"
                                label="用户"
                                width="180">
                        </el-table-column>
                        <el-table-column
                                label="商品信息">
                            <template slot-scope="scope">
                                <el-col style="margin-top: 20px" :span="24" v-for="(sp,i) in scope.row.dingDanWarehouses" :key="i">
                                    <img :src="sp.warehouseVo.goodsVo.goodsImg" style="width: 100px;height: 100px">
                                    <span >[{{sp.warehouseVo.goodsVo.goodsBrand}}]{{sp.warehouseVo.name}}</span>
                                    <span style="margin-left: 20px">x {{sp.count}}</span>
                                    <span style="margin-left: 200px">￥{{sp.warehouseVo.goodsOutPrice}}</span>

                                </el-col>
                            </template>
                        </el-table-column>
                        <el-table-column
                                prop="shanghu.address"
                                label="商户">
                        </el-table-column>
                        <el-table-column
                                prop="time"
                                label="订单时间">
                        </el-table-column>
                        <el-table-column
                                prop="sum"
                                label="总价">
                        </el-table-column>
                        <el-table-column
                                prop="count"
                                label="商品数量">
                        </el-table-column>
                        <el-table-column prop="" label="操作" width="180">
                            <template slot-scope="scope">
                                <el-button type="primary" icon="el-icon-edit" @click="pingjia(scope.row)">付款</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-tab-pane>
            </el-tabs>

            <el-dialog
                    title="正在付款"
                    :visible.sync="dialogfukuan"
                    width="30%">
                确认付款金额！
            </el-dialog>
            <el-dialog
                    title="提醒发货"
                    :visible.sync="dialogtixin"
                    width="30%">
                已提醒商家发货！
            </el-dialog>
        </div>
    </div>
</template>

<script>
    import Axios from "axios";

    export default {
        name: 'mygrzx',
        data(){
            return{
                daifukuan:[],
                daifahuo:[],
                daitihuo:[],
                yitihuo:[],
                dialogMy:false,
                dialogfukuan:false,
                dialogtixin:false,
                dialogtihuo:false,
                dialogpingjia:false
            }
        },
        method:{
            //获取表格数据
            getDaifukuan: function () {
                Axios({
                    url: '/application/dingdan/daifukuan.action',
                    method: "get"

                }).then((result) => {
                    this.daifukuan=result.data.rows;


                }).catch((error) => {
                    alert(error);
                })
            },
            getDaifahuo: function () {
                Axios({
                    url: '/application/dingdan/daifahuo.action',
                    method: "get"

                }).then((result) => {
                    this.daifahuo=result.data.rows;


                }).catch((error) => {
                    alert(error);
                })
            },
            getDaitihuo: function () {
                Axios({
                    url: '/application/dingdan/daitihuo.action',
                    method: "get"

                }).then((result) => {
                    this.daitihuo=result.data.rows;


                }).catch((error) => {
                    alert(error);
                })
            },
            getYitihuo: function () {
                Axios({
                    url: '/application/dingdan/yitihuo.action',
                    method: "get"

                }).then((result) => {
                    this.yitihuo=result.data.rows;


                }).catch((error) => {
                    alert(error);
                })
            },
            fukuan(){
                this.dialogfukuan=false;
            },
            tixin(){
                this.dialogtixin=false;
            },
            tihuo(){
                this.dialogtihuo=false;
            },
            pingjia(){
                this.dialogpingjia=false;
            }
        },
        created:function(){
            this.getDaifukuan();

            this.getDaifahuo();

            this.getDaitihuo();

            this.getYitihuo();
        }
    }
</script>

<style scoped>
    span{

        font-size: 28px;
    }
    .el-header{
        background-color: #409EFF;

    }
    .el-main {
        color: #333;
        text-align: center;

    }
    .el-tabs{

    }
</style>