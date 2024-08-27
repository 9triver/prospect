<template>
  <el-scrollbar height="calc(100vh - 100px)">
    <vue-office-pdf 
        :src="fileInfo"
        @rendered="renderedHandler"
        @error="errorHandler"
    />
  </el-scrollbar>
</template>

<script setup lang='ts'>
import { ref, reactive} from 'vue'
import VueOfficePdf from '@vue-office/pdf'//引入VueOfficePdf组件

    const props = defineProps({
        fileInfo:Blob as any
    })

    const renderedHandler = ()=>{
        console.log("渲染完成")
    }
    const errorHandler = ()=>{
        console.log("渲染失败")
    }

</script>
<style lang='scss' scoped>

</style>