<template>
  <div class="row justify-content-center">
  <!-- <div class="container"> -->
    <!-- 上半部分：承沿合同录入信息 -->
    <el-card class="form" shadow="hover">
      <h2>承沿合同信息</h2>
      <el-row>
          <el-col :span="8">
            <el-form-item label="合同编号" :rules="[{ required: true, message: '请输入', trigger: 'blur' }]">
              <el-input
                type="text"
                name="contractcode"
                id="contract-contractcode"
                data-cy="contractcode"
                :class="{ valid: !v$.contractcode.$invalid, invalid: v$.contractcode.$invalid }"
                v-model="v$.contractcode.$model"
              />
            </el-form-item>
          </el-col>
          
          <el-col :span="8">
            <el-form-item label="合同名称" :rules="[{ required: true, message: '请输入', trigger: 'blur' }]">
              <el-input
                type="text"
                name="contractname"
                id="contract-contractname"
                data-cy="contractname"
                :class="{ valid: !v$.contractname.$invalid, invalid: v$.contractname.$invalid }"
                v-model="v$.contractname.$model"
              />
            </el-form-item>
          </el-col>
          
          <el-col :span="8">
            <el-form-item label="重大项目" :rules="[{ required: true, message: '请输入', trigger: 'blur' }]">
              <el-select
                collapse-tags
                value-key="id"
                id="contract-projectwbs"
                data-cy="projectwbs"
                name="projectwbs"
                v-model="contract.projectwbs"
              >
                <el-option v-bind:value="null"></el-option>
                <el-option
                  v-bind:value="
                    contract.projectwbs && projectwbsOption.id === contract.projectwbs.id ? contract.projectwbs : projectwbsOption
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
            <el-form-item label="合同类型" :rules="[{ required: true, message: '请输入', trigger: 'blur' }]">
              <el-select
                collapse-tags
                value-key="id"
                name="contracttype"
                :class="{ valid: !v$.contracttype.$invalid, invalid: v$.contracttype.$invalid }"
                v-model="v$.contracttype.$model"
                id="contract-contracttype"
                data-cy="contracttype"
              >
                <el-option
                  v-for="contractType in contractTypeValues"
                  :key="contractType"
                  v-bind:value="contractType"
                  v-bind:label="t$('jy1App.ContractType.' + contractType)"
                  >{{ contractType }}</el-option
                >
              </el-select>
            </el-form-item>
          </el-col>
          
          <el-col :span="8">
            <el-form-item label="年份" label-width="80px" :rules="[{ required: true, message: '请输入', trigger: 'blur' }]">
              <el-input
                type="number"
                name="year"
                id="contract-year"
                data-cy="year"
                :class="{ valid: !v$.year.$invalid, invalid: v$.year.$invalid }"
                v-model.number="v$.year.$model"
              />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="密级" label-width="80px"  :rules="[{ required: true, message: '请输入', trigger: 'blur' }]">
              <el-select
                collapse-tags
                value-key="id"
                name="secretlevel"
                :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
                v-model="v$.secretlevel.$model"
                id="contract-secretlevel"
                data-cy="secretlevel"
              >
                <el-option
                  v-for="secretlevel in secretlevelValues"
                  :key="secretlevel"
                  v-bind:value="secretlevel"
                  v-bind:label="t$('jy1App.Secretlevel.' + secretlevel)"
                  >{{ secretlevel }}</el-option
                >
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="开始时间" :rules="[{ required: true, message: '请输入', trigger: 'blur' }]">
              <el-date-picker v-model="v$.starttime.$model" type="date" placeholder="" />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="结束时间" :rules="[{ required: true, message: '请输入', trigger: 'blur' }]">
              <el-date-picker v-model="v$.endtime.$model" type="date" placeholder="" />
            </el-form-item>
          </el-col>

          <!-- <el-col :span="12">
            <el-form-item label="备注" label-width="80px"  :rules="[{ required: true, message: '请输入', trigger: 'blur' }]">
              <el-input
                type="text"
                class="form-control"
                name="remark"
                id="contract-remark"
                data-cy="remark"
                :class="{ valid: !v$.remark.$invalid, invalid: v$.remark.$invalid }"
                v-model="v$.remark.$model"
              />
            </el-form-item>
          </el-col> -->

        </el-row>
        
        <el-row>
          <el-col :span="8">
            <el-form-item>
              <el-button type="primary" @click="save()" style="width: auto; float: left;">
                保存
              </el-button>
            </el-form-item>
          </el-col>
        </el-row>
    </el-card>


    <!-- 下半部分：Tab 页 -->
    <!-- <el-card v-if="isSaving" class="table-container" shadow="hover"> -->
    <el-card class="table-container" shadow="hover">
      <h2>经费预算</h2>
      <!-- Tab 页切换 -->
      <el-tabs v-model="activeTab">
        <el-tab-pane label="科目经费" name="subject">
          <el-table :data="subjectCostBudgets" style="width: 100%" border>
            <el-table-column label="序号" prop="id" min-width="20px">
            </el-table-column>

            <el-table-column label="科目" prop="subjectname">
            </el-table-column>

            <el-table-column label="预算金额" prop="budgetamount">
              <template #default="{ row }">
                <el-input v-model="row.budgetamount" placeholder="请输入"  />
              </template>
            </el-table-column>

            <!-- <el-table-column label="估算金额" prop="estimatedamount">
              <template #default="{ row }">
                <el-input v-model="row.estimatedamount" placeholder="请输入"  />
              </template>
            </el-table-column> -->

            <el-table-column label="百分比" prop="percentage">
              <template #default="{ row }">
                <el-input v-model="row.percentage" placeholder="请输入电话" size="small" />
              </template>
            </el-table-column>
          </el-table>
          <el-button type="success" @click="saveSubject()"  style="width: auto; float: left;">保存</el-button>
        </el-tab-pane>

        <el-tab-pane label="系统内经费" name="project">
          <el-table :data="projectBudgets" style="width: 100%" border>
            <el-table-column label="项目名称" prop="wbsname">
            </el-table-column>

            <el-table-column label="项目编号" prop="wbsid">
            </el-table-column>

            <!-- <el-table-column label="科目" prop="subjectname">
              <template #default="{ row }">
                <el-input v-model="row.subjectname" placeholder="请输入" size="small" />
              </template>
            </el-table-column> -->

            <el-table-column label="科目" prop="subjectname">
              <template #default="{ row }">
                <!-- 使用 el-select 来代替 el-input，显示 subject.name，保存 subject.id -->
                <el-select v-model="row.subjectname" placeholder="请选择" size="small">
                  <el-option
                    v-for="subject in subjects"
                    :key="subject.id"
                    :label="subject.name"
                    :value="subject.id"
                  ></el-option>
                </el-select>
              </template>
            </el-table-column>


            <el-table-column label="单位" prop="unit">
              <template #default="{ row }">
                <el-input v-model="row.unit" placeholder="请输入" size="small" />
              </template>
            </el-table-column>

            <el-table-column label="数量" prop="number">
              <template #default="{ row }">
                <el-input v-model="row.number" placeholder="请输入" size="small" />
              </template>
            </el-table-column>

            <el-table-column label="单价（元）" prop="unitprice">
              <template #default="{ row }">
                <el-input v-model="row.unitprice" placeholder="请输入" size="small" />
              </template>
            </el-table-column>

            <el-table-column label="预算金额" prop="budgetamount">
              <template #default="{ row }">
                <el-input v-model="row.budgetamount" placeholder="请输入" size="small" />
              </template>
            </el-table-column>

          </el-table>
          <el-button type="success" @click="saveProject()" style="width: auto; float: left;">保存</el-button>
        </el-tab-pane>
      </el-tabs>
    </el-card>
  </div>
</template>


<script lang="ts" src="./contract-create.component.ts"></script>


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