<template>
    <div style="height: 600px;">
        <el-container>
            <el-header class="header">
                <el-row>
                    <el-col>
                        <el-image
                                style="width: 64px;height: 64px;"
                                :src="icon"/>
                    </el-col>
                </el-row>
            </el-header>
            <el-container style="height: 600px">
                <el-aside>
                    <el-scrollbar style="height: 100%;" wrap-style="overflow: hidden scroll;" >
                        <el-menu ref="menu" :router="true" @open="openMenu">
                            <menu-item :aside-menus="asideMenus"></menu-item>
                        </el-menu>
                    </el-scrollbar>
                </el-aside>
                <el-main>
                    <router-view></router-view>
                </el-main>
            </el-container>
            <el-footer class="footer">
            </el-footer>
        </el-container>
    </div>
</template>

<script>
    import icon from "@/assets/gpsystem.jpg";
    import Axios from "axios";
    import MenuItem from "@/backstage/home/menu-item";
    export default {
        name: "index",
        components: {MenuItem},
        data: function () {
            return {
                icon,
                asideMenus: []
            }
        },
        methods:{
            listMenu(id=0){
                let _this = this;
                Axios.get("/application/listMenu.action",{
                    params: {
                        id
                    }
                }).then(request=>{
                    _this.asideMenus = request.data
                })
            },
            openMenu(id,path){
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
                Axios.get("/application/listMenu.action",{
                    params: {id}
                }).then(request=>{
                    asideMenus.children = request.data;
                })

            }
        },
        created: function () {
            this.listMenu();
        }
    }
</script>

<style scoped>
    .header{

        background: #ff7600;
    }
    .footer{
        background: aqua;
    }

</style>