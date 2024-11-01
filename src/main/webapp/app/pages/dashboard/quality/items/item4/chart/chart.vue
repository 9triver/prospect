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
                let { name, issue, toZero } = item;
                let color = "#31395d";

                if (toZero === issue) {
                    color = "green";
                    markPoint.push({
                        coord: [index, issue], // 使用坐标定义位置
                        name: '全部问题已归零',
                        symbol: 'pin', // 标记符号类型
                        symbolSize: 20, // 标记符号大小
                        label: {
                            show: true,
                            position: 'right',
                            formatter: '全部问题已归零'
                        },
                        itemStyle: {
                            color: color
                        },
                    })
                }
                xAxis.push(name);
                seriesData1.push({
                    name: item.name,
                    value: item.issue,
                    itemStyle: {
                        color: '#5470c6'
                    }
                });
                seriesData2.push({
                    name: item.name,
                    value: item.toZero,
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
                    name: "问题数量（个）"
                },
                grid: {
                    bottom: 30
                },
                series: [
                    {
                        name: "质量问题",
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
                        name: "质量归零",
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
            let { name, issue,toZero } = dataItem;
            // 添加一个标签，合同正常就用绿色，超预算就用红色
            let color = "#5470c6";
            if (issue === toZero) {
                color = "green";
            }
            let config = [
                {
                    name: "key1",
                    title: '实施方案评审问题',
                    // color: '#a092b0',
                },
                {
                    name: "key2",
                    title: '工艺评审问题',
                    // color: '#95b3d7',
                },
                {
                    name: "key3",
                    title: '首件鉴定评审问题',
                    // color: '#adbe8a',
                },
                {
                    name: "key4",
                    title: '出厂评审问题',
                    // color: '#df7f7f',
                }
            ]
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

            const generateData = () => {
                let data = [];
                config.forEach(item => {
                    const { name, title, color } = item
                    data.push({
                        value: dataItem[name],
                        name: title,
                        itemStyle: {
                            color: color
                        }
                    })
                })
                return data;
            }


            let option = {
                legend: {},
                tooltip: {
                    show: true,
                    // trigger:"item"
                },

                series: [
                    {
                        type: 'pie',
                        radius: '60%',
                        center: ['50%', '60%'],
                        avoidLabelOverlap: true,
                        label: {
                            show: false,
                            position: 'center'
                        },
                        itemStyle: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.7)'
                        },
                        emphasis: {
                            itemStyle: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.7)'
                            }
                        },
                        labelLine: {
                            show: false
                        },
                        data: generateData()
                    }
                ],
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

<style scoped></style>