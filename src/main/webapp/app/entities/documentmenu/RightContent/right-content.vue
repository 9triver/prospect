
<template>
  <div class="document-right-content-wrapper">
    <div class="operator-wrapper" v-if="selected">
      <el-upload
        class="upload-demo"
        action="api/files/upload"
        multiple
        :data="uploadData"
        :before-upload="beforeUpload"
        :on-success="handleSuccess"
        :on-error="handleError"
        list-type="text"
      >
        <el-button type="primary">上传文件</el-button>
        <el-button type="danger" v-if="delBtnVisible" @click="handleMultiDelete">批量删除</el-button>
      </el-upload>
      <p>{{ uploadMessage }}</p>
    </div>
    <div class="document-list-wrapper" >
      <el-table 
        :data="listData" 
        row-key="id"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55" />
        <el-table-column prop="menuname" label="名称"/>
        <el-table-column prop="createtime" label="上传时间"/>
        <el-table-column prop="creatorid" label="上传人"/>
        <el-table-column label="操作" align="center">
          <template #default="{ row }">
            <div class="operator-column">
              <el-icon @click="downloadFile(row)"><Download /></el-icon>
              <el-icon @click="handlePreview(row)"><View /></el-icon>
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
    <div class="file-preview-wrapper">
      <el-dialog
        v-model="previewDialogVisible"
        title="文件预览"
        width="500"
        destroy-on-close
      >
        <FilePreview/>
      </el-dialog>
    </div>
  </div>
</template>

<script setup lang='ts'>
import { inject, computed, type Ref, ref, provide} from 'vue'
import { convertToList } from '../utils';
import type { IDocumentmenu } from '@/shared/model/documentmenu.model';
import { ElMessage, ElMessageBox } from 'element-plus';
import type { UploadProps, UploadFile } from 'element-plus';
import axios from 'axios';
import FilePreview from '../FilePreview/file-preview.vue';

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
const uploadMessage = ref<string>('');
// 从父组件中注入 fileUrl，确保 fileUrl 是 string 或 null，不会是 undefined
// const fileUrl = inject<Ref<string | null>>('fileUrl', ref(null));
// const uploadData = computed(() => ({
//   fileUrl: fileUrl.value ?? ''
// }));

const selected = inject<Ref<IDocumentmenu | null>>('selected', ref(null));
const uploadData = computed(()=>{
  return {
    selected: selected.value ? JSON.stringify(selected.value) : ''
  };
})

// 处理文件上传前的验证
const beforeUpload = (file: File) => {
  // const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png';
  // const isLt500KB = file.size / 1024 < 500;

  // if (!isJpgOrPng) {
  //   ElMessage.error('Uploaded file must be JPG or PNG!');
  // }
  // if (!isLt500KB) {
  //   ElMessage.error('Uploaded file size cannot exceed 500KB!');
  // }

  // return isJpgOrPng && isLt500KB;

};
// 处理上传成功
const handleSuccess: UploadProps['onSuccess'] = (response, file) => {
  ElMessage.success('文件上传成功!');

};
// 处理上传失败
const handleError: UploadProps['onError'] = (error, file) => {
  ElMessage.error(`文件上传失败: ${file.name}`);
};



//下载文件
const downloadFile = (data: IDocumentmenu) =>{
  alert("下载文件:"+JSON.stringify(data));
  const fileurl = data.fileurl ?? '';
  const filename = data.menuname ?? '';
  const baseApiUrl = 'api/files/download';
  axios.get(baseApiUrl, {
    params:{fileurl: fileurl} 
  })
    .then(response => {
      const url = window.URL.createObjectURL(new Blob([response.data]));
      const link = document.createElement('a');
      link.href = url;
      link.setAttribute('download', filename);
      document.body.appendChild(link);
      link.click();
    })
    .catch(error => {
      alert('File download failed');
    });
}

// 允许预览的文件类型
const allowFileType = ['txt','png','jpg']
// 显隐属性，控制对话框显隐
const previewDialogVisible = ref(false)
const curSelectFile = ref<IDocumentmenu>()
// 预览文件
const handlePreview = (data: IDocumentmenu)=>{
  let fileType = data?.menuname?.split('.')?.pop()
  if(fileType){
    if(!allowFileType.includes(fileType)){
      ElMessage.error("不支持的文件格式");
    }else{
      curSelectFile.value = data
      previewDialogVisible.value = true
    }
  }else{
    ElMessage.error("无效的文件名");
  }
}
provide('curSelectFile', curSelectFile);
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
