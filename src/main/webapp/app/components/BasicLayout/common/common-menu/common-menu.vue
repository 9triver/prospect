<template>
    <div class="common-menu-wrapper">
        <!-- <EntitiesMenu/> -->
        <el-menu 
        :collapse="collapse"
        >
            <el-sub-menu  v-for="(item,index) in menuConfig.children" :index="index">
                <template #title>
                    <el-icon><component :is="item.icon"/></el-icon>
                    <span>{{item.title}}</span>
                </template>
                <el-menu-item v-for="(subItem,subIndex) in item.children" :index="index+'-'+subIndex" @click="openMenu(subItem)">
                    <el-icon><component :is="subItem.icon"/></el-icon>
                    {{subItem.title}}
                </el-menu-item>
            </el-sub-menu>
        </el-menu>
    </div>
  </template>
  
  <script setup lang='ts'>
    import { ref, defineProps} from 'vue'
    import _menuConfig from './config.json'
    import {ElMessage} from 'element-plus'
    import useMenuTabStore from '@/store/model/menuTabs'

    interface _subItem{
        title:string,
        icon:string,
        path:string,
        name:string
    }

    const props = defineProps({
        collapse:Boolean
    })
    const menuConfig = ref(_menuConfig)
    const menuTabStore = useMenuTabStore()
    const {addMenu} = menuTabStore
    const openMenu = (subItem:_subItem)=>{
        try{
            const {path} = subItem
            if(path){
                addMenu(subItem)
            }else{
                ElMessage({
                    message:'菜单未配置路由地址',
                    type: 'warning'
                })
            }
        }catch(e){
            console.error('zhou',e);
        }
    }

  </script>
  <style lang='scss'>
    .common-menu-wrapper{
        --el-menu-bg-color:#011c4a; //菜单背景颜色
        --el-menu-text-color:#fff; //菜单字体颜色
        --el-menu-border-color:#011c4a; 
        .el-sub-menu>ul.el-menu{
            background: #010d21;
            // box-shadow: 0px 0px 20px 0px #010d21;
        }
        .el-menu{
            .el-sub-menu__title{
                background: #011c4a;
                color: #fff;
            }
            // 父级菜单悬浮样式
            .el-sub-menu__title:hover{
                background: #fff;
                color: #011c4a;
            }
            li{
                &:hover{
                    background: #fff;
                    color: #011c4a;
                }
            }
            // 菜单选中样式
            li.el-menu-item.is-active{
                background: #fff;
                color: #011c4a;
                &::after{
                    content: "";
                    display: block;
                    height: 100%;
                    width: 5px;
                    background: #0030d675;
                    position: absolute;
                    right: -1px;
                }
            }
        }
    }
  </style>