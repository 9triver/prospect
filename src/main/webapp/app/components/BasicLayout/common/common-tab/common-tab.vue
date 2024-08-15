<template>
    <div class="common-tab-wrapper">
        <el-tabs
          v-model="menuTab.activeKey"
          type="card"
          @tab-remove="deleteMenu"
          @tab-change="undateMenu"
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
              <span class="custom-tabs-label">
                <el-icon><component :is="item.icon"/></el-icon>{{item.title}}
              </span>
            </template>
          </el-tab-pane>
        </el-tabs>
    </div>
  </template>
  
  <script lang="ts" setup>
  import { computed} from 'vue'
  import useMenuTabStore from '@/store/model/menuTabs/index'
  import {storeToRefs} from 'pinia'
  import { useI18n } from 'vue-i18n';

  let menuTabStore = useMenuTabStore()
  const {menuTab} = storeToRefs(menuTabStore)
  const {deleteMenu,undateMenu} = menuTabStore


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
  