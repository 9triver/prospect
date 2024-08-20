<!-- <template>
  <el-upload
    action="/api/files/upload"
    :on-success="handleSuccess"
    :on-error="handleError"
    :before-upload="beforeUpload"
  >
    <el-button type="primary">Upload</el-button>
  </el-upload>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
  methods: {
    handleSuccess(response: any, file: any, fileList: any) {
      console.log('File uploaded successfully', response);
    },
    handleError(error: any, file: any, fileList: any) {
      console.error('File upload failed', error);
    },
    beforeUpload(file: File) {
      // Perform any validation before upload
      return true;
    }
  }
});
</script> -->

<template>
  <div>
    <h2>文件上传</h2>
    <input type="file" @change="onFileChange" />
    <button @click="uploadFile">上传</button>
    <p>{{ uploadMessage }}</p>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      selectedFile: null,
      uploadMessage: ''
    };
  },
  methods: {
    onFileChange(event) {
      this.selectedFile = event.target.files[0];
    },
    uploadFile() {
      if (!this.selectedFile) {
        this.uploadMessage = '请至少原则一个文件';
        return;
      }
      const formData = new FormData();
      formData.append('file', this.selectedFile);

      const baseApiUrl = 'api/files';

      alert("文件："+JSON.stringify(formData));

      axios.post(`${baseApiUrl}/upload`, formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
      .then(response => {
        this.uploadMessage = response.data;
      })
      .catch(error => {
        this.uploadMessage = '上传文件异常';
      });
    }
  }
};
</script>