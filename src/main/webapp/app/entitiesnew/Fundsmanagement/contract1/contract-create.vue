<template>
  <div class="container">
    <!-- 上半部分 -->
    <div class="top-section col-8">
      <form name="editForm" novalidate @submit.prevent="save()">
        <h2>创建承沿合同预算信息</h2>
        <div class="form-row">
          <div class="form-group" v-if="contract.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="contract.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="contract-contractcode">承沿合同编号</label>
            <el-input
              type="text"
              name="contractcode"
              id="contract-contractcode"
              data-cy="contractcode"
              :class="{ valid: !v$.contractcode.$invalid, invalid: v$.contractcode.$invalid }"
              v-model="v$.contractcode.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="contract-contractname">承沿合同名称</label>
            <el-input
              type="text"
              name="contractname"
              id="contract-contractname"
              data-cy="contractname"
              :class="{ valid: !v$.contractname.$invalid, invalid: v$.contractname.$invalid }"
              v-model="v$.contractname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="contract-projectid">项目编号</label>
            <el-input
              type="text"
              name="projectid"
              id="contract-projectid"
              data-cy="projectid"
              :class="{ valid: !v$.projectid.$invalid, invalid: v$.projectid.$invalid }"
              v-model="v$.projectid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="contract-projectname">项目名称</label>
            <el-input
              type="text"
              name="projectname"
              id="contract-projectname"
              data-cy="projectname"
              :class="{ valid: !v$.projectname.$invalid, invalid: v$.projectname.$invalid }"
              v-model="v$.projectname.$model"
            />
          </div>
        </div>
        <div class="button-group">
          <button type="button" id="cancel-save" data-cy="entityCreateCancelButton" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.cancel')"></span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="v$.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.save')"></span>
          </button>
        </div>
      </form>
    </div>

    <!-- 下半部分 -->
    <div class="bottom-section col-8">
      <el-tabs v-model="activeTab">
        <el-tab-pane label="科目经费" name="first">
          <div class="form-group" v-if="subjectCostBudgets.length">
            <el-table :data="subjectCostBudgets" style="width: 100%; margin-top: 20px" row-key="id" border>
              <el-table-column prop="id" label="编号" />
              <el-table-column prop="subjectname" label="科目" />
              <el-table-column prop="budgetamount" label="预算金额" />
              <el-table-column prop="estimatedamount" label="估算金额" />
            </el-table>
            <el-button type="primary" @click="addToFirstTab">添加</el-button>
          </div>
        </el-tab-pane>
        <el-tab-pane label="系统内经费" name="second">
          <div class="form-group" v-if="projectBudgets.length">
            <el-table :data="projectBudgets" style="width: 100%; margin-top: 20px" row-key="id" border>
              <el-table-column prop="id" label="编号" />
              <el-table-column prop="wbsname" label="WBS名称" />
              <el-table-column prop="budgetamount" label="预算金额" />
              <el-table-column prop="estimatedamount" label="估算金额" />
            </el-table>
            <el-button type="primary" @click="addToSecondTab">添加</el-button>
          </div>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script lang="ts" src="./contract-create.component.ts"></script>


<style scoped>
.form-row {
  display: flex;
  flex-wrap: wrap; /* 允许换行 */
  margin-bottom: 20px; /* 输入框组的底部间距 */
}

.form-group {
  flex: 1 1 30%; /* 每个输入框占据 30% 宽度 */
  margin-right: 15px; /* 右侧间距 */
}

.form-group:last-child {
  margin-right: 0; /* 最后一个输入框不需要右侧间距 */
}
</style>