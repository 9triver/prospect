<template>
    <div class="item1-v2-wrapper">
        <div class="dashboard-card-wrapper">
            <div>
                <div>
                    <span>计划总数</span>
                </div>
            </div>
            <div style="margin: 20px 0px 0px 0px;">
                <h1 style="display: inline-block; margin: 0px;">220</h1>
                <span>（个）</span>
            </div>
            <i class="dashboard-card-icon" :style="`color: ${color}`">
                <svg data-v-d2e47025="" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1024 1024">
                    <path fill="currentColor" d="M288 128h608L736 384l160 256H288v320h-96V64h96z"></path>
                </svg>
            </i>
        </div>
        <div class="chart-content" ref="chart-content"></div>

    </div>
</template>
<script>
import * as echarts from 'echarts'
export default {
    data() {
        return {}
    },
    props: {},
    methods: {},
    components: {},
    mounted() {
        let chart = echarts.init(this.$refs['chart-content'])
        let option = {
            tooltip: {
                trigger: 'item',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: {
                left: '3%',
                right: '3%',
                bottom: '3%',
                top: '3%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'value',
                    name: '数量',
                    nameLocation: 'middle',
                    nameGap: 40,
                    splitLine: {
                        show: false
                    },
                    axisLine: {
                        lineStyle: {
                            color: '#999'
                        }
                    },
                    axisTick: {
                        show: false
                    }
                }
            ],
            yAxis: [
                {
                    type: 'category',
                    data: ['未开始', '进行中', '已完成', '已逾期'].reverse(),
                    axisLine: {
                        lineStyle: {
                            color: '#999'
                        }
                    },
                    axisTick: {
                        show: false
                    }
                }
            ],
            series: [
                {
                    name: '',
                    type: 'bar',
                    barWidth: '60%',
                    itemStyle: {
                        normal: {
                            color: new echarts.graphic.LinearGradient(
                                0, 0, 0, 1,
                                [
                                    { offset: 0, color: '#83bff6' },
                                    { offset: 1, color: '#188df0' }
                                ]
                            ),
                            borderRadius: 10,
                        }
                    },
                    data: [11, 32, 43, 54]
                }
            ]
        }
        chart.setOption(option)
        window.addEventListener('resize', () => {
            chart.resize();
        });
    }
}
</script>
<style>
.item1-v2-wrapper {
    height: 100%;
    display: flex;
}

.chart-content {
    flex: 2;
}

.dashboard-card-wrapper {
    flex: 3;
}


/* 卡片样式 */
.dashboard-card-wrapper {
    flex: 1 1 0%;
    padding: 20px;
    box-shadow: 1px 1px 20px 1px #ccc;
    margin: 20px;
    border-radius: 10px;
    border: 1px solid #ffffff00;
    position: relative;
    cursor: pointer;
    /* display: flex; */
}

/* 鼠标悬浮样式 | 选中的样式*/
.dashboard-card-wrapper:hover,
.dashboard-card-wrapper-selected {
    border: 1px dashed #000;
    transition: all 0.3s;
    box-shadow: 1px 1px 10px 1px #ccc;
    background: #ddd;
}

.dashboard-card-icon {
    height: 1em;
    width: 1em;
    position: absolute;
    right: 20px;
    top: 20px;
    font-size: 60px;
}
</style>