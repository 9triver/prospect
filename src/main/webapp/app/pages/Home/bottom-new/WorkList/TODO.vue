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
    import useMenuTabStore from '@/store/model/menuTabs';

    interface toDO{
        Task_Name_:string,
        PROC_NAME_:string,
        CREATE_TIME_:string,
        NO:string,
        PROC_INST_ID_:string,
        TASK_ID_:string
    }

    const menuTabStore = useMenuTabStore()
    const {addMenu} = menuTabStore
    const props = defineProps({
        assignee:String
    })

    const tableData = ref<toDO[]>()
    
    const handleClick = async(index:number,row:toDO)=>{
        addMenu({
            name:row.PROC_NAME_,
            path:"/workSpace",
            title:row.PROC_NAME_,
            icon:"",
            query:{
                TASK_ID_:row.TASK_ID_,
                PROC_INST_ID_:row.PROC_INST_ID_
            }
        })
        // await axios.post(
        //     "api/handletask",
        //     row.TASK_ID_,
        //     {
        //         headers: {
        //             'Content-Type': 'text/plain'
        //         }
        //     }
        // )
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
  