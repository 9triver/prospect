<template>
  <div>
    <div class="alert alert-warning" v-if="!isFetching && fundsEstimations && fundsEstimations.length === 0">
      <span>未查询到符合条件的数据</span>
    </div>
    <el-table
      :data="fundsEstimations"
      style="width: 100%; margin-bottom: 20px"
      row-key="id"
      border
      default-expand-all
    >
      <el-table-column prop="id" label="预算单元编号" sortable />
      <el-table-column prop="name" label="预算单元名称" sortable />
      <el-table-column prop="source" label="预算单元来源" sortable />
      <el-table-column prop="unit" label="单位" sortable />
      <el-table-column prop="number" label="数量" sortable />
      <el-table-column prop="unitprice" label="单价" sortable />   
      <el-table-column prop="materialfee" label="材料费" sortable />
      <el-table-column prop="specialfee" label="专用费" sortable />
      <el-table-column prop="outsourcingfee" label="外协费" sortable /> 
      <el-table-column label="编制人">
        <template #default="{ row }">
          <router-link v-if="row.responsibleperson" :to="{ name: 'OfficersView', params: { officersId: row.responsibleperson.id } }">{{
            row.responsibleperson.name
          }}</router-link>
          <span v-else></span>
        </template>
      </el-table-column>
      <el-table-column prop="totalbudgetprice" label="预算总价" sortable /> 
      <el-table-column>
        <template #default="{ row }">
          <router-link v-if="row.id" :to="{ name: 'FundsEstimationEdit',  params: { fundsEstimationId: row.id }  }" custom v-slot="{ navigate }">
            <el-button key="primary" type="primary" @click="navigate" link>估算</el-button>
          </router-link>
        </template>
      </el-table-column>
    </el-table> 

  </div>
</template>

<script lang="ts" src="./funds-estimationTree.component.ts"></script>
