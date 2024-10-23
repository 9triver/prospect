<template>
  <div id="chart-content" ref="chart-content"></div>
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
    let xAxisData = [];
    let data1 = [];
    let data2 = [];
    let data3 = [];
    let data4 = [];
    for (let i = 0; i < 10; i++) {
      xAxisData.push('科目' + (i + 1));
      data1.push(+(Math.random() * 2).toFixed(2));
      data2.push(+(Math.random() * 5).toFixed(2));
      data3.push(+(Math.random() + 0.3).toFixed(2));
      data4.push(+Math.random().toFixed(2));
    }
    let emphasisStyle = {
      itemStyle: {
        shadowBlur: 10,
        shadowColor: 'rgba(0,0,0,0.3)'
      }
    };
    let option = {
      legend: {
        data: ['整体金额', '支出金额']
      },
      toolbox: {
        feature: {
          magicType: {
            type: ['stack']
          },
        }
      },
      tooltip: {},
      xAxis: {
        data: xAxisData,
        name: 'X Axis',
        axisLine: { onZero: true },
        splitLine: { show: false },
        splitArea: { show: false }
      },
      yAxis: {},
      grid: {
        bottom: 30
      },
      series: [
        {
          name: '整体金额',
          type: 'bar',
          stack: 'one',
          emphasis: emphasisStyle,
          data: data1
        },
        {
          name: '支出金额',
          type: 'bar',
          stack: 'one',
          emphasis: emphasisStyle,
          data: data2
        }
      ]
    };

    chart.setOption(option);
  }
}

</script>

<style>
#chart-content {
  height: 100%;
  width: 100%;
}
</style>
