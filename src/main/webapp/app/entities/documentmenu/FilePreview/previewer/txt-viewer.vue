<template>
  <pre>
    {{fileInfo}}
  </pre>
</template>

<!-- 预览txt文件的核心是展示出来起换行符，使用pre标签承载内容 -->
<script setup lang='ts'>
import { ref, reactive, defineProps} from 'vue'
    const props = defineProps({
        fileInfo:{
			type:String as any,
			require:true
		}
    })
</script>
<style lang='scss' scoped>

</style>