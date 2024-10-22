<template>
	<div>
		<h1>计划管理</h1>
		<!-- 查询条件 计划名称；责任人；计划结束时间；层级 -->
		<!-- 查询条件 -->
		<div>
			<el-form :inline="true" :model="formInline" class="demo-form-inline">
				<el-form-item label="计划名称">
					<el-input v-model="formInline.user" placeholder="计划名称"></el-input>
				</el-form-item>
				<el-form-item label="责任人">
					<el-input v-model="formInline.user" placeholder="责任人"></el-input>
				</el-form-item>
				<el-form-item label="计划结束时间">
					<el-input v-model="formInline.user" placeholder="计划结束时间"></el-input>
				</el-form-item>
				<el-form-item label="层级">
					<el-input v-model="formInline.user" placeholder="层级"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="onQuery">查询</el-button>
					<el-button type="primary" @click="onCreate">创建</el-button>
				</el-form-item>
			</el-form>
		</div>
		<!-- 生成表格共有以下列计划名称；WBS；类别；层级；条件具备时间；计划结束时间；责任人；责任部门 -->
		<div>
			<el-table :data="tableData" style="width: 100%" v-loading="loading">
				<!-- 计划名称 -->
				<el-table-column prop="planname" label="计划名称" width="180"></el-table-column>

				<!-- 其他列定义 -->
				<el-table-column label="WBS名称" width="180">
					<template #default="scope">
						<span>{{scope.row.projectwbs?.wbsname}}</span>
					</template>
				</el-table-column>
				<el-table-column prop="wbsid" label="WBS ID" width="150"></el-table-column>
				<el-table-column prop="plantype" label="计划类别" width="150"></el-table-column>
				<el-table-column prop="planlevel" label="计划层级" width="120"></el-table-column>
				<el-table-column prop="time" label="条件具备时间" :formatter="formatDate" width="180"></el-table-column>
				<el-table-column prop="planendtime" label="计划结束时间" :formatter="formatDate"
					width="180"></el-table-column>
				<el-table-column prop="responsiblepersonname" label="责任人名称" width="180"></el-table-column>
				<el-table-column prop="responsiblepersonid" label="责任人 ID" width="150"></el-table-column>
				<el-table-column prop="responsibledpartmentname" label="责任部门名称" width="180"></el-table-column>
				<el-table-column prop="responsibledpartmentid" label="责任部门 ID" width="150"></el-table-column>
			</el-table>
		</div>
	</div>

</template>

<script setup lang='ts'>
import { ref, reactive, onMounted } from 'vue'
import type { ProgressPlanNew } from './interface';
import axios from 'axios';
import useMenuTabStore from '@/store/model/menuTabs';

const menuTabStore = useMenuTabStore()
const {addMenu} = menuTabStore

const formInline = reactive({
	user: '',
	region: ''
})
// 表格加载状态
const loading = ref(true)

const tableData = ref<ProgressPlanNew[]>([])

// 查询数据
const onQuery = async () => {
	loading.value = true
	const baseURL = "api/progress-plan-new"
	const res = await axios.get(`${baseURL}/findAll`)
	tableData.value = res.data
	loading.value = false
}
// 创建数据,路由跳转
const onCreate = async () => {
	addMenu({name: 'progressPlanCreateCustom',title:'创建进度计划',path: '/progress-plan-create-custom',icon: 'edit'})
}

const formatDate = (row:ProgressPlanNew)=>{
	const date = row.time || row.planendtime;
	return date ? format(date) : '';
}
const format = (date:Date	)=>{
	const d = new Date(date);
	return `${d.getFullYear()}-${d.getMonth() + 1}-${d.getDate()}`;
}

// 初始化的时候要查询数据
onMounted(async()=>{
	await onQuery()
	loading.value = false
})



</script>
<style lang='scss' scoped></style>