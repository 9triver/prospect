<template>
  <div>
    <el-table :data="tableData" style="width: 100%;margin-bottom: 20px;" row-key="id" border default-expand-all
      :tree-props="{ children: 'children', hasChildren: 'hasChildren' }">
      <el-table-column prop="projectName" label="任务名称" sortable width="180">
      </el-table-column>
      <el-table-column prop="actualStartTime" label="实际开始时间" sortable width="180" align="center">
      </el-table-column>
      <el-table-column prop="actualEndTime" label="实际结束时间" align="center">
      </el-table-column>
      <el-table-column label="进度" align="center">
        <template #default="scope">
          <el-progress :percentage="scope.row.process" :format="formatProgress"/>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
export default {

  data() {
    return {
      tableData: [
        {
          "id": 1,
          "projectName": "产品1",
          "actualStartTime": "2023-01-01",
          "actualEndTime": "-",
          "process": 24
        },
        {
          "id": 2,
          "projectName": "产品2",
          "actualStartTime": "2023-02-01",
          "actualEndTime": "2023-02-28",
          "process": 100
        },
        {
          "id": 3,
          "projectName": "产品3",
          "actualStartTime": "2023-03-01",
          "actualEndTime": "-",
          "process": 62,
          "children": [
            {
              "id": 31,
              "projectName": "子产品3-1",
              "actualStartTime": "2023-04-01",
              "actualEndTime": "-",
              "process": 7
            },
            {
              "id": 32,
              "projectName": "子产品3-2",
              "actualStartTime": "2023-05-01",
              "actualEndTime": "-",
              "process": 6
            }
          ]
        },
        {
          "id": 4,
          "projectName": "产品4",
          "actualStartTime": "2023-06-01",
          "actualEndTime": "-",
          "process": 79
        }
      ]
    }
  }
}
</script>