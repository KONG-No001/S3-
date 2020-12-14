import Vue from "vue";
import VueRouter from "vue-router";

import staffManagement from "@/backstage/home/view/staffManagement.vue";
import goodsList from "@/backstage/home/view/goodsList.vue";
import goodsType from "@/backstage/home/view/goodsType.vue"

Vue.use(VueRouter);

let routes = [
    {
        //路由的地址；Menu数据库对应的url
        path:"/staffManagement",
        //
        name: "staffManagement",
        //路由的组件需要导入  **.vue
        component: staffManagement,
    },{
        path:"/goodsManagement",
        name: "goodsManagement",
        component: goodsList,
    }
    ,{
        path:"/goodsType",
        name: "goodsType",
        component: goodsType,
    }
];

export default new VueRouter({
    routes
})