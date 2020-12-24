<template>
  <div>
    <el-tabs type="border-card">
      <el-tab-pane label="统计">
        所有账单
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

      </el-tab-pane>
      <el-tab-pane label="收入">
        销售账单
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import Axios from "axios";
export default {
  data() {
    return {
      purchaseData: [],
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
    getPurchaseData: function () {
      Axios({
        url: '/application/account/queryPurchase.action',
        method: "get",
      }).then((result) => {
       this.purchaseData=result.data;
      }).catch((error) => {
        alert(error);
      })
    }

  },

  created() {
    this.getPurchaseData();
  }
}
</script>


<style scoped>

</style>