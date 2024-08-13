<template>
  <div>
    
    <el-form :model="form" class="demo-form-inline" label-width="auto" ref="queryFormRef">
      <el-row :gutter="10">
        <el-col :span="8">
          <el-form-item label="名称" prop="name">
            <el-input v-model="form.name" ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="编号" prop="id">
            <el-input v-model="form.id" ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="目标" prop="objectives">
            <el-input v-model="form.objectives" ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="密级" prop="secretlevel">
            <el-select v-model="form.secretlevel" placeholder="请选择" size="moddle">
              <el-option label="机密" value="SECRET" />
              <el-option label="非保密-内部" value="NOSECTET_INTERNAL" />
              <el-option label="公开" value="PUBLIC" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="进度" prop="progress">
            <el-input type="number" v-model.number="form.progress" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="优先级" prop="priorty">
            <el-select v-model="form.priorty" placeholder="请选择" size="moddle">
              <el-option label="重要" value="1" />
              <el-option label="中等" value="2" />
              <el-option label="普通" value="3" />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  
    <div class="d-flex justify-content-center">
      <el-form-item>
          <el-button type="primary" plain @click="onSubmit">查询</el-button>
      </el-form-item>
      <el-form-item>
          <el-button type="primary" plain @click="handleSyncList(queryFormRef)">重置</el-button>
      </el-form-item>
      <el-form-item>
        <router-link :to="{ name: 'ProjectpbsCreate' }" custom v-slot="{ navigate }">
          <el-button type="primary" @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-projectpbs"
          >创建</el-button>
        </router-link>
      </el-form-item>
    </div>

    <div class="alert alert-warning" v-if="!isFetching && qualityObjectives && qualityObjectives.length === 0">
      <span v-text="t$('jy1App.qualityObjectives.home.notFound')"></span>
    </div>

    <div v-if="qualityObjectives.length">
      <el-table :data="qualityObjectives" style="width: 100%; margin-top: 20px" row-key="id" border>
        <el-table-column prop="id" label="计划编号" />
        <el-table-column prop="name" label="计划名称" />
        <el-table-column prop="description" label="计划描述" />
        <!-- <el-table-column prop="starttime" label="开始时间" />
        <el-table-column prop="endtime" label="结束时间" /> -->
        <el-table-column prop="secretlevel" label="密级" />
        <el-table-column prop="progress" label="进度" />
        <el-table-column label="质量回报" >
          <router-link :to="{ name: 'QualityReturns' }" custom v-slot="{ navigate }">
            <el-button type="primary" plain
              @click="navigate"
              id="jh-create-entity"
              data-cy="entityCreateButton"
              class="btn btn-primary jh-create-entity create-qualityReturns"
            >回报</el-button>
          </router-link>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script lang="ts" src="./quality-objectivesTree.component.ts"></script>
