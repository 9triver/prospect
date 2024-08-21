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
            <!-- 节点不是叶子节点（即有子节点）时渲染 -->
            <!-- <span v-if="!node.isLeaf" style="display: flex; align-items: center;"> -->
            <!-- 当 data.type === 1 时渲染 -->
            <span v-if="data.type === 1" style="display: flex; align-items: center;">
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
const curSelectTreeNode = ref<IDocumentmenuTreeNode | null>(null);
// 定义存储选中节点子节点的响应式变量
const selectedChildren = ref<IDocumentmenuTreeNode[]>([]);
// 记录当前节点的 fileUrl
const fileUrl = ref<string | null>(null);
// 记录当前节点的 fileUrl
const selected = ref<IDocumentmenuTreeNode | null>(null);

// 处理树节点的点击事件
const handleNodeClick = (data:IDocumentmenuTreeNode)=>{
  if (data.type === 2) { // 只在 type 为 2 时更新右侧内容区域
    // 将子节点数据存储到响应式变量中
    curSelectTreeNode.value = data;
  } else {
    // 确保 data.children 存在且是一个数组
    const children = Array.isArray(data.children) ? data.children : [];
    selectedChildren.value = children;

    // 查找 type 为 2 的子节点
    const matchingChildren = children.filter(item => item.type === 2);

    if (matchingChildren.length > 0) {
      curSelectTreeNode.value = data;
      // 如果找到符合条件的子节点，设置为当前选择节点
      // curSelectTreeNode.value = matchingChildren[0]; // 如果你只需要一个节点，可以选择第一个匹配的节点
    } else {
      // 如果没有找到符合条件的子节点，清空当前选择节点
      curSelectTreeNode.value = null;
    }
    fileUrl.value = data.fileurl ?? null;
    selected.value = data;
  }
} 
// 将子节点数据和节点状态提供给下游组件
provide('curSelectTreeNode', curSelectTreeNode);
provide('fileUrl', fileUrl);
provide('selected', selected);

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