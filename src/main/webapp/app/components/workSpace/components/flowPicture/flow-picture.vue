<template>
    <div>
        <el-dialog model-value="true" title="流程进度查看" width="1000" draggable
            @update:model-value="() => emit('closePreviewDialog')">
            <div id="process-preview-container" ref="canvas"></div>
            <svg xmlns="http://www.w3.org/2000/svg" style="position: absolute; left: -100px; top: -100px;">
                <defs>
                    <marker id="custom-marker" viewBox="0 -3 6 6" refX="8" refY="0" markerWidth="6" markerHeight="6"
                        orient="auto">
                        <path d="M0,-3L6,0L0,3" fill="#00be00" stroke="#00be00" stroke-width="1" />
                    </marker>
                </defs>
            </svg>
        </el-dialog>
    </div>
</template>

<script setup lang='ts'>
import axios from 'axios';
import { ref, reactive, onMounted } from 'vue'
import BpmnJS from 'bpmn-js';
import 'bpmn-js/dist/assets/diagram-js.css'; // 确保引入样式
import 'bpmn-js/dist/assets/bpmn-font/css/bpmn.css';
import 'bpmn-js/dist/assets/bpmn-font/css/bpmn-codes.css';
import 'bpmn-js/dist/assets/bpmn-font/css/bpmn-embedded.css';
import overlays from 'diagram-js/lib/features/overlays/Overlays.js';
import MoveModule from 'diagram-js/lib/features/move'
import ModelingModule from 'bpmn-js/lib/features/modeling'
import MoveCanvasModule from 'diagram-js/lib/navigation/movecanvas'
import zoomScroll from './zoomScroll.js' // 📌注意是使用自己定义过的哦~
import type { LocationQueryValue } from 'vue-router';
interface processDefinition {
    id: string,
    key: string,
    name: string,
    deploymentTime: string
}

const props = defineProps<{
    procInstId: LocationQueryValue | LocationQueryValue[]
}>()


const emit = defineEmits<{
    closePreviewDialog: []
}>()
const canvas = ref<any>(null)


const xml = ref<string>('')
onMounted(async () => {
    let res = await axios.post("api/getRunningProcessDiagram1", {
        procInstId: props.procInstId
    })
    const { activeActivityIds, flowIds, xmlInfo } = res.data
    xml.value = xmlInfo
    const viewer = new BpmnJS({
        container: canvas.value,
        additionalModules: [
            // MoveModule, // 可以调整元素
            ModelingModule, // 基础工具 MoveModule、SetColor 等依赖于此
            MoveCanvasModule, // 移动整个画布
            zoomScroll, // 放大缩小
            overlays,
        ]
    });
    viewer.importXML(xml.value, function (err: any, instance: any) {
        if (err) {
            console.error('Could not import BPMN 2.0 XML.', err);
        } else {

            // 获取 canvas 对象
            const canvas = viewer.get('canvas');

            // 假设您有一个要高亮的节点 ID 列表
            const highlightNodes = ['Activity_1lfavop'];

            const highlightLines = ['Flow_076nps6', 'Flow_07gpokg'];

            flowIds.forEach((lineId: string) => {
                canvas.addMarker(lineId, 'highlight-line');
            });

            activeActivityIds.forEach((nodeId: string) => {
                // 为节点添加标记
                canvas.addMarker(nodeId, 'highlight');
            });
        }
    });
    // viewer.resize(canvas.value?.clientWidth, canvas.value?.clientHeight);
    // viewer.get('canvas').zoom('fit-viewport', { nice: true });
    console.log('viewer', viewer)
})

</script>
<style lang='scss'>
#process-preview-container {
    height: 600px;
}

.highlight .djs-visual rect {
    stroke: rgba(214, 126, 125, 1) !important;
    stroke-width: 2px !important;
    fill: rgba(251, 233, 209, 1) !important;
    stroke-dasharray: 4 4;
    animation: rotateBorder 5s infinite linear;
}

/* 定义旋转动画 */
@keyframes rotateBorder {
    0% {
        stroke-dashoffset: 100;
    }

    50% {
        stroke-dashoffset: 50;
    }

    100% {
        stroke-dashoffset: 0;
    }
}

.highlight-line g.djs-visual>:nth-child(1) {
    stroke: rgba(0, 190, 0, 1) !important;
    marker-end: url(#custom-marker) !important;
}
</style>