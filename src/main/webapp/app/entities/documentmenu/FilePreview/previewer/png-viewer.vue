<template>
  <div>
    <!-- {{fileInfo}} -->
    <img :src="url" alt="" srcset="">
  </div>
</template>

<script setup lang='ts'>
import { ref, reactive} from 'vue'
    const props = defineProps({
        fileInfo:String as any
    })

    const url = ref(window.URL.createObjectURL(new Blob([props.fileInfo])));
	console.log(url)
</script>
<style lang='scss' scoped>

</style>