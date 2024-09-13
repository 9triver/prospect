<template>
    <div>
        <div class="process-definition-version-wrapper">
            <div class="version-list-wrapper">
                <el-scrollbar>
                    <div :class="`version-item-wrapper ${currentSelectInfo?.id == id ? 'selected' : ''}`"
                        v-for="{ id, name, deploymentTime, xmlInfo, version } in versionList"
                        @click="handleSelect({ id, xmlInfo })">
                        <div>
                            <div class="name">{{ name }}</div>
                            <div class="time">{{ moment.tz(deploymentTime, "Asia/Shanghai").tz("UTC").format("YYYY-MM-DD HH:mm:ss")}}</div>
                        </div>
                        <div class="version">V{{ version }}</div>
                    </div>
                </el-scrollbar>
            </div>
            <div class="process-definition-preview" v-loading="loading">
                <div id="process-version-preview-container" ref="canvas"></div>
            </div>
        </div>
    </div>
</template>

<script setup lang='ts'>
import axios from 'axios';
import { ref, reactive, onMounted } from 'vue'
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

onMounted(async () => {
    versionList.value = (await getVersionList())
    viewer.value = new BpmnJS({
        container: "#process-version-preview-container",
        additionalModules: [
            ModelingModule, // 基础工具 MoveModule、SetColor 等依赖于此
            MoveCanvasModule, // 移动整个画布
            zoomScroll // 放大缩小
        ]
    });
})

const getVersionList = async () => {
    return (await axios.post("api/queryProcessDefinitionVersion", {
        key: processDefinitionKey
    })).data
}

const handleSelect = (version: version) => {
    currentSelectInfo.value = version
    loading.value = true
    setTimeout(() => {
        loading.value = false
        viewer.value.importXML(version.xmlInfo, function (err: any, instance: any) {
            if (err) {
                console.error('Could not import BPMN 2.0 XML.', err);
            }
        });
    }, 200);
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
}
</style>