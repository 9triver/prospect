<template>
    <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="NO" label="序号" />
        <el-table-column prop="PROC_NAME_" label="流程名称" width="180"/>
        <el-table-column prop="Task_Name_" label="当前任务" width="180" />
        <el-table-column prop="CREATE_TIME_" label="创建时间" width="180" />
        <el-table-column label="操作">
            <template #default="scope">
                <el-button link type="primary" size="small" @click="handleClick(scope.$index, scope.row)">
                处理
                </el-button>
            </template>
        </el-table-column>
    </el-table>
  </template>
  
<script lang="ts" setup>
    import axios from 'axios';
import moment from 'moment';
    import {ref,onMounted,defineProps} from 'vue'

    const props = defineProps({
        assignee:String
    })

    const tableData = ref([])

    const handleClick = async(index,row)=>{
        await axios.post(
            "api/handletask",
            row.TASK_ID_,
            {
                headers: {
                    'Content-Type': 'text/plain'
                }
            }
        )
        refrushTableData()
    }

    const refrushTableData = async()=>{
        let dataSource = await axios.post(
            "api/todo",
            props.assignee,
            {
                headers: {
                    'Content-Type': 'text/plain'
                }
            }
        )
        tableData.value = dataSource.data.map((item,index)=>(
            {
                ...item,
                NO:index+1,
                CREATE_TIME_:moment(item.CREATE_TIME_).format("YYYY-MM-DD HH:mm:ss"),
            }
        ))
    }

    onMounted(async()=>{
        refrushTableData()
    })

</script>
  