<template>
  <div>
    <h2 id="page-heading" data-cy="ProjectpbsHeading">
      <span v-text="t$('jy1App.projectpbs.home.title')" id="projectpbs-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.projectpbs.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ProjectpbsCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-projectpbs"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.projectpbs.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <el-table
      :data="projectpbs"
      style="width: 100%; margin-bottom: 20px"
      row-key="id"
      border
      default-expand-all
    >
      <el-table-column label="名称" :width="200">
        <template #default="{ row }">
          <router-link :to="{ name: 'ProjectpbsParentCreate' , params: { parentId: row.id} }" custom v-slot="{ navigate }">
          <!-- <el-button type="primary" :icon="Edit" circle @click="navigate"/> -->
          <el-button type="create" plain size="small" @click="navigate" >+</el-button>
          <!-- <button @click="navigate" >
            <span>  + </span>
          </button> -->
          </router-link>
          <router-link :to="{ name: 'ProjectpbsView', params: { projectpbsId: row.id } }">{{  row.pbsname }}</router-link>
        </template>
      </el-table-column>
      <el-table-column prop="pbsname" label="名称" sortable />
      <el-table-column prop="description" label="PBS描述" sortable />
      <el-table-column prop="starttime" label="开始时间" sortable />
      <el-table-column prop="endtime" label="结束时间" sortable />
      <el-table-column prop="progress" label="进度" sortable />
      <el-table-column label="负责部门">
        <template #default="{ row }">
          <router-link v-if="row.department" :to="{ name: 'DepartmentView', params: { departmentId: row.department.id } }">{{
            row.department.name
          }}</router-link>
          <span v-else></span>
        </template>
      </el-table-column>
      <el-table-column label="负责人">
        <template #default="{ row }">
          <router-link v-if="row.responsibleid" :to="{ name: 'OfficersView', params: { officersId: row.responsibleid.id } }">{{
            row.responsibleid.name
          }}</router-link>
          <span v-else></span>
        </template>
      </el-table-column>
      <el-table-column label="审核人">
        <template #default="{ row }">
          <router-link v-if="row.auditorid" :to="{ name: 'OfficersView', params: { officersId: row.auditorid.id } }">{{
            row.auditorid.name
          }}</router-link>
          <span v-else></span>
        </template>
      </el-table-column>
      <el-table-column label="WBS">
        <!-- <router-link :to="{ name: 'ProjectwbsOne', params: { wbsId: row.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
            <font-awesome-icon icon="eye"></font-awesome-icon>
            <span>{{ 查看 }}</span>
          </button>
        </router-link> -->
        <router-link :to="{ name: 'ProjectwbsCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-projectwbs"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span>{{ 创建 }}</span>
          </button>
        </router-link>
      </el-table-column>
      
    </el-table>
  </div>
</template>

<script lang="ts" src="./projectpbsTree.component.ts"></script>
