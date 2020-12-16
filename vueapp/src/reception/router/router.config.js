import Vue from 'vue'
import VueRouter from "vue-router";

import spxq from "@/reception/spxq/spxq.vue";
import spfl from "@/reception/spfl/spfl.vue";
import gwc from "@/reception/gwc/gwc.vue";

Vue.use(VueRouter)

export  default  new VueRouter({
  routes:[
    {
      path:"/spfl",
      name:"spfl",
      component:spfl
    },
    {
      path:"/spxq",
      name:"spxq",
      component:spxq
    },
    {
      path:"/gwc",
      name:"gwc",
      component:gwc
    }
  ]
})
