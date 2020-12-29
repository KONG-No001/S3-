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
            <router-link to="/mygrzx"> 我的</router-link>
          </el-menu-item>
          <el-menu-item style="margin-left: 900px;" index="5" route="true" v-if="uid==null">
            <router-link to="/login"> 登录</router-link>
          </el-menu-item>
          <el-menu-item style="margin-left: 900px;" index="6" route="true" v-else>
            <strong style="color: orange;">{{name}}</strong>登录了
          </el-menu-item>
        </el-menu>
      </el-header>

        <div style="margin-top:20px;width: 1460px;height: 110px">
            <el-row>
                <div style="width: 100%;text-align: center">
                    <span style="color: dodgerblue"><strong>个人中心</strong></span>
                </div>

            </el-row>

        </div>

<!--      数据表格-->
        <div style="width: 1486px;margin-left: 22px;margin-top: -70px">
            <el-tabs type="border-card">
                <el-tab-pane label="待付款">
                    <el-table
                            :data="daifukuan"
                            height="500"
                            border
                            style="width: 100%;">
                        <el-table-column
                                prop="id"
                                label="订单ID"
                                width="180">
                        </el-table-column>
                      <el-table-column
                          label="商品信息">
                        <template slot-scope="scope">
                          <el-col v-for="(sp,i) in scope.row.dingDanWarehouses" :key="i">
                            <span >[{{sp.warehouseVo.goodsVo.goodsBrand}}]{{sp.warehouseVo.name}}</span>
                            <span > x{{sp.count}}</span>
                            <span style="color: red;">￥{{sp.warehouseVo.goodsOutPrice}}</span>

                          </el-col>
                        </template>
                      </el-table-column>
                        <el-table-column
                                prop="time"
                                label="订单时间">
                        </el-table-column>
                        <el-table-column
                                prop="sum"
                                label="总计">
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
                            height="500"
                            border
                            style="width: 100%">
                      <el-table-column
                          prop="id"
                          label="订单ID"
                          width="180">
                      </el-table-column>
                      <el-table-column
                          label="商品信息">
                        <template slot-scope="scope">
                          <el-col v-for="(sp,i) in scope.row.dingDanWarehouses" :key="i">
                            <span >[{{sp.warehouseVo.goodsVo.goodsBrand}}]{{sp.warehouseVo.name}}</span>
                            <span > x {{sp.count}}</span>
                            <span style="color: red">￥{{sp.warehouseVo.goodsOutPrice}}</span>

                          </el-col>
                        </template>
                      </el-table-column>
                      <el-table-column
                          prop="time"
                          label="订单时间">
                      </el-table-column>
                      <el-table-column
                          prop="sum"
                          label="总计">
                      </el-table-column>
                      <el-table-column
                          prop="count"
                          label="商品数量">
                      </el-table-column>
                    </el-table>
                </el-tab-pane>

                <el-tab-pane label="待提货">
                    <el-table
                            :data="daitihuo"
                            height="500"
                            border
                            style="width: 100%">
                      <el-table-column
                          prop="id"
                          label="订单ID"
                          width="180">
                      </el-table-column>
                      <el-table-column
                          label="商品信息">
                        <template slot-scope="scope">
                          <el-col v-for="(sp,i) in scope.row.dingDanWarehouses" :key="i">
                            <span >[{{sp.warehouseVo.goodsVo.goodsBrand}}]{{sp.warehouseVo.name}}</span>
                            <span > x {{sp.count}}</span>
                            <span style="color: red">￥{{sp.warehouseVo.goodsOutPrice}}</span>
                          </el-col>
                        </template>
                      </el-table-column>
                      <el-table-column
                          prop="time"
                          label="订单时间">
                      </el-table-column>
                      <el-table-column
                          prop="sum"
                          label="总计">
                      </el-table-column>
                      <el-table-column
                          prop="count"
                          label="商品数量">
                      </el-table-column>
                        <el-table-column prop="" label="操作" width="180">
                            <template slot-scope="scope">
                                <el-button type="primary" icon="el-icon-edit" @click="tihuo(scope.row)">确认收货</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-tab-pane>

                <el-tab-pane label="已提货">
                    <el-table
                            :data="yitihuo"
                            height="500"
                            border
                            style="width: 100%">
                      <el-table-column
                          prop="id"
                          label="订单ID"
                          width="180">
                      </el-table-column>
                      <el-table-column
                          label="商品信息">
                        <template slot-scope="scope">
                          <el-col v-for="(sp,i) in scope.row.dingDanWarehouses" :key="i">
                            <span >[{{sp.warehouseVo.goodsVo.goodsBrand}}]{{sp.warehouseVo.name}}</span>
                            <span > x {{sp.count}}</span>
                            <span style="color: red">￥{{sp.warehouseVo.goodsOutPrice}}</span>
                          </el-col>
                        </template>
                      </el-table-column>
                      <el-table-column
                          prop="time"
                          label="订单时间">
                      </el-table-column>
                      <el-table-column
                          prop="sum"
                          label="总计">
                      </el-table-column>
                      <el-table-column
                          prop="count"
                          label="商品数量">
                      </el-table-column>

                    </el-table>
                </el-tab-pane>

            </el-tabs>
          <div id="mydiv"></div>
        </div>
    </div>
</template>

<script>
    import Axios from "axios";

    export default {
        data(){
            return{
                daifukuan:[],
                daifahuo:[],
                daitihuo:[],
                yitihuo:[],
                dialogMy:false,
                uid:'',
                name:''
            }
        },
        methods:{
            //获取表格数据
            getDaifukuan () {
                Axios({
                    url: '/application/dingdan/daifukuan.action',
                    method: "get"

                }).then((result) => {
                    this.daifukuan=result.data;


                }).catch((error) => {
                    alert(error);
                })
            },
            getDaifahuo: function () {
                Axios({
                    url: '/application/dingdan/daifahuo.action',
                    method: "get"

                }).then((result) => {
                    this.daifahuo=result.data;


                }).catch((error) => {
                    alert(error);
                })
            },
            getDaitihuo: function () {
                Axios({
                    url: '/application/dingdan/daitihuo.action',
                    method: "get"

                }).then((result) => {
                    this.daitihuo=result.data;


                }).catch((error) => {
                    alert(error);
                })
            },
            getYitihuo: function () {
                Axios({
                    url: '/application/dingdan/yitihuo.action',
                    method: "get"

                }).then((result) => {
                    this.yitihuo=result.data;


                }).catch((error) => {
                    alert(error);
                })
            },
            fukuan(row){
              var params = new URLSearchParams();
              params.append("tradeno",row.id);  //订单号
              params.append("price",row.sum);    //价格
              params.append("tradename",'付款订单');  //订单名字

              Axios.post("/application/pay.action",params).then(function (result) {

                var bodystr = result.data;  //后端返回的支付页面代码
                console.log(bodystr)
                bodystr=bodystr.substr(0,bodystr.indexOf("<script>"));
                console.log(bodystr)
                document.getElementById("mydiv").innerHTML=bodystr;
                document.forms[0].submit();   //返回代码中需要表单提交得到真实的支付页面

              }).catch();
            },
          tihuo(row){
            Axios({
              url: '/application/dingdan/sh.action',
              method: "get",
              params:{id:row.id}
            }).then((result) => {
              this.$notify({
                title: '提示',
                message: result.data == true ? '收货成功' : '收货失败',
                type: 'success'
              });
              this.getDaifukuan();

              this.getDaifahuo();

              this.getDaitihuo();

              this.getYitihuo();

            }).catch((error) => {
              alert(error);
            })
          }

        },
        created(){
          this.getDaifukuan();

          this.getDaifahuo();

          this.getDaitihuo();

          this.getYitihuo();
          this.uid=sessionStorage.getItem('uid');
          this.name=sessionStorage.getItem('name');
        }
    }
</script>

<style scoped>
</style>