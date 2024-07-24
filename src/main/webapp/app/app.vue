<template>
  <div class="layout">
    <BasicLayout v-if="showIndex"/>
    <router-view v-else/>
  </div>
</template>

<script setup>
import { ref, provide} from 'vue'
import { useRouter } from 'vue-router'
import BasicLayout from '@/components/BasicLayout/basic-layout.vue'


const showIndex = ref(false)

function setshowIndex(_showIndex) {
  showIndex.value = _showIndex
}

provide('showIndex', {
  showIndex,
  setshowIndex
})


const router = useRouter()

router.afterEach((to)=>{
  let {path} = to
  if(path == '/login'){
    showIndex.value = false
  }else{
    showIndex.value = true
  }
})

router.beforeEach((to, from, next) => {
  let {path} = to
  if (path == '/login') {
    next()
  } else {
    if (!(sessionStorage.getItem('jhi-authenticationToken')||showIndex.value==true)) {
      // 没有token并且showIndex不为true
      next({ path: '/login' })
    } else {
      next()
    }
  }
})
</script>
