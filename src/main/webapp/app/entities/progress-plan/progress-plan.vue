<template>
  <div>
    <el-form :inline="true" :model="form" class="demo-form-inline">
      <el-form-item label="计划名称">
        <el-input v-model="form.planname" ></el-input>
      </el-form-item>
      <el-form-item label="计划编号">
        <el-input v-model="form.id" ></el-input>
      </el-form-item>
      <el-form-item label="计划状态">
        <el-input v-model="form.status" ></el-input>
      </el-form-item>
      <el-form-item label="密级">
        <el-select v-model="form.secretlevel" placeholder="请选择" size="moddle" style="width: 200px">
          <el-option label="机密" value="SECRET" />
          <el-option label="非保密-内部" value="NOSECTET_INTERNAL" />
          <el-option label="公开" value="PUBLIC" />
        </el-select>
      </el-form-item>
      <el-form-item label="进度">
        <el-input type="number" v-model.number="form.progress" />
      </el-form-item>
      <!-- <el-form-item label="技术负责人">
        <el-input v-model="form.technicaldirectorname" ></el-input>
      </el-form-item> -->
      <el-form-item label="优先级">
        <el-select v-model="form.priorty" placeholder="请选择" size="moddle" style="width: 200px">
          <el-option label="重要" value="1" />
          <el-option label="中等" value="2" />
          <el-option label="普通" value="3" />
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
        <router-link :to="{ name: 'ProjectpbsCreate' }" custom v-slot="{ navigate }">
          <el-button type="primary" plain @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-projectpbs"
          >创建</el-button>
        </router-link>
      </el-form-item> 
    </div>
  </div>
    <!-- 表单1 -->
    <div v-if="progressPlans.length">
      <el-table :data="progressPlans" style="width: 100%; margin-top: 20px" row-key="id" border>
        <el-table-column prop="id" label="计划编号" />
        <el-table-column prop="planname" label="计划名称" />
        <el-table-column prop="description" label="计划描述" />
        <el-table-column prop="starttime" label="开始时间" />
        <el-table-column prop="endtime" label="结束时间" />
        <el-table-column prop="secretlevel" label="密级" />
        <el-table-column prop="progress" label="进度" />
        <el-table-column prop="priorty" label="优先级" />
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
    </div>
</template>

<script lang="ts" src="./progress-planTree.component.ts"></script>
