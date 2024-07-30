<template>
  <el-tabs v-model="curSelectTab">
    <el-tab-pane name="project">
      <template #label>
        <span class="custom-tabs-label">
          <el-icon><User /></el-icon>
          <span>项目全局工作台</span>
        </span>
      </template>
      <Project v-if="curSelectTab=='project'"/>
    </el-tab-pane>
    <el-tab-pane name="depart">
      <template #label>
        <span class="custom-tabs-label">
          <el-icon><UserFilled /></el-icon>
          <span>部门领导工作台</span>
        </span>
      </template>
      <Project v-if="curSelectTab=='depart'"/>
    </el-tab-pane>
    <el-tab-pane name="group">
      <template #label>
        <span class="custom-tabs-label">
          <el-icon><Avatar /></el-icon>
          <span>课题组工作台</span>
        </span>
      </template>
      <Project v-if="curSelectTab=='group'"/>
    </el-tab-pane>
  </el-tabs>
</template>

<script lang="ts" setup>
  import {ref} from 'vue'
  import Project from "./Content/project/index.vue";
  const curSelectTab = ref("project")  

</script>

<style>
  /* .el-tabs__nav-scroll{
    background: #fff;
    padding: 5px 10px;
  }
  .el-tabs__header{
    margin: 10px 0px
  } */
</style>
