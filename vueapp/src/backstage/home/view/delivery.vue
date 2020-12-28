<template>
  <div>
    <!-- 添加、搜索 -->
    <el-row :gutter="10">
      <el-col :span="4">
        <el-input placeholder="订单号" v-model="did"></el-input>
      </el-col>
      <el-col :span="4">
        <el-button type="primary" @click="search">查询</el-button>
        <el-button type="primary" @click="reset">清空</el-button>
      </el-col>
    </el-row>

    <!-- 数据表格-->
    <el-table
        :data="tableData"
        stripe
    >
      <el-table-column
          prop="id"
          label="ID">
      </el-table-column>
      <el-table-column
          prop="dingdanId"
          label="订单号">
      </el-table-column>
      <el-table-column
          prop="dingDan.user.name"
          label="用户">
      </el-table-column>
      <el-table-column
          prop="dingDan.user.phone"
          width="110"
          label="电话">
      </el-table-column>
      <el-table-column
          prop="dingDan.shanghu.name"
          label="商户">
      </el-table-column>
      <el-table-column
          prop="dingDan.shanghu.address"
          label="地址">
      </el-table-column>
      <el-table-column
          prop="time"
          width="200px"
          label="时间">
      </el-table-column>
      <el-table-column
          prop="status"
          label="状态">
        <template slot-scope="{row}" >
          <el-tag v-if="row.status===0" type="info">未出库</el-tag>
          <el-tag v-else-if="row.status===1" type="warning">已出库</el-tag>
          <el-tag v-else-if="row.status===2" type="">已收货</el-tag>
          <el-tag v-else-if="row.status===3" type="success">已完成</el-tag>
          <el-tag v-else-if="row.status===4" type="danger">已取消</el-tag>
        </template>
      </el-table-column>
      <el-table-column width="280" label="操作">
        <template slot-scope="{row}">
          <el-button type="primary" icon="el-icon-tickets" @click="xq(row)">发货详情</el-button>
          <el-button v-if="row.status===0" type="primary"  icon="el-icon-s-promotion" @click="fh(row)">发货</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页-->
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="page"
        :page-sizes="[5, 10, 15, 20]"
        :page-size="row"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
    </el-pagination>

    <!-- 采购详情模态框-->
    <el-dialog title="采购商品详情" :visible.sync="dialogXQ" width="40%">
      <div>
        <el-table
            :data="goods"
            stripe>
          <el-table-column
              prop="warehouseVo.goodsVo.goodsName"
              label="商品名称">
          </el-table-column>
          <el-table-column
              prop="warehouseVo.goodsVo.goodsImg"
              label="图片">
            <template slot-scope="scope">
              <img  :src="scope.row.warehouseVo.goodsVo.goodsImg" style="width: 30px"/>
            </template>
          </el-table-column>
          <el-table-column
              prop="warehouseVo.goodsVo.goodsGuige"
              label="规格">
          </el-table-column>
          <el-table-column
              prop="warehouseVo.goodsVo.goodsInPrice"
              label="单价">
          </el-table-column>
          <el-table-column
              prop="count"
              label="数量">
          </el-table-column>
        </el-table>
      </div>
    </el-dialog>


  </div>
</template>

<script>
import Axios from "axios";
export default {
  data() {
    return {
      tableData: [],
      goods:[],
      dialogFormVisible: false,
      dialogXQ:false,
      form: {
        id: '', dingdanId: '', userId: '',shopId: '',time: '',status:''
      },
      total: 0,
      pages: 0,
      row: 5,
      page: 1,
      did:''
    }
  },

  methods: {
    //获取表格数据
    getData: function () {
      Axios({
        url: '/application/delivery/fenYe.action',
        method: "get",
        params: {
          dingdanId:this.did,
          page: this.page, row: this.row
        },
      }).then((result) => {
        this.total = result.data.total;
        this.pages = result.data.pages;
        this.tableData = result.data.records;
      }).catch((error) => {
        alert(error);
      })
    },

    //每页条数变化
    handleSizeChange(val) {
      this.row = val;
      this.getData();
    },

    //页数变化
    handleCurrentChange(val) {
      this.page = val;
      this.getData();
    },

    //搜索
    search() {
      this.getData();
    },

    //清空
    reset(){
      this.dingdanId='';
    },

    //详情
    xq(row){
      this.dialogXQ=true;
      //console.log(row.dingDan.dingDanWarehouses);
      this.goods=row.dingDan.dingDanWarehouses;
    },

    //发货
    fh(row){
      Axios({
        url: '/application/delivery/fh.action',
        method: "get",
        params: {
          id:row.id
        },
      }).then((result) => {
        this.$notify({
          title: '提示',
          message: result.data == true ? '发货成功' : '发货失败',
          type: 'success'
        });
        this.getData();
      }).catch((error) => {
        alert(error);
      })
    }
  },

  created() {
    this.getData();
  }
}
</script>


<style scoped>

</style>