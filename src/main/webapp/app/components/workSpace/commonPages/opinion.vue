<template>
  <div class="title">
    <h2>岗位意见</h2>
  </div>
  <el-form label-width="120px" :model="opinion">
    <el-form-item label="岗位意见">
      <el-input v-model="opinion.content" type="textarea" />
    </el-form-item>
  </el-form>
  <div class="form-buttons">
    <el-button type="primary" @click="savePositionOpinion">保存</el-button>
  </div>
  <div class="all-opinion-list">
    <el-table :data="allOpinionList" style="width: 100%">
      <el-table-column label="序号" width="180">
        <template #default="scope">
          {{ scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column prop="taskName" label="岗位名称"  />
      <el-table-column prop="assignee" label="操作员名称"  />
      <el-table-column label="提交时间"  >
        <template #default="scope">
          {{ moment.tz(scope.row.time, "Asia/Shanghai").tz("UTC").format('YYYY-MM-DD HH:mm:ss') }}
        </template>
      </el-table-column>
      <el-table-column prop="positionOpinion" label="意见"  />
    </el-table>
  </div>
</template>

<script setup lang='ts'>
import axios from 'axios';
import { ref, reactive, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import moment from 'moment-timezone';

interface IDynamicComponentProp {
  taskId: string;
  procInstId: string;
}
interface IOpinion {
  id: string,
  taskId: string,
  taskName : string,
  assignee : string,
  procInstId: string,
  positionOpinion: string
  time: string
}

const opinion = ref({
  content: ''
})

// 所有岗位意见
const allOpinionList = ref<IOpinion[]>()

const props = defineProps({
  dynamicComponentProp: {
    type: Object as () => IDynamicComponentProp,
    default: () => ({})
  }
})
const savePositionOpinion = async () => {
  const { taskId, procInstId } = props.dynamicComponentProp
  console.log("taskId", taskId, "procInstId", procInstId)
  let res = await axios.post("api/savePositionOpinion2", {
    taskId: taskId,
    procInstId: procInstId,
    positionOpinion: opinion.value.content
  })
  if (res.data) {
    ElMessage.success(res.data.message)
  }
}
onMounted(async () => {
  let res = await axios.post("api/getPositionOpinion2", {
    taskId: props.dynamicComponentProp.taskId,
    procInstId: props.dynamicComponentProp.procInstId
  })
  opinion.value.content = res.data.positionOpinion

  let R_allOpinionList = await axios.post("api/getAllOpinionList", {
    procInstId: props.dynamicComponentProp.procInstId,
    taskId: props.dynamicComponentProp.taskId
  })
  allOpinionList.value = R_allOpinionList.data

})

</script>
<style lang='scss' scoped>
.title {
  text-align: center;
}

.form-buttons {
  text-align: center;
}
</style>