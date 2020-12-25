<template>
    <div id="sczdsum">
        <el-row>
            <el-col :span="4">
<!--                <el-input placeholder="请输入商户名" clearable style="width: 300px;margin-right: 1100px" v-model="querysh_name" @change="selectname">-->
<!--                    <template slot="prepend">商户名</template>-->
<!--                </el-input>-->
            </el-col>
            <el-col :span="2">
                <el-button type="primary" @click="dialogAddsczdsum = true">添加</el-button>
            </el-col>
        </el-row>

        <el-table :data="sczdsumtable" border style="width: 100%"
            @selection-change="selectionchange">
            <el-table-column type="selection" width="55">
            </el-table-column>
            <el-table-column prop="sczd_id" label="ID" width="180">
            </el-table-column>
            <el-table-column prop="sczd_time" label="营收时间" width="180">
            </el-table-column>
            <el-table-column prop="sczd_money" label="总店营收" width="180">
            </el-table-column>
            <el-table-column prop="" label="操作" width="380">
                <template slot-scope="scope">
                    <el-button type="primary" icon="el-icon-edit" @click="updsczdsum1(scope.row)">修改</el-button>
                    <el-popconfirm icon="el-icon-info"
                                   icon-color="red"
                                   title="确定删除吗？"
                                   @confirm="delsczdsum(scope.row)">
                        <el-button slot="reference" type="danger" icon="el-icon-delete">删除</el-button>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination
                background
                layout="total, sizes, prev, pager, next, jumper"
                :page-sizes="[5, 10, 15, 20]"
                :page-size="size"
                :total="total"
                :current-page="currentpage"
                @prev-click="prvpage"
                @next-click="nextpage"
                @current-change="currentchange"
                @size-change="sizechange">
        </el-pagination>
        <!--添加对话框-->
        <el-dialog title="提示"
                   :visible.sync="dialogAddsczdsum"
                   width="30%">
            <el-form :model="addform" label-width="80px">
                <el-form-item label="总店营收时间">
                    <el-date-picker value-format="yyyy-MM-dd"
                                    v-model="addform.addsczd_time"
                                    type="date"
                                    placeholder="选择日期">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="金额">
                    <el-input v-model="addform.addsczdsum_money"></el-input>
                </el-form-item>

            </el-form >
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogAddsczdsum = false">取 消</el-button>
                <el-button type="primary" @click="addsczdsum">确 定</el-button>
            </div>
        </el-dialog>
        <!--修改对话框-->
        <el-dialog title="提示"
                   :visible.sync="dialogUpdsczdsum"
                   width="30%">
            <el-form :model="updform" label-width="80px">
                <el-form-item label="总店营收时间">
                    <el-date-picker value-format="yyyy-MM-dd"
                                    v-model="updform.updsczd_time"
                                    type="date"
                                    placeholder="选择日期">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="总店营收">
                    <el-input v-model="updform.updsczdsum_money"></el-input>
                </el-form-item>
            </el-form >
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogUpdsczdsum = false">取 消</el-button>
                <el-button type="primary" @click="updsczdsum2">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: 'sczdsum',
        data(){
            return{
                sczdsumtable:[],
                pageindex:1,//当前显示页面
                totalpage:0,//总页面
                total:0,  //总条目数
                size:5,  //每页显示多少条
                currentpage:1,
                dialogAddsczdsum:false,
                dialogUpdsczdsum:false,
                addform:{
                    addsczd_time:'',
                    addsczdsum_money:''
                },
                updform:{
                    updsczdsum_id:0,
                    updsczd_time:'',
                    updsczdsum_money:''
                },
                selectid:"", //复选框选中的id
            }
        },
        methods:{
            getsczdsum(){
                var _this=this;
                var params=new URLSearchParams();
                // params.append("sh_name",_this.querysh_name);
                params.append("page",_this.pageindex);
                params.append("rows",_this.size);
                this.$axios.post("/application/SCZDsumPageVo.do",params).then(function (result) {
                    _this.sczdsumtable=result.data.rows;
                        //计算总页数
                    _this.total=result.data.total;
                    _this.totalpage=_this.total%_this.size==0?_this.total/_this.size:Math.floor(_this.total/_this.size)+1
                }).
                catch(function (error) {
                    console.log(error)
                })
            },
            addsczdsum(){
                var _this = this;
                var params = new URLSearchParams();
                params.append("sczd_time",_this.addform.addsczd_time);
                params.append("sczd_money",_this.addform.addsczdsum_money);
                this.$axios.post("/application/addSCZDsum.do",params).then(function (result) {  //成功  执行then里面的方法
                        _this.$message({
                            message: result.data.msg,
                            type: 'success'
                        });

                }).catch(function (error) { //失败 执行catch方法
                    console.log(error)
                });
                _this.getsczdsum();
                _this.dialogAddsczdsum=false;
            },
            delsczdsum({sczd_id:id}){
                var _this=this;
                this.$axios.get("/application/deleteSCZDsum.do",{params:{id}}).
                then(function (result) {
                        _this.$message({
                            message: result.data.msg,
                            type: 'success'
                        });

                }).
                catch(function (error) {
                    console.log(error)
                });
                _this.getsczdsum();
            },
            updsczdsum1(row){
                this.dialogUpdsczdsum=true;
                this.updform.updsczdsum_id=row.sczd_id;
                this.updform.updsczd_time=row.sczd_time;
                this.updform.updsczdsum_money=row.sczd_money;
            },
            updsczdsum2(){
                var _this=this;
                var params=new URLSearchParams();
                params.append("sczd_id",_this.updform.updsczdsum_id);
                params.append("sczd_time",_this.updform.updsczd_time);
                params.append("sczd_money",_this.updform.updsczdsum_money);
                this.$axios.post("/application/updateSCZDsum.do",params).
                then(function (result) {
                        _this.$message({
                            message: result.data.msg,
                            type: 'success'
                        });
                }).
                catch(function (error) {
                    console.log(error)
                });
                _this.getsczdsum();
                _this.dialogUpdsczdsum=false;
            },
            //上一页
            prvpage() {
                this.pageindex=this.pageindex==1?1:this.pageindex-1;
                this.getsczdsum();
            },
            //下一页
            nextpage() {
                this.pageindex=this.pageindex==this.totalpage?this.totalpage:this.pageindex+1;
                this.getsczdsum();
            },
            //当前第一页
            currentchange(val){
                this.pageindex=val;
                this.getsczdsum();
            },
            //每页多少条
            sizechange(val){
                this.size=val;
                this.getsczdsum();
            },
            // //通过条件查询
            // selectname(){
            //     this.getsh();
            // },
            //复选框选中
            selectionchange(val){
                this.selectid="";
                for(var i=0;i<val.length;i++){
                    this.selectid+=val[i].sczd_id+",";
                }
                console.log(this.selectid)

            }

        },
        created:function(){
            this.getsczdsum();
        }
    }
</script>

