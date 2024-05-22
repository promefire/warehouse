import VueRouter from "vue-router";
import Vue  from "vue";
import index from "@/components/Index.vue";
import login from "@/components/Login.vue";
import home from "@/components/Home.vue";
import Main from "@/components/user/UserManage.vue.vue";
import Record from "@/components/record/RecordManage.vue";
import AdminManage from "@/components/admin/AdminManage.vue";
import StorageManage from "@/components/storage/StorageManage.vue";
import GoodstypeManage from "@/components/goodstype/GoodstypeManage.vue";
import GoodsManage from "@/components/goods/GoodsManage.vue";
import RecordManage from "@/components/record/RecordManage.vue";



Vue.use(VueRouter)

const  router = new VueRouter({
    routes: [
        // 指定hash属性与组件之间的对应关系

        {path:'/',component:login},
        {path:'/Index',
            component: index,
            children:[
                {
                    path:'/Home',
                    component: home,
                    meta:{
                        title:"首页"
                    }

                },
                // {
                //     path:'/User',
                //     name:'user',
                //     component: Main,
                //     meta:{
                //         title:"用户"
                //     }
                // },
                // {
                //     path:'/Storage',
                //     component: StorageManage,
                //     meta:{
                //         title:"记录"
                //     }
                // },


            ]
        },




    ]
})

// 在这行代码中，首先创建了一个新的 VueRouter 实例，其路由配置为空数组 []。然后，将这个新创建的 VueRouter 实例的 matcher 赋值给现有的 router.matcher。matcher 是 Vue Router 实例的一个属性，用于路由匹配。通过将 router.matcher 设置为新的 VueRouter 实例的 matcher，相当于将现有的路由配置重置为空数组，从而实现了重置 Vue Router 的目的。
export function resetRouter(){
    router.matcher = new VueRouter({
        routes : []
    }).matcher

}

export default router