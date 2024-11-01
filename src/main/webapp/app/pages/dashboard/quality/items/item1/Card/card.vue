<template>
    <div :class="`dashboard-card-wrapper`" @click="showInfo">
        <div>
            <div>
                <span>{{ title }}</span>
            </div>
        </div>
        <div style="margin: 20px 0px 0px 0px;">
            <h1 style="display: inline-block; margin: 0px;">{{ value }}</h1>
            <span>{{ unit }}</span>
        </div>
        <i class="dashboard-card-icon" :style="`color: ${color}`">
            <slot></slot>
        </i>
    </div>
</template>
<script>
export default {
    data() {
        return {}
    },
    props: {
        // 标题
        title: {
            type: String,
            default: ""
        },
        // 值
        value: {
            type: String,
            default: ""
        },
        // 单位
        unit: {
            type: String,
            default: "（个）"
        },
        // 颜色
        color: {
            type: String,
            default: "#011c4a"
        },
        // 下标
        index: {
            type: Number,
            default: 0
        },
        // 当前选中下标
        curShow: {
            type: Number,
            default: 0
        }
    },
    methods: {
        showInfo() {
            this.$emit('showInfo',this.index);
            console.log(this.index)
        }
    },
    components: {},
}
</script>
<style scoped>
.dashboard-card-wrapper {
    flex: 1 1 0%;
    padding: 20px;
    box-shadow: 1px 1px 10px 1px #ccc;
    margin: 10px;
    border-radius: 10px;
    border: 1px solid #ffffff00;
    position: relative;
    cursor: pointer;
    /* display: flex; */
}
/* 鼠标悬浮样式 | 选中的样式*/
.dashboard-card-wrapper:hover,.dashboard-card-wrapper-selected {
    border: 1px dashed #000;
    transition: all 0.3s;
    box-shadow: 1px 1px 10px 1px #ccc;
    background: #ddd;
}

.dashboard-card-icon{
    height: 1em;
    width: 1em;
    position: absolute;
    right: 20px;
    top: 20px;
    font-size: 60px;
}
</style>