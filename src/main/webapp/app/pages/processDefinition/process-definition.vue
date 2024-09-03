<template>
    <div>
        <div class="process-definition-title">
            <h2>流程定义</h2>
        </div>
        <el-form :inline="true" :model="queryCondition" class="demo-form-inline">
            <el-form-item label="标识">
                <el-input v-model="queryCondition.key" placeholder="流程定义标识" clearable style="width: 240px" />
            </el-form-item>
            <el-form-item label="名称">
                <el-input v-model="queryCondition.name" placeholder="流程定义名称" clearable style="width: 240px" />
            </el-form-item>
        </el-form>
        <div class="form-buttons">
            <el-button type="primary" @click="onQuery">查询</el-button>
            <el-button type="primary" @click="onQuery">重置</el-button>
            <el-button type="primary" @click="onQuery">新增</el-button>
        </div>
        <div class="table-wrapper">
            <el-table :data="tableData" style="width: 100%">
                <el-table-column prop="date" label="序号" />
                <el-table-column prop="name" label="流程标识" />
                <el-table-column prop="address" label="流程名称" />
                <el-table-column prop="address" label="最后修改时间" />
            </el-table>
        </div>
    </div>
</template>

<script setup lang='ts'>
import axios from 'axios';
import { ref, reactive, onMounted } from 'vue'

const queryCondition = reactive({
    key: '',
    name: ''
})

const tableData: any[] = []




const onQuery = () => {
    console.log('onQuery!')
}

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