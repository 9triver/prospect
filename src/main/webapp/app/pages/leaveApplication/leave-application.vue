<template>
    <div class="title-wrapper">
        <h2>请假申请</h2>
    </div>
    <el-form :model="form" label-width="auto">
        <el-row>
            <el-col :span="12">
                <el-form-item label="假期类型">
                    <el-select v-model="form.type" placeholder="请选择假期类型" disabled>
                        <el-option label="事假" value="1" />
                        <el-option label="年假" value="2" />
                    </el-select>
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="请假时间">
                    <el-date-picker v-model="form.date" type="daterange" range-separator="至" start-placeholder="开始时间"
                        end-placeholder="结束时间" disabled/>
                </el-form-item>
            </el-col>
            <el-col :span="24">
                <el-form-item label="请假事由">
                    <el-input v-model="form.reason" type="textarea" placeholder="请输入请假事由" disabled/>
                </el-form-item>
            </el-col>
        </el-row>
    </el-form>
</template>

<script setup lang='ts'>
import axios from 'axios';
import { ref, reactive, onMounted } from 'vue'

interface IDynamicComponentProp {
  taskId: string;
  procInstId: string;
}

const form = ref({
    reason: '',
    date: [],
    type: '',
})
const props = defineProps({
  dynamicComponentProp: {
    type: Object as () => IDynamicComponentProp,
    default: () => ({})
  }
})

onMounted(async() => {
    let res = await axios.post('api/getProcessVariable', {
        procInstId: props.dynamicComponentProp.procInstId
    })
    form.value = res.data
})

</script>
<style lang='scss' scoped>
.title-wrapper{
    text-align: center;
}
</style>