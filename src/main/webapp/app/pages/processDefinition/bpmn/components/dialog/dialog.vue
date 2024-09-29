<template>
    <div>
        <el-table :data="currentSelectRows" style="width: 100%" border>
            <el-table-column label='序号' width="100px" align="center">
                    <template #default="scope">
                        {{ scope.$index + 1 }}
                    </template>
                </el-table-column>
            <el-table-column prop="name" label="名称" align="center" />
            <el-table-column prop="operator" label="操作" align="center" >
                <!-- 高级 上移 下移 删除 -->
                <template #default="scope">
                    <el-button type="primary" link @click="() => configData(scope.$index)">
                        高级
                    </el-button>
                    <el-button :type="scope.$index === 0 ? 'disabled' : 'primary'" link @click="() => upData(scope.$index)">
                        上移
                    </el-button>
                    <el-button :type="scope.$index === currentSelectRows.length - 1 ? 'disabled' : 'primary'" link @click="() => downData(scope.$index)">
                        下移
                    </el-button>
                    <el-button type="danger" link @click="() => deleteData(scope.$index)">
                        删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <div class="sublist-add" @click="() => addData()">
            + 添加
        </div>
        <el-dialog v-model="dialogVisible" title="选择按钮" width="500" :before-close="handleClose">
            <el-table :data="dialogTable" style="width: 100%" highlight-current-row
                @current-change="handleCurrentChange">
                <el-table-column label='序号' width="200px" align="center">
                    <template #default="scope">
                        {{ scope.$index + 1 }}
                    </template>
                </el-table-column>
                <el-table-column prop="name" label="名称" align="center" />
            </el-table>
            <template #footer>
                <div class="dialog-footer">
                    <el-button @click="dialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="handleConfirm">
                        确认
                    </el-button>
                </div>
            </template>
        </el-dialog>
    </div>
</template>

<script setup lang='ts'>
import { ref, reactive, computed, watch } from 'vue'
interface IConfig {
    name: string,
    value: string
}
interface IRow {
    name: string,
    config: IConfig[]
}

const props = defineProps<{
    modelValue: IRow[]
}>()
const emits = defineEmits(['update:modelValue']);

const tableData = ref<IRow[]>([])
const dialogVisible = ref<Boolean>(false)
const currentRow = ref<IRow>()
const currentSelectRows = ref<IRow[]>(props.modelValue)
const defaultData = [
    {
        name: '提交',
    },
    {
        name: '取消',
    },
    {
        name: '退回',
    },
    {
        name: '批准',
    }
]

watch(currentSelectRows, (newVal) => {
    emits('update:modelValue', newVal)
})

// 关闭弹窗
const handleClose = () => {
    dialogVisible.value = false
}
// 点击弹窗中的确认按钮
const handleConfirm = () => {
    if (currentRow.value) {
        currentSelectRows.value.push(currentRow.value)
    }
    dialogVisible.value = false
}
// 新增按钮触发方法
const addData = () => {
    dialogVisible.value = true
}
// 计算弹窗中的表格数据
const dialogTable = computed(() => {
    return defaultData.filter((item) => {
        return !currentSelectRows.value.find((row) => {
            return row.name === item.name
        })
    })
})
// 表格行选中事件
const handleCurrentChange = (val: IRow | undefined) => {
    currentRow.value = val
}
// 删除按钮触发方法
const deleteData = (index: number) => {
    currentSelectRows.value.splice(index, 1)
}
// 上移按钮触发方法
const upData = (index: number) => {
    if (index === 0) {
        return
    }
    const temp = currentSelectRows.value[index]
    currentSelectRows.value[index] = currentSelectRows.value[index - 1]
    currentSelectRows.value[index - 1] = temp
}
// 下移按钮触发方法
const downData = (index: number) => {
    if (index === currentSelectRows.value.length - 1) {
        return
    }
    const temp = currentSelectRows.value[index]
    currentSelectRows.value[index] = currentSelectRows.value[index + 1]
    currentSelectRows.value[index + 1] = temp
}
// 配置按钮触发方法
const configData = (index: number) => {
    
}


</script>
<style lang='scss' scoped></style>