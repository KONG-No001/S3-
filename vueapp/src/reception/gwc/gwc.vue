<template>
    <div>
      <!--    顶部菜单栏    -->
      <el-header class="el-header">
        <el-menu default-active="4"
                 background-color="#545c64"
                 text-color="#fff"
                 active-text-color="#ffd04b" class="el-menu-demo" mode="horizontal">
          <el-menu-item index="1">
            <template slot="title">
              <span>首页</span>
            </template>
          </el-menu-item>
          <el-menu-item index="2">
            <router-link to="/spfl">商品分类</router-link>
          </el-menu-item>
          <el-submenu index="3">
            <template slot="title">我的</template>
            <el-menu-item index="3-1">个人信息</el-menu-item>
            <el-menu-item index="3-2">注册商户</el-menu-item>
            <el-menu-item index="3-3">我的订单</el-menu-item>
            <el-menu-item index="3-4">注销账号</el-menu-item>
          </el-submenu>
          <el-menu-item index="4" route="true">
            <router-link to="/gwc"> 购物车</router-link>
          </el-menu-item>
        </el-menu>
      </el-header>
        <el-table :data="tableData"
                  ref="multipleTable"
                height="400"
                  style="width: 100%"
                  @selection-change="handleSelectionChange">
            <el-table-column
                    type="selection"
                    width="55">
            </el-table-column>
            <el-table-column label="商品信息">
                <template slot-scope="scope">{{ scope.row.spxx }}</template>
            </el-table-column>
            <el-table-column label="单价">
                <template slot-scope="scope">${{ scope.row.danjia }}</template>
            </el-table-column>
            <el-table-column label="数量">
                <template slot-scope="scope">
                    <el-input-number @change="js" v-model="scope.row.sl" :min="1"></el-input-number>
                </template>
            </el-table-column>
            <el-table-column label="操作">
                <template>
                    <el-button >删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-row>
            <el-button>删除</el-button>
            已选商品：{{dsj}}件,合计：￥{{zojia}}元
        </el-row>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                tableData: [{
                    spxx: '商品信息',
                    danjia: 50,
                    sl:0
                }, {
                    spxx: '商品信息',
                    danjia: 50,
                    sl:0
                }, {
                    spxx: '商品信息',
                    danjia: 50,
                    sl:0
                }, {
                    spxx: '商品信息',
                    danjia: 50,
                    sl:0
                }, {
                    spxx: '商品信息',
                    danjia: 50,
                    sl:0
                }, {
                    spxx: '商品信息',
                    danjia: 50,
                    sl:0
                }, {
                    spxx: '商品信息',
                    danjia: 50,
                    sl:0
                }],
                zojia:0,
                dsj:0
            }
        },

        methods: {
            handleSelectionChange(val) {
                this.dsj=val.length;
                var jia=0;
                for(var i=0;i<val.length;i++){
                    jia=jia+val[i].sl*val[i].danjia;
                }
                this.zojia=jia;
            },
            js(){
                // 这里的 multipleTable 是上面ref的值。 直接访问子组件实例上的属性。
                var val=this.$refs.multipleTable.selection;
                var jia=0;
                for(var i=0;i<val.length;i++){
                    jia=jia+val[i].sl*val[i].danjia;
                }
                this.zojia=jia;
            }
        }
    }
</script>

<style scoped>

</style>