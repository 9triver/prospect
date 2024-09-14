<template>
    <div class="process-deployment-new" v-if="!loading">
      <div style="width: 55%;height: 100%;">
        <Modeler :defaultProcessXml="xml"/>
      </div>
      <Panel/>
      <BpmnActions/>
    </div>
  </template>
  
  <script setup lang='ts'>
  import { ref, reactive, onMounted} from 'vue'
  import Modeler from './bpmn/components/modeler/Modeler'
  import Panel from './bpmn/components/panel/Panel';
  import BpmnActions from './bpmn/components/bpmn-actions';
  import {useRoute,useRouter} from 'vue-router'
import axios from 'axios';

  const route = useRoute()
  const {processDefinitionId} = route.query
  const loading = ref(true)

  const xml = ref<string|null>(null)

  onMounted(async()=>{
    if(processDefinitionId){
      xml.value = await getXml()
    }
    loading.value = false
  })

  const getXml = async()=>{
    const res = await axios.post('api/processPreview',{
      id:processDefinitionId
    })
    return res.data
  }

  </script>
  <style lang='scss' scoped>
    .process-deployment-new{
      height: 700px;
    }
  </style>