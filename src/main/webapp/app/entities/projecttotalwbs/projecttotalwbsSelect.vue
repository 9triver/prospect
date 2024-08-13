<template>
  <div>
    <h2 id="page-heading" data-cy="ProjectwbsHeading">
      <span v-text="t$('jy1App.projectwbs.home.title')" id="projectwbs-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.projectwbs.home.refreshListLabel')"></span>
        </button>
      </div>
    </h2>
    <el-table
      ref="multipleTableRef"
      :data="projectwbs"
      style="width: 100%; margin-bottom: 20px"
      row-key="id"
      border
      default-expand-all
    >
      <el-table-column type="selection" width="55" />
      <el-table-column label="名称" width="220">
        <template #default="{ row }">
          <router-link :to="{ name: 'ProjectwbsParentCreate' , params: { parentId: row.id} }" custom v-slot="{ navigate }">
          <!-- <el-button type="primary" :icon="Edit" circle @click="navigate"/> -->
          <el-button type="create" plain size="small" @click="navigate" >+</el-button>
          <!-- <button @click="navigate" >
            <span>  + </span>
          </button> -->
          </router-link>
          <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: row.id } }">{{  row.wbsname }}</router-link>
        </template>
      </el-table-column>
      <el-table-column prop="wbsname" label="wbs名称" sortable />
      <el-table-column prop="description" label="WBS描述" sortable />
      <el-table-column prop="starttime" label="开始时间" sortable />
      <el-table-column prop="endtime" label="结束时间" sortable />
      <el-table-column prop="progress" label="进度" sortable />
      <el-table-column label="负责部门">
        <template #default="{ row }">
          <router-link v-if = "row.department" :to="{ name: 'DepartmentView', params: { departmentId: row.department.id } }">{{
            row.department.id
          }}</router-link>
          <span v-else></span>
        </template>
      </el-table-column>
      <el-table-column label="负责人">
        <template #default="{ row }">
          <router-link v-if = "row.responsibleid" :to="{ name: 'OfficersView', params: { officersId: row.responsibleid.id } }">{{
            row.responsibleid.id
          }}</router-link>
          <span v-else></span>
        </template>
      </el-table-column>
      <el-table-column label="审核人">
        <template #default="{ row }">
          <router-link v-if = "row.auditorid" :to="{ name: 'OfficersView', params: { officersId: row.auditorid.id } }">{{
            row.auditorid.id
          }}</router-link>
          <span v-else></span>
        </template>
      </el-table-column>
      
    </el-table>
    <div style="margin-top: 20px">
      <el-button @click="toggleSelection()">清空选择</el-button>
      <!-- <el-button @click="confirmSelection">确认选择</el-button> -->
    </div>

  </div>
</template>

<script lang="ts" src="./projecttotalwbsSelect.component.ts"></script>
