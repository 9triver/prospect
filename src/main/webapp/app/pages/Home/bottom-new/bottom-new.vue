<template>
  <div>
    <el-row class="home-page-row" :gutter="20">
        <el-col class="home-page-col" :span="12">
            <div class="col-content">
                <WorkList/>
            </div>
        </el-col>
        <el-col class="home-page-col" :span="12">
            <div class="col-content">
                <TaskList/>
            </div>
        </el-col>
        <el-col class="home-page-col" :span="12">
            <div class="col-content">
                <OverdueList/>
            </div>
        </el-col>
        <el-col class="home-page-col" :span="12">
            <div class="col-content">
                <ProjectBudget/>
            </div>
        </el-col>
    </el-row>
  </div>
</template>

<script setup lang='ts'>
import { ref, reactive, type StyleHTMLAttributes, type CSSProperties} from 'vue'
import WorkList from './WorkList/WorkList.vue';
import TaskList from './TaskList/task-list.vue';
import OverdueList from './overdueList/overdue-list.vue';
import ProjectBudget from './project-budget/project-budget.vue';
</script>
<style lang='scss' scoped>
    .home-page-row{
        margin: 0px 5px !important; 
        .home-page-col{
            margin: 10px 0px;
            .col-content{
                height: 275px;
                border: 1px solid #ccc;
                padding: 10px 20px;
                border-radius: 10px;
                position: relative;
            }
        }
    }
</style>