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
        <router-view/>
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
    const {name,matched} = to
    if(matched[1]){
        addMenu(String(name))
    }
  })


  const editableTabs = computed(()=>{
    return menuTab.value.opendMenus.map(menu=>{
        return{
        title: menu,
        name: menu,
        content: menu,
        }
    })
  }) 


  
  
  </script>
  
  <style>
    .el-tabs__new-tab{
        display: none;
    }
  </style>
  