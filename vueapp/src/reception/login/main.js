import Vue from 'vue'
import App from './App.vue'

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import Axios from "axios";

Axios.defaults.baseURL='http://localhost:8080/'
Vue.prototype.$axios = Axios;

Vue.config.productionTip = false

Vue.use(ElementUI)

new Vue({
  render: h => h(App),
}).$mount('#app')
