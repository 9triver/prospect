<template>
    <div id="chart-content">
        <div class="title">外协合同</div>
        <div class="main-content">
            <div class="left-wrapper">
                <div class="left-wrapper-title">
                    <span>应签合同</span>
                </div>
                <div class="left-wrapper-value">
                    <span>20</span>
                    <span class="unit">（个）</span>
                </div>
                <div class="left-wrapper-title">
                    <span>已签合同</span>
                </div>
                <div class="left-wrapper-value">
                    <span>10</span>
                    <span class="unit">（个）</span>
                </div>
                <div class="left-wrapper-title">
                    <span>已支付合同</span>
                </div>
                <div class="left-wrapper-value">
                    <span>8</span>
                    <span class="unit">（个）</span>
                </div>
            </div>
            <div ref="chart-content" style="flex: 1;height: 100%;">
            </div>
        </div>
    </div>
</template>

<script>
import * as echarts from "echarts";

export default {
    name: 'funds-basic',
    data() {
        return {}
    },
    props: {},
    methods: {},
    components: {

    },
    mounted() {
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
                data: ['合同1', '合同2', '合同3', '合同4', '合同5', '合同6', '合同7'],
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
                name:'金额（千万）',
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
                data: VALUE
            }]
        }
        chart.setOption(option);
    }
}

</script>

<style scoped>
#chart-content {
    height: 100%;
    width: 100%;
    display: flex;
    flex-direction: column;
}

#chart-content .title {
  font-size: 20px;
  font-weight: bold;
}

#chart-content .main-content{
    flex: 1;
    display: flex;
}

#chart-content .left-wrapper {
    margin-left: 30px;
}

#chart-content .left-wrapper .left-wrapper-value {
    text-align: right;
    font-size: 30px;
    font-weight: bold;
    margin-left: 70px;
}

#chart-content .left-wrapper .left-wrapper-value .unit {
    font-size: 14px;
}
</style>