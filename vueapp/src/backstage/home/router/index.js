import Vue from "vue";
import VueRouter from "vue-router";
import Axios from "axios";
Vue.use(VueRouter);
let routes = [];
let router = new VueRouter({routes})
export default router;


// 异步获取路由组件
Axios.get("/application/listRoutes.action").then(response=>{
    let data = response.data;
    let routes = [];
    for(let i in data){
        let url = data[i].url;
        routes.push({
            path: data[i].path,
            name: data[i].name,
            component: ()=>import(/* webpackChunkName: "routes_view" */ "@/" + url)
        })
    }
    router.addRoutes(routes)
})

