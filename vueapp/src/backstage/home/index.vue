<template>
    <div style="height: 600px;">
        <el-container style="height: 100%">
            <el-header class="header">
                <el-row>
                    <el-col>
                        <el-image
                                style="width: 64px;height: 64px;"
                                :src="img"/>
                    </el-col>
                </el-row>
            </el-header>
            <el-container style="height: 100%">
                <el-aside>
                    <el-scrollbar style="height: 100%;" wrap-style="overflow: hidden scroll;" >
                        <el-menu>
                            <el-submenu index="1">
                                <template slot="title"><i class="el-icon-s-data"></i>资产管理</template>
                                <el-menu-item index="1-1">
                                    <el-icon class="el-icon-user-solid"></el-icon>
                                    员工管理
                                </el-menu-item>
                                <el-menu-item index="1-2">
                                    <el-icon class="el-icon-s-goods"></el-icon>
                                    商品管理
                                </el-menu-item>
                                <el-menu-item index="1-3">
                                    <el-icon class="el-icon-s-home"></el-icon>
                                    仓库管理
                                </el-menu-item>
                                <el-menu-item index="1-4">
                                    <el-icon class="el-icon-s-shop"></el-icon>
                                    商户管理
                                </el-menu-item>
                                <el-menu-item index="1-5">
                                    <el-icon class="el-icon-s-promotion"></el-icon>
                                    配送管理
                                </el-menu-item>
                                <el-menu-item index="1-6">
                                    <el-icon class="el-icon-s-custom"></el-icon>
                                    供应商管理
                                </el-menu-item>
                            </el-submenu>
                            <el-menu-item index="2">
                                <template slot="title"><i class="el-icon-s-marketing"></i>营收统计</template>
                            </el-menu-item>
                            <el-submenu index="3">
                                <template slot="title"><i class="el-icon-s-tools"></i>设置</template>
                                <el-submenu index="3-1">
                                    <template slot="title"><el-icon class="el-icon-lock"></el-icon>安全设置</template>
                                    <el-menu-item index="3-1-1">
                                        <el-icon class="el-icon-user"></el-icon>
                                        角色管理
                                    </el-menu-item>
                                    <el-menu-item index="3-1-2">
                                        <el-icon class="el-icon-s-check"></el-icon>
                                        权限管理
                                    </el-menu-item>
                                    <el-menu-item index="3-1-3">
                                        <el-icon class="el-icon-s-ticket"></el-icon>
                                        加密管理
                                    </el-menu-item>
                                </el-submenu>
                                <el-menu-item index="3-2">
                                    <el-icon class="el-icon-menu"></el-icon>
                                    菜单设置
                                </el-menu-item>
                                <el-menu-item index="3-3">
                                    <el-icon class="el-icon-s-operation"></el-icon>
                                    系统设置
                                </el-menu-item>
                            </el-submenu>
                        </el-menu>
                    </el-scrollbar>
                </el-aside>
                <el-main>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    import img from "@/assets/gpsystem.jpg";
    export default {
        name: "index",
        data: function () {
            return {
                img,
                window,
                vavMenus:[
                    'HOME',
                    'SYSTEM',
                    'LOGIN',
                ],
                asideMenus: [
                    // "商品管理",
                    // "仓库管理",
                    // "商户管理",
                    // "配送管理",
                    // "供应商管理",
                    // "营收统计",
                    // "安全管理",
                    // "系统管理",
                    // "员工管理",
                ]
            }
        },
        computed: {
        },
    }
</script>

<style scoped>
    .header{
        background: #ff7600;
    }

</style>