<template>
  <div>
    <!-- 添加、搜索 -->
    <el-row :gutter="10">
      <el-col :span="2">
        <el-button type="primary" @click="addBtn">添加</el-button>
      </el-col>
    </el-row>

    <!-- 数据表格-->
    <el-table
        :data="tableData"
        stripe
    >
      <el-table-column
          prop="id"
          label="ID"
       >
      </el-table-column>
      <el-table-column
          prop="supplierId"
          label="供应商"
          :formatter="formatter"
          >
      </el-table-column>
      <el-table-column
          prop="time"
          label="时间">
      </el-table-column>
      <el-table-column
          prop="sum"
          label="总计/元">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-tickets" @click="xq(scope.row)" >订单详情</el-button>
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
    <el-dialog title="添加" :visible.sync="dialogFormVisibleAdd" width="65%">
      <!-- 商品数据展示 -->
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
            <el-button type="primary" @click="search2">查询</el-button>
            <el-button type="primary" @click="reset2">清空</el-button>
          </el-col>
        </el-row>

        <!-- 数据表格-->
        <el-table
            :data="addTableData"
            stripe
            @selection-change="selectionChange"
            @select="select"
            ref="addTableData"

        >
          <el-table-column
              type="selection"
          >
          </el-table-column>
          <el-table-column
              prop="goodsId"
              label="ID"
              width="50">
          </el-table-column>
          <el-table-column
              prop="goodsName"
              width="100"
              label="名称">
          </el-table-column>
          <el-table-column
              prop="goodsTypeId"
              label="类型"
              width="100"
              :formatter="formatterGoodsTypeId">
          </el-table-column>
          <el-table-column
              prop="goodsGuige"
              width="100"
              label="规格">
          </el-table-column>
          <el-table-column
              width="100"
              prop="goodsInPrice"
              label="进价">
          </el-table-column>
          <el-table-column
              prop="goodsImg"
              label="图片1"
              width="100">
            <template slot-scope="scope">
              <img  :src="scope.row.goodsImg" style="width: 30px"/>
            </template>
          </el-table-column>
          <el-table-column
              width="100"
              prop="goodsTime"
              label="生产日期">
          </el-table-column>
          <el-table-column width="190" label="数量">
              <el-input-number slot-scope="{row}" @change="countChange" v-model="row.count" :min="1" :max="1000">:
              </el-input-number>
          </el-table-column>
        </el-table>

        <!-- 分页-->
        <el-pagination
            @size-change="handleSizeChange2"
            @current-change="handleCurrentChange2"
            :current-page="page2"
            :page-sizes="[5, 10, 15, 20]"
            :page-size="row2"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total2">
        </el-pagination>
      </div>
      <br>
      <el-form :inline="true" :model="form" ref="form" size="medium" label-width="100px" class="demo-ruleForm">
        <el-form-item label="供应商">
          <el-select placeholder="请选择商品类型" v-model="form.supplierId">
            <el-option
                v-for="item in supplier"
                :key="item.supplierId"
                :label="item.supplierText"
                :value="item.supplierId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="时间" >
          <el-date-picker
              v-model="form.time"
              type="datetime"
              placeholder="选择日期时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="总计">
          <el-input :value="form.sum"></el-input>

        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisibleAdd = false">取 消</el-button>
        <el-button type="primary" @click="add">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 订单详情模态框-->
    <el-dialog title="采购商品详情" :visible.sync="dialogXQ" width="40%">
      <div>
        <el-table
            :data="goods"
            stripe>
          <el-table-column
              prop="goodsVo.goodsName"
              label="商品名称">
          </el-table-column>
          <el-table-column
              prop="goodsVo.goodsImg"
              label="图片">
            <template slot-scope="scope">
              <img  :src="scope.row.goodsVo.goodsImg" style="width: 30px"/>
            </template>
          </el-table-column>
          <el-table-column
              prop="goodsVo.goodsGuige"
              label="规格">
          </el-table-column>
          <el-table-column
              prop="goodsVo.goodsInPrice"
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
      addTableData:[],
      goodsType: [],
      supplier:[],
      goods:[],
      dialogFormVisible: false,
      dialogFormVisibleAdd: false,
      dialogXQ:false,
      form: {
        id: '', supplierId: '', time: '',sum: 0
      },
      total: 0,
      pages: 0,
      row: 5,
      page: 1,
      total2: 0,
      pages2: 0,
      row2: 5,
      page2: 1,
      selectIds:[],
      purchaseGoods:{id:'',purchaseId:'',goodsId:'',count:''},
      goodsName:'',
      goodsTypeId:'',
    }
  },

  methods: {
    //获取表格数据
    getData: function () {
      Axios({
        url: '/application/purchase/fenYe.action',
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

    //获取添加表格的数据
    getAddData:function(){
      Axios({
        url: '/application/goods/fenYe.action',
        method: "get",
        params: {
          goodsName: this.goodsName,
          goodsTypeId: this.goodsTypeId,
          page: this.page2, row: this.row2
        },
      }).then((result) => {
        result.data.records.forEach(item=>{
          item.count = 0;
        });
        this.total2 = result.data.total;
        this.pages2 = result.data.pages;
        this.addTableData = result.data.records;
      }).catch((error) => {
        alert(error);
      })
    },

    //获取商品类型下拉框数据
    getGoodsType: function () {
      let _this=this;
      Axios.get('/application/goodsType/queryAll.action')
          .then(function (result) {
            _this.goodsType = result.data;
          }).catch(function (error) {
        alert(error)
      });
    },

    //获取所有供应商
    getSupplier:function(){
      let _this=this;
      Axios.get('/application/supplier/queryAll.action')
          .then(function (result) {
            _this.supplier = result.data;
          }).catch(function (error) {
        alert(error)
      });
    },

    //显示供应商
    formatter(row){
      return row.supplierVo.supplierText;
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

    //每页条数变化
    handleSizeChange2(val) {
      this.row2 = val;
      this.getAddData();
    },

    //页数变化
    handleCurrentChange2(val) {
      this.page2 = val;
      this.getAddData();
    },


    //添加搜索
    search2() {
      this.getAddData();
    },

    //清空
    reset2(){
      this.goodsName=''
      this.goodsTypeId=''
    },

    //点击添加按钮
    addBtn() {
      this.dialogFormVisibleAdd = true;
    },

    //添加信息
    add() {
      this.dialogFormVisibleAdd = false;
      Axios({
        url: '/application/purchase/add.action',
        method: "post",
        data: {
          form: this.form,
          goodsIds:this.selectIds
        },
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

    //选中行变化
    selectionChange(){
      this.updateSelectedAndCounts()
    },

    //总计
    countChange(){
      this.updateSelectedAndCounts();
    },

    select(){
      this.updateSelectedAndCounts()
    },

    updateSelectedAndCounts(){
      let selecteds = [];
      let total = 0;
      let items = this.$refs["addTableData"].selection;
      for (let i = 0; i < items.length; i++) {
        selecteds.push({
          goodsId:items[i].goodsId,
          count: items[i].count,
        });
        total += items[i].count*items[i].goodsInPrice;
      }
      this.selectIds = selecteds;
      this.form.sum = total.toFixed(2);
    },
    xq(row){
      this.dialogXQ=true;
      Axios({
        url: '/application/purchase/queryByPurchaseId.action',
        method: "get",
        params: {
          id:row.id
        },
      }).then((result) => {
        this.goods=result.data;
      }).catch((error) => {
        alert(error);
      })
    }

  },

  created() {
    this.getData();
    this.getAddData();
    this.getGoodsType();
    this.getSupplier();
  }
}
</script>

<style scoped>

</style>