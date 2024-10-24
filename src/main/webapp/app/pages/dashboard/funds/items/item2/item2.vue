<template>
  <div id="chart-content">
    <div class="title">承研合同</div>
    <div ref="chart-content" style="flex: 1;">
      <div>加载中...</div>
    </div>
  </div>
</template>

<script>
import * as echarts from "echarts";
import {getDataSource} from './api/index.js'

export default {
  name: 'funds-basic',
  data() {
    return {
    }
  },
  props: {},
  methods: {},
  components: {

  },
  async mounted() {
    const chart = echarts.init(this.$refs['chart-content'])
    let dataSource = await getDataSource()
    let emphasisStyle = {
      itemStyle: {
        shadowBlur: 10,
        shadowColor: 'rgba(0,0,0,0.3)'
      }
    };
    let option = {
      legend: {},
      tooltip: {
        trigger: 'axis',
      },
      xAxis: {
        data: dataSource.xAxis.data,
        name: dataSource.xAxis.name,
        axisLine: { onZero: true },
        splitLine: { show: false },
        splitArea: { show: false }
      },
      yAxis: {
        name:"金额（万元）"
      },
      grid: {
        bottom: 30
      },
      series: [
        {
          name: dataSource.series1.name,
          type: 'bar',
          emphasis: emphasisStyle,
          data: dataSource.series1.data,
          barGap:"-100%",
          barWidth:"20%",
          itemStyle: {
            normal: {
              color: '#5470c6',
              borderRadius:10
            }
          }
        },
        {
          name: dataSource.series2.name,
          type: 'bar',
          emphasis: emphasisStyle,
          data: dataSource.series2.data,
          barWidth:"20%",
          itemStyle: {
            normal: {
              color: '#31395d',
              borderRadius:10
            }
          }
        }
      ]
    };
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
</style>
