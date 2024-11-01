<template>
    <div ref="chart-content" style="flex: 1;height: 100%;">
        <div>加载中...</div>
    </div>

</template>
<script>
import * as echarts from "echarts";
import { getData } from './api/index.js'
export default {
    name: 'myComponent',
    data() {
        return {}
    },
    props: {},
    methods: {
    },
    components: {},
    async mounted() {
        const data = await getData()
        const chart = echarts.init(this.$refs['chart-content'])
        let option = {
            backgroundColor: '#ffffff', // 设置背景为白色
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    lineStyle: {
                        color: {
                            type: 'linear',
                            x: 0,
                            y: 0,
                            x2: 0,
                            y2: 1,
                            colorStops: [
                                { offset: 0, color: 'rgba(240,240,240,0)' }, // 设置渐变起始颜色
                                { offset: 0.5, color: 'rgba(240,240,240,1)' }, // 设置渐变中间颜色
                                { offset: 1, color: 'rgba(240,240,240,0)' } // 设置渐变结束颜色
                            ],
                            global: false
                        }
                    },
                },
            },
            grid: {
                    top: '20%',
                    bottom: "20%"
                },
            legend: {
                top:"20"
            },
            xAxis: [
                {
                    type: 'category',
                    axisLine: {
                        show: true,
                        lineStyle: {
                            color: '#cccccc' // 设置轴线颜色为淡灰色
                        }
                    },
                    splitArea: {
                        // show: true,
                        color: '#f00',
                        lineStyle: {
                            color: '#f00'
                        },
                    },
                    axisLabel: {
                        color: '#333333', // 设置标签颜色为深灰色
                    },
                    splitLine: {
                        show: false
                    },
                    boundaryGap: false,
                    data: data.map(item=> item.title), // 最近六个月的数据
                },
            ],
            yAxis: [
                {
                    name: '金额（千万）',
                    type: 'value',
                    min: 0,
                    // max: 140,
                    splitNumber: 4,
                    splitLine: {
                        show: true,
                        lineStyle: {
                            color: 'rgba(192,192,192,0.5)' // 设置分割线颜色为淡灰色
                        }
                    },
                    axisLine: {
                        show: false,
                    },
                    axisLabel: {
                        show: true,
                        margin: 20,
                        textStyle: {
                            color: '#000',
                        },
                    },
                    axisTick: {
                        show: false,
                    },
                },
            ],
            series: [
                {
                    name: '零星采购',
                    type: 'line',
                    smooth: true, // 是否平滑
                    showAllSymbol: true,
                    symbol: 'circle',
                    symbolSize: 15,
                    lineStyle: {
                        normal: {
                            color: "#5470c6", // 设置线条颜色
                            shadowColor: 'rgba(0, 0, 0, .3)',
                            shadowBlur: 0,
                            shadowOffsetY: 5,
                            shadowOffsetX: 5,
                        },
                    },
                    itemStyle: {
                        color: "#5470c6", // 设置标记颜色
                        borderColor: "#ffffff",
                        borderWidth: 3,
                        shadowColor: 'rgba(0, 0, 0, .3)',
                        shadowBlur: 0,
                        shadowOffsetY: 2,
                        shadowOffsetX: 2,
                    },
                    areaStyle: {
                        normal: {
                            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                                { offset: 0, color: 'rgba(84,112,198,0.3)' }, // 设置填充颜色
                                { offset: 1, color: 'rgba(84,112,198,0)' }
                            ], false),
                            shadowColor: 'rgba(84,112,198, 0.9)',
                            shadowBlur: 20
                        }
                    },
                    data: data.map(item=>item.purchase)
                },
                {
                    name: '分摊费用',
                    type: 'line',
                    smooth: true, // 是否平滑
                    showAllSymbol: true,
                    symbol: 'circle',
                    symbolSize: 15,
                    lineStyle: {
                        normal: {
                            color: "#02a1d9", // 设置线条颜色
                            shadowColor: 'rgba(0, 0, 0, .3)',
                            shadowBlur: 0,
                            shadowOffsetY: 5,
                            shadowOffsetX: 5,
                        },
                    },
                    itemStyle: {
                        color: "#02a1d9", // 设置标记颜色
                        borderColor: "#ffffff",
                        borderWidth: 3,
                        shadowColor: 'rgba(0, 0, 0, .3)',
                        shadowBlur: 0,
                        shadowOffsetY: 2,
                        shadowOffsetX: 2,
                    },
                    areaStyle: {
                        normal: {
                            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                                { offset: 0, color: 'rgba(2,161,217,0.3)' }, // 设置填充颜色
                                { offset: 1, color: 'rgba(2,161,217,0)' }
                            ], false),
                            shadowColor: 'rgba(2,161,217, 0.9)',
                            shadowBlur: 20
                        }
                    },
                    data: data.map(item=>item.share)
                },
                {
                    name: '事务费',
                    type: 'line',
                    smooth: true, // 是否平滑
                    showAllSymbol: true,
                    symbol: 'circle',
                    symbolSize: 15,
                    lineStyle: {
                        normal: {
                            color: "#31395d", // 设置线条颜色
                            shadowColor: 'rgba(0, 0, 0, .3)',
                            shadowBlur: 0,
                            shadowOffsetY: 5,
                            shadowOffsetX: 5,
                        },
                    },
                    itemStyle: {
                        color: "#31395d", // 设置标记颜色
                        borderColor: "#ffffff",
                        borderWidth: 3,
                        shadowColor: 'rgba(0, 0, 0, .3)',
                        shadowBlur: 0,
                        shadowOffsetY: 2,
                        shadowOffsetX: 2,
                    },
                    areaStyle: {
                        normal: {
                            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                                { offset: 0, color: 'rgba(49,57,93,0.3)' }, // 设置填充颜色
                                { offset: 1, color: 'rgba(49,57,93,0)' }
                            ], false),
                            shadowColor: 'rgba(49,57,93, 0.9)',
                            shadowBlur: 20
                        }
                    },
                    data: data.map(item=>item.work)
                },
                {
                    name: '外协费',
                    type: 'line',
                    smooth: true, // 是否平滑
                    showAllSymbol: true,
                    symbol: 'circle',
                    symbolSize: 15,
                    lineStyle: {
                        normal: {
                            color: "#3ba272", // 设置线条颜色
                            shadowColor: 'rgba(0, 0, 0, .3)',
                            shadowBlur: 0,
                            shadowOffsetY: 5,
                            shadowOffsetX: 5,
                        },
                    },
                    itemStyle: {
                        color: "#3ba272", // 设置标记颜色
                        borderColor: "#ffffff",
                        borderWidth: 3,
                        shadowColor: 'rgba(0, 0, 0, .3)',
                        shadowBlur: 0,
                        shadowOffsetY: 2,
                        shadowOffsetX: 2,
                    },
                    areaStyle: {
                        normal: {
                            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                                { offset: 0, color: 'rgba(59, 162, 114,0.3)' }, // 设置填充颜色
                                { offset: 1, color: 'rgba(59, 162, 114,0)' }
                            ], false),
                            shadowColor: 'rgba(59, 162, 114, 0.9)',
                            shadowBlur: 20
                        }
                    },
                    data: data.map(item=>item.outSource)
                }
            ]
        };
        chart.setOption(option);
        window.addEventListener('resize', function () {
            chart.resize();
        });
    }
}
</script>
<style></style>