<template>
    <div class="process-deployment-new">
      <div style="width: 55%;height: 100%;">
        <Modeler/>
      </div>
      <Panel/>
      <BpmnActions/>
    </div>
  </template>
  
  <script setup lang='ts'>
  import { ref, reactive} from 'vue'
  import Modeler from './bpmn/components/modeler/Modeler'
  import Panel from './bpmn/components/panel/Panel';
  import BpmnActions from './bpmn/components/bpmn-actions';
  </script>
  <style lang='scss' scoped>
    .process-deployment-new{
      height: 700px;
    }
  </style>