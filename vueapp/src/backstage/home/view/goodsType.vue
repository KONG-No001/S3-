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

      <el-col :span="2">
        <el-button type="primary" @click="addBtn">添加</el-button>
      </el-col>
      <el-col :span="2">
        <el-button type="danger" @click="dels">批量删除</el-button>
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
          prop="goodsTypeId"
          label="ID"
          width="50">
      </el-table-column>
      <el-table-column
          prop="goodsTypeName"
          label="名称">
      </el-table-column>
      <el-table-column
          prop="goodsTypeRemark"
          label="备注">
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

    <!-- 添加模态框-->
    <el-dialog title="添加" :visible.sync="dialogFormVisibleAdd">
      <el-form :inline="true" :model="form" ref="form" size="medium" label-width="100px" class="demo-ruleForm">
        <el-form-item label="名称" prop="goodsTypeName">
          <el-input v-model="form.goodsTypeName"></el-input>
        </el-form-item>
        <el-form-item label="备注" prop="goodsTypeRemark">
          <el-input v-model="form.goodsTypeRemark"></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisibleAdd = false">取 消</el-button>
        <el-button type="primary" @click="add">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 编辑模态框-->
    <el-dialog title="编辑" :visible.sync="dialogFormVisible">
      <el-form :inline="true" :model="form" ref="form" size="medium" label-width="100px" class="demo-ruleForm">
        <el-form-item label="ID" prop="goodsTypeId">
          <el-input v-model="form.goodsTypeId" readonly></el-input>
        </el-form-item>
        <el-form-item label="名称" prop="goodsTypeName">
          <el-input v-model="form.goodsTypeName"></el-input>
        </el-form-item>
        <el-form-item label="备注" prop="goodsTypeRemark">
          <el-input v-model="form.goodsTypeRemark"></el-input>
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
      dialogFormVisibleAdd: false,
      form: {
        goodsTypeId: '', goodsTypeName: '', goodsTypeRemark: ''
      },
      total: 0,
      pages: 0,
      row: 5,
      page: 1,
      name:'',
      selectIds:[]
    }
  },

  methods: {
    //获取表格数据
    getData: function () {
      Axios({
        url: '/application/goodsType/fenYe.action',
        method: "get",
        params: {
          goodsTypeName: this.name,
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
      this.name='';
    },

    //点击添加按钮
    addBtn() {
      this.form = {};
      this.dialogFormVisibleAdd = true;
    },

    //添加信息
    add() {
      this.dialogFormVisibleAdd = false;
      Axios({
        url: '/application/goodsType/add.action',
        method: "post",
        params: this.form,
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
    editBtn(row) {
      this.dialogFormVisible = true;
      this.form = row;
    },

    //修改信息
    edit() {
      this.dialogFormVisible = false;
      Axios({
        url: '/application/goodsType/update.action',
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
        url: '/application/goodsType/delete.action',
        method: "get",
        params: {goodsTypeId: row.goodsTypeId},
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
        this.selectIds.push(val[i].goodsTypeId)
      }
    },

    //批量删除
    dels() {
      Axios({
        url: '/application/goodsType/deleteByIds.action',
        method: "get",
        params: {goodsTypeIds:this.selectIds.toLocaleString()},
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