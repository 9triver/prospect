<template>
    <div ref="chart-content" style="flex: 1;height: 100%;"></div>
</template>
<script>
import * as echarts from "echarts";
import { getDataSource } from './api/index.js'
export default {
    data() {
        return {}
    },
    props: {},
    methods: {},
    components: {},
    async mounted() {
        let dataSource = await getDataSource()
        let color = ["#02a1d9", "rgb(84, 112, 198)"]
        const chart = echarts.init(this.$refs['chart-content'])
        chart.setOption({
            tooltip: {
                trigger: 'item'
            },
            legend: {
            },
            grid: {
                top: '20%',
                bottom: "20%"
            },
            xAxis: {
                data: dataSource.map(item => item.name),
                axisLine: { onZero: true },
                splitLine: { show: false },
                splitArea: { show: false }
            },
            yAxis: {
                name: "问题数量（个）"
            },
            series: [
                {
                    name: '质量目标',
                    type: 'bar',
                    barWidth: '20%',
                    data: dataSource.map((item, index) => {
                        return {
                            ...item,
                            itemStyle: {
                                // color: color[index]
                            }
                        }
                    }),
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.7)',
                        borderRadius: 5
                    },
                    emphasis: {
                        itemStyle: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.7)'
                        }
                    },
                }
            ]
        })
        window.addEventListener('resize', function () {
            chart.resize();
        });
    }
}
</script>
<style scoped>
.total {
    position: absolute;
    right: 0;
    bottom: 40px;
}
</style>