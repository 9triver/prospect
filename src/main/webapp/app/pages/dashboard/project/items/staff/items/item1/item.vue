<template>
    <div style="flex: 2;height: 100%;" ref="chart-content">
    </div>
</template>
<script>
import * as echarts from 'echarts'

// 调用接口查询数据
import { getDataSource } from './api/index.js'
import { nextTick } from 'process';
export default {
    name: 'myComponent',
    data() {
        return {
            dataSource: []
        }
    },
    props: {},
    methods: {
        async renderChart() {
            let dataSource = await getDataSource()
            let option = {
                tooltip: {
                    trigger: 'item',
                    axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                        type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                grid: {
                    top: '0%',
                    bottom: "20%"
                },
                xAxis: {
                    type: 'value',
                    name: '工时',
                    splitLine: {
                        show: false
                    },
                    axisLine: {
                        show:true,
                        lineStyle: {
                            color: '#ccc'
                        }
                    },
                    axisTick: {
                        show: false
                    }
                },
                yAxis: [
                    {
                        type: 'category',
                        data: dataSource.map(item => item.name),
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
                        data: dataSource.map(item => item.value)
                    }
                ]
            }
            this.chart.setOption(option)
            window.addEventListener('resize', () => {
                this.chart.resize();
            });
        },
    },
    components: {},
    async mounted() {
        nextTick(() => {
            this.chart = echarts.init(this.$refs['chart-content'])
            this.renderChart()
        })
    }

}
</script>
<style></style>