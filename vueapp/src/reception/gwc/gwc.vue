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
              <router-link to="/shouye"> 首页</router-link>
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
                <template slot-scope="scope">
                  <span>[{{scope.row.warehouseVo.goodsVo.goodsBrand}}]{{scope.row.warehouseVo.name}}</span>
                  <img style="height: 150px;width: 150px;" :src="scope.row.warehouseVo.goodsVo.goodsImg" class="image">
                </template>
            </el-table-column>
            <el-table-column label="单价">
                <template slot-scope="scope">${{ scope.row.warehouseVo.goodsOutPrice }}</template>
            </el-table-column>
            <el-table-column label="数量">
                <template slot-scope="scope">
                    <el-input-number @change="js(scope.row.shu,scope.row.id)" v-model="scope.row.shu" :min="1"></el-input-number>
                </template>
            </el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                  <el-popconfirm icon="el-icon-info"
                                 icon-color="red"
                                 title="确定删除吗？"
                                 @confirm="del(scope.row.id)">
                    <el-button slot="reference" type="danger" icon="el-icon-delete"></el-button>
                  </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>
        <el-row>
            <span>已选商品：{{dsj}}件,合计：￥{{zojia}}元</span>
          <el-button style="margin-left: 1000px" @click="gm">结算</el-button>
        </el-row>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                tableData: [],
                zojia:0,
                dsj:0,
              wid:"",
              count:""
            }
        },

    created() {
      this.getsp();
    },
        methods: {
            handleSelectionChange(val) {
              this.wid="";
              this.count="";
                this.dsj=val.length;
                var jia=0;
                for(var i=0;i<val.length;i++){
                    jia=jia+val[i].shu*val[i].warehouseVo.goodsOutPrice;
                    this.wid=this.wid+val[i].warehouseVo.id+",";
                    this.count=this.count+val[i].shu+",";
                }
                this.zojia=jia;
            },
            js(shu,id){
                // 这里的 multipleTable 是上面ref的值。 直接访问子组件实例上的属性。
                var val=this.$refs.multipleTable.selection;
                var jia=0;
                for(var i=0;i<val.length;i++){
                    jia=jia+val[i].shu*val[i].warehouseVo.goodsOutPrice;
                }
                this.zojia=jia;
              var _this = this;
              var params = new URLSearchParams();
              params.append('id',id);
              params.append('sl',shu);
              this.
              $axios.post('/application/goodscar/updateGoodsCar.action',params).
              then(function(result) {
                console.log(result.data)
                _this.getsp();
              }).
              catch(function(error) {
                alert(error)
              });
            },
          getsp(){
            var _this = this;
            var params = new URLSearchParams();
            params.append('uid',1);
            this.
            $axios.post('/application/goodscar/queryByUid.action',params).
            then(function(result) {
              console.log(result)
              _this.tableData = result.data;
            }).
            catch(function(error) {
              alert(error)
            });
          },
          del(id){
            var _this = this;
            var params = new URLSearchParams();
            params.append('id',id);
            this.
            $axios.post('/application/goodscar/delGoodsCar.action',params).
            then(function(result) {
              console.log(result)
              _this.$message({
                message: result.data ,
                type: 'success'
              });
              _this.getsp();
            }).
            catch(function(error) {
              alert(error)
            });
          },
          gm(){
            var _this = this;
            if(_this.dsj==0){
              alert("至少选中一个");
              return;
            }
            var params = new URLSearchParams();
            params.append("User.id",1);
            params.append("Shanghu.id",1);
            params.append("count",_this.dsj);
            params.append("sum",_this.zojia);
            params.append("shu",_this.count);
            params.append("wid",_this.wid);
            this.
            $axios.post('/application/dingdan/addDingDan.action',params).
            then(function(result) {
              _this.$router.push({name:"dingdan",params:{id:result.data}});
            }).
            catch(function(error) {
              alert(error)
            });
          }
        }
    }
</script>

<style scoped>

</style>