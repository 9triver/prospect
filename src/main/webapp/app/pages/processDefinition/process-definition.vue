<template>
    <div>
        <div class="process-definition-title">
            <h2>流程定义</h2>
        </div>
        <el-form :inline="true" :model="queryCondition" class="demo-form-inline" ref="formRef">
            <el-form-item label="标识" prop="key">
                <el-input v-model="queryCondition.key" placeholder="流程定义标识" clearable style="width: 240px" />
            </el-form-item>
            <el-form-item label="名称" prop="name">
                <el-input v-model="queryCondition.name" placeholder="流程定义名称" clearable style="width: 240px" />
            </el-form-item>
        </el-form>
        <div class="form-buttons">
            <el-button type="primary" @click="onQuery">查询</el-button>
            <el-button type="primary" @click="onReset">重置</el-button>
            <el-button type="primary" @click="onQuery">新增</el-button>
        </div>
        <div class="table-wrapper">
            <el-table :data="tableData" style="width: 100%">
                <el-table-column label="序号" >
                    <template #default="scope">
                        <span>{{scope.$index+1}}</span>
                    </template>
                </el-table-column>
                <el-table-column prop="key" label="流程定义标识" />
                <el-table-column prop="name" label="流程定义名称" />
                <el-table-column prop="deploymentTime" label="部署时间" />
                <el-table-column label="操作" />
            </el-table>
        </div>
    </div>
</template>

<script setup lang='ts'>
import axios from 'axios';
import type { FormInstance } from 'element-plus';
import { ref, reactive, onMounted } from 'vue'

const queryCondition = ref({
    key: '',
    name: ''
})

interface processDefinition{
    id:string,
    key:string,
    name:string,
    deploymentTime:string
}

const tableData = ref<processDefinition[]>([])

// 查询数据
const onQuery = async() => {
    let processDefinition = await axios.post(
        "api/queryProcessDefinition",
        queryCondition.value
    )
    tableData.value =processDefinition.data
}
const formRef = ref<FormInstance>()
// 重置查询条件以及查询列表
const onReset = ()=>{
    formRef.value?.resetFields()
    onQuery()
}

// 初始化生命周期函数
onMounted(()=>{
    onQuery()
})



</script>
<style lang='scss' scoped>
.process-definition-title {
    text-align: center;
    margin-bottom: 20px;
}

.form-buttons {
    text-align: center
}
</style>