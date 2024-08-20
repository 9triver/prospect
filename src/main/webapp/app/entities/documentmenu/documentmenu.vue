<template>
  <div class="document-menu-wrapper">
    <div class="left">
      <div class="left-tree-wrapper">
        <el-tree
          :data="treeData"
          :props="{
            children: 'children',
            label: 'menuname',
          }"
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
    </div>
    <div class="right">
      <rightContent/>
    </div>
  </div>
</template>

<script setup lang='ts'>
import { ref, inject,provide, type Ref, onMounted} from 'vue'
import DocumentmenuService from './documentmenu.service';
import { type IDocumentmenuTreeNode } from '@/shared/model/documentmenu.model';
import { useAlertService } from '@/shared/alert/alert.service';
import {convertToTree} from './utils'
import rightContent from './RightContent/right-content.vue';

const documentmenuService = inject('documentmenuService', () => new DocumentmenuService());
const alertService = inject('alertService', () => useAlertService(), true);

// 定义数据源 树结构
const treeData: Ref<IDocumentmenuTreeNode[]> = ref([]);
// 初始化函数 用来初始化树结构的数据
const initTree = async () => {
  try {
    const res = await documentmenuService().retrieve();
    //转换扁平数据为树状结构，拼接树状结构
    const _treeData = convertToTree(res.data);
    treeData.value = _treeData;
  } catch (err:any) {
    alertService.showHttpError(err.response);
  } finally {
  }
}
// 在生命周期里面调用 初始化函数 
onMounted(async () => {
  await initTree();
});
// 记录当前点击的树节点
const curSelectTreeNode = ref<IDocumentmenuTreeNode>()
// 该值可以被后代组件获取
provide('curSelectTreeNode',curSelectTreeNode)
// 处理树节点的点击事件
const handleNodeClick = (data:IDocumentmenuTreeNode)=>{
  curSelectTreeNode.value = data
}


</script>

<style>
  .document-menu-wrapper{
    display: flex;
    .left{
      flex-basis: 300px;
    }
    .right{
      flex: 1;
    }
  }
</style>