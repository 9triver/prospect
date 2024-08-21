<template>
  <div class="document-right-content-wrapper">
    <div class="operator-wrapper">
      <input type="file" @change="onFileChange" />
      <el-button type="primary" @click="uploadFile">上传文档</el-button>
      <el-button type="danger" v-if="delBtnVisible" @click="handleMultiDelete">批量删除</el-button>
    </div>
    <div class="document-list-wrapper">
      <el-table 
        :data="listData" 
        row-key="id"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55" />
        <el-table-column prop="menuname" label="名称"/>
        <el-table-column prop="createTime" label="上传时间">
          <template #default="scope">
            <span>2024-08-20 10:00</span>
          </template>
        </el-table-column>
        <el-table-column prop="createUserId" label="上传人">
          <template #default="scope">
            <span>admin</span>
          </template>
        </el-table-column>
        <el-table-column prop="createUserId" label="操作" align="center">
          <template #default="scope">
            <div class="operator-column">
              <el-icon><Edit /></el-icon>
              <el-popconfirm title="是否删除这条数据?" confirm-button-text="是" cancel-button-text="否" @confirm="handleSingleDelete(scope.row)"	>
                <template #reference>
                  <el-icon><Delete /></el-icon>
                </template>
              </el-popconfirm>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script setup lang='ts'>
import { inject, computed, type Ref, ref} from 'vue'
import { convertToList } from '../utils';
import type { IDocumentmenu } from '@/shared/model/documentmenu.model';
import { ElMessage, ElMessageBox } from 'element-plus';

import axios from 'axios';


const curSelectTreeNode = inject<Ref>('curSelectTreeNode')

  // 展示为列表结构的表格数据 这时候就需要将树结构数据修改为列表数据
  const listData = computed(()=>{
    if(curSelectTreeNode){
      return convertToList(curSelectTreeNode.value)
    }else{
      return []
    }
  })

// 定义选中行对应的响应式变量
const curSelectRows = ref<IDocumentmenu[]>([])
// 多选的时候记录选择的的数据
const handleSelectionChange = (selectRows:IDocumentmenu[])=>{
  curSelectRows.value = selectRows
}

// 删除按钮的显隐控制
const delBtnVisible = computed(()=>{
  return curSelectRows.value?.length >= 1
})
// 批量删除对应的提示
const handleMultiDelete = ()=>{
  ElMessageBox.confirm(
    `你确定删除选中的${curSelectRows.value.length}条数据吗？`,
    '警告',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      confirmButtonClass:"el-button--danger",
      type: 'warning',
    }
  )
    .then(() => {
      deleteDocuments(curSelectRows.value)
      ElMessage({
        type: 'success',
        message: '操作成功',
      })
    })
    .catch(() => {
      ElMessage({
        type: 'info',
        message: '操作取消',
      })
    })
}
// 单条删除对应的提示
const handleSingleDelete = (document:IDocumentmenu)=>{
  deleteDocuments([document])
}
// 删除对应的公共方法 批量删除 单个删除都调用这个方法
const deleteDocuments = (documents:IDocumentmenu[])=>{
  console.log("将要删除的数据",documents)
}

  //文件上传
  const selectedFile = ref<File | null>(null);
  const uploadMessage = ref<string>('');
  const onFileChange = (event: Event) => {
      const input = event.target as HTMLInputElement;
      if (input.files && input.files.length > 0) {
        selectedFile.value = input.files[0];
      }
    };

  const uploadFile = async () => {
    if (!selectedFile.value) {
      uploadMessage.value = '请至少选择一个文件';
      return;
    }

    const formData = new FormData();
    formData.append('file', selectedFile.value);

    const baseApiUrl = 'api/files';

    try {
      const response = await axios.post(`${baseApiUrl}/upload`, formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      });
      uploadMessage.value = '文件上传成功: ' + response.data;
    } catch (error) {
      uploadMessage.value = '上传文件异常: ' + error.message;
    }
  };
</script>
<style lang='scss' scoped>
  .document-right-content-wrapper{
    // 操作列图标
    .operator-column{
      .el-icon{
        cursor: pointer;
        color: #409eff;
        font-size: 16px;
        margin: 0px 10px;
        &:hover{
          color: #79bbff;
        }
      }
    }
  }

</style>
