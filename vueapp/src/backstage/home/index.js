/**
 * 基本代码模板，可复制，如果没有骚操作的话...
 */
import Vue from "@/index.js";
import index from "./index.vue";
import router from "./router/index.js";
import Vuex from "vuex";
import store from"./store/index.js"

Vue.use(Vuex);

new Vue({
    render:h=>h(index),
    router,
    store,
}).$mount("#app")




