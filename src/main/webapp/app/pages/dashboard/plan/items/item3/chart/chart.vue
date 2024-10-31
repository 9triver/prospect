<template>
  <div ref="chart-content" style="flex: 1;height: 100%;">
    <div>加载中...</div>
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
  methods: {
    // 生成渐变色
    generateGradientColor(color, reverse) {
      return new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
        offset: reverse ? 1 : 0,
        color: color
      },
      {
        offset: reverse ? 0 : 1,
        color: color + "50"
      }
      ])
    }
  },
  components: {},

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
      tooltip: {
        trigger: 'axis'
      },
      backgroundColor: '#fff',
      animation: false,
      grid: {
        top: "20%",
        bottom: "15%",
        left: "0%",
        right: "0%",
        containLabel: true
      },
      legend: {
        show: true,
        data: [
          {
            name:"进度"
          },
          // {
          //   name:"实际进度"
          // },
          // {
          //   name:"进度偏差",
          //   itemStyle: {
          //     color: '#74da81'
          //   }
          // }
        ],
      },
      xAxis: {
        data: ['任务包1', '任务包2', '任务包3', '任务包4', '任务包5', '任务包6', '任务包7', '任务包8', '任务包9', '任务包10'],
        axisLine: {
          show: true, //隐藏X轴轴线
          lineStyle: {
            color: '#ccc'
          }
        },
        axisTick: {
          show: false //隐藏X轴刻度
        },
        axisLabel: {
          show: true,
          margin: 14,
          fontSize: 14,
          textStyle: {
            color: "#A3C0DF" //X轴文字颜色
          }
        },

      },
      yAxis: [
        {
          type: "value",
          gridIndex: 0,
          // min: -100,
          max: 100,
          interval: 25,
          // splitNumber: 4,
          splitLine: {
            show: true,
            lineStyle: {
              color: '#ccc',
              width: 1,
              type:'dashed'
            },
          },
          axisTick: {
            show: false
          },
          axisLine: {
            show: false,
            lineStyle: {
              color: '#11417a'
            }
          },
          axisLabel: {
            show: true,
            margin: 14,
            fontSize: 14,
            textStyle: {
              color: "#A3C0DF" //X轴文字颜色
            }
          },
        },
      ],
      series: [
        {
          name: "进度",
          type: "bar",
          barWidth: 10,
          itemStyle: {
            normal: {
              color: this.generateGradientColor("#409eff"),
              barBorderRadius: [30, 30, 0, 0]
            }
          },
          data: [20, 80, 100, 40, 34, 90, 60, 60,100, 80],
          z: 10,
          zlevel: 0,
          "label": {
            "show": false,
            "position": "top",
            "distance": 10,
            fontSize: 16,
            "color": "#01fff4"
          }
        },
        // {
        //   // 分隔
        //   type: "pictorialBar",
        //   itemStyle: {
        //     normal: {
        //       color: "#fff"
        //     }
        //   },
        //   symbolRepeat: "fixed",
        //   symbolMargin: 6,
        //   symbol: "rect",
        //   symbolClip: true,
        //   symbolSize: [20, 1],
        //   symbolPosition: "start",
        //   symbolOffset: [-24, 0],
        //   // symbolBoundingData: this.total,
        //   data: [20, 80, 100, 40, 34, 90, 60, 60],
        //   width: 25,
        //   z: 0,
        //   tooltip: {
        //     show: false
        //   },
        //   zlevel: 1,
        // },
        // {
        //   name: "实际进度",
        //   type: "bar",
        //   barWidth: 10,
        //   itemStyle: {
        //     normal: {
        //       color: this.generateGradientColor("#31395d"),
        //       barBorderRadius: [30, 30, 0, 0]
        //     }
        //   },
        //   data: [20, 80, 100, 40, 34, 90, 60, 60, 20, 80],
        //   z: 10,
        //   zlevel: 0,
        //   "label": {
        //     "show": false,
        //     "position": "top",
        //     "distance": 10,
        //     fontSize: 16,
        //     "color": "#01fff4"
        //   }
        // },
        // {
        //   // 分隔
        //   type: "pictorialBar",
        //   itemStyle: {
        //     normal: {
        //       color: "#fff"
        //     }
        //   },
        //   symbolRepeat: "fixed",
        //   symbolMargin: 6,
        //   symbol: "rect",
        //   symbolClip: true,
        //   symbolSize: [20, 1],
        //   symbolPosition: "start",
        //   symbolOffset: [0, -1],
        //   // symbolBoundingData: this.total,
        //   data: [20, 80, 100, 40, 34, 90, 60, 60],
        //   width: 25,
        //   z: 0,
        //   tooltip: {
        //     show: false
        //   },
        //   zlevel: 1,
        // },
        // {
        //   name: "进度偏差",
        //   type: "bar",
        //   barWidth: 10,
        //   data: [1, 2, 10, -4, -10, 10, 20, 60, -80, 80].map(item => {
        //     return {
        //       value: item,
        //       itemStyle: {
        //         color: item >= 0 ? this.generateGradientColor("#74DA81") : this.generateGradientColor("#FF0000", true),
        //         barBorderRadius: item >= 0 ? [30, 30, 0, 0] : [0, 0, 30, 30]
        //       }
        //     }
        //   }),
        //   itemStyle: {
        //     color: (params) => {
        //       return params.data >= 0 ? this.generateGradientColor("#74DA81") : this.generateGradientColor("#FF0000", true);
        //     },
        //     barBorderRadius: [30, 30, 0, 0]
        //   }
        // },
        // {
        //   // 分隔
        //   type: "pictorialBar",
        //   itemStyle: {
        //     normal: {
        //       color: "#fff"
        //     }
        //   },
        //   symbolRepeat: "fixed",
        //   symbolMargin: 6,
        //   symbol: "rect",
        //   symbolClip: true,
        //   symbolSize: [20, 1],
        //   symbolPosition: "start",
        //   symbolOffset: [24, -1],
        //   // symbolBoundingData: this.total,
        //   data: [1, 2, 10, -4, -10, 10, 20, 60],
        //   width: 25,
        //   z: 0,
        //   tooltip: {
        //     show: false
        //   },
        //   zlevel: 1,
        // },

      ],
      "dataZoom": [{
        "show": true,
        "height": 30,
        "xAxisIndex": [
          0
        ],
        bottom: 0,
        "start": 10,
        "end": 80
      }],
    };
    chart.setOption(option);
    window.addEventListener('resize', function () {
      chart.resize();
    });
  }
}
</script>
<style></style>