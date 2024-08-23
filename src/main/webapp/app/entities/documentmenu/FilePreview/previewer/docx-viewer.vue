<template>
  <el-scrollbar height="calc(100vh - 100px)">
    <vue-office-docx
        :src="fileInfo"
        @rendered="renderedHandler"
        @error="errorHandler"
    />
  </el-scrollbar>
</template>

<script setup lang='ts'>
import { ref, reactive} from 'vue'
import VueOfficeDocx from '@vue-office/docx'//引入VueOfficeDocx组件
import '@vue-office/docx/lib/index.css'//引入相关样式

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