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
            :label="calcName(item.title)"
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
  import { useI18n } from 'vue-i18n';

  let menuTabStore = useMenuTabStore()
  const {menuTab} = storeToRefs(menuTabStore)
  const {addMenu,deleteMenu,undateMenu} = menuTabStore


  const router = useRouter()
  router.afterEach(to=>{
    const {name,matched,path} = to
    console.log("zhou",to)
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
  