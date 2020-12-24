<template>
    <div id="shanghu">
        <el-row>
            <el-col :span="4">
                <el-input placeholder="请输入商户名" clearable style="width: 250px;margin-right: 1100px" v-model="querysh_name" @change="selectname">
                    <template slot="prepend">商户名</template>
                </el-input>
            </el-col>
            <el-col :span="2">
                <el-button type="primary" @click="dialogAddsh = true">添加</el-button>
            </el-col>
        </el-row>

        <el-table :data="shtable" border style="width: 100%"
            @selection-change="selectionchange">
            <el-table-column type="selection" width="55">
            </el-table-column>
            <el-table-column prop="sh_id" label="ID" width="180">
            </el-table-column>
            <el-table-column prop="sh_name" label="商户名" width="180">
            </el-table-column>
            <el-table-column prop="sh_phone" label="商户电话" width="180">
            </el-table-column>
            <el-table-column
                    prop="sh_image"
                    label="图片">
                <template slot-scope="scope">
                    <img  :src="scope.row.sh_image" style="width: 30px"/>
                </template>
            </el-table-column>
            <el-table-column prop="sh_address" label="详细地址" width="180">
            </el-table-column>
            <el-table-column prop="sh_createtime" label="商户创造时间" width="180">
            </el-table-column>
            <el-table-column prop="" label="操作" width="380">
                <template slot-scope="scope">
                    <el-button type="primary" icon="el-icon-edit" @click="updsh1(scope.row)">修改</el-button>
                    <el-popconfirm icon="el-icon-info"
                                   icon-color="red"
                                   title="确定删除吗？"
                                   @confirm="delsh(scope.row)">
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
                   :visible.sync="dialogAddsh"
                   width="30%">
            <el-form :model="addform" label-width="80px">
                <el-form-item label="商户名">
                    <el-input v-model="addform.addsh_name"></el-input>
                </el-form-item>
                <el-form-item label="商户电话">
                    <el-input v-model="addform.addsh_phone"></el-input>
                </el-form-item>
                <el-form-item label="商户图片" >
                    <input type="file" @change="addgetFile($event)">
                </el-form-item>
                <el-form-item label="详细地址">
                    <el-input v-model="addform.addsh_address"></el-input>
                </el-form-item>
                <el-form-item label="商户创建时间">
                    <el-date-picker value-format="yyyy-MM-dd"
                                    v-model="addform.addsh_createtime"
                                    type="date"
                                    placeholder="选择日期">
                    </el-date-picker>
                </el-form-item>
            </el-form >
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogAddsh = false">取 消</el-button>
                <el-button type="primary" @click="addsh">确 定</el-button>
            </div>
        </el-dialog>
        <!--修改对话框-->
        <el-dialog title="提示"
                   :visible.sync="dialogUpdsh"
                   width="30%">
            <el-form :model="updform" label-width="80px">
                <el-form-item label="商户名">
                    <el-input v-model="updform.updsh_name"></el-input>
                </el-form-item>
                <el-form-item label="商户电话">
                    <el-input v-model="updform.updsh_phone"></el-input>
                </el-form-item>
                <el-form-item label="商户图片" >
                    <input type="file" @change="updgetFile($event)">
                </el-form-item>
                <el-form-item label="详细地址">
                    <el-input v-model="updform.updsh_address"></el-input>
                </el-form-item>
                <el-form-item label="生日">
                    <el-date-picker value-format="yyyy-MM-dd"
                                    v-model="updform.updsh_createtime"
                                    type="date"
                                    placeholder="选择日期">
                    </el-date-picker>
                </el-form-item>
            </el-form >
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogUpdsh = false">取 消</el-button>
                <el-button type="primary" @click="updsh2">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import Axios from "axios";

    export default {
        name: 'shanghu',
        data(){
            return{
                shtable:[],
                pageindex:1,//当前显示页面
                totalpage:0,//总页面
                total:0,  //总条目数
                size:5,  //每页显示多少条
                currentpage:1,
                dialogAddsh:false,
                dialogUpdsh:false,
                addform:{
                    addsh_name:'',
                    addsh_phone:'',
                    addsh_image:'',
                    addsh_address:'',
                    addsh_createtime:''
                },
                updform:{
                    updsh_id:0,
                    updsh_name:'',
                    updsh_phone:'',
                    updsh_image:'',
                    updsh_address:'',
                    updsh_createtime:''
                },
                querysh_name:'',
                selectid:"", //复选框选中的id
            }
        },
        methods:{
            getsh: function () {
                this.$axios({
                    url: '/application/ShanghuPageVo.do',
                    method: "get",
                    params: {
                        // goodsName: this.goodsName,
                        // goodsTypeId: this.goodsTypeId,
                        page: this.pageindex, rows: this.size
                    },
                }).then((result) => {
                    this.shtable=result.data.rows;
                    this.total=result.data.total;
                    this.totalpage=this.total%this.size==0?this.total/this.size:Math.floor(this.total/this.size)+1
                }).catch((error) => {
                    alert(error);
                })
            },
            addsh(){
                this.dialogAddsh=false;
                let formData=new FormData();
                Object.keys(this.addform).forEach((key) => {
                    formData.append(key, this.addform[key]);
                });

                Axios({
                    url: '/application/addShanghu.do',
                    method: "post",
                    data:formData,
                    headers: {
                        'Content-Type':'multipart/form-data'
                    }
                }).then((result) => {
                    this.$notify({
                        title: '提示',
                        message: result.data == true ? '添加成功' : '添加失败',
                        type: 'success'
                    });
                    this.getsh();
                }).catch((error) => {
                    alert(error);
                })
            },
            delsh({sh_id:id}){
                var _this=this;
                this.$axios.get("/application/deleteShanghu.do",{params:{id}}).
                then(function (result) {
                        _this.$message({
                            message: result.data.msg,
                            type: 'success'
                        });
                }).
                catch(function (error) {
                    console.log(error)
                });
                _this.getsh();
            },
            updsh1(row){
                this.dialogUpdsh=true;
                this.updform = row;
            },
            updsh2(){
                this.dialogUpdsh=false;
                let  formData = new FormData();
                Object.keys(this.updform).forEach((key) => {
                    formData.append(key, this.updform[key]);
                });
                Axios({
                    url: '/application/updateShanghu.do',
                    method: "post",
                    data:formData,
                    headers: {
                        'Content-Type':'multipart/form-data'
                    }
                }).then((result) => {
                    this.$notify({
                        title: '提示',
                        message: result.data == true ? '修改成功' : '修改失败',
                        type: 'success'
                    });
                    this.getsh();
                }).catch((error) => {
                    alert(error);
                })
            },
            addgetFile: function (event) {
                this.addform.addsh_image = event.target.files[0];
            },
            updgetFile: function (event) {
                this.updform.updsh_image = event.target.files[0];
            },
            //上一页
            prvpage() {
                this.pageindex=this.pageindex==1?1:this.pageindex-1;
                this.getsh();
            },
            //下一页
            nextpage() {
                this.pageindex=this.pageindex==this.totalpage?this.totalpage:this.pageindex+1;
                this.getsh();
            },
            //当前第一页
            currentchange(val){
                this.pageindex=val;
                this.getsh();
            },
            //每页多少条
            sizechange(val){
                this.size=val;
                this.getsh();
            },
            //通过条件查询
            selectname(){
                this.getsh();
            },
            //复选框选中
            selectionchange(val){
                this.selectid="";
                for(var i=0;i<val.length;i++){
                    this.selectid+=val[i].sh_id+",";
                }
                console.log(this.selectid)

            }

        },
        created:function(){
            this.getsh();
        }
    }
</script>

