<template>
  <div>

    <el-result icon="success" title="流程结束" :sub-title="`任务已结束`" v-if="status=='end'">
    </el-result>
    <el-result icon="success" title="任务退回成功" :sub-title="`任务已退回至【${assignee}】`" v-if="status=='back'">
    </el-result>
    <el-result icon="success" title="提交成功" :sub-title="`任务已提交至【${assignee}】`" v-else>
      <template #extra>
        <!-- <el-button type="primary" @click="handleBack">Back</el-button> -->
      </template>
    </el-result>
  </div>
</template>

<script setup lang='ts'>
import { ref, reactive } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()
const { assignee, status } = route.query

const handleBack = () => {
  window.history.back()
}

</script>
<style lang='scss' scoped></style>