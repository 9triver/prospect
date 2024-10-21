<template>
  <el-form :model="form" label-position="right" label-width="80px">
    <el-row :gutter="20">
      <el-col :span="8">
        <el-form-item label="关联WBS">
          <el-select v-model="form.wbsName" placeholder="请选择">
            <el-option v-for="item in wbsOptions" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="计划名称">
          <el-input v-model="form.planName" placeholder="请输入计划名称"></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="类别">
          <el-select v-model="form.planType" placeholder="请选择">
            <el-option v-for="item in planTypeOptions" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="8">
        <el-form-item label="层级">
          <el-select v-model="form.planLevel" placeholder="请选择">
            <el-option v-for="item in planLevelOptions" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="计划内容及标的">
          <el-input type="textarea" v-model="form.planContent" placeholder="请输入计划内容及标的"></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="前置计划">
          <el-select v-model="form.prePlanName" placeholder="请选择">
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
          <el-date-picker v-model="form.planEndTime" type="datetime" placeholder="请选择时间">
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
          <el-select v-model="form.responsiblepersonname" placeholder="请选择">
            <el-option v-for="item in responsiblepersonOptions" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="责任部门">
          <el-select v-model="form.responsibledpartmentname" placeholder="请选择">
            <el-option v-for="item in responsibledpartmentOptions" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="配合人">
          <el-select v-model="form.assistingPerson" placeholder="请选择">
            <el-option v-for="item in assistingPersonOptions" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="8">
        <el-form-item label="配合部门">
          <el-select v-model="form.assistingDepartment" placeholder="请选择">
            <el-option v-for="item in assistingDepartmentOptions" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="是否为重点计划">
          <el-radio-group v-model="form.keyPlan">
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
</template>

<script setup>
import { ref } from 'vue'
import { ElForm, ElFormItem, ElRow, ElCol, ElSelect, ElOption, ElInput, ElDatePicker, ElRadioGroup, ElRadio } from 'element-plus'

const form = ref({
  wbsName: '',
  planName: '',
  planType: '',
  planLevel: '',
  planContent: '',
  prePlanName: '',
  time: '',
  planEndTime: '',
  deliverable: '',
  responsiblepersonname: '',
  responsibledpartmentname: '',
  assistingPerson: '',
  assistingDepartment: '',
  keyPlan: false,
  risks: ''
})

// 示例选项数据
const wbsOptions = [
  { value: 'option1', label: 'Option 1' },
  { value: 'option2', label: 'Option 2' }
]
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
const responsiblepersonOptions = [
  { value: 'person1', label: 'Person 1' },
  { value: 'person2', label: 'Person 2' }
]
const responsibledpartmentOptions = [
  { value: 'dept1', label: 'Department 1' },
  { value: 'dept2', label: 'Department 2' }
]
const assistingPersonOptions = [
  { value: 'assistPerson1', label: 'Assist Person 1' },
  { value: 'assistPerson2', label: 'Assist Person 2' }
]
const assistingDepartmentOptions = [
  { value: 'assistDept1', label: 'Assist Department 1' },
  { value: 'assistDept2', label: 'Assist Department 2' }
]
</script>
