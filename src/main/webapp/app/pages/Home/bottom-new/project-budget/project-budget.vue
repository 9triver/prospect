
<template>
  <div>
    <div class="home-card-title">预算预警</div>
    <div class="home-card-more">more</div>
    <el-table :data="dataSource">
        <el-table-column label="项目名称" prop="projectName"></el-table-column>
        <el-table-column label="预算使用情况">
            <template #default="scope">
                <el-progress
                    :text-inside="true"
                    :stroke-width="22"
                    :percentage="(scope.row.cost/scope.row.budget*100).toFixed(2)"
                    :status="calculateStatus(scope.row)"
                />
                <span>{{}}</span>
            </template>
        </el-table-column>
    </el-table>
  </div>
</template>

<script setup lang='ts'>
import { ref, reactive} from 'vue'

    interface budget{
        projectName:string,
        budget:string,
        cost:string
    }

    const dataSource:budget[] = [
        {
            projectName:"子产品1研制",
            budget:"31644.96",
            cost:"31000"
        },
        {
            projectName:"子产品2研制",
            budget:"6114.06",
            cost:"6000"
        },
        {
            projectName:"子产品3研制",
            budget:"31644.96",
            cost:"27000"
        },
        {
            projectName:"子产品4研制",
            budget:"6114.06",
            cost:"4000"
        }
    ]

    const calculateStatus = (row:budget)=>{
        let percent = Number(row.cost)/Number(row.budget)*100
        if(percent>90){
            return "exception"
        }
        if(percent>80){
            return "warning"
        }
        return "success"
    }


</script>
<style lang='scss' scoped>
    
</style>