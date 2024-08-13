<template>
  <div>
    <el-form :inline="true" :model="form" class="demo-form-inline">
      <el-form-item label="名称">
        <el-input v-model="form.wbsname" ></el-input>
      </el-form-item>
      <el-form-item label="WBS编号">
        <el-input v-model="form.id" ></el-input>
      </el-form-item>
      <el-form-item label="状态">
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
  </div>

  <div class="d-flex justify-content-end">
    <el-form-item>
        <el-button type="primary" plain @click="onSubmit">查询</el-button>
    </el-form-item>
    <el-form-item>
        <el-button type="primary" plain @click="handleSyncList">全部</el-button>
    </el-form-item>
    <el-form-item>
      <router-link :to="{ name: 'ProjectwbsCreate' }" custom v-slot="{ navigate }">
        <el-button type="primary" plain @click="navigate"
          id="jh-create-entity"
          data-cy="entityCreateButton"
          class="btn btn-primary jh-create-entity create-projectwbs"
        >创建</el-button>
      </router-link>
    </el-form-item>
  </div>

  <!-- 表单1 -->
  <!-- <div v-if="projectwbs.length">
    <el-table :data="projectwbs" style="width: 100%; margin-top: 20px" row-key="id" border>
      <el-table-column prop="id" label="编号" />
      <el-table-column prop="wbsname" label="名称" />
      <el-table-column prop="description" label="描述" />
    </el-table>
  </div> -->
  <!-- 表单2 -->
  <div>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && projectwbs && projectwbs.length === 0">
      <span>未查询到符合条件的数据</span>
    </div>
    <el-table
      :data="projectwbs"
      style="width: 100%; margin-bottom: 20px"
      row-key="id"
      border
      default-expand-all
    >
      <el-table-column label="名称" :width="200">
        <template #default="{ row }">
          <router-link :to="{ name: 'ProjectwbsOne', params: { ProjectwbsOneId: row.id }  }">{{  row.wbsname }}</router-link>
        </template>
      </el-table-column>
      <el-table-column prop="wbsname" label="名称" sortable />
      <el-table-column prop="description" label="WBS描述" sortable />
      <el-table-column prop="starttime" label="开始时间" sortable />
      <el-table-column prop="endtime" label="结束时间" sortable />
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
      <el-table-column prop="priorty" label="优先级" sortable >   
        <template #default="{ row }">
          <span>
            <template v-if="row.priorty === 1">重要</template>
            <template v-else-if="row.priorty === 2">中等</template>
            <template v-else-if="row.priorty === 3">普通</template>
            <template v-else> </template>
          </span>
        </template>
      </el-table-column>
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
    </el-table> 

  </div>
</template>

<script lang="ts" src="./projectwbsTree.component.ts"></script>
