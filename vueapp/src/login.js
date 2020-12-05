import Vue from "vue";
import login from "./login.vue";
import ElementUI from "element-ui";

import "element-ui/lib/theme-chalk/index.css"

Vue.use(ElementUI);

new Vue({
    render:h=>h(login)
}).$mount("#app")
