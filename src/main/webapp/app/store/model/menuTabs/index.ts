import { defineStore } from 'pinia'
import { ref } from 'vue'
import {useRouter, type LocationQueryRaw} from 'vue-router'
export interface menu{
    name:string,
    title:string,
    path:string,
    icon:string,
    query?:LocationQueryRaw
}


const useMenuTabStore = defineStore('menuTab', () => {
    const router = useRouter()
    const menuTab = ref({        
        openMenus: [] as menu[],
        activeKey: 'home'
    })


    // 新增菜单
    const addMenu = (menu:menu)=>{
        menuTab.value.activeKey = menu.name
        router.push({
            path:menu.path,
            query:menu.query
        })
        if(menuTab.value.openMenus.filter(item=>item.name==menu.name).length == 0){
            menuTab.value.openMenus.push(menu)
        }
    }

    // 通过下标跳转 跳转到删除页签之后的数组的下标位置
    // 倒数第二个页签 即为index-1（被删除页签对应的下标-1）
    // 该页签的后一个页签 即为index（该页签被删除了，那么后一个页签就对应删除前页签的下标）
    const jumpByIndex = (index:number)=>{
        let openMenus = menuTab.value.openMenus
        let targetActiveKey = openMenus[index]?.name??'home'
        menuTab.value.activeKey = targetActiveKey
        jumpByName(targetActiveKey)
    }

    // 通过页面名称跳转
    // 通过名称找到openMenu数组里面的对应item的path，路由跳转到这个path
    const jumpByName = (name:string)=>{
        if(name == 'home'){
            router.push(name)
        }else{
            let targetMenu = menuTab.value.openMenus.find(item=>item.name==name)
            router.push({
                path:targetMenu?.path||'not-found',
                query:targetMenu?.query
            })
        }
    }

    // 删除页签
    const deleteMenu = (menuName:string|number)=>{
        let openMenus = menuTab.value.openMenus
        // elementPlus Tabs组件，不仅可以关闭当前打开的页签，还可以关闭未打开的页签
        if(menuName != menuTab.value.activeKey){
            // 关闭的未激活的页签 直接关闭即可
            let index = menuTab.value.openMenus.findIndex(item=>item.name == String(menuName))
            if(index!=-1){
                openMenus.splice(index,1)
            }
        }else{
            // 关闭的激活的页签 分情况处理
            let index = menuTab.value.openMenus.findIndex(item=>item.name == String(menuName)) //作删除操作前，目标要操作页签的下标
            if(index!=-1){
                openMenus.splice(index,1)
                if(openMenus.length == index){
                    // 关闭最后一个页签，显示倒数第二个页签
                    jumpByIndex(index-1)
                }else{
                    // 关闭的不是最后一个页签,显示该页签的后一个页签
                    jumpByIndex(index)
                }
            }
        }
    }

    // 切换页签
    const undateMenu = (menu:string|number)=>{
        jumpByName(String(menu))
    }

    return {
        menuTab,
        addMenu,
        deleteMenu,
        undateMenu
    }
})

export default useMenuTabStore