<template>
  <div>
    <div class="form-wrapper">
      <el-form :inline="true" :model="queryCondition" ref="ruleFormRef">
        <el-form-item label="名称" prop="name">
          <el-input v-model="queryCondition.name" placeholder="请输入名称"/>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-checkbox-group v-model="queryCondition.status">
          <el-checkbox value="1" size="large">有效</el-checkbox>
          <el-checkbox value="0" size="large">无效</el-checkbox>
        </el-checkbox-group>
        </el-form-item>
      </el-form>
    </div>
    <div class="operator">
      <el-button type="primary" @click="currentSelectDepartmentId='';dialogVisible=true">新增</el-button>
      <el-button type="primary" @click="handleQuery">查询</el-button>
      <el-button @click="handleReset">重置</el-button>
    </div>
    <el-table :data="tableData" row-key="id" style="width: 100%" v-loading="loading">
      <!-- <el-table-column prop="id" label="ID" /> -->
      <el-table-column prop="name" label="名称" />
      <el-table-column prop="description" label="描述" />
      <el-table-column prop="role" label="角色" />
      <el-table-column prop="officersnum" label="人员数量" >
        <template #default="scope">
          <span class="field-default">{{ scope.row.officersnum }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="superior.id" label="上级机构">
        <template #default="scope">
          <td>
            <div v-if="scope.row.superior">
              <router-link :to="{ name: 'DepartmentView', params: { departmentId: scope.row.superior.id } }">{{
                scope.row.superior.id
              }}</router-link>
            </div>
          </td>
        </template>
      </el-table-column>  
      <el-table-column prop="officers.id" label="下属人员" >
        <template #default="scope">
          <td>
            <span v-for="(officers, i) in scope.row.officers" :key="officers.id"
              >{{ i > 0 ? ', ' : '' }}
              <router-link class="form-control-static" :to="{ name: 'OfficersView', params: { officersId: officers.id } }">{{
                officers.id
              }}</router-link>
            </span>
          </td>
        </template>
      </el-table-column>
      <el-table-column label="状态">
        <template #default="scope">
          <el-tag type="success">有效</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template #default="scope">
          <div class="operator-wrapper">
            <a href="#/department" @click="handleEdit(scope.row)">编辑</a>
          </div>
        </template>
      </el-table-column>
    </el-table>

    <!-- 维护表单弹窗 -->
    <el-dialog
      v-model="dialogVisible"
      :title="`组织机构-${currentSelectDepartmentId?'编辑':'新增'}`"
      width="500"
      destroy-on-close
    >
      <div class="department-dialog department-dialog-create" style="height: 300px;">
        <DepartmentUpdate ref="editFormRef" :currentSelectDepartmentId="currentSelectDepartmentId" callByFather/>
      </div>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="editFormRef.save();dialogVisible = false">
            保存
          </el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup lang='ts'>
import { ref, reactive,defineProps, computed} from 'vue'
import {type IDepartmentWithChildren } from '@/shared/model/department.model';
// 引入jhipster生成的编辑界面
import DepartmentUpdate from '../../../../department-update.vue'
import type { FormInstance } from 'element-plus';

// 定义组件预期的prop
const props = defineProps<{curSelectTreeNodeData:IDepartmentWithChildren[]}>()
// 定义一个计算属性来获取表格的树形数据源
const tableData = computed(()=>{
  let {curSelectTreeNodeData} = props
  if(queryConditionTemp.value.name){
    let filterData = findAllMatches(curSelectTreeNodeData,(node:IDepartmentWithChildren)=>node?.name?.includes(queryConditionTemp.value.name))
    return filterData
  }else{
    return curSelectTreeNodeData
  }
})
// 查询条件
const queryCondition = ref({
  name:"",
  status:["1"]
})
// 维护表单弹窗对应的显隐控制属性
const dialogVisible = ref(false)
// 维护表单的引用，通过该引用实现保存
const editFormRef = ref()
// 点击操作按钮编辑 对应列表项组织机构id
const currentSelectDepartmentId = ref<string>()
// 点击编辑按钮之后的处理逻辑
const handleEdit = (row:IDepartmentWithChildren)=>{
  currentSelectDepartmentId.value = row.id
  dialogVisible.value = true
}
// 定义一个中间变量，用来映射查询条件的值，该值不与查询条件同步更新，只有点击查询按钮的时候同步一下查询条件的值，
// 表格数据源计算属性的值依赖于这个中间变量,所以可实现效果,点击查询按钮,中间变量的值会变,中间变量的值变了之后表格数据源也会跟着变
const queryConditionTemp = ref({
  name:"",
  status:[]
})
// 设定一个加载状态,搜索过程中进入加载状态
const loading = ref(false)
// 处理查询逻辑
const handleQuery = ()=>{
  loading.value = true
  queryConditionTemp.value = JSON.parse(JSON.stringify(queryCondition.value))
  setTimeout(()=>{loading.value=false},500)
}
// 前端模拟查询,通过查找树结构数据来实现
const findAllMatches = (treeData:IDepartmentWithChildren[], findFn:Function)=>{
  const results:IDepartmentWithChildren[] = [];

  // 递归遍历树形结构
  function traverseAndFind(node: IDepartmentWithChildren) {
    if (findFn(node)) {
      results.push(node); // 如果节点符合条件，加入结果数组
    }

    if (node.children && node.children.length > 0) {
      for (const child of node.children) {
        traverseAndFind(child); // 继续递归查找子节点
      }
    }
  }

  // 开始递归查找
  for (const item of treeData) {
    traverseAndFind(item);
  }

  return results; // 返回所有匹配项
}
// 定义查询form引用,通过form引用来实现重置效果
const ruleFormRef = ref<FormInstance>()
// 处理重置逻辑
const handleReset = ()=>{
  ruleFormRef.value?.resetFields()
}


</script>
<style lang='scss'>
  .department-dialog{
    h2{
      display: none;
    }
    .row.justify-content-center > .col-8 > form[name=editForm] > div:last-child{
      display: none;
    }
  }
</style>