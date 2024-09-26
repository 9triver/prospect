<template>
    <div class="leave-application-wrapper">
        <div class="title-wrapper">
            <h2>请假申请</h2>
        </div>
        <el-form :model="form" label-width="auto">
            <el-row>
                <el-col :span="12">
                    <el-form-item label="假期类型">
                        <el-select v-model="form.type" placeholder="请选择假期类型">
                            <el-option label="事假" value="1" />
                            <el-option label="年假" value="2" />
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="请假时间">
                        <el-date-picker v-model="form.date" type="daterange" range-separator="至"
                            start-placeholder="开始时间" end-placeholder="结束时间" />
                    </el-form-item>
                </el-col>
                <el-col :span="24">
                    <el-form-item label="请假事由">
                        <el-input v-model="form.reason" type="textarea" placeholder="请输入请假事由" />
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form>
        <div class="form-buttons">
            <el-button type="primary" @click="onSubmit">发起请假申请</el-button>
        </div>
    </div>
</template>

<script lang="ts" setup>
import axios from 'axios';
import { inject, reactive } from 'vue'
import { ElMessage } from 'element-plus'
import { useStore, useTranslationStore } from '@/store'
import moment from 'moment';
// do not use same name with ref
const form = reactive({
    reason: '',
    date: [],
    type: '',
})
const store = useStore()

const onSubmit = async() => {
   let res = await axios.post('api/startproc', {
    key:"test",
    variables:{...form,date:form.date.map(item=>moment(item).format("YYYY-MM-DD"))},
    userName:store.account?.login
   },
   {
    headers: {
        'Content-Type': 'application/json'
    }
   }
)
   ElMessage({
    message: '申请成功已提交',
    type: 'success',
   })
   console.log(res.data)
}
</script>

<style lang="scss" scoped>
.leave-application-wrapper {
    padding: 20px;
    margin: 10px;
    background-color: #fff;
    border-radius: 9px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);

    .title-wrapper {
        margin-bottom: 20px;
    }

    .form-buttons {
        text-align: center;
    }
}
</style>