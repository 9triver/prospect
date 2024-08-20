<template>
  <div class="element-plus-tree">
    <el-tree
      :data="documentmenus"
      :props="defaultProps"
      :show-checkbox="false"
      :default-expand-all="true"
      :highlight-current="true"
      :expand-on-click-node="false"
      :indent="22"
      @node-click="handleNodeClick"
    >
      <template #default="{ node, data }">
        <span v-if="!node.isLeaf" style="display: flex; align-items: center;">
          <el-icon v-if="node.expanded" style="margin: 0 6px 0 0px;" size="16"><FolderOpened /></el-icon>
          <el-icon v-else style="margin: 0 6px 0 0px;" size="16"><Folder /></el-icon>
          <small>{{ node.label }}</small>
        </span>
 
        <span v-else style="display: flex; align-items: center;">
          <el-icon style="margin: 0 6px 0 0px;" size="16"><Document /></el-icon>
          <small>{{ node.label }}</small>
        </span>
      </template>
    </el-tree>
  </div>
</template>

<script lang="ts" src="./documentmenu.component.ts"></script>
