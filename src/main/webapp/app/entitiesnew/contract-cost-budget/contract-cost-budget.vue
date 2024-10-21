<template>
  <div>
    <div class="alert alert-warning" v-if="!isFetching && contractCostBudgets && contractCostBudgets.length === 0">
      <span>未查询到符合条件的数据</span>
    </div>
    <el-table
      :data="contractCostBudgets"
      style="width: 100%; margin-bottom: 20px"
      row-key="id"
      border
      default-expand-all
    >
      <el-table-column prop="id" label="经费科目编号" sortable />
      <el-table-column prop="subject" label="经费科目名称" sortable />
      <el-table-column prop="auxiliaryitem" label="辅助科目" sortable >
        <template #default="{ row }">
                <span>
                  <template v-if="row.auxiliaryitem === 'Materialfee'">材料费</template>
                  <template v-else-if="row.auxiliaryitem === 'Specialfee'">专用费</template>
                  <template v-else-if="row.auxiliaryitem === 'Outsourcingfee'">外协费</template>
                  <template v-else> </template>
                </span>
              </template>
      </el-table-column>
      <el-table-column prop="unit" label="单位" sortable />
      <el-table-column prop="number" label="数量" sortable />
      <el-table-column prop="unitprice" label="单价" sortable />     
      <el-table-column prop="totalprice" label="预算总价" sortable /> 
      <el-table-column>
        <template #default="{ row }">
          <router-link v-if="row.id" :to="{ name: 'ContractCostBudgetEdit',  params: { contractCostBudgetId: row.id }  }" custom v-slot="{ navigate }">
            <el-button key="primary" type="primary" @click="navigate" link>预算</el-button>
          </router-link>
        </template>
      </el-table-column>
    </el-table> 

  </div>
</template>

<script lang="ts" src="./contract-cost-budgetTree.component.ts"></script>
