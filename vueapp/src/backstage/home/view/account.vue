<template>
  <div>
    <el-tabs type="border-card">
      <el-tab-pane label="统计">
        <div class="block">
          <!-- 查询时间-->
          <el-date-picker
                  v-model="accountTime"
                  type="datetimerange"
                  :picker-options="pickerOptions"
                  value-format="yyyy-MM-dd HH:mm:ss"
                  range-separator="至"
                  start-placeholder="开始时间"
                  end-placeholder="结束时间"
                  align="right">
          </el-date-picker>
          <el-button type="primary" @click="searchAccount()">查询</el-button>
        </div>

        <el-table
                  :data="accountData"
                  stripe
                  show-summary
                  :summary-method="getSummaries"
                  style="width: 100%"
          >
            <el-table-column
                    prop="id"
                    label="ID">
            </el-table-column>
            <el-table-column
                    prop="time"
                    label="时间">
            </el-table-column>
            <el-table-column
                    prop="total"
                    label="金额/元">
              <template slot-scope="{row}" >
                <span v-if="row.accountType===0">-{{row.total}}</span>
                <span v-if="row.accountType===1">+{{row.total}}</span>
              </template>
            </el-table-column>
            <el-table-column
                    prop="accountType"
                    label="类型"
            >
              <template slot-scope="{row}" >
                <el-tag v-if="row.accountType===0" type="">采购</el-tag>
                <el-tag v-else-if="row.accountType===1" type="success">销售</el-tag>
              </template>
            </el-table-column>
          </el-table>

        <el-pagination
                background
                layout="total, sizes, prev, pager, next, jumper"
                :page-sizes="[5, 10, 15, 20]"
                :page-size="size"
                :total="total"
                :current-page="currentpage"
                @prev-click="prvpage"
                @next-click="nextpage"
                @current-change="currentchange"
                @size-change="sizechange">
          啊啊啊
        </el-pagination>

      </el-tab-pane>

      <el-tab-pane label="支出">

        <div class="block">
          <!-- 查询时间-->
          <el-date-picker
                  v-model="purchaseTime"
                  type="datetimerange"
                  :picker-options="pickerOptions"
                  value-format="yyyy-MM-dd HH:mm:ss"
                  range-separator="至"
                  start-placeholder="开始时间"
                  end-placeholder="结束时间"
                  align="right">
          </el-date-picker>
          <el-button type="primary" @click="searchPurchase()">查询</el-button>
        </div>

        <el-table
                :data="purchaseData"
                stripe
                show-summary
                :summary-method="getSummaries1"
                style="width: 100%"
        >
          <el-table-column
                  prop="purchaseId"
                  label="ID">
          </el-table-column>
          <el-table-column
                  prop="time"
                  label="时间">
          </el-table-column>
          <el-table-column
                  prop="total"
                  label="金额/元">
          </el-table-column>
        </el-table>

        <el-pagination
                background
                layout="total, sizes, prev, pager, next, jumper"
                :page-sizes="[5, 10, 15, 20]"
                :page-size="size1"
                :total="total1"
                :current-page="currentpage1"
                @prev-click="prvpage1"
                @next-click="nextpage1"
                @current-change="currentchange1"
                @size-change="sizechange1">
        </el-pagination>
      </el-tab-pane>

      <el-tab-pane label="收入">
        <div class="block">
          <!-- 查询时间-->
          <el-date-picker
                  v-model="orderTime"
                  type="datetimerange"
                  :picker-options="pickerOptions"
                  value-format="yyyy-MM-dd HH:mm:ss"
                  range-separator="至"
                  start-placeholder="开始时间"
                  end-placeholder="结束时间"
                  align="right">
          </el-date-picker>
          <el-button type="primary" @click="searchOrder()">查询</el-button>
        </div>
        <el-table
                :data="orderData"
                stripe
                show-summary
                :summary-method="getSummaries2"
                style="width: 100%"
        >
          <el-table-column
                  prop="orderId"
                  label="ID">
          </el-table-column>
          <el-table-column
                  prop="time"
                  label="时间">
          </el-table-column>
          <el-table-column
                  prop="total"
                  label="金额/元">
          </el-table-column>
        </el-table>

        <el-pagination
                background
                layout="total, sizes, prev, pager, next, jumper"
                :page-sizes="[5, 10, 15, 20]"
                :page-size="size2"
                :total="total2"
                :current-page="currentpage2"
                @prev-click="prvpage2"
                @next-click="nextpage2"
                @current-change="currentchange2"
                @size-change="sizechange2">
        </el-pagination>
      </el-tab-pane>

    </el-tabs>
  </div>
</template>

<script>
import Axios from "axios";
export default {
  data() {
    return {
      accountData:[],
      purchaseData:[],
      orderData:[],

      page:1,//当前显示页面
      totalpage:0,//总页面
      total:0,  //总条目数
      size:5,  //每页显示多少条
      currentpage:1,

      page1:1,//当前显示页面
      totalpage1:0,//总页面
      total1:0,  //总条目数
      size1:5,  //每页显示多少条
      currentpage1:1,

      page2:1,//当前显示页面
      totalpage2:0,//总页面
      total2:0,  //总条目数
      size2:5,  //每页显示多少条
      currentpage2:1,

      pickerOptions: {
        shortcuts: [{
          text: '最近一周',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近一个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近三个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
            picker.$emit('pick', [start, end]);
          }
        }]
      },
      accountTime: '',
      purchaseTime:'',
      orderTime:'',
    }
  },

  methods: {
    //获取表格数据
    getAccountData: function () {
      Axios({
        url: '/application/account/fenYe.action',
        method: "get",
        params: {
          page: this.page, rows: this.size,beginTime:this.accountTime[0],endTime:this.accountTime[1]
        },
      }).then((result) => {
        this.accountData=result.data.rows;
        this.total=result.data.total;
        this.totalpage=this.total%this.size==0?this.total/this.size:Math.floor(this.total/this.size)+1

      }).catch((error) => {
        alert(error);
      })
    },
    //获取采购表格数据
    getPurchaseData: function () {
      Axios({
        url: '/application/account/fenYe1.action',
        method: "get",
        params: {
          page: this.page1, rows: this.size1,beginTime:this.purchaseTime[0],endTime:this.purchaseTime[1]
        },
      }).then((result) => {
        this.purchaseData=result.data.rows;
        this.total1=result.data.total;
        this.totalpage1=this.total1%this.size1==0?this.total1/this.size1:Math.floor(this.total1/this.size1)+1

      }).catch((error) => {
        alert(error);
      })
    },
    //获取收入表格数据
    getOrderData: function () {
      Axios({
        url: '/application/account/fenYe2.action',
        method: "get",
        params: {
          page: this.page2, rows: this.size2,beginTime:this.orderTime[0],endTime:this.orderTime[1]
        },
      }).then((result) => {
        this.orderData=result.data.rows;
        this.total2=result.data.total;
        this.totalpage2=this.total2%this.size2==0?this.total2/this.size2:Math.floor(this.total2/this.size2)+1

      }).catch((error) => {
        alert(error);
      })
    },
    //上一页
    prvpage() {
      this.page=this.page==1?1:this.page-1;
      this.getAccountData();
    },
    //下一页
    nextpage() {
      this.page=this.page==this.totalpage?this.totalpage:this.page+1;
      this.getAccountData();
    },
    //当前第一页
    currentchange(val){
      this.page=val;
      this.getAccountData();
    },
    //每页多少条
    sizechange(val){
      this.size=val;
      this.getAccountData();
    },
    //查询
    searchAccount(){
      this.getAccountData();
    },
    getSummaries(param){
      let sum = 0;
      let sum1=0;
      let data = param.data;
      for (let i = 0; i < data.length; i++) {
        if(data[i].accountType==0){
          sum=sum-data[i].total;
        }else {
          sum1+=data[i].total;
        }
      }
      return ["合计","",(sum+sum1).toFixed(2),""]
    },

    //上一页
    prvpage1() {
      this.page1=this.page1==1?1:this.page1-1;
      this.getPurchaseData();
    },
    //下一页
    nextpage1() {
      this.page1=this.page1==this.totalpage1?this.totalpage1:this.page1+1;
      this.getPurchaseData();
    },
    //当前第一页
    currentchange1(val){
      this.page1=val;
      this.getPurchaseData();
    },
    //每页多少条
    sizechange1(val){
      this.size1=val;
      this.getPurchaseData();
    },
    //查询
    searchPurchase(){
      this.getPurchaseData();
    },
    getSummaries1(param){
      let sum = 0;
      let data = param.data;
      for (let i = 0; i < data.length; i++) {
          sum+=data[i].total;
      }
      return ["合计","",sum.toFixed(2),""]
    },
    //上一页
    prvpage2() {
      this.page2=this.page2==1?1:this.page2-1;
      this.getOrderData();
    },
    //下一页
    nextpage2() {
      this.page2=this.page2==this.totalpage2?this.totalpage2:this.page2+1;
      this.getOrderData();
    },
    //当前第一页
    currentchange2(val){
      this.page2=val;
      this.getOrderData();
    },
    //每页多少条
    sizechange2(val){
      this.size2=val;
      this.getOrderData();
    },
    //查询
    searchOrder(){
      this.getOrderData();
    },
    getSummaries2(param){
      let sum = 0;
      let data = param.data;
      for (let i = 0; i < data.length; i++) {
        sum+=data[i].total;
      }
      return ["合计","",sum.toFixed(2),""]
    }
  },

  created() {
    this.getAccountData();
    this.getPurchaseData();
    this.getOrderData();
  }
}
</script>


<style scoped>

</style>