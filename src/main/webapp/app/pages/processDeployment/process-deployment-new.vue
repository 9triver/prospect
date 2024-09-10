<template>
  <div class="process-deployment-new">
    <Modeler/>
    <Panel/>
    <!-- <BpmnActions/> -->
  </div>
</template>

<script setup lang='ts'>
import { ref, reactive} from 'vue'
import Modeler from './bpmn/components/modeler/Modeler'
import Panel from './bpmn/components/panel/Panel';
// import BpmnActions from './model-creator-online/components/bpmn-actions';
</script>
<style lang='scss' scoped>
  .process-deployment-new{
    height: 700px;
  }
</style>