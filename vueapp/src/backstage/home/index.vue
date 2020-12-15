<template>
    <div style="height: 600px;">
        <el-container>
            <el-header class="header" height="80px" >
                <el-row>
                    <el-col :span="6">
                        <h2 style="color: #FFF" >后台管理系统</h2>
<!--                        <el-image-->
<!--                                style="width: 64px;height: 64px;"-->
<!--                                :src="icon"/>-->
                    </el-col>
                    <el-col :offset="14" :span="4">
                        <span style="line-height:80px"><a>hi: 10086</a></span>
                    </el-col>
                </el-row>
            </el-header>
            <el-container style="height: 600px">
                <el-aside class="aside" width="250px" >
                    <el-scrollbar style="height: 100%;" wrap-style="overflow: hidden scroll;" >
                        <el-menu
                                ref="menu"
                                :router="true"
                                :collapse="isCollapse"
                                @open="openMenu"
                                @close="closeMenu">
                            <menu-item :aside-menus="asideMenus"></menu-item>
                        </el-menu>
                    </el-scrollbar>
                </el-aside>
                <el-main>
                    <el-scrollbar style="height: 100%;" wrap-style="overflow: hidden scroll;">
                        <router-view></router-view>
                    </el-scrollbar>
                </el-main>
            </el-container>
            <el-footer class="footer" height="50px">
            </el-footer>
        </el-container>
    </div>
</template>

<script>
    // import icon from "@/assets/gpsystem.jpg";
    import icon from "@/assets/pjyx.png";
    import Axios from "axios";
    import MenuItem from "@/backstage/home/menu-item";
    // import { Loading } from 'element-ui';
    export default {
        name: "index",
        components: {MenuItem},
        data: function () {
            return {
                icon,
                isCollapse: false,
                asideMenus: []
            }
        },
        methods:{
            listMenu(id,menus=null){
                let _this = this;
                Axios.get("/application/listMenu.action",{
                    params: {id}
                }).then(request=>{
                    if(menus!==null){
                        menus.children = request.data

                    }else{
                        _this.asideMenus = request.data
                    }
                    console.log(request.data[0].id)
                })
            },
            openMenu(id,path){
                let _this = this;
                let asideMenus = _this.selectMenu(id,path);
                asideMenus.children = [{title:"loading",icon: "el-icon-loading"}];
                _this.listMenu(id,asideMenus)

            },
            selectMenu(id,path){
                let _this = this;
                let asideMenus = _this.asideMenus;
                for(let i=0; i<path.length; i++){
                    asideMenus.some(menu=>{
                        if (menu.id == path[i]){
                            if(menu.id==id) {
                                asideMenus = menu;
                            }else{
                                asideMenus = menu.children;
                            }
                            return true;
                        }
                        return false;
                    })
                }
                return asideMenus;
            },
            closeMenu(id,path){
                let _this = this;
                _this.selectMenu(id,path).children = [];
            }

        },
        created: function () {
            this.listMenu(0);
        }
    }
</script>

<style scoped>
    .header{
        background: #409EFF;
        /*display: flex;*/
        /*align-items: center;*/
    }
    .aside{
        /*background: #409EFF;*/
    }
    .footer{
        background: #409EFF;
    }

    .el-menu-vertical-demo:not(.el-menu--collapse) {
        width: 200px;
        min-height: 400px;
    }

</style>