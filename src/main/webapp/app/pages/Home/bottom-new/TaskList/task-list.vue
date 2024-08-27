<template>
  <div>
    <div class="home-card-title">工作任务</div>
    <div class="home-card-more">more</div>
    <el-table :data="dataSource">
        <el-table-column label="项目" prop="projectName"></el-table-column>
        <el-table-column label="任务" prop="taskName"></el-table-column>
        <el-table-column label="优先级" prop="priority"></el-table-column>
        <el-table-column label="状态" prop="status"></el-table-column>
        <el-table-column label="截至时间" prop="deadline"></el-table-column>
        <el-table-column label="操作">
            <template #default="scope">
                <el-button>处理</el-button>
            </template>
        </el-table-column>
    </el-table>
  </div>
</template>

<script setup lang='ts'>
import { ref, reactive} from 'vue'

const dataSource = [
    {
        projectName:"组件a项目",
        taskName:"需求确认",
        deadline:"2024-08-30",
        priority:"普通",
        status:"新增"
    },
    {
        projectName:"组件b项目",
        taskName:"详细设计",
        deadline:"2024-08-30",
        priority:"普通",
        status:"正在处理"
    },
    {
        projectName:"组件c项目",
        taskName:"开发测试",
        deadline:"2024-08-30",
        priority:"普通",
        status:"新增"
    },
    {
        projectName:"组件d项目",
        taskName:"部署上线",
        deadline:"2024-08-30",
        priority:"普通",
        status:"新增"
    },
]

</script>
<style lang='scss' scoped>

</style>