<template>
  <div class="work-space-wrapper">
    <!-- <pre>{{ JSON.stringify(businessUnit, null, 4) }}</pre> -->
    <div class="step-wrapper" >
      <!-- {{activeStep}} -->
      <el-steps direction="vertical" :active="activeStep" style="height: 300px; max-width: 600px">
        <el-step v-for="(item, index) in businessUnit" :title="item.name" @click="handleSwitch(item,index)" />
      </el-steps>
    </div>
    <div class="main-content-wrapper">
      <KeepAlive>
        <component :is="dynamicComponent" :dynamicComponentProp="dynamicComponentProp"/>
      </KeepAlive>
      <!-- <component :is="dynamicComponent" /> -->
    </div>
    <div class="operator-buttons">
      <el-button type="primary" @click="submitTask">提交</el-button>
      <el-button>取消</el-button>
    </div>
  </div>
</template>

<script setup lang='ts'>
import router from '@/router';
import axios from 'axios';
import { ref, reactive, onMounted, defineAsyncComponent, type DefineComponent } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute();
const { TASK_ID_,PROC_INST_ID_ } = route.query
interface IBusinessUnit {
  name: string,
  path: string,
  ci?: DefineComponent,
}
const businessUnit = ref<IBusinessUnit[]>()
const dynamicComponent = ref<DefineComponent>()
const dynamicComponentProp = {
  taskId: TASK_ID_,
  procInstId :PROC_INST_ID_
}
const activeStep = ref<number>(1)

// 切换业务单元
const handleSwitch = async(item: IBusinessUnit,index:number) => {
  activeStep.value = index
  const path = item.path
  const viteComponents:Record<string,Function> = import.meta.glob('@/**/*.vue')
  const module = await viteComponents[path]()
  dynamicComponent.value = module.default
}

// 提交任务
const submitTask = async () => {
  const res = await axios.post("api/submitTask", {
    taskId: TASK_ID_,
    procInstId :PROC_INST_ID_
  })
  if(res.data.assignee){
    router.push({ name: 'feedbackInfo', query: {assignee:res.data.assignee}})
  }else{
    router.push({ name: 'feedbackInfo', query: {flowEnd:1}})
  }
}

onMounted(async () => {
  const res = await axios.post("api/getTaskInfoByTaskId", {
    taskId: TASK_ID_
  })
  res.data = res.data.map((item:IBusinessUnit)=>{
    return {
      ...item,
      path: "/app"+item.path.replaceAll("\\","/")
    }
  })
  businessUnit.value = res.data
  // 刚进来工作台的时候加载第一个业务单元
  if(res.data.length > 0){
    const path = res.data[0].path
    const viteComponents:Record<string,Function> = import.meta.glob('@/**/*.vue')
    const module = await viteComponents[path]()
    dynamicComponent.value = module.default
  }
})

</script>
<style lang='scss' scoped>
  .work-space-wrapper{
    display: flex;
    .step-wrapper{
      height: calc(100vh - 200px);
      flex-basis: 200px;
      &::after{
        content: "";
        display: block;
        width: 2px;
        height: 100%;
        background: linear-gradient(244deg, rgba(255, 255, 255, 0) 0%, #dcdfe6 50%, rgba(255, 255, 255, 0) 100%);
        position: absolute;
        left: 200px;
        top: 0;
      }
    }
    .main-content-wrapper{
      flex: 1;
      padding: 30px 40px;
    }
    .operator-buttons{
      position: absolute;
      right: 0px;
      top: 0px;
    }
  }


</style>