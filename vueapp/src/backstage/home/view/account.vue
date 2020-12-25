<template>
  <div>
    <el-tabs type="border-card">
      <el-tab-pane label="统计">
        <div class="block">
          <el-date-picker
                  v-model="value2"
                  type="datetimerange"
                  :picker-options="pickerOptions"
                  range-separator="至"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期"
                  align="right">
          </el-date-picker>
          <el-button type="primary">查询</el-button>
        </div>
          <el-table
                  :data="accuntData"
                  stripe
                  style="width: 100%"
          >
            <el-table-column
                    prop="id"
                    label="ID">
            </el-table-column>
            <el-table-column
                    prop="dingdanId"
                    label="订单id">
            </el-table-column>
            <el-table-column
                    prop="order_id"
                    label="销售id">
            </el-table-column>
            <el-table-column
                    prop="time"
                    label="订单时间">
            </el-table-column>
            <el-table-column
                    prop="total"
                    label="金额/元">
            </el-table-column>
            <el-table-column
                    prop="accountType"
                    label="订单类型">
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
        </el-pagination>
      </el-tab-pane>

      <el-tab-pane label="支出">
        <div class="block">
          <el-date-picker
              v-model="value2"
              type="datetimerange"
              :picker-options="pickerOptions"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              align="right">
          </el-date-picker>
          <el-button type="primary">查询</el-button>
        </div>

        <el-table
            :data="purchaseData"
            stripe
        >
          <el-table-column
              prop="id"
              label="ID">
          </el-table-column>
          <el-table-column
              prop="purchaseId"
              label="采购">
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
                :page-size="size"
                :total="total"
                :current-page="currentpage"
                @prev-click="prvpage"
                @next-click="nextpage"
                @current-change="currentchange"
                @size-change="sizechange">
        </el-pagination>
      </el-tab-pane>
      <el-tab-pane label="收入">
        <div class="block">
          <el-date-picker
                  v-model="value2"
                  type="datetimerange"
                  :picker-options="pickerOptions"
                  range-separator="至"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期"
                  align="right">
          </el-date-picker>
          <el-button type="primary">查询</el-button>
        </div>
        <el-table
                :data="getOrderData"
                stripe
        >
          <el-table-column
                  prop="id"
                  label="ID">
          </el-table-column>
          <el-table-column
                  prop="purchaseId"
                  label="采购">
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
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import Axios from "axios";
export default {
  data() {
    return {
      accuntData:[],
      purchaseData: [],
      orderData:[],
      pageindex:1,//当前显示页面
      totalpage:0,//总页面
      total:0,  //总条目数
      size:5,  //每页显示多少条
      currentpage:1,
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
      value1: [new Date(2000, 10, 10, 10, 10), new Date(2000, 10, 11, 10, 10)],
      value2: ''
    }
  },

  methods: {
    //获取表格数据
    getAccountData: function () {
      Axios({
        url: '/application/account/queryAll.action',
        method: "get",
      }).then((result) => {
        this.accuntData=result.data;
      }).catch((error) => {
        alert(error);
      })
    },
    getPurchaseData: function () {
      Axios({
        url: '/application/account/queryPurchase.action',
        method: "get",
      }).then((result) => {
       this.purchaseData=result.data;
      }).catch((error) => {
        alert(error);
      })
    },
    getOrderData: function () {
      Axios({
        url: '/application/account/queryOrder.action',
        method: "get",
      }).then((result) => {
        this.orderData=result.data;
      }).catch((error) => {
        alert(error);
      })
    },
    //上一页
    prvpage() {
      this.pageindex=this.pageindex==1?1:this.pageindex-1;
      this.getsh();
    },
    //下一页
    nextpage() {
      this.pageindex=this.pageindex==this.totalpage?this.totalpage:this.pageindex+1;
      this.getsh();
    },
    //当前第一页
    currentchange(val){
      this.pageindex=val;
      this.getsh();
    },
    //每页多少条
    sizechange(val){
      this.size=val;
      this.getsh();
    },

  },

  created() {
    this.getPurchaseData();
    this.getAccountData();
  }
}
</script>


<style scoped>

</style>