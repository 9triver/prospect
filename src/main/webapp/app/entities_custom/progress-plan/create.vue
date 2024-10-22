<template>
  <el-form :model="form" label-position="right" label-width="80px">
    <el-row :gutter="20">
      <el-col :span="8">
        <el-form-item label="关联WBS">
          <el-select v-model="form.projectwbs" placeholder="请选择" value-key="id">
            <el-option v-for="projectwbs in projectwbsOptions" :key="projectwbs.id" :label="projectwbs.wbsname" :value="projectwbs">
            </el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="计划名称">
          <el-input v-model="form.planname" placeholder="请输入计划名称"></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="类别">
          <el-select v-model="form.plantype" placeholder="请选择">
            <el-option v-for="item in planTypeOptions" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="8">
        <el-form-item label="层级">
          <el-select v-model="form.planlevel" placeholder="请选择">
            <el-option v-for="item in planLevelOptions" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="计划内容及标的">
          <el-input type="textarea" v-model="form.plancontent" placeholder="请输入计划内容及标的"></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="前置计划">
          <el-select v-model="form.preplanname" placeholder="请选择">
            <el-option v-for="item in prePlanOptions" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="8">
        <el-form-item label="条件具备时间">
          <el-date-picker v-model="form.time" type="datetime" placeholder="请选择时间">
          </el-date-picker>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="计划结束时间">
          <el-date-picker v-model="form.planendtime" type="datetime" placeholder="请选择时间">
          </el-date-picker>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="交付物">
          <el-input v-model="form.deliverable" placeholder="请输入交付物"></el-input>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="8">
        <el-form-item label="责任人">
          <el-select v-model="form.responsibleperson" placeholder="请选择" value-key="id">
            <el-option v-for="responsibleperson in responsiblepersonOptions" :key="responsibleperson.id" :label="responsibleperson.name"
              :value="responsibleperson">
            </el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="责任部门">
          <el-select v-model="form.responsibledepartment" placeholder="请选择" value-key="id">
            <el-option v-for="responsibledpartment in responsibledpartmentOptions" :key="responsibledpartment.id" :label="responsibledpartment.name"
              :value="responsibledpartment">
            </el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="配合人">
          <el-select v-model="form.coordinator" placeholder="请选择" value-key="id">
            <el-option v-for="coordinator in coordinatorOptions" :key="coordinator.id" :label="coordinator.name" :value="coordinator">
            </el-option>
          </el-select>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="8">
        <el-form-item label="配合部门">
          <el-select v-model="form.coordinatedepartment" placeholder="请选择" value-key="id">
            <el-option v-for="coordinatedepartment in coordinatedepartmentOptions" :key="coordinatedepartment.id" :label="coordinatedepartment.name"
              :value="coordinatedepartment">
            </el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="是否为重点计划">
          <el-radio-group v-model="form.keyplan">
            <el-radio :label="true">是</el-radio>
            <el-radio :label="false">否</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="存在问题及风险">
          <el-input type="textarea" v-model="form.risks" placeholder="请输入存在的问题及风险"></el-input>
        </el-form-item>
      </el-col>
    </el-row>
  </el-form>
  <div style="text-align: center;">
    <el-button type="primary" @click="onSave">保存</el-button>
  </div>
</template>

<script setup lang="ts">
import axios from 'axios';
import { ref, onMounted } from 'vue'
import ProjectWbsService from '@/entities/projectwbs/projectwbs.service';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import { type ProgressPlanNew } from './interface'
import OfficersService from '@/entities/officers/officers.service';
import DepartmentService from '@/entities/department/department.service';
import type { IOfficers } from '@/shared/model/officers.model';
import type { IDepartment } from '@/shared/model/department.model';

const form = ref<ProgressPlanNew>({})

// wbs列表
const projectwbsOptions = ref<IProjectwbs[]>([])
// 责任人
const responsiblepersonOptions = ref<IOfficers[]>([])
// 责任部门
const responsibledpartmentOptions = ref<IDepartment[]>([])
// 配合人
const coordinatorOptions = ref<IOfficers[]>([])
// 配合部门
const coordinatedepartmentOptions = ref<IDepartment[]>([])

// 初始化关系
const initRelationship = async () => {
  let projectWbsService = new ProjectWbsService()
  let officersService = new OfficersService()
  let departmentService = new DepartmentService()
  projectWbsService
    .retrieve()
    .then(res => {
      projectwbsOptions.value = res.data;
    });
  officersService
    .retrieve()
    .then(res => {
      responsiblepersonOptions.value = res.data
      coordinatorOptions.value = res.data
    });
  departmentService
    .retrieve()
    .then(res => {
      responsibledpartmentOptions.value = res.data
      coordinatedepartmentOptions.value = res.data
    });
}

initRelationship()


// 示例选项数据
// const wbsOptions = [
//   { value: 'option1', label: 'Option 1' },
//   { value: 'option2', label: 'Option 2' }
// ]
const planTypeOptions = [
  { value: 'type1', label: 'Type 1' },
  { value: 'type2', label: 'Type 2' }
]
const planLevelOptions = [
  { value: 'level1', label: 'Level 1' },
  { value: 'level2', label: 'Level 2' }
]
const prePlanOptions = [
  { value: 'prePlan1', label: 'Pre Plan 1' },
  { value: 'prePlan2', label: 'Pre Plan 2' }
]
// const responsiblepersonOptions = [
//   { value: 'person1', label: 'Person 1' },
//   { value: 'person2', label: 'Person 2' }
// ]
// const responsibledpartmentOptions = [
//   { value: 'dept1', label: 'Department 1' },
//   { value: 'dept2', label: 'Department 2' }
// ]
// const assistingPersonOptions = [
//   { value: 'assistPerson1', label: 'Assist Person 1' },
//   { value: 'assistPerson2', label: 'Assist Person 2' }
// ]
// const assistingDepartmentOptions = [
//   { value: 'assistDept1', label: 'Assist Department 1' },
//   { value: 'assistDept2', label: 'Assist Department 2' }
// ]

const onSave = async () => {
  console.log('Form data:', form.value)
  const baseURL = "api/progress-plan-new"
  const res = await axios.post(`${baseURL}/create`, form.value)
}


</script>