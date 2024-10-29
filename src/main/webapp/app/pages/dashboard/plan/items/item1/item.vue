<template>
    <div style="display: flex;">
        <Card v-for="({ title, value, notStart, isEnd, overDue, icon }, index) in dataSource" :title="title"
            :value="value" :index="index" @showInfo="showInfo" :curShow="curShow">
            <component :is="icon" />
        </Card>
        <!-- <Card title="1计划总数1" value="120" notStart="10" isEnd="20" overDue="30" >
            <PlanTotal />
        </Card>
        <Card title="总体级WBS" value="120" notStart="10" isEnd="20" overDue="30" >
            <WBS/>
        </Card>
        <Card title="任务包" value="120" notStart="10" isEnd="20" overDue="30" >
            <WorkBag/>
        </Card> -->
        <div style="flex: 2;" ref="chart-content">
        </div>
    </div>
</template>
<script>
import Card from './Card/card.vue'
import * as echarts from 'echarts'
import PlanTotal from './assets/planTotal.vue' //计划总数图标
import WBS from './assets/WBS.vue';//总体级WBS图标
import WorkBag from './assets/workBag.vue';//任务包图标

// 调用接口查询数据
import { getDataSource } from './api/index.js'
import { nextTick } from 'process';
export default {
    name: 'myComponent',
    data() {
        return {
            curShow: 0,
            dataSource: []
        }
    },
    props: {},
    methods: {
        showInfo(index) {
            if(this.curShow != index) {
                this.curShow = index;
                this.renderChart()
            }
        },
        renderChart() {
            let dataItem = this.dataSource[this.curShow]
            let { notStart=0, process=0, isEnd=0, overDue=0 } = dataItem
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
                        data: [overDue,isEnd,process,notStart]
                    }
                ]
            }
            this.chart.setOption(option)
            window.addEventListener('resize',  ()=> {
                this.chart.resize();
            });
        }
    },
    components: { Card, PlanTotal, WBS, WorkBag },
    async mounted() {
        let dataSource = await getDataSource()
        this.dataSource = dataSource
        nextTick(() => {
            this.chart = echarts.init(this.$refs['chart-content'])
            this.renderChart()
        })
        // 设定一个定时器，每10s切换一次选中的卡片
        window.setInterval(() => {
            let nextIndex = (this.curShow+1)%dataSource.length
            this.showInfo(nextIndex)
            console.log(nextIndex)
        },5000)
    }

}
</script>
<style></style>