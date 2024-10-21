<template>
  <div>
    <div v-if="!selectNodeData">
      <span>请选择目录或数据</span>
    </div>
    <div v-else>
      <div class="operator">
          <el-button type="primary" >上传文档</el-button>
          <el-button>修改文档信息</el-button>
          <el-button type="danger">删除文档</el-button>
      </div>
      <el-table :data="tableData" style="width: 100%" v-loading="loading">
        <el-table-column prop="index" label="NO" width="80">
          <template #default="scope">
            {{scope.$index+1}}
          </template>
        </el-table-column>
        <el-table-column prop="name" label="名称"  align="center"/>
        <el-table-column prop="createTime" label="上传时间"  align="center"/>
        <el-table-column prop="createUserId" label="上传人" align="center"/>
      </el-table>
    </div>

  </div>
  </template>
  
  <script lang="ts" setup>
  import {computed, defineProps, ref} from 'vue'
  import {type TreeNode} from './api/index'

  const props = defineProps<{
    selectNodeData: TreeNode|undefined;
  }>();

  const loading = ref(false);

  const tableData = computed(()=>{
    let data:any = []
    loading.value = true
    const func = (selectNodeData:TreeNode)=>{
      if(selectNodeData.fileType=='file'){
        data.push({
          ...selectNodeData
        })
      }else{
        if(selectNodeData.children&&selectNodeData.children.length>0){
          selectNodeData.children.forEach(item=>{
            func(item)
          })
        }
      }
    }
    if(props.selectNodeData){
      func(props.selectNodeData)
    }
    setTimeout(()=>{
      loading.value = false
    },500)
    return data
  })


</script>
  