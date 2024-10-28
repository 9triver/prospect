<template>
    <div ref="chart-content-1" style="flex: 7;">
        <div>加载中...</div>
    </div>
    <div ref="chart-content-2" style="flex: 3;">
        <div>加载中...</div>
    </div>
</template>

<script>
import * as echarts from "echarts";
import { getDataSource } from '../api/index.js'

export default {
    data() {
        return {
            curDisplayItem: "",
        }
    },
    props: {},
    methods: {
        renderChart1(dataSource) {
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
                    bottom: 30
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
        renderChart2(dataSource) {
            let data = dataSource[0];//初始化环形图的时候默认展示第一个数据
            let chart = echarts.init(this.$refs['chart-content-2'])
            this.resetChart2(data, chart)
            window.addEventListener('resize', function () {
                chart.resize();
            });
            return chart
        },
        // 点击柱状图的时候，环形图重新绘制
        resetChart2(dataItem, chartInstance) {
            let { name, pay, total } = dataItem;
            // 添加一个标签，合同正常就用绿色，超预算就用红色
            let color = "green"
            let config = [
                {
                    name: "cost",
                    title: '成本',
                    color: '#a092b0',
                },
                {
                    name: "pay",
                    title: '支出',
                    color: '#95b3d7',
                },
                {
                    name: "apply",
                    title: '实施',
                    color: '#adbe8a',
                },
                {
                    name: "total",
                    title: '预算',
                    color: '#df7f7f',
                }
            ]
            if (pay > total) {
                color = "red"
            }
            this.curDisplayItem = name

            const generateSeries = () => {
                let series = [];
                config.forEach(item => {
                    const { name, title, color } = item
                    series.push(
                        {
                            name: title,
                            type: 'bar',
                            data: [
                                {
                                    value: dataItem[name]
                                }
                            ],
                            itemStyle: {
                                color: color
                            },
                            coordinateSystem: 'polar'
                        })
                })
                return series;
            }

            let option = {
                legend: {},
                polar: {
                    radius: [10, '80%']
                },
                tooltip: {
                    show: true,
                    // trigger:"item"
                },
                angleAxis: {
                    max: 100,
                    startAngle: 90,
                    axisLine: {
                        show: false // 去掉极坐标的轴线
                    },
                    splitLine: {
                        show: false // 去掉分割线
                    },
                    axisLabel: {
                        show: false // 去掉角度轴的刻度标签
                    },
                    axisTick: {
                        show: false // 去掉角度轴的刻度线
                    }
                },
                radiusAxis: {
                    type: 'category',
                    data: ['a'],
                    axisLine: {
                        show: false // 去掉半径轴的轴线
                    },
                    splitLine: {
                        show: false // 去掉分割线
                    },
                    axisLabel: {
                        show: false // 去掉半径轴的刻度标签
                    },
                    axisTick: {
                        show: false // 去掉半径轴的刻度线
                    }
                },
                series: generateSeries(),
                graphic: [
                    {
                        type: 'group',
                        rotation: Math.PI / 4,
                        bounding: 'raw',
                        right: 40,
                        bottom: 40,
                        z: 100,
                        children: [
                            {
                                type: 'rect',
                                left: 'center',
                                top: 'center',
                                z: 100,
                                shape: {
                                    width: 400,
                                    height: 50
                                },
                                style: {
                                    fill: color
                                }
                            },
                            {
                                type: 'text',
                                left: 'center',
                                top: 'center',
                                z: 100,
                                style: {
                                    fill: '#fff',
                                    fontSize: 16,
                                    text: this.curDisplayItem,
                                    font: 'bold 26px sans-serif'
                                }

                            }
                        ]
                    }
                ]
            };

            chartInstance.setOption(option);
        }
    },
    components: {

    },
    async mounted() {
        let dataSource = await getDataSource()
        let chart1 = this.renderChart1(dataSource)
        let chart2 = this.renderChart2(dataSource)
        chart1.on('click', (params) => {
            let { componentType, dataIndex } = params;
            if (componentType == 'series') {
                let dataItem = dataSource[dataIndex]
                let { name } = dataItem
                // 避免过多渲染
                if (name != this.curDisplayItem) {
                    this.resetChart2(dataItem, chart2)
                }
            }
        })
    }
}

</script>

<style scoped>

</style>