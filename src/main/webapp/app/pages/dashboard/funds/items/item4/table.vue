<template>
    <div>
      <el-table
        :data="tableData"
        style="width: 100%;margin-bottom: 20px;"
        row-key="id"
        border
        default-expand-all
        :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
        <el-table-column
          prop="projectName"
          label="项目"
          sortable
          width="180">
        </el-table-column>
        <el-table-column
          prop="budgetAmount"
          label="预算金额"
          sortable
          width="180">
        </el-table-column>
        <el-table-column
          prop="estimateAmount"
          label="估算金额">
        </el-table-column>
        <el-table-column
          prop="actualPaymentAmount"
          label="实际支付金额">
        </el-table-column>
      </el-table>
    </div>
    </template>
    <script>
      export default {
        data() {
          return {
            tableData: [{
              id: 1,
              projectName: '产品1',
              budgetAmount: '100',
              estimateAmount: '100',
              actualPaymentAmount: '200'
            }, {
              id: 2,
              projectName: '产品2',
              budgetAmount: '100',
              estimateAmount: '200',
              actualPaymentAmount: '200'
            }, {
              id: 3,
              projectName: '产品3',
              budgetAmount: '100',
              estimateAmount: '200',
              actualPaymentAmount:200,
              children: [{
                  id: 31,
                  projectName: '子产品3-1',
                  budgetAmount: '100',
                  estimateAmount: '200',
                  actualPaymentAmount: '200'
                }, {
                  id: 32,
                  projectName: '子产品3-2',
                  budgetAmount: '100',
                  estimateAmount: '200',
                  actualPaymentAmount: '200'
              }]
            }, {
              id: 4,
              projectName: '产品4',
              budgetAmount: '100',
              estimateAmount: '200',
              actualPaymentAmount: '200'
            }]
          }
        }
      }
    </script>