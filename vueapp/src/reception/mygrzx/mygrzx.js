/**
 * 基本代码模板，可复制，如果没有骚操作的话...
 */
import Vue from "@/index.js";
import index from "./mygrzx.vue";

import router from "@/reception/router/router.config.js";
new Vue({
    render:h=>h(index),
    router
}).$mount("#app")



