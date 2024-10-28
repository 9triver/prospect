<template>
    <div ref="chart-content" style="flex: 1;height: 100%;"></div>
    <div class="total">
        <span>经费汇总:</span>
        <span>5千万</span>
    </div>
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
                right: '20',
                orient: 'vertical',
                top: 'middle',
            },
            series: [
                {
                    name: '项目经费',
                    type: 'pie',
                    radius: '70%',
                    center: ['40%', '50%'],
                    data: dataSource.map((item, index) => {
                        return {
                            ...item,
                            itemStyle: {
                                color: color[index]
                            }
                        }
                    }),
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
                    label: {
                        show: true,
                        position: 'inside',
                        formatter: '{d}%',
                        color: '#fff'
                    }
                }
            ]
        })
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