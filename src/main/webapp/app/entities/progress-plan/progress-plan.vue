<template>
  <div>
    <el-form :inline="true" :model="form" class="demo-form-inline">
      <el-form-item label="计划名称">
        <el-input v-model="form.planname" ></el-input>
      </el-form-item>
      <el-form-item label="计划编号">
        <el-input v-model="form.id" ></el-input>
      </el-form-item>
      <el-form-item label="密级">
        <el-select v-model="form.secretlevel" placeholder="请选择" size="moddle" style="width: 200px">
          <el-option label=" " value="" />
          <el-option label="机密" value="SECRET" />
          <el-option label="非保密-内部" value="NOSECTET_INTERNAL" />
          <el-option label="公开" value="PUBLIC" />
        </el-select>
      </el-form-item>
      <el-form-item label="进度">
        <el-input type="number" v-model.number="form.progress" />
      </el-form-item>
      <el-form-item label="类型">
        <el-select type="number" v-model="form.plantype" placeholder="请选择" size="moddle" style="width: 200px">
          <el-option label=" " value=null />
          <el-option label="技术研制类" value=1 />
          <el-option label="管理控制类" value=2 />
        </el-select>
      </el-form-item>
    </el-form>
  
    <div class="d-flex justify-content-end">
      <el-form-item>
          <el-button type="primary" plain @click="onSubmit">查询</el-button>
      </el-form-item>
      <el-form-item>
          <el-button type="primary" plain @click="handleSyncList">全部</el-button>
      </el-form-item>
      <el-form-item>
        <router-link :to="{ name: 'ProgressPlanCreate' }" custom v-slot="{ navigate }">
          <el-button type="primary" plain @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-progressPlan"
          >创建</el-button>
        </router-link>
      </el-form-item> 
    </div>
  </div>

  <div class="alert alert-warning" v-if="!isFetching && progressPlans && progressPlans.length === 0">
    <span>未查询到符合条件的数据</span>
  </div>
    <!-- 表单1 -->
    <!-- <div v-if="progressPlans.length">
      <el-table :data="progressPlans" style="width: 100%; margin-top: 20px" row-key="id" border>
        <el-table-column prop="id" label="计划编号" />
        <el-table-column prop="planname" label="计划名称" />
        <el-table-column prop="description" label="计划描述" />
        <el-table-column prop="starttime" label="开始时间" />
        <el-table-column prop="endtime" label="结束时间" />
        <el-table-column prop="secretlevel" label="密级" />
        <el-table-column prop="progress" label="进度" />
        <el-table-column label="计划回报" >
          <router-link :to="{ name: 'PlanReturns' }" custom v-slot="{ navigate }">
            <el-button type="primary" plain
              @click="navigate"
              id="jh-create-entity"
              data-cy="entityCreateButton"
              class="btn btn-primary jh-create-entity create-planReturns"
            >回报</el-button>
          </router-link>
        </el-table-column>
      </el-table>
    </div> -->
    <el-table
      :data="progressPlans"
      style="width: 100%; margin-bottom: 20px"
      row-key="id"
      border
      default-expand-all
    >
      <el-table-column type="selection"  width="55" />
      <el-table-column label="计划名称" :width="200">
        <template #default="{ row }">
          <router-link :to="{ name: 'ProgressPlanView', params: { progressPlanId: row.id } }">{{  row.planname }}</router-link>
        </template>
      </el-table-column>
      <el-table-column prop="description" label="计划描述" />
      <el-table-column prop="starttime" label="开始时间" />
      <el-table-column prop="endtime" label="结束时间" />
      <el-table-column prop="secretlevel" label="密级" sortable >   
        <template #default="{ row }">
          <span>
            <template v-if="row.secretlevel === 'SECRET'">机密</template>
            <template v-else-if="row.secretlevel === 'NOSECTET_INTERNAL'">非保密-内部</template>
            <template v-else-if="row.secretlevel === 'PUBLIC'">公开</template>
            <template v-else>无</template>
          </span>
        </template>
      </el-table-column>
      <el-table-column prop="progress" label="进度" sortable />
      <el-table-column prop="planlevel" label="计划层级" sortable >
        <template #default="{ row }">
          {{t$('jy1App.PlanLevel.'+row.planlevel)}}
        </template>
      </el-table-column>
      <el-table-column label="审核状态" sortable align="center">
        <template #default="{ row }">
          {{t$('jy1App.AuditStatus.'+row.auditStatus)}}
        </template>
      </el-table-column>
      <el-table-column label="计划回报" >
        <router-link :to="{ name: 'PlanReturns' }" custom v-slot="{ navigate }">
          <el-button type="primary" plain
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-planReturns"
          >回报</el-button>
        </router-link>
      </el-table-column>
      
    </el-table>

</template>

<script lang="ts" src="./progress-planTree.component.ts"></script>
