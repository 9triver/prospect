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
            <el-button type="primary" @click="onAdd">新增</el-button>
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
                <el-table-column label="部署时间" >
                    <template #default="scope">
                        <!-- 转化为UTC时间 -->
                        <div>{{moment.tz(scope.row.deploymentTime,"Asia/Shanghai").tz("UTC").format("YYYY-MM-DD HH:mm:ss")}}</div>
                    </template>
                </el-table-column>
                <el-table-column label="操作">
                    <template #default="scope">
                        <el-button type="primary" link @click="showPreviewDialog(scope.row)" >预览</el-button>
                        <el-button type="primary" link @click="handleEdit(scope.row)">编辑</el-button>
                        <el-button type="primary" link>版本</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <ProcessDefinitionPreview 
            v-if="previewProcessDefinition"
            :definition="previewProcessDefinition"
            @closePreviewDialog="closePreviewDialog"
        />
    </div>
</template>

<script setup lang='ts'>
import router from '@/router';
import useMenuTabStore from '@/store/model/menuTabs';
import axios from 'axios';
import type { FormInstance } from 'element-plus';
import { ref, reactive, onMounted } from 'vue'
import moment from 'moment-timezone';
import ProcessDefinitionPreview from './process-definition-preview.vue'

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
const menuTabStore = useMenuTabStore()
const {addMenu} = menuTabStore
// 新增建模
const onAdd = ()=>{
    addMenu({
        title:"在线建模",
        path:"/processDefinitionAdd",
        name:"processDefinitionAdd",
        icon:"Finished"
    })
}

// 预览流程
// 记录当前要预览的流程定义的信息
const previewProcessDefinition = ref<processDefinition|null>(null)
// 预览用弹窗实现
const showPreviewDialog = (row:processDefinition)=>{
    previewProcessDefinition.value = row
}
//用于关闭弹窗组件的方法
const closePreviewDialog = ()=>{
    previewProcessDefinition.value = null
}
// 编辑流程
const handleEdit = (row:processDefinition)=>{
    addMenu({
        title:row.name,
        path:"/processDefinitionAdd",
        name:row.key,
        icon:"Finished"
    },{processDefinitionId:row.id})
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