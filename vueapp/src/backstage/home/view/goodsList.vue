<template>
  <div>
    <!-- 数据表格-->
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

    <!-- 编辑模态框-->
    <el-dialog title="编辑" :visible.sync="dialogFormVisible">
      <el-form :inline="true" :model="editForm"  ref="editForm" size="medium" label-width="100px" class="demo-ruleForm">
        <el-form-item label="ID" prop="goodsId">
          <el-input v-model="editForm.goodsId"></el-input>
        </el-form-item>
        <el-form-item label="编码" prop="goodsCode">
          <el-input v-model="editForm.goodsCode"></el-input>
        </el-form-item>
        <el-form-item label="名称" prop="goodsName">
          <el-input v-model="editForm.goodsName"></el-input>
        </el-form-item>
        <el-form-item label="品牌" prop="goodsBrand">
          <el-input v-model="editForm.goodsBrand"></el-input>
        </el-form-item>
        <!-- 下拉框 -->
        <el-form-item label="类型" prop="goodsTypeId">
          <el-select v-model="editForm.goodsTypeId" placeholder="请选择商品类型" >
            <el-option
                v-for="item in goodsType"
                :key="item.goodsTypeId"
                :label="item.goodsTypeName"
                :value="item.goodsTypeId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="规格" prop="goodsGuige">
          <el-input v-model="editForm.goodsGuige"></el-input>
        </el-form-item>
        <el-form-item label="进价" prop="goodsInPrice">
          <el-input v-model="editForm.goodsInPrice"></el-input>
        </el-form-item>
        <el-form-item label="图片地址" prop="goodsImg">
          <el-input v-model="editForm.goodsImg"></el-input>
        </el-form-item>
        <el-form-item label="生产时间" prop="goodsTime">
              <el-date-picker value-format="yyyy-MM-dd" type="date" placeholder="选择日期" v-model="editForm.goodsTime" style="width: 100%;">
              </el-date-picker>
        </el-form-item>
        <el-form-item label="厂家" prop="goodsFactory">
          <el-input v-model="editForm.goodsFactory"></el-input>
        </el-form-item>
        <el-form-item label="地址" prop="goodsAddress">
          <el-input v-model="editForm.goodsAddress"></el-input>
        </el-form-item>
        <!-- 单选按钮-->
        <el-form-item label="状态" prop="goodsStatus">
          <el-radio-group v-model="editForm.goodsStatus">
            <el-radio :label="0" >销售中</el-radio>
            <el-radio :label="1" >已下架</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="editGoods2">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import Axios from "axios";

export default {
  data() {
    return {
      goodsType:[],
      tableData: [],
      dialogFormVisible: false,
      editForm:{goodsId:'', goodsCode:'', goodsName:'', goodsBrand:'',
        goodsTypeId:'', goodsGuige:'', goodsInPrice:'', goodsImg:'',
        goodsTime:'', goodsFactory:'', goodsAddress:'', goodsStatus:''}
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
    getGoodsType: function () {
      let _this = this;
      Axios.get('/application/goodsType/queryAll.action')
          .then(function (result) {
            _this.goodsType = result.data;
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
      this.dialogFormVisible = true;

      this.editForm=row;
    },
    editGoods2(){
      this.dialogFormVisible = false;
      this.editForm.goodsTypeVo=null;
      Axios({
        url:'/application/goods/update.action',
        method: "post",
        params:this.editForm,
      }).then(()=>{
        this.getData();
      }).catch((option)=>{
          alert(option)
      })

      /*Axios.post("/application/goods/update.action",)
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
      });*/
    }
  },
  created() {
    this.getData();
    this.getGoodsType();
  }

}
</script>

<style>
</style>