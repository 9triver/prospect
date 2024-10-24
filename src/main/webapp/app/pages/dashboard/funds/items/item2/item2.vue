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
    const generateDataAndMarkPoint = () => {
      let data = []
      let markPoint = []
      dataSource.series2.data.forEach((item, index) => {
        let total = dataSource.series1.data[index]
        let color = "#31395d"
        if (item > total) {
          color = "#ff4d4f"
          markPoint.push({
            coord: [index, item], // 使用坐标定义位置
            name: '超出预算',
            symbol: 'pin', // 标记符号类型
            symbolSize: 20, // 标记符号大小
            label: {
              show: true,
              position: 'right',
              formatter: '超出预算'
            },
            itemStyle: {
              color: color
            },
          })
        }
        if (item > total*0.75&& item <= total) {
          markPoint.push({
            coord: [index, total], // 使用坐标定义位置
            name: '预算不足25%',
            symbol: 'pin', // 标记符号类型
            symbolSize: 20, // 标记符号大小
            label: {
              show: true,
              position: 'right',
              formatter: '预算不足25%'
            },
            itemStyle: {
              color: color
            },
          })
        }
        data.push({
          value: item,
          itemStyle: {
            color: color
          }
        })
      })
      return {
        data,
        markPoint
      }
    }
    let { data, markPoint } = generateDataAndMarkPoint()

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
        name: "金额（万元）"
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
          name: dataSource.series2.name,
          type: 'bar',
          emphasis: emphasisStyle,
          data: data,
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
