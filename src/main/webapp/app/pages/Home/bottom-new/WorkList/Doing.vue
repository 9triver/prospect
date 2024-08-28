<template>
    <div>
        <el-table :data="tableData" style="width: 100%">
            <el-table-column prop="NO" label="序号" />
            <el-table-column prop="PROC_NAME_" label="流程名称" width="180"/>
            <el-table-column prop="Task_Name_" label="当前任务" width="180" />
            <el-table-column prop="CREATE_TIME_" label="创建时间" width="180" />
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
    import axios from 'axios';
import moment from 'moment';
    import {ref,onMounted,defineProps} from 'vue'
    import ShowWorkHistory from './ShowWorkHistory.vue';

    interface Doing {
        Task_Name_: string
        PROC_DEF_ID_: string
        PROC_INST_ID_: string
        CREATE_TIME_: string
        TASK_ID_: string
        PROC_NAME_: string
    }

    const props = defineProps({
        assignee:String
    })

    const tableData = ref<Doing[]>([])
    const drawer = ref(false)
    const curSelectProcId = ref<string>("")

    const handleClick = (index:number,row:Doing)=>{
        drawer.value = true;
        curSelectProcId.value = row.PROC_INST_ID_
    }

    const refrushTableData = async()=>{
        let dataSource = await axios.post(
            "api/doing",
            props.assignee,
            {
                headers: {
                    'Content-Type': 'text/plain'
                }
            }
        )
        tableData.value = dataSource.data.map((item:Doing,index:number)=>(
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
  