<template>
  <div class="department-wrapper">
    <div class="collapse-trigger">
      <span v-if="collapse" @click="collapse=!collapse">
        <el-icon><ArrowRight /></el-icon>
      </span>
      <span v-else @click="collapse=!collapse">
        <el-icon><ArrowLeft /></el-icon>
      </span>
    </div>
    <div :class="`left ${collapse?'left-collapse':''}`">
      <div class="left-wrapper">
        <h3 style="margin: 20px 0px">组织机构树</h3>
        <el-input v-model="filterInputValue" placeholder="搜索" >
          <template #suffix>
            <el-icon class="el-input__icon"><search /></el-icon>
          </template>
        </el-input>
        <el-tree
          ref="treeRef"
          class="filter-tree"
          :data="treeData"
          highlight-current
          :expand-on-click-node="false"
          :props="{
            children: 'children',
            label: 'name',
          }"
          default-expand-all
          @node-click="handleTreeSelect"
          :filter-node-method="filterNode"
        />
      </div>
    </div>
    <div :class="`right ${collapse?'right-collapse':''}`">
      <rightContent :curSelectTreeNodeData="curSelectTreeNodeData"/>
    </div>
  </div>
</template>



<script lang="ts" src="./department.component.ts"></script>

<style>
.department-wrapper{
  display: flex;
  position: relative;
  .left{
    width: 300px;
    flex-basis: 300px;
    border: 1px solid #ccc;
    transition: all .5s;
    margin: 0px 20px;
    .left-wrapper{
      padding: 10px 20px;
      width: 240px;
    }
  }
  .collapse-trigger{
    position: absolute;
    left: 0px;
    top: 100px;
    .el-icon{
      cursor: pointer;
      &:hover{
        font-size: 17px;
        transition: all .2s;
      }
    }
  }
  .left-collapse{
    width: 0px;
    flex-basis: 0px;
    transition: all .5s;
    overflow: hidden;
    border: none;
    padding: unset;
    margin-right: unset;
  }
  .right{
    flex: 1;
    &:not(.right-collapse){
      /* 防止flex布局被撑开 */
      max-width: calc(100% - 300px);
    }
  }
}
</style>
