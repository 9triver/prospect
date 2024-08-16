<template>
  <div>
    <el-form :model="form" class="demo-form-inline" label-width="auto" ref="queryFormRef">
      <el-row :gutter="10">
        <el-col :span="8">
          <el-form-item label="姓名" prop="name">
            <el-input v-model="form.name" ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="状态">
            <el-select v-model="form.status" placeholder="请选择" size="moddle" >
              <el-option label="在职" value="ON_JOB" />
              <el-option label="离职" value="NO_JOB" />
              <el-option label="实习" value="JOB_WAITION" />
            </el-select>
          </el-form-item>
        </el-col>
        <!-- <el-col :span="8">
          <el-form-item label="部门" prop="department">
            <el-input v-model="form.department" />
          </el-form-item>
        </el-col> -->
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
        <router-link :to="{ name: 'OfficersCreate' }" custom v-slot="{ navigate }">
          <el-button type="primary" @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-officers"
          >录入</el-button>
        </router-link>
      </el-form-item>
    </div>

    <div class="alert alert-warning" v-if="!isFetching && officers && officers.length === 0">
      <span>未查询到符合条件的数据</span>
    </div>
    <el-table
      :data="officers"
      style="width: 100%; margin-bottom: 20px"
      row-key="id"
      border
      default-expand-all
    >
      <el-table-column label="姓名" :width="200">
        <template #default="{ row }">
          <router-link :to="{ name: 'OfficersView', params: { officersId: row.id } }">{{  row.name }}</router-link>
        </template>
      </el-table-column>
      <el-table-column prop="hiredate" label="入职时间" sortable />
      <el-table-column prop="years" label="合同年限" sortable />
      <el-table-column prop="status" label="人员状态" sortable >
        <template #default="{ row }">
          <span>
            <template v-if="row.status === 'ON_JOB'">在职</template>
            <template v-else-if="row.status === 'NO_JOB'">离职</template>
            <template v-else>其他状态</template>
          </span>
        </template>
      </el-table-column>
      <el-table-column label="部门">
        <template #default="{ row }">
          <span v-if="row.departments && row.departments.length">
            <span v-for="(department, i) in row.departments" :key="department.id">
              {{ i > 0 ? ', ' : '' }}
              <router-link :to="{ name: 'DepartmentView', params: { departmentId: department.id } }">
                {{ department.name }}
              </router-link>
            </span>
          </span>
          <span v-else> </span>
        </template>
      </el-table-column>
      <el-table-column label="人员角色">
        <template #default="{ row }">
          <span v-if="row.roles && row.roles.length">
            <span v-for="(role, i) in row.roles" :key="role.id">
              {{ i > 0 ? ', ' : '' }}
              <router-link :to="{ name: 'RoleView', params: { roleId: role.id } }">
                {{ role.rolename }}
              </router-link>
            </span>
          </span>
          <span v-else> </span>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script lang="ts" src="./officers.component.ts"></script>
