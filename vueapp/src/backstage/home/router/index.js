import Vue from "vue";
import VueRouter from "vue-router";

import staffManagement from "@/backstage/home/view/staffManagement.vue";
import goodsList from "@/backstage/home/view/goodsList.vue"

Vue.use(VueRouter);

let routes = [
    {
        path:"/staffManagement",
        name: "staffManagement",
        component: staffManagement,
    },{
        path:"/goodsManagement",
        name: "goodsManagement",
        component: goodsList,
    }
];

export default new VueRouter({
    routes
})