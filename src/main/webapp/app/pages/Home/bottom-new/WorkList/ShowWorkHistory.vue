<template>
    <el-timeline style="max-width: 600px">
		<el-timeline-item
			v-for="(task,index) in dataSource"
			:key="index"
			:icon="task.END_TIME_?Check:Edit"
			:timestamp="task.START_TIME_"
			placement="top"
			:color="task.END_TIME_?'green':''"
		>	
			<el-card v-if="task.END_TIME_">
				<div>
					<span>任务名称：</span>
					<span class="time-line-item-value">{{task.NAME_}}</span>
				</div>
				<div>
					<span>开始时间：</span>
					<span class="time-line-item-value">{{task.START_TIME_}}</span>
				</div>
				<div>
					<span>任务状态：</span>
					<span class="time-line-item-value">已完成</span>
				</div>
				<div>
					<span>处理用时：</span>
					<span class="time-line-item-value">{{task.DURATION_}}</span>
				</div>
				<div>
					<span>经办人员：</span>
					<span class="time-line-item-value">{{task.ASSIGNEE_}}</span>
				</div>
			</el-card>
			<el-card v-else>
				<div>
					<span>任务名称：</span>
					<span class="time-line-item-value">{{task.NAME_}}</span>
				</div>
				<div>
					<span>开始时间：</span>
					<span class="time-line-item-value">{{task.START_TIME_}}</span>
				</div>
				<div>
					<span>任务状态：</span>
					<span class="time-line-item-value">处理中</span>
				</div>
				<div>
					<span>处理用时：</span>
					<span class="time-line-item-value">{{calcTime(moment().diff(moment(task.START_TIME_))+"")}}</span>
				</div>
				<div>
					<span>经办人员：</span>
					<span class="time-line-item-value">{{task.ASSIGNEE_}}</span>
				</div>
			</el-card>
		</el-timeline-item>
    </el-timeline>
</template>
  

<script setup lang='ts'>
    import axios from 'axios';
    import { ref, reactive, defineProps, onMounted, computed} from 'vue'
	import {Check,Edit} from '@element-plus/icons-vue'
	import moment from 'moment';
	import { calcTime } from '@/utils/utils';

    interface historicTask{
        ID_:string//任务ID
        NAME_:string, //任务名称
        ASSIGNEE_:string //经办人
        START_TIME_:string//开始时间
        END_TIME_:string//结束时间
        DURATION_:string//持续时间
    }
    
    const dataSource = ref<historicTask[]>([])

    const props = defineProps({
        PROC_INST_ID_:String
    })

    onMounted(async()=>{
        let queryResult = await axios.post(
            "/activiti7/queryhistory",
            props.PROC_INST_ID_,
            {
                headers: {
                    'Content-Type': 'text/plain'
                }
            }
        )
        dataSource.value = queryResult.data.map((item:historicTask)=>({
			...item,
			START_TIME_:moment(item.START_TIME_).format("YYYY-MM-DD HH:mm:ss"),
			END_TIME_:item.END_TIME_?moment(item.END_TIME_).format("YYYY-MM-DD HH:mm:ss"):null,
			DURATION_:calcTime(item.DURATION_)
		}))
        
    })

</script>
<style scoped>
	.time-line-item-value{
		font-weight: bold;
	}
</style>