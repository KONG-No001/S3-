<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="8">
                <el-card style="width: 100%;height: 540px;" body-style="height:445px;padding:0">
                    <template slot="header">
                        <span style="font-size: 18px;color: #303133;line-height: 24px">
                            菜单树
                            <template v-if="treeEditMode.enable">
                                <span style="color: #ff7600;font-size: 15px">[编辑模式开启]</span>
                                <el-button type="text" size="mini" icon="el-icon-edit-outline" @click="closeTreeEditMode">关闭</el-button>
                            </template>
                            <el-button v-if="!treeEditMode.enable" type="text" size="mini" icon="el-icon-edit-outline" @click="enableTreeEditMode">编辑模式</el-button>
                        </span>
                        <span style="float: right">
                            <el-button type="text" :loading="tableLoading" icon="el-icon-refresh-right" size="mini" @click="refreshMenuTree()"></el-button>
                            <el-button type="text" size="mini" :disabled="treeEditMode.enable" @click="showAddMenuRootNode">添加根</el-button>
                        </span>
                    </template>
                    <template>
                        <el-scrollbar slot="default" style="height: 100%;border-bottom: 1px solid #EBEEF5;" wrap-style="overflow: hidden scroll;">
                            <div style="width: 99%">
                                <el-tree :data="menuTree"
                                         node-key="id"
                                         ref="menuTree"
                                         :props="treeProps"
                                         :lazy="true"
                                         :load="loadingMenuNode"
                                         :draggable="treeEditMode.enable"
                                         :default-expanded-keys="defaultExpandedKeys"
                                         @node-collapse="collapseMenuNode"
                                         @node-click="showMenuNodeInfoPage"
                                         @node-drop="handleTreeDrag"
                                >
                                    <span class="custom-tree-node"  slot-scope="{node,data}">
                                        <span>
                                            <span style="font-size: 13px;">{{node.label}}</span>
                                        </span>
                                        <span>
                                            <el-button type="text" size="mini" :disabled="treeEditMode.enable" @click.stop="showAppendMenuNodePage(data)">追加</el-button>
                                            <el-button type="text" size="mini" :disabled="treeEditMode.enable" @click.stop="showUpdateMenuNodePage(data)">更改</el-button>
                                            <el-button type="text" size="mini" :disabled="treeEditMode.enable" @click.stop="deleteMenuNode(data)">删除</el-button>
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
                            <el-page-header v-if="pageProps.form==='add'" @back="doClearPage" :content="pageProps.title"></el-page-header>
                            <el-page-header v-else @back="doGoBack" :content="pageProps.title"></el-page-header>
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
                                    <template v-if="pageProps.form!=='add'">
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
                                    </template>
                                </el-form>
                                <el-row class="menu-info-row">
                                    <el-col :span="24">
                                    <span style="float: right">
                                        <el-button v-if="pageProps.form==='add'" type="success" size="small" :disabled="treeEditMode.enable" @click="doAddMenuRootNodeAction">确认</el-button>
                                        <el-button v-if="pageProps.form==='append'" type="success" size="small" :disabled="treeEditMode.enable" @click="doAppendMenuNodeAction">确认</el-button>
                                        <el-button v-if="pageProps.form==='update'" type="success" size="small" :disabled="treeEditMode.enable" @click="doUpdateMenuNodeAction">确认</el-button>
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
                                        <el-button type="primary" size="small" :disabled="treeEditMode.enable" @click="showAppendMenuNodePage({id:menuInfoPageData.id,label:menuInfoPageData.title})">追加</el-button>
                                        <el-button type="warning" size="small" :disabled="treeEditMode.enable" @click="showUpdateMenuNodePage({id:menuInfoPageData.id,label:menuInfoPageData.title})">更改</el-button>
                                        <el-button type="danger" size="small" :disabled="treeEditMode.enable" @click="deleteMenuNode(menuInfoPageData)">删除</el-button>
                                    </span>
                                </el-col>
                            </el-row>
                        </el-scrollbar>
                    </template>
                    <template v-else>
                        <div style="width: 100%; height: 540px; display: flex; justify-content:center; align-items: center">
                            <span v-if="!treeEditMode.enable" style="color: #606266;font-size: 20px;">你还没有选择数据......</span>
                            <span v-else class="editModel-msg-span">在编辑模式下只能查看节点信息...</span>
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
                defaultExpandedKeys:[],
                treeProps:{label:"label"},
                menuTree:[],
                treeEditMode:{
                    enable: false,
                    nodeCurrentCount:0,
                    nodeMaxCount:0,
                    currentTreeNode: undefined,
                },
                pageProps:{
                    type:"",
                    title:"",
                    form:"",
                    backInfo:{},
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
                let url = "/application"+(_this.treeEditMode.enable?"/listMenu.action":"/menu/listMenuTree.action");
                _this.$axios.get(url,{
                    params:{id:0}
                }).then(response=>{
                    _this.menuTree = response.data;
                    _this.treeEditMode.nodeCurrentCount = response.data.length;
                    _this.tableLoading=false
                })
            },
            loadingMenuNode(node, resolve){
                let _this = this;
                if(!_this.treeEditMode.enable || _this.treeEditMode.nodeCurrentCount<_this.treeEditMode.nodeMaxCount){
                    let id = 0
                    if (node.level !== 0){
                        id = node.data.id;
                        if(_this.defaultExpandedKeys.indexOf(id)===-1)_this.defaultExpandedKeys.push(id);
                    }
                    let url = "/application"+(_this.treeEditMode.enable?"/listMenu.action":"/menu/listMenuTree.action");
                    _this.$axios.get(url,{
                        params:{id}
                    }).then(response=>{
                        resolve(response.data);
                        if(_this.treeEditMode.enable) _this.treeEditMode.nodeCurrentCount+=response.data.length
                    })
                }else{
                    setTimeout(()=>{
                        resolve([]);
                        if(this.treeEditMode.currentTreeNode){
                            let n = this.treeEditMode.currentTreeNode;
                            this.treeEditMode.currentTreeNode = undefined;
                            _this.updateTreePathInfo(node,n)
                        }
                    },10)

                }

            },
            collapseMenuNode(data){
                let _this = this;
                if(!_this.treeEditMode.enable){
                    let index = _this.defaultExpandedKeys.indexOf(data.id);
                    _this.defaultExpandedKeys.splice(index,1)
                }
            },
            showMenuNodeInfoPage(data){
                let _this = this;
                let id;
                let label;
                _this.pageProps.type = "info";
                if(!_this.treeEditMode.enable){
                    id = data.id;
                    label = data.label;
                    _this.pageProps.title = label;
                    _this.pageLoading = true;
                    _this.$axios.get("/application/menu/getMenu.action",{
                        params:{id}
                    }).then(response=>{
                        _this.menuInfoPageData = response.data;
                        _this.pageLoading = false;
                    })
                }else {
                    _this.pageProps.title = data.title;
                    _this.menuInfoPageData = data;
                    _this.pageLoading = false;
                }

            },
            showAppendMenuNodePage({id,label}){
                let _this = this;
                _this.pageProps.type = "form";
                _this.pageProps.form = "append";
                _this.pageProps.title = label+" / 追加节点";
                _this.pageProps.backInfo = {id,label};
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
                _this.pageProps.backInfo = {id,label};
                _this.pageLoading = true;
                _this.$axios.get("/application/menu/getUpdateInfo.action",{
                    params:{id}
                }).then(response=>{
                    _this.menuFormPageData = response.data
                    _this.pageLoading = false;
                })
            },
            showAddMenuRootNode(){
                let _this = this;
                _this.pageProps.type = "form";
                _this.pageProps.form = "add";
                _this.pageProps.title = "添加根节点";
                _this.pageLoading = false;
                _this.menuFormPageData = {
                    id:-1
                }
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
                this.showMenuNodeInfoPage(this.pageProps.backInfo)
            },
            doAppendMenuNodeAction(){
                let _this = this;
                let data = _this.menuFormPageData;
                const h = _this.$createElement;
                const parentElTag = h('span',{style:{color:"#409EFF"}},_this.pageProps.backInfo.label);
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
                                    if(this.treeEditMode.enable){
                                        _this.defaultExpandedKeys.push(response.data.id)
                                    }
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
            doAddMenuRootNodeAction(){
                let _this = this;
                let data = _this.menuFormPageData;
                const h = _this.$createElement;
                const currentElTag = h('span',{style:{color:"#F56C6C"}},data.title);
                _this.$refs["menuForm"].validate(valid=>{
                    if(valid){
                        _this.$msgbox({
                            title: "添加根",
                            message:h(
                                "p",null,[
                                    h('span',null,"将在目录下添加 "),
                                    currentElTag,
                                    h('span',null," 根节点, 是否继续"),
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
                            _this.$axios.post("/application/menu/addRootMenu.action",data).then(response=>{
                                if(response.data.state){
                                    _this.$message({
                                        message: "添加成功！！！",
                                        type: "success"
                                    });
                                    if(this.treeEditMode.enable){
                                        _this.defaultExpandedKeys.push(response.data.id)
                                    }
                                    _this.refreshMenuTree();
                                    _this.showMenuNodeInfoPage(response.data)

                                }else {
                                    _this.$message({
                                        message:"添加失败",
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
                                message: '已取消添加'
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
            queryIconClassSearch(queryString, cb){
                import("@/assets/element-ui-icon-list.json").then(data=>{
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
                this.pageProps.backInfo = {};
                this.menuInfoPageData = null;
                this.menuFormPageData = null;
            },

            enableTreeEditMode(){
                let _this = this;
                _this.$confirm("你将可以移动随意移动节点，并保存节点的树位置。注意！此功能对性能要求较高，请不要长时间开启节点编辑模式！！","开启编辑模式",{
                    confirmButtonText: "开启",
                    cancelButtonText: "取消",
                    type: "warning",
                    center: true
                }).then(()=>{
                    _this.treeEditMode.enable = true;
                    _this.tableLoading = true;
                    const loading = _this.$loading({
                        lock: true,
                        text: "Loading",
                        spinner: 'el-icon-loading',
                        background: 'rgba(0, 0, 0, 0.7)'
                    });
                    _this.$axios.get("/application/menu/listAllId.action").then(response=>{
                        _this.defaultExpandedKeys = response.data;
                        _this.treeEditMode.nodeMaxCount = response.data.length;
                        _this.treeEditMode.nodeCurrentCount = response.data.length;
                        _this.tableLoading = false;
                        _this.treeProps.label="title";
                        _this.doClearPage();
                        _this.refreshMenuTree();
                        loading.close();
                        this.$message({
                            type: 'success',
                            message: '编辑模式已开启'
                        });
                        // setTimeout(function () {
                        //
                        // },1000)
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消编辑模式'
                    });
                });

            },
            closeTreeEditMode(){
                let _this = this;
                _this.$confirm("你对树节点的更改将被提交，请确保你对的节点所作的操作是符合你的要求","关闭编辑模式",{
                    confirmButtonText: "提交更改",
                    cancelButtonText: '放弃更改',
                    type: "warning",
                    distinguishCancelAndClose: true,
                    center: true
                }).then(()=>{
                    this.$message({
                        type: 'info',
                        message: '正在提交修改'
                    });
                    const loading = _this.$loading({
                        lock: true,
                        text: "Loading",
                        spinner: 'el-icon-loading',
                        background: 'rgba(0, 0, 0, 0.7)'
                    });
                    let getMenuNode = _this.$refs["menuTree"].getNode;
                    let keys = _this.defaultExpandedKeys;
                    let menus = [];
                    for(let i=0;i<keys.length;i++){
                        menus.push(getMenuNode(keys[i]).data)
                    }
                    _this.$axios.post("/application/menu/updateMenuNode.action",menus).then(({data})=>{
                        const h = _this.$createElement;
                        _this.$notify({
                            title:"已提交修改",
                            message: h("div",null,[
                                h("p",{style:{color:"#67C23A"}},"成功："+data.success),
                                h("p",{style:{color:"#F56C6C"}},"失败："+data.error),
                                h("p",{style:{color:"#E6A23C"}},"执行："+data.execute),
                                h("p",{style:{color:"#909399"}},"总数："+data.total),
                            ]),
                            type: 'success',
                        })
                        _this.treeEditMode.enable = false;
                        _this.defaultExpandedKeys = [];
                        _this.treeProps.label="label";
                        _this.refreshMenuTree();
                        loading.close();
                    })
                }).catch(action=>{
                    if(action === 'cancel'){
                        this.$message({
                            type: 'info',
                            message: "已放弃修改"
                        })
                        const loading = _this.$loading({
                            lock: true,
                            text: "Loading",
                            spinner: 'el-icon-loading',
                            background: 'rgba(0, 0, 0, 0.7)'
                        });
                        _this.treeEditMode.enable = false;
                        _this.defaultExpandedKeys = [];
                        _this.treeProps.label="label";
                        _this.refreshMenuTree();
                        loading.close();
                    }else {
                        this.$message({
                            type: 'info',
                            message: "已取消"
                        })
                    }
                })

            },
            handleTreeDrag(draggingNode,dropNode){
                this.treeEditMode.currentTreeNode = draggingNode;
                this.updateTreeStateInfo(dropNode.parent)
            },
            updateTreeStateInfo(pnode){
                let _this = this;
                let i = 0;
                pnode.childNodes.forEach(node=>{
                    let data = node.data
                    data.i = i++;
                    data.level = node.level-1;
                    data.isParent = node.childNodes.length>0?1:0;
                    if(pnode.parent){
                        data.print = pnode.data.id;
                        data.prints = pnode.data.prints+"/"+pnode.data.id;
                    }else{
                        data.print = 0;
                        data.prints = "0";
                    }
                    _this.updateTreeStateInfo(node)
                })
            },
            updateTreePathInfo(baseNode,currentNode){
                let _this = this;
                currentNode.childNodes.forEach(node=>{
                    let data = node.data
                    data.level = baseNode.data.level+1;
                    data.print = baseNode.data.id;
                    data.prints = baseNode.data.prints+"/"+baseNode.data.id;
                    _this.$refs["menuTree"].append(data,baseNode.data)
                    setTimeout(()=>{
                        _this.updateTreePathInfo(node,node)
                    },150)
                })
                this.treeEditMode.currentTreeNode = undefined;
            }

        },
        computed:{
        },
        watch:{
        }
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

    .editModel-msg-span{
        display: inline-block;
        min-height: 24px;
        font-size: 18px;
        color: #606266;
        line-height: 24px;
        transition: color .15s linear;
    }
    .editModel-msg-span:hover{
        color: #ff7600;
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