<template>
  <div>
    <el-table
        :data="tableData"
        stripe
    >
      <el-table-column
          type="selection">
      </el-table-column>
      <el-table-column
          prop="goodsId"
          label="ID"
          width="50">
      </el-table-column>
      <el-table-column
          prop="goodsCode"
          label="条码">
      </el-table-column>
      <el-table-column
          prop="goodsName"
          label="名称">
      </el-table-column>
      <el-table-column
          prop="goodsBrand"
          label="品牌">
      </el-table-column>
      <el-table-column
          prop="goodsTypeId"
          label="类型"
          :formatter="formatterGoodsTypeId">
      </el-table-column>
      <el-table-column
          prop="goodsGuige"
          label="规格">
      </el-table-column>
      <el-table-column
          prop="goodsInPrice"
          label="进价">
      </el-table-column>
      <el-table-column
          prop="goodsImg"
          label="图片地址">
      </el-table-column>
      <el-table-column
          prop="goodsTime"
          label="生产日期">
      </el-table-column>
      <el-table-column
          prop="goodsFactory"
          label="厂家">
      </el-table-column>
      <el-table-column
          prop="goodsAddress"
          label="地址">
      </el-table-column>
      <el-table-column
          prop="goodsStatus"
          label="状态"
          :formatter="formatterGoodsStatus">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" @click="editGoods(scope.row)"></el-button>
          <el-popconfirm title="这是一段内容确定删除吗？" @confirm="delGoods(scope.row)">
            <el-button slot="reference" type="danger" icon="el-icon-delete"></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <!--    编辑模态框-->
    <el-dialog title="编辑" :visible.sync="dialogFormVisible">
      <el-form label-width="80px">
        <h1>编辑</h1>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
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
      pageindex:1,//当前显示页面
      total:0,//总页面
      size:0,  //总条目数
      row:5,  //每页显示多少条
    }
  },
  methods: {
    getData: function () {
      let _this = this;
      Axios.get('/application/goods/queryAll.action')
          .then(function (result) {
            _this.tableData = result.data;
          }).catch(function (error) {
        alert(error)
      });
    },
    formatterGoodsTypeId(row) {
      return row.goodsTypeVo.goodsTypeName;
    },
    formatterGoodsStatus(row) {
      if (row.goodsStatus=="0"){
        return "销售中";
      }
      return "已下架";
    },
    delGoods(row) {
      let _this = this;
      let params = new URLSearchParams();
      params.append("goodsId", row.goodsId);
      Axios.post("/application/goods/delete.action", params)
          .then(function (result) {
            if (result.data == true) {
              _this.$message({
                message: '删除成功',
                type: 'success'
              });
              _this.getData();
            } else {
              alert('删除失败');
            }
          }).catch(function (error) {
        alert(error)
      });
    },
    editGoods(row) {
      console.log(row)
      this.dialogFormVisible = true;
    }
  },
  created() {
    this.getData();
  }

}
</script>

<style>
</style>