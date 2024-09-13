<template>
    <div>
        <div class="process-definition-version-wrapper">
            <div class="version-list-wrapper">
                <el-scrollbar>
                    <div :class="`version-item-wrapper ${currentSelectInfo?.id == id ? 'selected' : ''}`"
                        v-for="{ id, name, deploymentTime, xmlInfo, version } in versionList"
                        @click="handleSelect({ id, xmlInfo, deploymentTime, name })">
                        <div>
                            <div class="name">{{ name }}</div>
                            <div class="time">{{ deploymentTime}}</div>
                        </div>
                        <div class="version">V{{ version }}</div>
                    </div>
                </el-scrollbar>
            </div>
            <div class="process-definition-preview" v-loading="loading">
                <div id="process-version-preview-container" ref="canvas"></div>
            </div>
            <div class="operator-buttons">
                <el-button @click="showXml">查看xml</el-button>
                <el-button @click="exportAsSvg">导出svg</el-button>
                <el-button @click="exportAsXml">导出xml</el-button>
            </div>
            <el-drawer v-model="xmlDrawerVisible" title="I am the title" :with-header="false" size="35%">
                <textarea id="xml-highlight-container"/>
            </el-drawer>
        </div>
    </div>
</template>

<script setup lang='ts'>
import axios from 'axios';
import { ref, reactive, onMounted, watch} from 'vue'
import { useRoute } from 'vue-router'
import moment from 'moment-timezone';
import BpmnJS from 'bpmn-js';
import 'bpmn-js/dist/assets/diagram-js.css'; // 确保引入样式
import 'bpmn-js/dist/assets/bpmn-font/css/bpmn.css';
import 'bpmn-js/dist/assets/bpmn-font/css/bpmn-codes.css';
import 'bpmn-js/dist/assets/bpmn-font/css/bpmn-embedded.css';
import MoveModule from 'diagram-js/lib/features/move'
import ModelingModule from 'bpmn-js/lib/features/modeling'
import MoveCanvasModule from 'diagram-js/lib/navigation/movecanvas'
import zoomScroll from './zoomScroll.js' // 📌注意是使用自己定义过的哦~
import CodeMirror from 'codemirror';
import 'codemirror/mode/xml/xml.js';
import 'codemirror/addon/hint/xml-hint.js';
import 'codemirror/lib/codemirror.css';
import 'codemirror/theme/material.css';
import { nextTick } from 'process';

interface version {
    id?: string,
    name?: string,
    deploymentTime?: string,
    xmlInfo?: string,
    version?: string
}

const versionList = ref<version[]>([])
const route = useRoute()
const { processDefinitionKey } = route.query
const currentSelectInfo = ref<version>()
const viewer = ref()
const loading = ref(false)
const xmlDrawerVisible = ref(false)
let coder: CodeMirror.EditorFromTextArea;

onMounted(async () => {
    let _versionList:Array<version> = (await getVersionList())
    _versionList = _versionList.map(item=>{
        return{
            ...item,
            deploymentTime:moment.tz(item.deploymentTime, "Asia/Shanghai").tz("UTC").format("YYYY-MM-DD HH:mm:ss")
        }
    })
    versionList.value = _versionList
    viewer.value = new BpmnJS({
        container: "#process-version-preview-container",
        additionalModules: [
            ModelingModule, // 基础工具 MoveModule、SetColor 等依赖于此
            MoveCanvasModule, // 移动整个画布
            zoomScroll // 放大缩小
        ]
    });
    currentSelectInfo.value = _versionList[0]
})

watch(currentSelectInfo,(currentSelectInfo)=>{
    loading.value = true
    setTimeout(() => {
        loading.value = false
        viewer.value.importXML(currentSelectInfo?.xmlInfo, function (err: any, instance: any) {
            if (err) {
                console.error('Could not import BPMN 2.0 XML.', err);
            }
        });
    }, 200);
})

const getVersionList = async () => {
    return (await axios.post("api/queryProcessDefinitionVersion", {
        key: processDefinitionKey
    })).data
}

const handleSelect = (version: version) => {
    currentSelectInfo.value = version
}

// 导出为svg
const exportAsSvg = ()=>{
    // 获取渲染后的 canvas 元素
    const canvasElement = viewer.value.get('canvas').getContainer();

    // 使用 innerHTML 获取 SVG 字符串
    let svgString = canvasElement.querySelector("svg").innerHTML;
    // 确保 SVG 字符串是完整的 XML 文档
    svgString = '<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">\r\n' + svgString+"</svg>";
    // 创建一个新的 Blob 对象
    const blob = new Blob([svgString], { type: "image/svg+xml;charset=utf-8" });
    // 创建一个下载链接
    const link = document.createElement('a');
    link.href = URL.createObjectURL(blob);
    link.download = (`${currentSelectInfo.value?.name}-${currentSelectInfo.value?.deploymentTime}`||'未命名')+'.svg';
    // 触发点击事件以下载文件
    link.click();
}
// 预览xml
const showXml = ()=>{
    xmlDrawerVisible.value = true
    nextTick(()=>{
        debugger
        console.log('nextTick',nextTick)
        // 初始化编辑器
        if(!coder){
            coder = CodeMirror.fromTextArea(
                document.getElementById('xml-highlight-container') as HTMLTextAreaElement,
                {
                lineWrapping: true,
                mode: 'application/xml', // HMTL混合模式
                theme: 'material',
                lineNumbers: true,
                lint: true,
                // theme: 'monokai', // 使用monokai模版
                },
            );
            coder.setSize('100%', '100%');
            coder.setValue(currentSelectInfo.value?.xmlInfo)
        }else{
            coder.setValue(currentSelectInfo.value?.xmlInfo)
        }
    })
}
// 导出xml
const exportAsXml = ()=>{
    const blob = new Blob([currentSelectInfo.value?.xmlInfo||""], { type: 'text/plain;charset=utf-8' });
    const link = document.createElement('a');
    link.href = URL.createObjectURL(blob);
    link.download = (`${currentSelectInfo.value?.name}-${currentSelectInfo.value?.deploymentTime}`||'未命名')+'.xml';
    link.click();
}


</script>
<style lang='scss' scoped>
.process-definition-version-wrapper {
    display: flex;
    height: calc(100vh - 160px);
    overflow: auto;

    // flex-direction: column;
    .version-list-wrapper {
        flex-basis: 200px;

        .version-item-wrapper {
            display: flex;
            margin: 5px 0px;
            cursor: pointer;
            transition: all .2s;
            justify-content: center;

            .version {
                flex-basis: 20px;
                display: flex;
                align-items: center;
                justify-content: center;
                margin-left: 5px;
            }

            .time {
                font-size: 14px;
                color: #9f9c9c;
            }

            &:hover {
                background: #85c2ff;
                color: #fff;
                border-radius: 5px;

                .time {
                    color: #fff;
                }
            }
        }

        .version-item-wrapper.selected {
            transition: all .2s;
            background: #409eff;
            color: #fff;
            border-radius: 5px;

            .time {
                color: #fff;
            }
        }
    }

    .process-definition-preview {
        flex: 1;

        #process-version-preview-container {
            height: 100%;
        }
    }

    .operator-buttons{
        position: absolute;
        right: 0;
    }
}
</style>