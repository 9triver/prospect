<template>
  <div>
    <img :src="url" alt="" srcset="">
  </div>
</template>

<script setup lang='ts'>
import { ref, reactive, computed} from 'vue'
    const props = defineProps({
        fileInfo:String as any
    })

  const url = computed(()=>{
    if(props.fileInfo){
      let _url = window.URL.createObjectURL(props.fileInfo)
      return _url
    }
    return ""
  })
	// console.log(url)
</script>
<style lang='scss' scoped>
  img{
    width: 100%;
  }
</style>