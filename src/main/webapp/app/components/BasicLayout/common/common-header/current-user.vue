<template>
  <div class='current-user-wrapper common-header-item'>
    <el-dropdown>
      <span class="el-dropdown-link">
        {{username}} [超级管理员]
      </span>
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item @click="setUser">编辑资料</el-dropdown-item>
          <el-dropdown-item @click="logout">注销</el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>
  </div>
</template>

<script setup lang='ts'>
import { inject } from 'vue'
import {useRouter} from 'vue-router'
import { useStore } from '@/store';
const router = useRouter();
const store = useStore();

import useMenuTabStore from '@/store/model/menuTabs/index'
const username = inject('currentUsername');

let menuTabStore = useMenuTabStore()
const {addMenu} = menuTabStore


const setUser = ()=>{
  addMenu({path:"/account/settings",name:"Settings",title:"用户设置",icon:"User"})
}

const logout = ()=>{
  localStorage.removeItem('jhi-authenticationToken');
  sessionStorage.removeItem('jhi-authenticationToken');
  store.logout();
  router.push('/login');
}

</script>
<style lang='scss' scoped>

</style>