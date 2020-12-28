import Vue from 'vue'
import VueRouter from "vue-router";

import spxq from "@/reception/spxq/spxq.vue";
import spfl from "@/reception/spfl/spfl.vue";
import gwc from "@/reception/gwc/gwc.vue";
import mygrzx from "@/reception/mygrzx/mygrzx.vue";
import dingdan from "@/reception/dingdan/dingdan.vue";
import shouye from "@/reception/shouye/shouye.vue";

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
    },
    {
      path:"/mygrzx",
      name:"mygrzx",
      component:mygrzx
    },
    {
      path:"/dingdan",
      name:"dingdan",
      component:dingdan
    },
    {
      path:"/shouye",
      name:"shouye",
      component:shouye
    }
  ]
})
