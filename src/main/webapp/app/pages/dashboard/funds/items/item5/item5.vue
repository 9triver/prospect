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
    let option = {
      xAxis: {
        type: 'category',
        data: ['2024-04', '2024-05', '2024-06', '2024-07', '2024-08', '2024-09', '2024-10']
      },
      yAxis: {
        type: 'value'
      },
      legend: {},
      series: [
        {
          name: "合同支付",
          data: [Math.floor(Math.random() * 200) + 50, Math.floor(Math.random() * 200) + 50, Math.floor(Math.random() * 200) + 50, Math.floor(Math.random() * 200) + 50, Math.floor(Math.random() * 200) + 50, Math.floor(Math.random() * 200) + 50, Math.floor(Math.random() * 200) + 50],
          type: 'bar'
        },
        {
          name: "零星采购",
          data: [Math.floor(Math.random() * 200) + 50, Math.floor(Math.random() * 200) + 50, Math.floor(Math.random() * 200) + 50, Math.floor(Math.random() * 200) + 50, Math.floor(Math.random() * 200) + 50, Math.floor(Math.random() * 200) + 50, Math.floor(Math.random() * 200) + 50],
          type: 'bar'
        },
        {
          name: "分摊费用",
          data: [Math.floor(Math.random() * 200) + 50, Math.floor(Math.random() * 200) + 50, Math.floor(Math.random() * 200) + 50, Math.floor(Math.random() * 200) + 50, Math.floor(Math.random() * 200) + 50, Math.floor(Math.random() * 200) + 50, Math.floor(Math.random() * 200) + 50],
          type: 'bar'
        },
        {
          name: "事务费",
          data: [Math.floor(Math.random() * 200) + 50, Math.floor(Math.random() * 200) + 50, Math.floor(Math.random() * 200) + 50, Math.floor(Math.random() * 200) + 50, Math.floor(Math.random() * 200) + 50, Math.floor(Math.random() * 200) + 50, Math.floor(Math.random() * 200) + 50],
          type: 'bar'
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
