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
                                         @node-click="menuNodeClick"
                                >
                                    <span class="custom-tree-node"  slot-scope="{node,data}">
                                        <span>
                                            <span style="font-size: 13px;">{{node.label}}</span>
                                        </span>
                                        <span>
                                            <el-button type="text" size="mini" @click.stop="showAppendMenuNodePage(data)">追加</el-button>
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
                    <template v-if="pageProps.type==='add'||pageProps.type==='append'" >
                        <template slot="header">
                            <el-page-header :content="pageProps.title"></el-page-header>
                        </template>
                        <template>
                            <el-scrollbar slot="default" style="height: 100%;border-bottom: 1px solid #EBEEF5;" wrap-style="overflow: hidden scroll;">
                                <el-form style="margin: 10px 20px" label-width="100px" label-position="right">
                                    <el-form-item label="字段:">
                                        <el-input></el-input>
                                    </el-form-item>
                                    <el-form-item label="标题:">
                                        <el-input></el-input>
                                    </el-form-item>
                                    <el-form-item label="路由地址:">
                                        <el-input></el-input>
                                    </el-form-item>
                                    <el-form-item label="组件地址:">
                                        <el-input></el-input>
                                    </el-form-item>
                                    <el-form-item label="图标:">
                                        <el-row>
                                            <el-col :span="2">
                                                <i class="el-icon-platform-eleme menu-form-icon-i"></i>
                                            </el-col>
                                            <el-col :span="22">
                                                <el-input ></el-input>
                                            </el-col>
                                        </el-row>
                                    </el-form-item>
                                </el-form>
                                <div style="margin: 10px 20px;">
                                    <el-button type="warning" style="float: right">更改</el-button>
                                </div>
                            </el-scrollbar>
                        </template>
                    </template>
                    <template v-else-if="pageProps.type==='info'">
                        <template slot="header">
                            <el-page-header content="节点详情"></el-page-header>
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
                                        <el-button type="warning" size="small">更改</el-button>
                                        <el-button type="danger" size="small" @click="deleteMenuNode(menuInfoPageData)">删除</el-button>
                                    </span>
                                </el-col>
                            </el-row>
                        </el-scrollbar>
                    </template>
                    <template v-else-if="pageProps.type==='update'"></template>
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
                },
                menuInfoPageData:null,
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
            menuNodeClick({id}){
                let _this = this;
                _this.pageProps.type = "info";
                _this.pageLoading = true;
                _this.$axios.get("/application/menu/getMenu.action",{
                    params:{id}
                }).then(response=>{
                    _this.menuInfoPageData = response.data;
                    _this.pageLoading = false;
                })
            },
            showAppendMenuNodePage(id){
                let _this = this;
                _this.pageProps.type = "append";
                _this.pageLoading = true;
                _this.$axios.get("/application/menu/getMenu.action",{
                    params:{id}
                }).then(response=>{
                    _this.menuFormPageData = response.data;
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

            }

        },
        // created(){
        //     this.refreshMenuTree(0);
        // }
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
        display: block;
        font-size: 32px;
        color: #606266;
        line-height: 40px;
        transition: color .15s linear;
    }
    .menu-form-icon-i:hover{
        color: #409EFF;
    }

    .menu-info-icon-i{
        display: inline-block;
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

</style>