<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="projectwbs">
        <h1 style="text-align: right;">
          <div class="mb-4">
            <router-link :to="{ name: 'ProgressPlan'}" custom v-slot="{ navigate }">
              <el-button type="primary" @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                进度计划
              </el-button>
            </router-link>
            <router-link :to="{ name: 'OutsourcingContractual'}" custom v-slot="{ navigate }">
              <el-button type="success" @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                外协管理
              </el-button>
            </router-link>
            <router-link :to="{ name: 'QualityObjectives'}" custom v-slot="{ navigate }">
              <el-button type="warning" @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                质量管理
              </el-button>
            </router-link>
            <router-link :to="{ name: 'CostControlSystem'}" custom v-slot="{ navigate }">
              <el-button type="info" @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                资金管理
              </el-button>
            </router-link>
            <router-link :to="{ name: 'ProjectRisk'}" custom v-slot="{ navigate }">
              <el-button type="danger" @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                项目风险
              </el-button>
            </router-link>
            <router-link :to="{ name: 'Document'}" custom v-slot="{ navigate }">
              <el-button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                项目文档
              </el-button>
            </router-link>
          </div>
        </h1>

        <!-- <div class="d-flex justify-content-end">
          <el-form-item>
              <el-button type="primary" plain @click="onSubmit">查询</el-button>
          </el-form-item>
          <el-form-item>
              <el-button type="primary" plain @click="handleSyncList">全部</el-button>
          </el-form-item>
          <el-form-item>
            <router-link :to="{ name: 'ProjectTotalwbsCreate' }" custom v-slot="{ navigate }">
              <el-button type="primary" plain @click="navigate"
                id="jh-create-entity"
                data-cy="entityCreateButton"
                class="btn btn-primary jh-create-entity create-projecttotalwbs"
              >创建</el-button>
            </router-link>
          </el-form-item>
        </div> -->
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
                <!-- <router-link :to="{ name: 'ProjectpbsParentCreate' , params: { parentId: row.id} }" custom v-slot="{ navigate }">
                <el-button type="create" plain size="small" @click="navigate" >+</el-button>
                </router-link> -->
                <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: row.id } }">{{  row.wbsname }}</router-link>
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
                  <template v-else>无</template>
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
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./projectwbs-oneTree.component.ts"></script>
