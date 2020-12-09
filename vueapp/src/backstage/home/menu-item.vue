<template>
    <div>
        <template v-for="menu in asideMenus">
            <el-submenu v-if="menu.isParent===1" :index="''+menu.id" :key="menu.id">
                <template slot="title"><i :class="menu.icon"></i>{{menu.title}}</template>
                <menu-item :aside-menus="menu.children"></menu-item>
            </el-submenu>
            <el-menu-item v-else :index="''+menu.id" :key="menu.id" :route="menu.url">
                <template slot="title"><i :class="menu.icon"></i>{{menu.title}}</template>
            </el-menu-item>
        </template>
    </div>
</template>

<script>
    export default {
        name: "menu-item",
        props: {
            asideMenus: {
                type: Array,
                default: function () {
                    return []
                }
            },
        },
    }
</script>

<style scoped>

</style>