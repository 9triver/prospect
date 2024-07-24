<template>
  <div class="sinout-wrapper common-header-item" @click="logout">
      <svg width="32" height="32" viewBox="0 0 32 32" fill="none" xmlns="http://www.w3.org/2000/svg" data-v-746b41f0=""><path d="M32 0H0V32H32V0Z" fill="white" fill-opacity="0.01"></path><path d="M15.9945 4H4V28H16" stroke="black" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round"></path><path d="M22 22L28 16L22 10" stroke="black" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round"></path><path d="M10.6667 15.9949H28.0001" stroke="black" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round"></path></svg>
      <span>注销</span>
  </div>
</template>

<script setup lang='ts'>
import { useStore } from '@/store';
import { useRouter } from 'vue-router';
const router = useRouter();
const store = useStore();
async function logout(){
      localStorage.removeItem('jhi-authenticationToken');
      sessionStorage.removeItem('jhi-authenticationToken');
      store.logout();
      router.push('/login');
  }
</script>
<style lang='scss' >
  .common-header-item:hover{
    path:first-child{
      stroke: #fff !important;
    }
  }
</style>