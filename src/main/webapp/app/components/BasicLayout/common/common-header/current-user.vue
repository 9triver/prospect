<template>
  <div class='current-user-wrapper common-header-item'>
    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1024 1024" ><path fill="currentColor" d="M288 320a224 224 0 1 0 448 0 224 224 0 1 0-448 0m544 608H160a32 32 0 0 1-32-32v-96a160 160 0 0 1 160-160h448a160 160 0 0 1 160 160v96a32 32 0 0 1-32 32z"></path></svg>
    <span @click="setUser">
      {{username}}[xxx部门]
    </span>
  </div>
</template>

<script setup lang='ts'>
import { inject } from 'vue'
import {useRouter} from 'vue-router'
import useMenuTabStore from '@/store/model/menuTabs/index'
const username = inject('currentUsername');

let menuTabStore = useMenuTabStore()
const {addMenu} = menuTabStore

const router = useRouter()

const setUser = ()=>{
  router.push("/account/settings")
  addMenu({name:'账户设置',path:"/account/settings"})
}

</script>
<style lang='scss' scoped>

</style>