import ElementUI from "element-ui";
import Vue from "vue";
import "element-ui/lib/theme-chalk/index.css"
import Axios from "axios";
Vue.use(ElementUI);
Vue.prototype.$axios = Axios;

export default Vue


