<template>
    <div ref="chart-content" style="flex: 1;height: 100%;">
    </div>
</template>
<script>
import * as echarts from "echarts";
import {getData} from './api/index.js'

export default {
    name: 'myComponent',
    data() {
        return {}
    },
    props: {},
    methods: {},
    components: {},
    async mounted() {
        const data = await getData()
        console.log("item6",data)
        const chart = echarts.init(this.$refs['chart-content'])
        const CubeLeft = echarts.graphic.extendShape({
            shape: {
                x: 0,
                y: 0
            },
            buildPath: function (ctx, shape) {
                const xAxisPoint = shape.xAxisPoint
                const c0 = [shape.x, shape.y]
                const c1 = [shape.x - 9, shape.y - 9]
                const c2 = [xAxisPoint[0] - 9, xAxisPoint[1] - 9]
                const c3 = [xAxisPoint[0], xAxisPoint[1]]
                ctx.moveTo(c0[0], c0[1]).lineTo(c1[0], c1[1]).lineTo(c2[0], c2[1]).lineTo(c3[0], c3[1]).closePath()
            }
        })
        const CubeRight = echarts.graphic.extendShape({
            shape: {
                x: 0,
                y: 0
            },
            buildPath: function (ctx, shape) {
                const xAxisPoint = shape.xAxisPoint
                const c1 = [shape.x, shape.y]
                const c2 = [xAxisPoint[0], xAxisPoint[1]]
                const c3 = [xAxisPoint[0] + 18, xAxisPoint[1] - 9]
                const c4 = [shape.x + 18, shape.y - 9]
                ctx.moveTo(c1[0], c1[1]).lineTo(c2[0], c2[1]).lineTo(c3[0], c3[1]).lineTo(c4[0], c4[1]).closePath()
            }
        })
        const CubeTop = echarts.graphic.extendShape({
            shape: {
                x: 0,
                y: 0
            },
            buildPath: function (ctx, shape) {
                const c1 = [shape.x, shape.y]
                const c2 = [shape.x + 18, shape.y - 9]
                const c3 = [shape.x + 9, shape.y - 18]
                const c4 = [shape.x - 9, shape.y - 9]
                ctx.moveTo(c1[0], c1[1]).lineTo(c2[0], c2[1]).lineTo(c3[0], c3[1]).lineTo(c4[0], c4[1]).closePath()
            }
        })
        echarts.graphic.registerShape('CubeLeft', CubeLeft)
        echarts.graphic.registerShape('CubeRight', CubeRight)
        echarts.graphic.registerShape('CubeTop', CubeTop)
        const VALUE = [2012, 1230, 3790, 2349, 1654, 1230, 3790]
        let option = {
            backgroundColor: "#FFF",
            legend: {
                show: true
            },
            title: {
                text: '',
                top: 32,
                left: 18,
                textStyle: {
                    color: '#000',
                    fontSize: 24
                }
            },
            grid: {
                left: 20,
                right: 40,
                bottom: '10%',
                top: "15%",
                containLabel: true
            },
            tooltip: {},
            xAxis: {
                type: 'category',
                data: data.map(item => item.title),
                axisLine: {
                    show: true,
                    lineStyle: {
                        color: '#000'
                    }
                },
                axisTick: {
                    show: false,
                    length: 9,
                    alignWithLabel: true,
                    lineStyle: {
                        color: '#000'
                    }
                },
                axisLabel: {
                    fontSize: 10
                }
            },
            yAxis: {
                name: '金额（千万）',
                type: 'value',
                axisLine: {
                    show: false,
                    lineStyle: {
                        color: '#000'
                    }
                },
                axisTick: {
                    show: false
                },
                axisLabel: {
                    fontSize: 16
                },
                boundaryGap: ['20%', '20%']
            },
            series: [{
                type: 'custom',
                renderItem: (params, api) => {
                    const location = api.coord([api.value(0), api.value(1)])
                    return {
                        type: 'group',
                        children: [{
                            type: 'CubeLeft',
                            shape: {
                                api,
                                xValue: api.value(0),
                                yValue: api.value(1),
                                x: location[0],
                                y: location[1],
                                xAxisPoint: api.coord([api.value(0), 0])
                            },
                            style: {
                                fill: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                    offset: 0,
                                    color: '#3B80E2'
                                },
                                {
                                    offset: 1,
                                    color: '#000'
                                }
                                ])
                            }
                        }, {
                            type: 'CubeRight',
                            shape: {
                                api,
                                xValue: api.value(0),
                                yValue: api.value(1),
                                x: location[0],
                                y: location[1],
                                xAxisPoint: api.coord([api.value(0), 0])
                            },
                            style: {
                                fill: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                    offset: 0,
                                    color: '#3B80E2'
                                },
                                {
                                    offset: 1,
                                    color: '#49BEE5'
                                }
                                ])
                            }
                        }, {
                            type: 'CubeTop',
                            shape: {
                                api,
                                xValue: api.value(0),
                                yValue: api.value(1),
                                x: location[0],
                                y: location[1],
                                xAxisPoint: api.coord([api.value(0), 0])
                            },
                            style: {
                                fill: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                    offset: 0,
                                    color: '#3B80E2'
                                },
                                {
                                    offset: 1,
                                    color: '#49BEE5'
                                }
                                ])
                            }
                        }]
                    }
                },
                data: data.map(item=>item.value)
            }]
        }
        chart.setOption(option);
    }
}
</script>
<style></style>