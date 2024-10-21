<template>
  <div class="document-wrapper">
    <div class="left-tree-wrapper">
      <LeftTree :treeData="treeData" :setCurSelectData="setCurSelectData"/>
    </div>
    <div class="main-content">
      <div class="display-list">
        <DispalyList :selectNodeData="selectNodeData"/>
      </div>
    </div>
  </div>
</template>

<script setup lang='ts'>
import {onMounted,ref} from 'vue'
import LeftTree from './custom/left-tree.vue'
import DispalyList from './custom/display-list.vue'
import {getTreeData,type TreeNode} from './custom/api/index'


const treeData = ref<TreeNode[]>()
const selectNodeData = ref<TreeNode >()


onMounted(async()=>{
  let _treeData = await getTreeData()
  console.log('treeData',treeData)
  treeData.value = _treeData
})

const setCurSelectData = (_selectNodeData:TreeNode)=>{
  console.log('zhou',_selectNodeData.fileType)
  selectNodeData.value = _selectNodeData
}


</script>
<style lang='scss' scoped>
  .document-wrapper{
    display: flex;
    .left-tree-wrapper{
      flex-basis: 300px;
    }
    .main-content{
      flex: 1;
    }
  }
</style>