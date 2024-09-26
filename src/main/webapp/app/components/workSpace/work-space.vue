<template>
  <div class="work-space-wrapper">
    <!-- <pre>{{ JSON.stringify(businessUnit, null, 4) }}</pre> -->
    <div class="step-wrapper">
      <!-- {{activeStep}} -->
      <el-steps direction="vertical" :active="activeStep" style="height: 300px; max-width: 600px">
        <el-step v-for="(item, index) in businessUnit" :title="item.name" @click="handleSwitch(item, index)" />
      </el-steps>
    </div>
    <div class="right-content-wrapper">
      <div class="float-header">
        <el-affix :offset="120" style="height: 100%">
          <el-tooltip
            class="box-item"
            effect="dark"
            content="显示流程图"
            placement="top-start"
          >
            <svg style="height: 25px;width: 25px;" t="1727162349707" class="icon" viewBox="0 0 1024 1024" version="1.1"
              xmlns="http://www.w3.org/2000/svg" p-id="1515" width="200" height="200"
              @click="handleShowFlow"
              >
              <path
                d="M1024 938.665984C1024 985.812992 985.812992 1024 938.665984 1024H85.334016C38.187008 1024 0 985.812992 0 938.665984V85.334016C0 38.187008 38.187008 0 85.334016 0h853.331968C985.812992 0 1024 38.187008 1024 85.334016v853.331968z"
                fill="#0a22bc" p-id="1516"></path>
              <path
                d="M615.936 801.531904c-10.799104 0-14.8992-1.200128-24.100864-5.89824l-0.299008-0.200704-189.200384-97.601536c-17.299456-8.298496-28.499968-25.899008-28.499968-45.19936 0-19.2 11.200512-36.900864 28.499968-45.19936l189.50144-97.800192c10.799104-5.400576 18.098176-5.900288 24.098816-5.900288 6.00064 0 13.299712 0.499712 24.100864 5.89824l0.299008 0.200704 189.50144 97.800192c17.39776 8.800256 28.198912 26.001408 28.198912 45.000704s-10.801152 36.200448-28.20096 45.000704l-186.90048 96.499712c-9.699328 7.399424-19.99872 7.399424-26.998784 7.399424z m-0.299008-53.100544l0.299008 0.1024 0.100352-0.1024 185.6-95.799296-185.401344-95.700992-0.299008-0.098304-0.299008 0.098304-185.401344 95.700992 185.401344 95.799296zM768.835584 459.931648H463.036416c-48.300032 0-87.601152-39.30112-87.601152-87.599104V311.13216c0-48.300032 39.30112-87.601152 87.601152-87.601152h305.89952c48.300032 0 87.599104 39.30112 87.599104 87.601152v61.200384c0 48.300032-39.399424 87.599104-87.699456 87.599104z m-305.799168-183.5008c-19.099648 0-34.701312 15.601664-34.701312 34.701312v61.200384c0 19.099648 15.601664 34.699264 34.701312 34.699264h305.89952c19.099648 0 34.699264-15.599616 34.699264-34.699264V311.13216c0-19.099648-15.599616-34.699264-34.699264-34.699264H463.036416zM303.036416 679.131136h-108.40064c-14.600192 0-26.50112-11.798528-26.50112-26.499072 0-14.600192 11.800576-26.50112 26.50112-26.50112h108.500992c14.600192 0 26.50112 11.800576 26.50112 26.50112-0.1024 14.700544-11.900928 26.50112-26.60352 26.50112zM275.33312 369.031168H194.635776c-14.600192 0-26.50112-11.798528-26.50112-26.499072 0-14.600192 11.800576-26.50112 26.50112-26.50112h80.699392c14.60224 0 26.50112 11.800576 26.50112 26.50112-0.100352 14.600192-11.89888 26.50112-26.50112 26.50112z"
                fill="#FFFFFF" p-id="1517"></path>
              <path
                d="M194.635776 679.131136c-14.600192 0-26.50112-11.798528-26.50112-26.499072V348.532736c0-14.60224 11.800576-26.50112 26.50112-26.50112 14.600192 0 26.50112 11.800576 26.50112 26.50112v304.19968c-0.1024 14.600192-11.900928 26.39872-26.50112 26.39872z m79.29856-252.299264v-167.19872l83.601408 83.59936-83.59936 83.59936z"
                fill="#FFFFFF" p-id="1518"></path>
            </svg>
          </el-tooltip>
          <div class="operator-buttons">
            <el-button type="primary" @click="submitTask">提交</el-button>
            <el-button @click="handleBackFlow">退回</el-button>
          </div>
        </el-affix>
      </div>
      <div class="main-content-wrapper">
        <KeepAlive>
          <component :is="dynamicComponent" :dynamicComponentProp="dynamicComponentProp" />
        </KeepAlive>
        <!-- <component :is="dynamicComponent" /> -->
      </div>
    </div>
    <div class="flow-picture-wrappe" v-if="showFlowPicture">
      <FlowPicture :procInstId="PROC_INST_ID_" @close-preview-dialog="showFlowPicture = false" />
    </div>
    <div class="back-flow-list" v-if="showBackFlowList" >
      <BackFlowList :taskId="TASK_ID_" @closeBackDialog="showBackFlowList = false" />
    </div>
  </div>
</template>

<script setup lang='ts'>
import router from '@/router';
import axios from 'axios';
import { ref, reactive, onMounted, defineAsyncComponent, type DefineComponent } from 'vue'
import { useRoute } from 'vue-router'
import FlowPicture from './components/flowPicture/flow-picture.vue';
import BackFlowList from './components/backFlowList/back-flow-list.vue';

const route = useRoute();
const { TASK_ID_, PROC_INST_ID_, PROC_DEF_ID_ } = route.query
interface IBusinessUnit {
  name: string,
  path: string,
  ci?: DefineComponent,
}
const businessUnit = ref<IBusinessUnit[]>()
const dynamicComponent = ref<DefineComponent>()
const dynamicComponentProp = {
  taskId: TASK_ID_,
  procInstId: PROC_INST_ID_
}
const activeStep = ref<number>(1)
const showFlowPicture = ref<boolean>(false)
const showBackFlowList = ref<boolean>(false)

// 切换业务单元
const handleSwitch = async (item: IBusinessUnit, index: number) => {
  activeStep.value = index
  const path = item.path
  const viteComponents: Record<string, Function> = import.meta.glob('@/**/*.vue')
  const module = await viteComponents[path]()
  dynamicComponent.value = module.default
}

// 提交任务
const submitTask = async () => {
  const res = await axios.post("api/submitTask", {
    taskId: TASK_ID_,
    procInstId: PROC_INST_ID_
  })
  if (res.data.assignee) {
    router.push({ name: 'feedbackInfo', query: { assignee: res.data.assignee } })
  } else {
    router.push({ name: 'feedbackInfo', query: { status: "end" } })
  }
}

// 任务驳回
const handleBackFlow = () => {
  showBackFlowList.value = true
}

onMounted(async () => {
  const res = await axios.post("api/getTaskInfoByTaskId", {
    taskId: TASK_ID_
  })
  res.data = res.data.map((item: IBusinessUnit) => {
    return {
      ...item,
      path: "/app" + item.path.replaceAll("\\", "/")
    }
  })
  businessUnit.value = res.data
  // 刚进来工作台的时候加载第一个业务单元
  if (res.data.length > 0) {
    const path = res.data[0].path
    const viteComponents: Record<string, Function> = import.meta.glob('@/**/*.vue')
    const module = await viteComponents[path]()
    dynamicComponent.value = module.default
  }
})

// 显示流程图
const handleShowFlow = ()=>{
  showFlowPicture.value = true
}

</script>
<style lang='scss' scoped>
.work-space-wrapper {
  display: flex;

  .step-wrapper {
    height: calc(100vh - 200px);
    flex-basis: 200px;

    &::after {
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

  .right-content-wrapper {
    display: flex;
    flex: 1;
    flex-direction: column;

    .main-content-wrapper {
      flex: 1;
      padding: 30px 40px;
    }

    .float-header {
      flex-basis: 50px;
      svg {
        cursor: pointer;
        &:hover {
          transition: all 0.3s;
          path {
            &:nth-child(1) {
              fill: #687bf7;
              transition: all 0.3s;
            }
          }
        }

      }
      .operator-buttons {
        position: absolute;
        right: 0px;
        top: 0px;
      }
    }
  }
}
</style>