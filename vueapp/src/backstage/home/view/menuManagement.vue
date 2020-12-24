<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="8">
                <el-card style="width: 100%;height: 540px;" body-style="height:445px;padding:0">
                    <template slot="header">
                        <span style="font-size: 18px;color: #303133;line-height: 24px">菜单树</span>
                        <span style="float: right">
                            <el-button type="text" :loading="tableLoading" icon="el-icon-refresh-right" size="mini" @click="refreshMenuTree()"></el-button>
                            <el-button type="text" size="mini" @click="addMenuRootNode">添加根</el-button>
                        </span>
                    </template>
                    <template>
                        <el-scrollbar slot="default" style="height: 100%;border-bottom: 1px solid #EBEEF5;" wrap-style="overflow: hidden scroll;">
                            <div style="width: 99%">
                                <el-tree :data="menuTree"
                                         node-key="id"
                                         lazy
                                         :load="loadingMenuNode"
                                         :default-expanded-keys="defaultExpandedKeys"
                                         @node-collapse="collapseMenuNode"
                                         @node-click="showMenuNodeInfoPage"
                                >
                                    <span class="custom-tree-node"  slot-scope="{node,data}">
                                        <span>
                                            <span style="font-size: 13px;">{{node.label}}</span>
                                        </span>
                                        <span>
                                            <el-button type="text" size="mini" @click.stop="showAppendMenuNodePage(data)">追加</el-button>
                                            <el-button type="text" size="mini" @click.stop="showUpdateMenuNodePage(data)">更改</el-button>
                                            <el-button type="text" size="mini" @click.stop="deleteMenuNode(data)">删除</el-button>
                                        </span>
                                    </span>
                                </el-tree>
                            </div>
                        </el-scrollbar>
                    </template>
                </el-card>
            </el-col>
            <el-col :span="16">
                <el-card style="width: 100%;height: 540px;" body-style="height:445px;padding:0">
                    <template v-if="pageProps.type==='form'" >
                        <template slot="header">
                            <el-page-header @back="doGoBack" :content="pageProps.title"></el-page-header>
                        </template>
                        <template>
                            <template v-if="menuFormPageData=== undefined ||menuFormPageData==null || Object.keys(menuFormPageData).length===0">
                                <div style="width: 100%; height: 445px; display: flex; justify-content:center; align-items: center">
                                    <span style="color: #606266;font-size: 20px;">你不应该看到这条信息的(っ °Д °;)っ</span>
                                </div>
                            </template>
                            <el-scrollbar v-loading="pageLoading" v-else slot="default" style="height: 100%;border-bottom: 1px solid #EBEEF5;" wrap-style="overflow: hidden scroll;">
                                <el-form style="margin: 10px 20px" ref="menuForm" :rules="menuFormPageRules" :model="menuFormPageData" label-width="100px" label-position="left">
                                    <el-form-item label="字段:" prop="field">
                                        <el-input v-model="menuFormPageData.field" ></el-input>
                                    </el-form-item>
                                    <el-form-item label="标题:" prop="title">
                                        <el-input v-model="menuFormPageData.title"></el-input>
                                    </el-form-item>
                                    <el-form-item label="路由地址:" prop="path">
                                        <el-input v-model="menuFormPageData.path"></el-input>
                                    </el-form-item>
                                    <el-form-item label="组件地址:" prop="url">
                                        <el-input v-model="menuFormPageData.url"></el-input>
                                    </el-form-item>
                                    <el-form-item label="图标:" prop="icon">
                                        <el-row>
                                            <el-col :span="2">
                                                <div class="demo">
                                                    <i :class="menuFormPageData.icon+' menu-form-icon-i'"></i>
                                                </div>

                                            </el-col>
                                            <el-col :span="22">
                                                <el-autocomplete
                                                        v-model="menuFormPageData.icon"
                                                        :fetch-suggestions="queryIconClassSearch"
                                                        style="width: 100%"
                                                        clearable
                                                >
                                                    <template slot-scope="{item}">
                                                        <span style="float:left">
                                                            <span v-for="(span,index) in splitIconClassValue(item.value,menuFormPageData.icon)" :key="index" >
                                                                <span v-if="index===1" style="color: #409EFF;font-weight:bold;">{{span}}</span>
                                                                <span v-else>{{span}}</span>
                                                            </span>
                                                        </span>
                                                        <span>
                                                            <i :class="item.value" class="menu-form-icon-search-icon-i"></i>
                                                        </span>
                                                    </template>
                                                </el-autocomplete>
                                            </el-col>
                                        </el-row>
                                    </el-form-item>
                                    <el-form-item label="父节点:">
                                        <span style="line-height: 40px ">{{menuFormPageData.print}}</span>
                                    </el-form-item>
                                    <el-form-item label="节点地址:">
                                        <span style="line-height: 40px ">{{menuFormPageData.prints}}/<span v-if="menuFormPageData.field">{{menuFormPageData.id}}</span><span v-else>#</span></span>
                                    </el-form-item>
                                    <el-form-item label="下标:">
                                        <span style="line-height: 40px ">{{menuFormPageData.i}}</span>
                                    </el-form-item>
                                    <el-form-item label="层级:">
                                        <span style="line-height: 40px ">{{menuFormPageData.level}}</span>
                                    </el-form-item>
                                    <el-form-item v-if="pageProps.form==='update'" label="是否为父类:">
                                        <el-tag size="small" :type="menuFormPageData.isParent===1?'success':'danger'" effect="dark" >{{menuFormPageData.isParent===1}}</el-tag>
                                    </el-form-item>
                                </el-form>
                                <el-row class="menu-info-row">
                                    <el-col :span="24">
                                    <span style="float: right">
                                        <el-button v-if="pageProps.form==='add'" type="success" size="small">确认</el-button>
                                        <el-button v-if="pageProps.form==='append'" type="success" size="small" @click="doAppendMenuNodeAction">确认</el-button>
                                        <el-button v-if="pageProps.form==='update'" type="success" size="small" @click="doUpdateMenuNodeAction">确认</el-button>
                                    </span>
                                    </el-col>
                                </el-row>
                            </el-scrollbar>
                        </template>
                    </template>
                    <template v-else-if="pageProps.type==='info'">
                        <template slot="header">
                            <el-page-header @back="doClearPage" :content="pageProps.title"></el-page-header>
                        </template>
                        <template v-if="menuInfoPageData=== undefined ||menuInfoPageData==null || Object.keys(menuInfoPageData).length===0">
                            <div style="width: 100%; height: 445px; display: flex; justify-content:center; align-items: center">
                                <span style="color: #606266;font-size: 20px;">你不应该看到这条信息的(っ °Д °;)っ</span>
                            </div>
                        </template>
                        <el-scrollbar v-loading="pageLoading" v-else slot="default" style="height: 100%;border-bottom: 1px solid #EBEEF5;" wrap-style="overflow: hidden scroll;">
                            <el-row class="menu-info-row">
                                <el-col :span="3">编号</el-col>
                                <el-col :span="21">
                                    {{menuInfoPageData.id}}
                                </el-col>
                            </el-row>
                            <el-row class="menu-info-row">
                                <el-col :span="3">字段</el-col>
                                <el-col :span="21">{{menuInfoPageData.field}}</el-col>
                            </el-row>
                            <el-row class="menu-info-row">
                                <el-col :span="3">标题</el-col>
                                <el-col :span="21">{{menuInfoPageData.title}}</el-col>
                            </el-row>
                            <el-row class="menu-info-row">
                                <el-col :span="3">路由地址</el-col>
                                <el-col :span="21">{{menuInfoPageData.path}}</el-col>
                            </el-row>
                            <el-row class="menu-info-row">
                                <el-col :span="3">组件地址</el-col>
                                <el-col :span="21">{{menuInfoPageData.url}}</el-col>
                            </el-row>
                            <el-row class="menu-info-row" style="margin: 0 20px; margin-top: -10px">
                                <el-col :span="3">
                                    <div style="height: 40px">
                                        <span style="line-height: 40px">图标</span>
                                    </div>
                                </el-col>
                                <el-col :span="21">
                                    <el-row :gutter="20">
                                        <el-col :span="2"><i :class="menuInfoPageData.icon+' menu-info-icon-i'"></i></el-col>
                                        <el-col :span="22"><span style="line-height: 40px ">{{menuInfoPageData.icon}}</span></el-col>
                                    </el-row>
                                </el-col>
                            </el-row>
                            <el-row class="menu-info-row">
                                <el-col :span="3">父节点</el-col>
                                <el-col :span="21">{{menuInfoPageData.print}}</el-col>
                            </el-row>
                            <el-row class="menu-info-row">
                                <el-col :span="3">节点地址</el-col>
                                <el-col :span="21">{{menuInfoPageData.prints}}/{{menuInfoPageData.id}}</el-col>
                            </el-row>
                            <el-row class="menu-info-row">
                                <el-col :span="3">下标</el-col>
                                <el-col :span="21">{{menuInfoPageData.i}}</el-col>
                            </el-row>
                            <el-row class="menu-info-row">
                                <el-col :span="3">层级</el-col>
                                <el-col :span="21">{{menuInfoPageData.level}}</el-col>
                            </el-row>
                            <el-row class="menu-info-row">
                                <el-col :span="3">是否为父类</el-col>
                                <el-col :span="21">
                                    <el-tag size="small" :type="menuInfoPageData.isParent===1?'success':'danger'" effect="dark" >{{menuInfoPageData.isParent===1}}</el-tag>
                                </el-col>
                            </el-row>
                            <el-row class="menu-info-row">
                                <el-col :span="24">
                                    <span style="float: right">
                                        <el-button type="primary" size="small" @click="showAppendMenuNodePage({id:menuInfoPageData.id,label:menuInfoPageData.title})">追加</el-button>
                                        <el-button type="warning" size="small" @click="showUpdateMenuNodePage({id:menuInfoPageData.id,label:menuInfoPageData.title})">更改</el-button>
                                        <el-button type="danger" size="small" @click="deleteMenuNode(menuInfoPageData)">删除</el-button>
                                    </span>
                                </el-col>
                            </el-row>
                        </el-scrollbar>
                    </template>
                    <template v-else>
                        <div style="width: 100%; height: 540px; display: flex; justify-content:center; align-items: center">
                            <span style="color: #606266;font-size: 20px;">你还没有选择数据...</span>
                        </div>
                    </template>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: "menuManagement",
        data(){
            return {
                tableLoading: false,
                treeProps:{},
                defaultExpandedKeys:[],
                menuTree:[],
                pageProps:{
                    type:"",
                    title:"",
                    form:"",
                    doBack:{},
                },
                menuInfoPageData:null,
                menuFormPageRules:{
                    field:[
                        {required:"true",message: '该属性是必须输入的'}
                    ],
                    title:[
                        {required:"true",message: '该属性是必须输入的'}
                    ],
                    path:[
                        {required:"true",message: '该属性是必须输入的'}
                    ],
                    url:[
                        {required:"true",message: '该属性是必须输入的'}
                    ],
                    icon:[
                        {required:"true",message: '该属性是必须输入的'}
                    ],
                },
                menuFormPageData:null,
                pageLoading:true,
            }
        },
        methods:{
            refreshMenuTree(){
                let _this = this;
                _this.tableLoading=true
                _this.$axios.get("/application/menu/listMenuTree.action",{
                    params:{id:0}
                }).then(response=>{
                    _this.menuTree = response.data;
                    _this.tableLoading=false
                })
            },
            loadingMenuNode(node, resolve){
                let _this = this;
                let id = 0
                if (node.level !== 0){
                    id = node.data.id;
                    if(_this.defaultExpandedKeys.indexOf(id)===-1)_this.defaultExpandedKeys.push(id);
                }
                _this.$axios.get("/application/menu/listMenuTree.action",{
                    params:{id}
                }).then(response=>{
                    resolve(response.data);
                })
            },
            collapseMenuNode(data){
                let _this = this;
                let index = _this.defaultExpandedKeys.indexOf(data.id);
                _this.defaultExpandedKeys.splice(index,1)
            },
            showMenuNodeInfoPage({id,label}){
                let _this = this;
                _this.pageProps.type = "info";
                _this.pageProps.title = label;
                _this.pageLoading = true;
                _this.$axios.get("/application/menu/getMenu.action",{
                    params:{id}
                }).then(response=>{
                    _this.menuInfoPageData = response.data;
                    _this.pageLoading = false;
                })
            },
            showAppendMenuNodePage({id,label}){
                let _this = this;
                _this.pageProps.type = "form";
                _this.pageProps.form = "append";
                _this.pageProps.title = label+" / 追加节点";
                _this.pageProps.doBack = {id,label};
                _this.pageLoading = true;
                _this.$axios.get("/application/menu/getAppendInfo.action",{
                    params:{id}
                }).then(response=>{
                    _this.menuFormPageData = response.data
                    _this.pageLoading = false;
                })
            },
            showUpdateMenuNodePage({id,label}){
                let _this = this;
                _this.pageProps.type = "form";
                _this.pageProps.form = "update";
                _this.pageProps.title = label+" / 更改节点";
                _this.pageProps.doBack = {id,label};
                _this.pageLoading = true;
                _this.$axios.get("/application/menu/getUpdateInfo.action",{
                    params:{id}
                }).then(response=>{
                    _this.menuFormPageData = response.data
                    _this.pageLoading = false;
                })
            },
            addMenuRootNode(){
                console.log("AddRoot")
            },
            deleteMenuNode({id}){
                let _this = this;
                _this.$confirm("此操作将永久删除该节点以及该节点下的所有字节点, 是否继续?","确认删除",{
                    confirmButtonText: "确认",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(()=>{
                    const loading = _this.$loading({
                        lock: true,
                        text: "Loading",
                        spinner: 'el-icon-loading',
                        background: 'rgba(0, 0, 0, 0.7)'
                    });
                    _this.$axios.post("/application/menu/deleteMenu.action",{id}).then(response=>{
                        if(response.data){
                            _this.$message({
                                message: "删除成功！！！",
                                type: "success"
                            });
                            _this.refreshMenuTree();
                            _this.doClearPage();
                        }else {
                            _this.$message({
                                message:"删除失败",
                                type: "error"
                            });
                        }
                        loading.close();
                    }).catch(() => {
                        this.$message({
                            type: 'error',
                            message: '执行异常!'
                        });
                        loading.close();
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });

            },
            doGoBack(){
                this.showMenuNodeInfoPage(this.pageProps.doBack)
            },
            doAppendMenuNodeAction(){
                let _this = this;
                let data = _this.menuFormPageData;
                const h = _this.$createElement;
                const parentElTag = h('span',{style:{color:"#409EFF"}},_this.pageProps.doBack.label);
                const currentElTag = h('span',{style:{color:"#F56C6C"}},data.title);
                _this.$refs["menuForm"].validate(valid=>{
                    if(valid){
                        _this.$msgbox({
                            title: "确认追加",
                            message:h(
                                "p",null,[
                                    h('span',null,"此操作将该在 "),
                                    parentElTag,
                                    h('span',null," 节点下追加 "),
                                    currentElTag,
                                    h('span',null," 子节点,"),
                                    h('span',null," 如果该 "),
                                    parentElTag,
                                    h('span',null," 节点有路由数据, 该路由将会被忽略。"),
                                ]
                            ),
                            confirmButtonText: "确认",
                            cancelButtonText: "取消",
                            showCancelButton:true,
                            type: "warning"
                        }).then(()=>{
                            const loading = _this.$loading({
                                lock: true,
                                text: "Loading",
                                spinner: 'el-icon-loading',
                                background: 'rgba(0, 0, 0, 0.7)'
                            });
                            _this.$axios.post("/application/menu/appendMenu.action",data).then(response=>{
                                if(response.data.state){
                                    _this.$message({
                                        message: "追加成功！！！",
                                        type: "success"
                                    });
                                    _this.refreshMenuTree();
                                    _this.showMenuNodeInfoPage(response.data)

                                }else {
                                    _this.$message({
                                        message:"追加失败",
                                        type: "error"
                                    });
                                }
                                loading.close();
                            }).catch(() => {
                                this.$message({
                                    type: 'error',
                                    message: '执行异常!'
                                });
                                loading.close();
                            });
                        }).catch(() => {
                            this.$message({
                                type: 'info',
                                message: '已取消追加'
                            });
                        });
                    }else {
                        _this.$message({
                            message:"请将信息输入完整！！！",
                            type: "error"
                        });
                    }
                });
            },
            doUpdateMenuNodeAction(){
                let _this = this;
                let data = _this.menuFormPageData;

                _this.$refs["menuForm"].validate(valid=>{
                    if(valid){
                        _this.$confirm("请确认修改无误，否则可能会有意想不到的事情发生","确认更改",{
                            confirmButtonText: "确认",
                            cancelButtonText: "取消",
                            type: "warning"
                        }).then(()=>{
                            const loading = _this.$loading({
                                lock: true,
                                text: "Loading",
                                spinner: 'el-icon-loading',
                                background: 'rgba(0, 0, 0, 0.7)'
                            });
                            _this.$axios.post("/application/menu/updateMenu.action",data).then(response=>{
                                if(response.data.state){
                                    _this.$message({
                                        message: "更改成功！！！",
                                        type: "success"
                                    });
                                    _this.refreshMenuTree();
                                    _this.showMenuNodeInfoPage(response.data)
                                }else {
                                    _this.$message({
                                        message:"更改失败",
                                        type: "error"
                                    });
                                }
                                loading.close();
                            }).catch(() => {
                                this.$message({
                                    type: 'error',
                                    message: '执行异常!'
                                });
                                loading.close();
                            });
                        }).catch(()=>{
                            this.$message({
                                type: 'info',
                                message: '已取消更改'
                            });
                        })

                    }else{
                        _this.$message({
                            message:"请将信息输入完整！！！",
                            type: "error"
                        });
                    }
                })
            },
            queryIconClassSearch(queryString, cb){
                import("@/assets/element-uI-icon-list.json").then(data=>{
                    let restaurants = data.default;
                    let results = queryString? restaurants.filter((restaurant)=>{
                        return restaurant.value.indexOf(queryString) > -1;
                    }) : restaurants;
                    cb(results);
                })
            },
            splitIconClassValue(value,search){
                let results = search?value.split(search):[value];
                if(results.length>1)results.splice(1,0,search)
                return results;
            },
            doClearPage(){
                this.pageProps.type = "";
                this.pageProps.title = "";
                this.pageProps.form = "";
                this.pageProps.doBack = {};
                this.menuInfoPageData = null;
                this.menuFormPageData = null;
            }

        },
        computed:{}
    }
</script>

<style scoped>
    .custom-tree-node {
        flex: 1;
        display: flex;
        align-items: center;
        justify-content: space-between;
        font-size: 14px;
        padding-right: 8px;
    }
    .menu-form-icon-i{
        display: inline-block;
        min-height: 40px;
        font-size: 32px;
        color: #606266;
        line-height: 40px;
        transition: color .15s linear;
    }
    .menu-form-icon-i:hover{
        color: #409EFF;
    }

    .menu-info-icon-i{
        font-size: 32px;
        color: #606266;
        line-height: 40px;
        transition: color .15s linear;
    }
    .menu-info-icon-i:hover{
        color: #409EFF;
    }
    .menu-info-row{
        margin: 10px 20px 20px 20px;
    }
    .menu-form-icon-search-icon-i{
        float:right;
        min-height: 34px;
        font-size:24px;
        color: #606266;
        line-height:34px;
        transition: color .15s linear;
    }
    .menu-form-icon-search-icon-i:hover{
        color: #409EFF;
    }

</style>