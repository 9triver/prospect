<template>
    <div class="row justify-content-center">
    <!-- <div class="container"> -->
      <!-- 上半部分：查询重大项目 -->
      <el-card class="form" shadow="hover">
        <el-form :model="form" class="demo-form-inline" label-width="auto" ref="queryFormRef">
            <el-row :gutter="10" type="flex" align="middle">
                <el-col :span="8">
                    <el-form-item label="重大项目" prop="wbsname">
                        <el-select
                            collapse-tags
                            value-key="id"
                            id="contract-projectwbs"
                            data-cy="projectwbs"
                            name="projectwbs"
                            v-model="selectedProjectwbs"
                        >
                            <el-option v-bind:value="null"></el-option>
                            <el-option
                            v-bind:value="
                                projectwbs && projectwbsOption.id === projectwbs.id ? projectwbs : projectwbsOption
                            "
                            v-for="projectwbsOption in projectwbs"
                            :key="projectwbsOption.id"
                            :label="projectwbsOption.wbsname"
                            >{{ projectwbsOption.wbsname }}</el-option
                            >
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-button type="primary" plain @click="onSubmit" style="width: 15%; height: 32px;">查询</el-button>
                </el-col>
            </el-row>
        </el-form>
      </el-card>
      

  
  
      <!-- 下半部分：Tab 页 -->
      <!-- <el-card v-if="isSaving" class="table-container" shadow="hover"> -->
      <el-card class="table-container" shadow="hover">
        <h2>经费预算</h2>
        <!-- Tab 页切换 -->
        <el-tabs v-model="activeTab">
          <el-tab-pane label="科目经费" name="subject">
            <el-table :data="subjectCostBudgets" style="width: 100%" border>
              <el-table-column label="序号" prop="id" min-width="30px"/>  
              <el-table-column label="科目" prop="subjectname"/>  
              <el-table-column label="预算金额" prop="budgetamount"/>  
              <el-table-column label="估算金额" prop="estimatedamount">
                <template #default="{ row }">
                  <el-input v-model="row.estimatedamount" placeholder="请输入"  />
                </template>
              </el-table-column>  
              <el-table-column label="已实施金额" prop="implementedamount"/>
              <el-table-column label="待实施金额" prop="unimplementedamount"/>
              <el-table-column label="发票付款金额" prop="invoicepaymentamount"/>
              <el-table-column label="借款支付金额" prop="borrowpaymentamount"/>
              <el-table-column label="挂帐金额" prop="accountamount"/>
            </el-table>
          </el-tab-pane>
  
          <el-tab-pane label="系统内经费" name="project">
            <el-table :data="projectBudgets" style="width: 100%" border>
                <el-table-column label="WBS编号" prop="wbsid"/>
                <el-table-column label="WBS名称" prop="wbsname"/>
                <el-table-column label="科目" prop="subjectname"/>
                <el-table-column label="管理部登记号" prop="registrationnumber"/>
                <el-table-column label="财务登记号" prop="financialregistrationnumber"/>
                <el-table-column label="预算金额" prop="budgetamount"/>              
                <el-table-column label="估算金额" prop="estimatedamount"/>
                <el-table-column label="已实施金额" prop="implementedamount"/>
                <el-table-column label="待实施金额" prop="unimplementedamount"/>  
            </el-table>
          </el-tab-pane>
        </el-tabs>
      </el-card>
    </div>
  </template>
  
  
  <script lang="ts" src="./funds-management.component.ts"></script>
  
  
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
