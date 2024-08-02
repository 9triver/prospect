<template>
    <div class="common-tab-wrapper">
        <el-tabs
          v-model="menuTab.activeKey"
          type="card"
          @tab-remove="deleteMenu"
          @tab-change="undateMenu"
        >
          <el-tab-pane key="home" label="主页" name="home"  />
          <el-tab-pane
            v-for="item in editableTabs"
            :key="item.name"
            :label="item.title"
            :name="item.name"
            closable
          />
        </el-tabs>
    </div>
  </template>
  
  <script lang="ts" setup>
  import { ref,computed} from 'vue'
  import useMenuTabStore from '@/store/model/menuTabs/index'
  import {useRouter} from 'vue-router'
  import {storeToRefs} from 'pinia'

  let menuTabStore = useMenuTabStore()
  const {menuTab} = storeToRefs(menuTabStore)
  const {addMenu,deleteMenu,undateMenu} = menuTabStore


  const router = useRouter()
  router.afterEach(to=>{
    const {name,matched,path} = to
    if(matched[1]){
        addMenu({name:String(name),path})
    }
  })


  const editableTabs = computed(()=>{
    return menuTab.value.openMenus.map(menu=>{
        return{
        title: menu.name,
        name: menu.name
        }
    })
  }) 


  
  
  </script>
  
  <style scoped>
    .common-tab-wrapper{
      background: #fff;
      margin: 10px -10px 0px;
      border-radius: 10px;
    }
    .el-tabs__new-tab{
        display: none;
    }
  </style>
  