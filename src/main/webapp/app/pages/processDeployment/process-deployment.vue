<template>
    <div>
        <div class="process-definition-title">
            <h2>流程部署</h2>
        </div>
        <el-form :inline="true" :model="queryCondition" class="demo-form-inline">
            <el-form-item label="标识">
                <el-input v-model="queryCondition.name" placeholder="部署名称" clearable style="width: 240px" />
            </el-form-item>
        </el-form>
        <div class="form-buttons">
            <el-button type="primary" @click="onQuery">查询</el-button>
            <el-button type="primary" @click="onQuery">重置</el-button>
            <el-button type="primary" @click="handleAdd">新增</el-button>
        </div>
        <div class="table-wrapper">
            <el-table :data="calDataWithPagition" style="width: 100%;height:440px">
                <el-table-column prop="date" label="序号" >
                    <template #default="scope">
                        {{scope.$index+1}}
                    </template>
                </el-table-column>
                <el-table-column prop="name" label="部署名称" />
                <el-table-column prop="deploymentTime" label="部署时间" />
                <el-table-column prop="category" label="类别" />
            </el-table>
            <el-pagination background layout="prev, pager, next" :total="pagination.total" v-model:current-page="pagination['current-page']" @current-change="handleCurrentChange"/>
        </div>
    </div>
</template>

<script setup lang='ts'>
import router from '@/router';
import axios from 'axios';
import { ref, reactive, onMounted, computed } from 'vue'
import useMenuTabStore from '@/store/model/menuTabs'
const menuTabStore = useMenuTabStore()
const {addMenu} = menuTabStore

interface deployment {
    "id": string
    "name": string
    "deploymentTime": string
    "category": string
    "key": string
    "tenantId": string
}

const queryCondition = reactive({
    key: '',
    name: ''
})

const tableData  = ref<deployment[]>()
const pagination = ref({
    "total":0,
    "current-page":2,
    "page-size":10
})

const handleCurrentChange = (value:number)=>{
    pagination.value['current-page'] = value
}

const calDataWithPagition = computed(()=>{
    return tableData.value?.slice(pagination.value['page-size']*(pagination.value['current-page']-1),pagination.value['page-size']*pagination.value['current-page'])
})

onMounted(async () => {
    onQuery()
})

const onQuery = async () => {
    let deployments = await axios.get(
        "api/queryProcessDeployment",
        {
            headers: {
                'Content-Type': 'text/plain'
            }
        }
    )
    tableData.value = deployments.data;
    pagination.value.total = deployments.data.length
}

// 处理新增逻辑
const handleAdd = ()=>{
    addMenu({
        title:"在线建模",
        path:"/processDeploymentNew",
        name:"processDeploymentNew",
        icon:"Finished"
    })
    router.push("/processDeploymentNew")
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