<template>
    <div ref="chart-content-1" style="flex: 1;height: 100%;">
        <div>加载中...</div>
    </div>

</template>

<script>
import * as echarts from "echarts";
import { getDataSource } from './api/index.js'

export default {
    data() {
        return {
            curDisplayItem: "",
        }
    },
    props: {},
    methods: {
        renderChart(dataSource) {
            let chart = echarts.init(this.$refs['chart-content-1'])
            let emphasisStyle = {
                itemStyle: {
                    shadowBlur: 30,
                    shadowColor: 'rgba(0,0,0,0.9)',
                    shadowOffsetX: 10
                    // borderWidth:5,
                    // borderColor: '#ccc'
                }
            };

            let seriesData1 = [];
            let seriesData2 = [];
            let xAxis = [];
            let markPoint = [];

            dataSource.forEach((item, index) => {
                let { name, total, pay } = item;
                let color = "#31395d";
                if (pay > total) {
                    color = "#ff4d4f";
                    markPoint.push({
                        coord: [index, pay], // 使用坐标定义位置
                        name: '超出预算',
                        symbol: 'pin', // 标记符号类型
                        symbolSize: 20, // 标记符号大小
                        label: {
                            show: true,
                            position: 'right',
                            formatter: '超出预算'
                        },
                        itemStyle: {
                            color: color
                        },
                    })
                }
                if (pay > total * 0.8 && pay <= total) {
                    markPoint.push({
                        coord: [index, total], // 使用坐标定义位置
                        name: '预算不足20%',
                        symbol: 'pin', // 标记符号类型
                        symbolSize: 20, // 标记符号大小
                        label: {
                            show: true,
                            position: 'right',
                            formatter: '预算不足20%'
                        },
                        itemStyle: {
                            color: color
                        },
                    })
                }
                xAxis.push(name);
                seriesData1.push({
                    name: item.name,
                    value: item.total,
                    itemStyle: {
                        color: '#5470c6'
                    }
                });
                seriesData2.push({
                    name: item.name,
                    value: item.pay,
                    itemStyle: {
                        color: color
                    }
                });
            })

            let option = {
                legend: {},
                tooltip: {
                    trigger: 'axis',
                },
                xAxis: {
                    data: xAxis,
                    axisLine: { onZero: true },
                    splitLine: { show: false },
                    splitArea: { show: false }
                },
                yAxis: {
                    name: "金额（万元）"
                },
                grid: {
                    top: '20%',
                    bottom: "20%"
                },
                series: [
                    {
                        name: "整体金额",
                        type: 'bar',
                        emphasis: emphasisStyle,
                        data: seriesData1,
                        barGap: "-100%",
                        barWidth: "20%",
                        itemStyle: {
                            normal: {
                                color: '#5470c6',
                                borderRadius: 10
                            }
                        }
                    },
                    {
                        name: "支出金额",
                        type: 'bar',
                        emphasis: emphasisStyle,
                        data: seriesData2,
                        barWidth: "20%",
                        itemStyle: {
                            normal: {
                                color: '#31395d',
                                borderRadius: 10
                            }
                        },
                        markPoint: {
                            data: markPoint
                        }
                    }
                ]
            };
            chart.setOption(option);
            window.addEventListener('resize', function () {
                chart.resize();
            });
            return chart
        },
    },
    components: {

    },
    async mounted() {
        let dataSource = await getDataSource()
        this.renderChart(dataSource)
    }
}

</script>

<style scoped>

</style>