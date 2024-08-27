<template>
  <div>
    <div class="home-card-title">审批流程</div>
    <div class="home-card-more">more</div>
    <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
      <el-tab-pane label="待办" name="TODO" lazy>
        <TODO :assignee="assignee"/>
      </el-tab-pane>
      <el-tab-pane label="在办" name="DOING" lazy>
        <DOING :assignee="assignee"/>
      </el-tab-pane>
      <el-tab-pane label="结办" name="DONE" lazy>
        <DONE :assignee="assignee"/>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script setup lang='ts'>
    import { ref,defineProps } from 'vue'
    import type { TabsPaneContext } from 'element-plus'

    import TODO from './TODO.vue';
    import DOING from './Doing.vue';
    import DONE from './DONE.vue';

    const props = defineProps({
        assignee:String
    })

    const activeName = ref('TODO')
    
    const handleClick = (tab: TabsPaneContext, event: Event) => {
        console.log(tab, event)
    }
</script>
<style lang='scss' >
  .home-card-title{
    font-size: 20px;
    font-weight: bold;
  }
  .home-card-more{
    position: absolute;
    right: 20px;
    top: 10px;
  }
</style>