<template>
  <div id="chart-content">
    <div class="title">项目科目</div>
    <div ref="chart-content" style="flex: 1;">
      <div>加载中...</div>
    </div>
  </div>
</template>

<script>
import * as echarts from "echarts";
import { getDataSource } from './api/index.js'

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
      legend: {
        data: [
          {
            name: dataSource.series1.name,
            icon: 'rect',
          }, {
            name: dataSource.series2.name,
            icon: 'rect',
          }, {
            name: "支出占比"
          }
        ]
      },
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
      yAxis: [
        {
          name: "金额（千万）",
          splitLine: {
            show: false
          }
        },
        {
          name: "占比（%）",
          splitLine: {
            show: false
          }
        }
      ],
      grid: {
        bottom: 30
      },
      series: [
        {
          name: dataSource.series1.name,
          type: 'bar',
          emphasis: emphasisStyle,
          data: dataSource.series1.data,
          barGap: "-100%",
          barWidth: "60%",
          itemStyle: {
            normal: {
              color: '#dddddd',
            }
          }
        },
        {
          name: dataSource.series2.name,
          barMinHeight: 10,
          type: "pictorialBar",
          barCategoryGap: "60%",
          barWidth: "60%",
          // symbol: 'path://M0,10 L10,10 L5,0 L0,10 z',
          symbol: "path://M0,10 L10,10 C5.5,10 5.5,5 5,0 C4.5,5 4.5,10 0,10 z",
          itemStyle: {
            normal: {
              //barBorderRadius: 5,
              //渐变色
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                offset: 0,
                color: "#01EAED"
              },
              {
                offset: 0.5,
                color: "#02C4DD"
              },
              {
                offset: 1,
                color: "#029ED9"
              }
              ])
            }
          },
          label: {
            normal: {
              show: false,
              position: "top",
              textStyle: {
                // color: "#fff",
                fontSize: 20
              }
            }
          },
          data: dataSource.series2.data,
          z: 10
        },
        {
          name: "支出占比",
          type: 'line',
          data: dataSource.series2.data.map((item, index) => item / dataSource.series1.data[index] * 100),
          smooth: true,
          symbol: 'none',
          symbolSize: 0,
          yAxisIndex: 1,
          symbol: 'circle',
          itemStyle: {
            color: '#FF0000'
          },
          lineStyle: {
            color: '#ffcaca'
          },
          markLine: {
            // data: [{
            //     yAxis: 80
            // }]
            data: [
              [{ coord: [0, 80] }, { coord: [dataSource.xAxis.data.length - 1, 80] }]  // 自定义线段
            ]
          }
          // areaStyle: {
          // color: '#FF0000'
          // }
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
