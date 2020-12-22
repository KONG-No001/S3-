<template>
  <div>
    <!-- 添加、搜索 -->
    <el-row :gutter="10">
      <el-col :span="4">
        <el-input placeholder="请输入商品名称" v-model="goodsName"></el-input>
      </el-col>
      <el-col :span="4">
        <el-select placeholder="请选择商品类型" v-model="goodsTypeId">
          <el-option :key="null"
                     :label="null"
                     :value="null">请选择</el-option>
          <el-option
              v-for="item in goodsType"
              :key="item.goodsTypeId"
              :label="item.goodsTypeName"
              :value="item.goodsTypeId">
          </el-option>
        </el-select>
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
        @selection-change="selectionChange"
    >
      <el-table-column
          type="selection">
      </el-table-column>
      <el-table-column
          prop="id"
          label="ID"
          width="50">
      </el-table-column>
      <el-table-column
          prop="name"
          label="商品名称">
      </el-table-column>
      <el-table-column
          prop="goodsImg"
          label="图片">
        <template slot-scope="scope">
          <img  :src="scope.row.goodsVo.goodsImg" style="width: 30px"/>
        </template>
      </el-table-column>
      <el-table-column
          prop="goodsTypeId"
          label="类型"
          :formatter="formatterGoodsTypeId">
      </el-table-column>
      <el-table-column
          prop="goodsVo.goodsGuige"
          label="规格">
      </el-table-column>
      <el-table-column
          prop="goodsVo.goodsInPrice"
          label="进价">
      </el-table-column>
      <el-table-column
          prop="goodsOutPrice"
          label="售价">
      </el-table-column>
      <el-table-column
          prop="count"
          label="数量">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" @click="editBtn(scope.row)"></el-button>
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
        <el-form-item label="ID" >
          <el-input v-model="form.id" readonly></el-input>
        </el-form-item>
        <el-form-item label="名称" >
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="类型" >
          <el-select v-model="form.goodsTypeId" placeholder="请选择商品类型">
            <el-option
                v-for="item in goodsType"
                :key="item.goodsTypeId"
                :label="item.goodsTypeName"
                :value="item.goodsTypeId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="售价" prop="supplierTel">
          <el-input v-model="form.goodsOutPrice"></el-input>
        </el-form-item>
        <el-form-item label="数量" prop="supplierAddress">
          <el-input v-model="form.count"></el-input>
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
      goodsType: [],
      dialogFormVisible: false,
      dialogFormVisibleAdd: false,
      form: {
        id: '', goodsId: '', name: '',goodsTypeId: '',goodsOutPrice: '',count: ''
      },
      total: 0,
      pages: 0,
      row: 5,
      page: 1,
      goodsName:'',
      goodsTypeId:'',
      selectIds:[]
    }
  },

  methods: {
    //获取表格数据
    getData: function () {
      Axios({
        url: '/application/warehouse/fenYe.action',
        method: "get",
        params: {
          name: this.goodsName,
          goodsTypeId: this.goodsTypeId,
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

    //显示类型名称
    formatterGoodsTypeId(row) {
      return row.goodsTypeVo.goodsTypeName;
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
      this.goodsName=''
      this.goodsTypeId=''
    },

    //点击修改按钮
    editBtn(row) {
      this.dialogFormVisible = true;
      this.form = row;
    },

    //修改信息
    edit() {
      this.dialogFormVisible = false;
      this.$delete(this.form,'goodsTypeVo');
      this.$delete(this.form,'goodsVo');
      Axios({
        url: '/application/warehouse/update.action',
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



    //选中行变化
    selectionChange(val){
      this.selectIds=[];
      for (let i = 0; i < val.length; i++) {
        this.selectIds.push(val[i].supplierId)
      }
    }
  },

  created() {
    this.getData();
    this.getGoodsType();
  }
}
</script>


<style scoped>

</style>