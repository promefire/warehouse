import vue from 'vue'
import Vuex from 'vuex'
import router from "@/router";
import {resetRouter} from "@/router";

vue.use(Vuex)

function addNewRouter(menulist){
    let routes = router.options.routes
    console.log(routes)

    routes.forEach(routerItem=>{
        if(routerItem.path == '/Index'){
            menulist.forEach(menuitem=>{
                let childRoute = {
                    path:"/" + menuitem.menuclick,
                    // component: menuitem.menucomponent,
                    name: menuitem.menuname,
                    meta:{
                        title: menuitem.menuname
                    },
                    component:()=>import('../components/' + menuitem.menucomponent)
                }

                routerItem.children.push(childRoute)
            })

        }

    })
    resetRouter()
    router.addRoutes(routes)
}


export default new Vuex.Store({
    state:{
        menu:[]
    },
    mutations:{
        setMenu(state,menu){
            state.menu = menu

            addNewRouter(menu)
        },
        getMenu(state){
            return state.menu
        },



    }
})