<template>
    <div>
      <div class="home-card-title">逾期任务</div>
      <div class="home-card-more">more</div>
      <el-table :data="dataSource">
          <el-table-column label="项目" prop="projectName"></el-table-column>
          <el-table-column label="任务" prop="taskName"></el-table-column>
          <el-table-column label="截至时间" prop="deadline"></el-table-column>
          <el-table-column label="逾期天数" prop="overdueTime"></el-table-column>
      </el-table>
    </div>
  </template>
  
  <script setup lang='ts'>
  import { ref, reactive} from 'vue'
  
  const dataSource = [
      {
          projectName:"组件d项目",
          taskName:"需求确认",
          deadline:"2024-08-30",
          overdueTime:"3天"
      },
      {
          projectName:"组件e项目",
          taskName:"详细设计",
          deadline:"2024-08-30",
          overdueTime:"3天"
      },
      {
          projectName:"组件f项目",
          taskName:"开发测试",
          deadline:"2024-08-30",
          overdueTime:"3天"
      },
      {
          projectName:"组件g项目",
          taskName:"部署上线",
          deadline:"2024-08-30",
          overdueTime:"3天"
      },
  ]
  
  </script>
  <style lang='scss' scoped>
  
  </style>