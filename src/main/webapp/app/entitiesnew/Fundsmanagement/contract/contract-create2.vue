<template>
    <div class="row justify-content-center">
      <!-- 上半部分：姓名和年龄输入框 -->
      <el-card class="form" shadow="hover">
        <h2>承沿合同信息</h2>
        <el-row>
            <el-col :span="8">
              <el-form-item label="姓名" :rules="[{ required: true, message: '请输入姓名', trigger: 'blur' }]">
                <el-input v-model="personalInfo.name" placeholder="请输入姓名" />
              </el-form-item>
            </el-col>
            
            <el-col :span="8">
              <el-form-item label="年龄" :rules="[{ required: true, message: '请输入年龄', trigger: 'blur' }]">
                <el-input-number v-model="personalInfo.age" :min="1" :max="120" placeholder="请输入年龄" />
              </el-form-item>
            </el-col>
            
            <el-col :span="8">
              <el-form-item label="年龄" :rules="[{ required: true, message: '请输入年龄', trigger: 'blur' }]">
                <el-input-number v-model="personalInfo.age" :min="1" :max="120" placeholder="请输入年龄" />
              </el-form-item>
            </el-col>
          </el-row>
          
          <el-row>
            <el-col :span="8">
              <el-form-item>
                <el-button type="primary" @click="savePersonalInfo" style="width: auto; float: left;">
                  保存
                </el-button>
              </el-form-item>
            </el-col>
          </el-row>
      </el-card>
  
      <!-- 下半部分：Tab 页 -->
      <el-card class="table-container" shadow="hover">
        <h2>经费预算</h2>
        <!-- Tab 页切换 -->
        <el-tabs v-model="activeTab">
          <el-tab-pane label="科目经费" name="contact">
            <el-table :data="contactRows" style="width: 100%" border>
              <el-table-column label="序号" prop="id">
                <template #default="{ row }">
                  <el-input v-model="row.address" disabled />
                </template>
              </el-table-column>
  
              <el-table-column label="科目" prop="address">
                <template #default="{ row }">
                  <el-input v-model="row.address" disabled />
                </template>
              </el-table-column>
  
              <el-table-column label="电话" prop="phone">
                <template #default="{ row }">
                  <el-input v-model="row.phone" placeholder="请输入"  />
                </template>
              </el-table-column>
  
              <el-table-column label="电话" prop="phone">
                <template #default="{ row }">
                  <el-input v-model="row.phone" placeholder="请输入"  />
                </template>
              </el-table-column>
  
              <el-table-column label="电话" prop="phone">
                <template #default="{ row }">
                  <el-input v-model="row.phone" placeholder="请输入电话" size="small" />
                </template>
              </el-table-column>
  
              <el-table-column label="操作" width="100" >
                <template #default="{ row, $index }">
                  <el-button type="danger" size="small" @click="removeContactRow($index)" style="width: auto; float: left;">删除</el-button>
                </template>
              </el-table-column>
            </el-table>
            <el-button type="success" @click="saveContactInfo"  style="width: auto; float: left;">保存</el-button>
          </el-tab-pane>
  
          <el-tab-pane label="系统内经费" name="grades">
            <el-button type="primary" @click="addGradeRow">新增成绩行</el-button>
            <el-table :data="gradeRows" style="width: 100%" border>
              <el-table-column label="科目" prop="subject">
                <template #default="{ row }">
                  <el-input v-model="row.subject" placeholder="请输入科目" size="small" disabled />
                </template>
              </el-table-column>
  
              <el-table-column label="成绩" prop="score">
                <template #default="{ row }">
                  <el-input v-model="row.score" placeholder="请输入成绩" size="small" />
                </template>
              </el-table-column>
  
              <el-table-column label="操作">
                <template #default="{ row, $index }">
                  <el-button type="danger" size="small" @click="removeGradeRow($index)">删除</el-button>
                </template>
              </el-table-column>
            </el-table>
            <el-button type="success" @click="saveGradeInfo" style="width: auto; float: left;">保存</el-button>
          </el-tab-pane>
        </el-tabs>
      </el-card>
    </div>
  </template>
  
  <script lang="ts">
  import { ref } from 'vue';
  import { ElForm, ElFormItem, ElInput, ElButton, ElInputNumber, ElTable, ElTableColumn, ElCard, ElTabs, ElTabPane } from 'element-plus';
  
  export default {
    components: {
      ElForm,
      ElFormItem,
      ElInput,
      ElButton,
      ElInputNumber,
      ElTable,
      ElTableColumn,
      ElCard,
      ElTabs,
      ElTabPane
    },
    setup() {
  
      const personalInfo = ref({
        name: '',
        age: null
      })
  
      const activeTab = ref('contact')  // 默认显示“联系方式”Tab
  
      // 联系方式表格数据
      const contactRows = ref([
        { address: '北京', phone: '12345678901' },
        { address: '上海', phone: '98765432100' }
      ])
  
      // 学习成绩表格数据
      const gradeRows = ref([
        { subject: '数学', score: 95 },
        { subject: '英语', score: 88 }
      ])
  
      // 保存个人信息
      const savePersonalInfo = () => {
        if (!personalInfo.value.name || !personalInfo.value.age) {
          return this.$message.error('姓名和年龄不能为空！')
        }
        console.log("个人信息已保存:", personalInfo.value)
        this.$message.success('个人信息保存成功！')
      }
  
      // 保存联系方式
      const saveContactInfo = () => {
        if (contactRows.value.some(row => !row.phone)) {
          return this.$message.error('电话不能为空！')
        }
        console.log("联系方式已保存:", contactRows.value)
        this.$message.success('联系方式保存成功！')
      }
  
      // 保存学习成绩
      const saveGradeInfo = () => {
        if (gradeRows.value.some(row => !row.score)) {
          return this.$message.error('成绩不能为空！')
        }
        console.log("学习成绩已保存:", gradeRows.value)
        this.$message.success('学习成绩保存成功！')
      }
  
      // 删除联系方式行
      const removeContactRow = (index) => {
        contactRows.value.splice(index, 1)
      }
  
      // 新增学习成绩行
      const addGradeRow = () => {
        gradeRows.value.push({ subject: '', score: '' })
      }
  
      // 删除学习成绩行
      const removeGradeRow = (index) => {
        gradeRows.value.splice(index, 1)
      }
  
      return {
        personalInfo,
        activeTab,
        contactRows,
        gradeRows,
        savePersonalInfo,
        saveContactInfo,
        saveGradeInfo,
        removeContactRow,
        addGradeRow,
        removeGradeRow
      }
    }
  }
  </script>
  
  <style scoped>
  .container {
    padding: 20px;
    max-width: 900px;
    margin: 20px auto;
  }
  
  h2 {
    font-size: 1.5em;
    margin-bottom: 15px;
  }
  
  .el-card {
    margin-bottom: 30px;
  }
  
  .el-button {
    width: 100%;
    margin-top: 15px;
  }
  
  .el-table {
    margin-top: 15px;
  }
  
  .el-table-column {
    width: 200px;
  }
  
  .el-input {
    width: 100%;
  }
  
  .el-input-number {
    width: 100%;
  }
  </style>
  