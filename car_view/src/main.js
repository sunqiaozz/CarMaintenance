import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
//引入axios
import http from './utils/request'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './assets/global.css'

Vue.config.productionTip = false
Vue.use(ElementUI,{size:"small"})
Vue.prototype.$http=http;

new Vue({
  router,
  store,
  render: h => h(App),
  beforeCreate() {
    Vue.prototype.$bus=this
  }
}).$mount('#app')
