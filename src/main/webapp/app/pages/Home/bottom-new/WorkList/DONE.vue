<template>
    <div>
        <el-table :data="tableData" style="width: 100%">
            <el-table-column prop="NO" label="序号" />
            <el-table-column prop="processDefinitionName" label="流程名称" width="180"/>
            <el-table-column prop="startTime" label="创建时间" width="180" />
            <el-table-column prop="endTime" label="结束时间" width="180" />
            <el-table-column prop="durationInMillis" label="持续时间" width="180" />
            <el-table-column label="操作">
                <template #default="scope">
                    <el-button link type="primary" size="small" @click="handleClick(scope.$index, scope.row)">
                    查看
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <!-- 每次打开drawer都重新查询数据 -->
        <el-drawer
            v-model="drawer"
            title="流程进度查看"
            direction="rtl"
            destroy-on-close
        >
            <ShowWorkHistory  :PROC_INST_ID_="curSelectProcId"/>
        </el-drawer>
    </div>
  </template>
  
<script lang="ts" setup>
    import { calcTime } from '@/utils/utils';
    import axios from 'axios';
    import moment from 'moment';
    import {ref,onMounted,defineProps} from 'vue'
    import ShowWorkHistory from './ShowWorkHistory.vue';

    interface done{
        processDefinitionId:string
        processInstanceId:string
        startTime:string
        endTime:string
        durationInMillis:string
        processDefinitionName:string
    }

    const props = defineProps({
        assignee:String
    })

    const tableData = ref([])
    const drawer = ref(false)
    const curSelectProcId = ref<string>("")

    const handleClick = async(index:number,row:done)=>{
        drawer.value = true
        curSelectProcId.value = row.processInstanceId
    }

    const refrushTableData = async()=>{
        let dataSource = await axios.post(
            "api/done",
            props.assignee,
            {
                headers: {
                    'Content-Type': 'text/plain'
                }
            }
        )
        tableData.value = dataSource.data.map((item:done,index:number)=>(
            {
                ...item,
                NO:index+1,
                startTime:moment(item.startTime).format("YYYY-MM-DD HH:mm:ss"),
                endTime:moment(item.endTime).format("YYYY-MM-DD HH:mm:ss"),
                durationInMillis:calcTime(item.durationInMillis)
            }
        ))
    }



    onMounted(async()=>{
        refrushTableData()
    })

</script>
  