import { defineStore } from 'pinia'
import { ref } from 'vue'
import {useRouter} from 'vue-router'

const useMenuTabStore = defineStore('menuTab', () => {
    const router = useRouter()
    const menuTab = ref({        
        opendMenus: [] as string[],
        activeKey: 'home'
    })

    // 新增菜单
    const addMenu = (menu:string)=>{
        menuTab.value.activeKey = menu
        if(!menuTab.value.opendMenus.includes(menu)){
            menuTab.value.opendMenus.push(menu)
        }
    }

    // 通过下标跳转 跳转到删除页签之后的数组的下标位置
    // 倒数第二个页签 即为index-1（被删除页签对应的下标-1）
    // 该页签的后一个页签 即为index（该页签被删除了，那么后一个页签就对应删除前页签的下标）
    const jumpByIndex = (index:number)=>{
        let opendMenus = menuTab.value.opendMenus
        let targetActiveKey = opendMenus[index]
        if(!targetActiveKey){
            targetActiveKey = 'home'
        }
        menuTab.value.activeKey = targetActiveKey
        router.push(targetActiveKey)
    }

    // 删除页签
    const deleteMenu = (menu:string|number)=>{
        let opendMenus = menuTab.value.opendMenus
        // elementPlus Tabs组件，不仅可以关闭当前打开的页签，还可以关闭未打开的页签
        if(menu != menuTab.value.activeKey){
            // 关闭的未激活的页签 直接关闭即可
            let index = menuTab.value.opendMenus.indexOf(String(menu))
            if(index!=-1){
                opendMenus.splice(index,1)
            }
        }else{
            // 关闭的激活的页签 分情况处理
            let index = menuTab.value.opendMenus.indexOf(String(menu)) //作删除操作前，目标要操作页签的下标
            if(index!=-1){
                opendMenus.splice(index,1)
                if(opendMenus.length == index){
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
        router.push(String(menu))
    }

    return {
        menuTab,
        addMenu,
        deleteMenu,
        undateMenu
    }
})

export default useMenuTabStore