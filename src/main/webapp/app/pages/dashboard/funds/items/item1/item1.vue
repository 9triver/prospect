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
    chart.setOption({
      tooltip: {
        trigger: 'item'
      },
      legend: {
      },
      series: [
        {
          name: 'Access From',
          type: 'pie',
          radius: '50%',
          data: [
            { value: 1048, name: '总计' },
            { value: 735, name: '已花费' }
          ],
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }
      ]
    })
  }
}

</script>

<style>
#chart-content {
  height: 100%;
  width: 100%;
}
</style>
