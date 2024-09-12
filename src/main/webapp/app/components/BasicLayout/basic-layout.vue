<template>
  <el-container class="layout-container-cvicse" style="height: 100vh">
    <el-aside :class="`basic-layout-aside ${collapse?'collapse-menu':''}`">
      <div class="logo-wrapper">项目管理系统</div>
      <el-scrollbar>
        <commonMenu :collapse="collapse"/>
      </el-scrollbar>
    </el-aside>

    <el-container>
      <el-header style="text-align: right; font-size: 12px">
        <div class="collapse-operator" @click="collapse = !collapse">
          <el-icon >
            <Expand v-if="collapse"/>
            <Fold v-else/>
          </el-icon>
        </div>
        <commonHeader/>
        <commonTab/>
      </el-header>
      <el-main class="main-content-wrapper">
        <el-scrollbar>
          <div v-show="!showHome">
            <router-view v-slot="{ Component,route }">
              <keep-alive v-if="shouldKeepAlive(route)">
                <component :is="Component" />
              </keep-alive>
              <component :is="Component" v-else/>
            </router-view>
          </div>
          <div v-show="showHome">
            <HomePage/>
          </div>
        </el-scrollbar>
      </el-main>
    </el-container>
  </el-container>
</template>

<script lang="ts" setup>
import { computed, ref} from 'vue'
import commonMenu from './common/common-menu/common-menu.vue';
import commonHeader from './common/common-header/common-header.vue';
import commonTab from './common/common-tab/common-tab.vue';
import useMenuTabStore from '@/store/model/menuTabs'
import HomePage from '@/pages/Home/index.vue'
import type { RouteLocationNormalizedLoadedGeneric } from 'vue-router';


const menuTabStore = useMenuTabStore()
const collapse = ref(false)

const showHome = computed(()=>{
  return !(menuTabStore.menuTab.openMenus.length>0&&menuTabStore.menuTab.activeKey!='home')
})

// 路由中配置了keepAlive: false的时候不缓存路由
const shouldKeepAlive = (route:RouteLocationNormalizedLoadedGeneric)=>{
  return route.meta.keepAlive != false
}

</script>

<style lang="scss" scoped>
.layout-container-cvicse .el-header {
  position: relative;
  background-color: #fff;
  color: var(--el-text-color-primary);
  box-shadow: 7px 0px 10px 0px #ccc;
}
.layout-container-cvicse .el-aside {
  color: var(--el-text-color-primary);
  background: #011c4a;
  padding-top: 60px;
}
.layout-container-cvicse .el-menu {
  border-right: none;
}
.layout-container-cvicse .el-main {
  padding: 0;
}
.layout-container-cvicse .toolbar {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  height: 100%;
  right: 20px;
}
.layout-container-cvicse{
  .logo-wrapper{
    height: 60px;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 30px;
    background: #fff;
    position: absolute;
    top: 0;
    color: #000;
    width: 200px;
    border-bottom: 1px solid #565353;
    background: #011c4a;
    border-bottom: none;
    color: #fff;
    font-family: cursive;
  }
  .main-content-wrapper{
    padding: 20px 30px;
    margin: 10px;
    margin-top: 50px;
    border-radius: 5px;
    background-color: #fff;
  }
}
.basic-layout-aside{
  width: 200px;
  transition: all .2s;
}

.collapse-menu{
  width: 64px;
  transition: all .2s;
}
.collapse-operator{
  position: absolute;
    font-size: 28px;
    cursor: pointer;
    height: 60px;
    width: 60px;
    left: 0;
    display: flex;
    align-items: center;
    justify-content: center;
  &:hover{
    background: #011c4a;
    color: #fff;
    transition: all .2s;
  }
}
</style>
