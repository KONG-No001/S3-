<template>
  <div>
    <!-- 添加、搜索 -->
    <el-row :gutter="10">
      <el-col :span="4">
        <el-input placeholder="请输入类型名称" v-model="name"></el-input>
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
          prop="user.name"
          label="用户">
      </el-table-column>
      <el-table-column
          prop="dingdanId"
          label="订单号">
      </el-table-column>
      <el-table-column
          prop="shanghu.name"
          label="商户">
      </el-table-column>
      <el-table-column
          prop="shanghu.address"
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
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" @click="editBtn(scope.row)"></el-button>
          <el-popconfirm icon="el-icon-info"
                         icon-color="red"
                         title="确定删除吗？"
                         @confirm="del(scope.row)">
            <el-button slot="reference" type="danger" icon="el-icon-delete"></el-button>
          </el-popconfirm>
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

    <!-- 编辑模态框-->
    <el-dialog title="编辑" :visible.sync="dialogFormVisible">
      <el-form :inline="true" :model="form" ref="form" size="medium" label-width="100px" class="demo-ruleForm">
        <el-form-item label="ID" prop="supplierId">
          <el-input v-model="form.supplierId" readonly></el-input>
        </el-form-item>
        <el-form-item label="名称" prop="supplierText">
          <el-input v-model="form.supplierText"></el-input>
        </el-form-item>
        <el-form-item label="负责人" prop="supplierName">
          <el-input v-model="form.supplierName"></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="supplierTel">
          <el-input v-model="form.supplierTel"></el-input>
        </el-form-item>
        <el-form-item label="地址" prop="supplierAddress">
          <el-input v-model="form.supplierAddress"></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="edit">确 定</el-button>
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
      dialogFormVisible: false,
      form: {
        id: '', dingdanId: '', userId: '',shopId: '',time: '',status:''
      },
      total: 0,
      pages: 0,
      row: 5,
      page: 1,
      name:''
    }
  },

  methods: {
    //获取表格数据
    getData: function () {
      Axios({
        url: '/application/delivery/fenYe.action',
        method: "get",
        params: {
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
    formatterStatus(row){
      alert(row)

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
      this.name='';
    },

    //点击修改按钮
    editBtn(row) {
      this.dialogFormVisible = true;
      this.form = row;
    },

    //修改信息
    edit() {
      this.dialogFormVisible = false;
      Axios({
        url: '/application/supplier/update.action',
        method: "post",
        params: this.form,
      }).then((result) => {
        this.$notify({
          title: '提示',
          message: result.data == true ? '修改成功' : '修改失败',
          type: 'success'
        });
        this.getData();
      }).catch((error) => {
        alert(error);
      })

    },

    //删除信息
    del(row) {
      Axios({
        url: '/application/supplier/delete.action',
        method: "get",
        params: {id: row.supplierId},
      }).then((result) => {
        this.$notify({
          title: '提示',
          message: result.data == true ? '删除成功' : '删除失败',
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