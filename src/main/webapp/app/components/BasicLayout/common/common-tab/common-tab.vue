<template>
    <div class="common-tab-wrapper">
        <el-tabs
          v-model="menuTab.activeKey"
          type="card"
          @tab-remove="deleteMenu"
          @tab-change="updateMenu"
        >
          <el-tab-pane key="home" label="主页" name="home">
            <template #label>
              <span class="custom-tabs-label">
                <el-icon><HomeFilled/></el-icon>主页
              </span>
            </template>
          </el-tab-pane>
          <el-tab-pane
            v-for="item in editableTabs"
            :key="item.name"
            :label="item.title"
            :name="item.name"
            closable
          >
            <template #label>
              <el-dropdown trigger="contextmenu">
                <span class="custom-tabs-label">
                  <el-icon><component :is="item.icon"/></el-icon>{{item.title}}
                </span>
                <template #dropdown>
                <el-dropdown-menu>
                  <el-dropdown-item @click="handleCloseCurrent(item)">关闭当前</el-dropdown-item>
                  <el-dropdown-item @click="handleCloseAllAfterRight(item)">关闭右侧</el-dropdown-item>
                  <el-dropdown-item @click="handleCloseAll">关闭全部</el-dropdown-item>
                  <el-dropdown-item @click="handleCloseOther(item)">关闭其他</el-dropdown-item>
                </el-dropdown-menu>
              </template>
              </el-dropdown>
            </template>
          </el-tab-pane>
        </el-tabs>
    </div>
  </template>
  
  <script lang="ts" setup>
  import { computed} from 'vue'
  import useMenuTabStore,{type menu} from '@/store/model/menuTabs/index'
  import {storeToRefs} from 'pinia'
  import { useI18n } from 'vue-i18n';
  import {Plus,CirclePlusFilled,CirclePlus,Check,CircleCheck} from '@element-plus/icons-vue'
  import router from '@/router';

  let menuTabStore = useMenuTabStore()
  const {menuTab} = storeToRefs(menuTabStore)
  const {deleteMenu,updateMenu} = menuTabStore


  // const router = useRouter()
  // router.afterEach(to=>{
  //   const {name,matched,path} = to
  //   console.log("zhou",to)
  //   if(matched.length>1){
  //       addMenu({name:String(name),path})
  //   }else{
  //     if(matched.length==1&&matched[0].name!="NotFound"){
  //       addMenu({name:String(name),path})
  //     }
  //   }
  // })


  const editableTabs = computed(()=>{
    return menuTab.value.openMenus.map(menu=>{
        return menu
    })
  }) 

  const calcName = (name:string)=>{
    if(name.endsWith("Edit")){  
      return transName(name.split("Edit")[0])+"-更新"
    }
    if(name.endsWith("Create")){  
      return transName(name.split("Create")[0])+"-创建"
    }
    if(name.endsWith("View")){  
      return transName(name.split("View")[0])+"-查看"
    }
    return transName(name)
  }

  const transName = (name:string)=>{
    return useI18n().t('global.menu.entities.'+strFromatter(name))
  }

  // 字符串格式化
  // 将首字母改为小写
  const strFromatter = (str:string)=>{
    let s1 = str[0].toLowerCase()
    let s2 = str.substr(1)
    return s1+s2
  }

    // 处理页签下拉菜单的方法
    // 关闭当前
    const handleCloseCurrent = (menu:menu)=>{
        deleteMenu(menu.name)
    }
    // 关闭右侧
    const handleCloseAllAfterRight = (menu:menu)=>{
        let openMenus = menuTab.value.openMenus
        let activeKey = menuTab.value.activeKey
        let arr:menu[] = [] //记录所有应该被打开的页签
        let flag = true //找到触发事件的页签之后置为false
        let resetActiveKey = true //在找到触发事件的页签之前如果找到了当前激活的页签那么就不用重置当前激活的页签，否则则需要重置
        for(let i=0;i<openMenus.length;i++){
            if(!flag){
                break;
            }
            let item = openMenus[i]
            if(item.name==menu.name){
                flag = false
            }
            if(item.name==activeKey){
                resetActiveKey = false
            }
            arr.push(item)
        }
        menuTab.value.openMenus = arr
        if(resetActiveKey){
            menuTab.value.activeKey = menu.name
            router.push(menu.path)
        }
    }
    // 关闭全部
    const handleCloseAll = ()=>{
        menuTab.value.openMenus = []
        menuTab.value.activeKey = 'home'
    }
    // 关闭其他
    const handleCloseOther = (menu:menu)=>{
        menuTab.value.openMenus = [menu]
        menuTab.value.activeKey = menu.name
        router.push(menu.path)
    }
  
  </script>
  
  <style lang="scss">
    .common-tab-wrapper{
      background: #fff;
      margin: 10px -10px 0px;
      border-radius: 10px;
      .custom-tabs-label {
        display: flex;
        align-items: center;
      }
      .el-tabs__item.is-top.is-active{
        i{
          color: #011c4a !important;
        }
        .custom-tabs-label {
          color: #011c4a !important;
          font-weight: bold;
          font-size: 16px;
        }
      }
    }
    .el-tabs__new-tab{
        display: none;
    }
  </style>
  