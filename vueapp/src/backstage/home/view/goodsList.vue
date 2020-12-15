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

      <el-col :span="2">
        <el-button type="primary" @click="addGoods">添加</el-button>
      </el-col>
      <el-col :span="2">
        <el-button type="danger" @click="delGoods2">批量删除</el-button>
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
          <el-popconfirm icon="el-icon-info"
                         icon-color="red"
                         title="确定删除吗？"
                         @confirm="delGoods(scope.row)">
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

    <!-- 添加模态框-->
    <el-dialog title="添加" :visible.sync="dialogFormVisibleAdd">
      <el-form :inline="true" :model="editForm" ref="editForm" size="medium" label-width="100px" class="demo-ruleForm">
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
          <el-select v-model="editForm.goodsTypeId" placeholder="请选择商品类型">
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
          <el-date-picker value-format="yyyy-MM-dd" type="date" placeholder="选择日期" v-model="editForm.goodsTime"
                          style="width: 100%;">
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
            <el-radio :label="0">销售中</el-radio>
            <el-radio :label="1">已下架</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisibleAdd = false">取 消</el-button>
        <el-button type="primary" @click="addGoods2">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 编辑模态框-->
    <el-dialog title="编辑" :visible.sync="dialogFormVisible">
      <el-form :inline="true" :model="editForm" ref="editForm" size="medium" label-width="100px" class="demo-ruleForm">
        <el-form-item label="ID" prop="goodsId">
          <el-input readonly v-model="editForm.goodsId"></el-input>
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
          <el-select v-model="editForm.goodsTypeId" placeholder="请选择商品类型">
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
          <el-date-picker value-format="yyyy-MM-dd" type="date" placeholder="选择日期" v-model="editForm.goodsTime"
                          style="width: 100%;">
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
            <el-radio :label="0">销售中</el-radio>
            <el-radio :label="1">已下架</el-radio>
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
      goodsType: [],
      tableData: [],
      dialogFormVisible: false,
      dialogFormVisibleAdd: false,
      editForm: {
        goodsId: '', goodsCode: '', goodsName: '', goodsBrand: '',
        goodsTypeId: '', goodsGuige: '', goodsInPrice: '', goodsImg: '',
        goodsTime: '', goodsFactory: '', goodsAddress: '', goodsStatus: ''
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
        url: '/application/goods/fenYe.action',
        method: "get",
        params: {
          goodsName: this.goodsName,
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

    //显示状态
    formatterGoodsStatus(row) {
      if (row.goodsStatus == "0") {
        return "销售中";
      }
      return "已下架";
    },

    //点击添加按钮
    addGoods() {
      this.dialogFormVisibleAdd = true;
      this.editForm = {};
    },

    //添加信息
    addGoods2() {
      this.dialogFormVisibleAdd = false;
      Axios({
        url: '/application/goods/add.action',
        method: "post",
        params: this.editForm,
      }).then((result) => {
        this.$notify({
          title: '提示',
          message: result.data == true ? '添加成功' : '添加失败',
          type: 'success'
        });
        this.getData();
      }).catch((error) => {
        alert(error);
      })

    },

    //点击修改按钮
    editGoods(row) {
      this.dialogFormVisible = true;
      this.editForm = row;
    },

    //修改信息
    editGoods2() {
      this.dialogFormVisible = false;
      this.editForm.goodsTypeVo = null;
      Axios({
        url: '/application/goods/update.action',
        method: "post",
        params: this.editForm,
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
    delGoods(row) {
      Axios({
        url: '/application/goods/delete.action',
        method: "get",
        params: {goodsId: row.goodsId},
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
    },

    //选中行变化
    selectionChange(val){
      this.selectIds=[];
      for (let i = 0; i < val.length; i++) {
        this.selectIds.push(val[i].goodsId)
      }
    },

    //批量删除
    delGoods2() {
      Axios({
        url: '/application/goods/deleteByIds.action',
        method: "get",
        params: {goodsIds:this.selectIds.toLocaleString()},
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
    this.getGoodsType();
  }
}
</script>

<style>
</style>