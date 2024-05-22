// import { createApp } from 'vue'
// import ElementPlus from 'element-plus'
// import 'element-plus/dist/index.css'
// import App from './App.vue'
// import './assets/global.css'
//
// const app = createApp(App)
// app.use(ElementPlus)
// app.mount('#app')
import Vue from 'vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios'
axios.defaults.baseURL = "http://101.200.228.42:8090"
Vue.prototype.$axios = axios
import router from "./router/index";

// vuex
import store from './store'

import App from './App.vue';

Vue.use(ElementUI);

new Vue({
    el: '#app',
    router:router,
    store,
    render: h => h(App)
});