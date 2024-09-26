<template>
    <div>
        <el-dialog v-model="dialogVisible" @update:model-value="() => { emit('closeBackDialog') }" width="500">
            <el-table ref="singleTableRef" :data="tableData" highlight-current-row style="width: 100%"
                @current-change="handleCurrentChange">
                <el-table-column label="序号" width="100" align="center">
                    <template #default="scope">
                        {{ scope.$index + 1 }}
                    </template>
                </el-table-column>
                <el-table-column property="assignee" label="操作员" />
                <el-table-column property="activityName" label="活动名称" />
            </el-table>
            <template #footer>
                <div class="dialog-footer">
                    <el-button @click="dialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="handleBack">
                        驳回
                    </el-button>
                </div>
            </template>
        </el-dialog>
    </div>
</template>

<script setup lang='ts'>
import axios from 'axios';
import { ElMessage } from 'element-plus';
import { ref, reactive, onMounted } from 'vue'
import type { LocationQueryValue } from 'vue-router';

interface IBackItem{
    activityId:string,
    activityName:string,
    assignee:string,
    taskId: string
}

const props = defineProps<{
    taskId: LocationQueryValue | LocationQueryValue[]
}>()
const emit = defineEmits<{
    closeBackDialog: []
}>()

const dialogVisible = ref(true)
const currentRow = ref<IBackItem>()
const tableData = ref<IBackItem[]>()

// 处理选择回调
const handleCurrentChange = (val: IBackItem | undefined) => {
  currentRow.value = val
}

// 处理驳回
const handleBack = async () => {
    if (currentRow.value) {
        let res = await axios.post(`api/flow/rollback/${props.taskId}/${currentRow.value.taskId}`)
        if (res.data) {
            dialogVisible.value = false
            emit('closeBackDialog')
        }
    }else {
        ElMessage({
            message: '请选择要驳回哪个节点',
            type: 'warning',
        })
    }
}
onMounted(async () => {
    let res = await axios.post(`api/flow/rollbackList/${props.taskId}`)
    tableData.value = res.data 
})





</script>
<style lang='scss' scoped></style>