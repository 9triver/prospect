<template>
    <div class="file-preview-wrapper" v-loading="loading">
        <component :is="map[fileType]" :fileInfo="fileInfo"/>
    </div>
</template>

<script setup lang='ts'>
import type { IDocumentmenu } from '@/shared/model/documentmenu.model';
import axios from 'axios';
import { ref, reactive, onMounted, inject, computed, defineComponent, type Ref} from 'vue'
import TxtViewer from './previewer/txt-viewer.vue';
import pngViewer from './previewer/png-viewer.vue'; 
import docxViewer from './previewer/docx-viewer.vue';
import xlsxViewer from './previewer/xlsx-viewer.vue'
import pdfViewer from './previewer/pdf-viewer.vue';

    const map:Record<string,any> = {
        "txt":TxtViewer,
        "png":pngViewer,
        "docx":docxViewer,
        "xlsx":xlsxViewer,
        "pdf":pdfViewer
    }


    const loading = ref(true)
    const curSelectFile = inject<Ref<IDocumentmenu>>('curSelectFile')
    const fileType = computed(()=>{
        let fileName = curSelectFile?.value?.menuname
        return fileName?.split('.').pop()||''
    })
    // 根据选中的文件查询到的文件内容
    const fileInfo = ref()
    onMounted(async()=>{
        const fileurl = curSelectFile?.value.fileurl ?? '';
        const filename = curSelectFile?.value.menuname ?? '';
        const baseApiUrl = 'api/files/download';
        let responseType:Record<string,string> = fileType.value=='txt'?{}:{responseType: 'blob'}
        let _fileInfo = await axios.get(baseApiUrl, {
            ...responseType,
            timeout: 60000, // 设置超时时间为60秒
            params:{fileurl: fileurl} 
        })
        fileInfo.value = _fileInfo.data
        loading.value = false
    })

</script>
<style lang='scss' scoped>

</style>