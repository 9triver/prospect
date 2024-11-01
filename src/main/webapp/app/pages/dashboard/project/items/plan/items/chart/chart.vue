<template>
    <div ref="chart-content" style="flex: 1;height: 100%;"></div>
</template>
<script>
import * as echarts from "echarts";
import { getDataSource } from './api/index.js'
export default {
    name: 'myComponent',
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
                align: 'right',
                right: '0',
                orient: 'vertical',
                top: 'middle',
            },
            series: [
                {
                    name: '质量目标',
                    type: 'pie',
                    roseType: 'area',
                    radius: ['20%', '80%'],
                    center: ['40%', '50%'],
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
                    label: {
                        show: true,
                        position: 'outside',
                        formatter: '{d}%',
                        color: '#000'
                    }
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