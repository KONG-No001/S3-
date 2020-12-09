import Vue from "vue";
import VueRouter from "vue-router";

import staffManagement from "@/backstage/home/view/staffManagement.vue";

Vue.use(VueRouter);

let routes = [
    {
        path:"/staffManagement",
        name: "staffManagement",
        component: staffManagement,
    }
];

export default new VueRouter({
    routes
})