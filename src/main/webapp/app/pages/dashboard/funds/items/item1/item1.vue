<template>
  <div id="chart-content">
    <div class="title">项目经费</div>
    <div ref="chart-content" style="flex: 1;"></div>
    <div class="total">
      <span>经费汇总:</span>
      <span>5千万</span>
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
          data: [
            {
              value: 2000,
              name: '已支付',
              itemStyle: {
                color: "#02a1d9"
              }
            },
            {
              value: 3000,
              name: '未支付',
              itemStyle: {
                color: "rgb(84, 112, 198)"
              }
            }
          ],
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
#chart-content {
  height: 100%;
  width: 100%;
  display: flex;
  flex-direction: column;
  position: relative;
}

#chart-content .title {
  font-size: 20px;
  font-weight: bold;
}

#chart-content .total {
  position: absolute;
  right: 0;
  bottom: 40px;
}
</style>
