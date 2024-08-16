<template>
    <div class="element-plus-tree">
      <el-tree
        :data="treeData"
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
   
  <script lang='ts' setup>
  import { onMounted, ref, defineProps } from 'vue'
  import type { TreeNode } from './api/index.ts';
  import type Node from 'element-plus/es/components/tree/src/model/node'
  const props = defineProps({
    treeData:{
      type:Array,
      require:true
    },
    setCurSelectData:{
      type:Function,
      require:true
    }
  })
  console.log('treeData',props.treeData)
  // 树节点属性映射关系
  const defaultProps = {
    children: 'children',
    label: 'name',
  }
   
  /**
   * 树组件点击事件句柄方法
   */
  const handleNodeClick = (data:TreeNode) => {
    console.log("zzz",data.fileType)
    props.setCurSelectData&&props.setCurSelectData(data)
    // console.log(
    //   '%c 树组件点击事件句柄方法 %c handleNodeClick',
    //   'padding: 1px; background-color: #35495e; color: #fff',
    //   'padding: 1px; background-color: #5e7ce0; color: #fff',
    // )
    // console.log('%c ∟ %c node %c =>', 'text-indent: 10px', 'padding: 1px; background-color: #41b883; color: #fff', 'color: #000', node)
    // console.log('%c ∟ %c data %c =>', 'text-indent: 10px', 'padding: 1px; background-color: #41b883; color: #fff', 'color: #000', data)
  }
   
  onMounted(() => {
    // ...
  })
  </script>
  