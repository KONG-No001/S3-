import Vue from "@/index.js";
import index from "./main.vue";
new Vue({
    render:h=>h(index),
}).$mount("#app")