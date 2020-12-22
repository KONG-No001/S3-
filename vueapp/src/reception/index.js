/**
 * 基本代码模板，可复制，如果没有骚操作的话...
 */
import Vue from "@/index.js";
import index from "./index.vue";
import router from "./router/router.config.js";
import Vuex from "vuex";

Vue.use(Vuex);

new Vue({
    render:h=>h(index),
    router
}).$mount("#app")




