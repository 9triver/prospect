<template>
  <span class="title" style="margin: 10px; display: block">本年度外协经费执行情况</span>
  <div id="chart-horizon-bar" style="height: 100%; width: 100%"></div>
</template>
<script>
import * as echarts from "echarts";
export default {
  name: "myComponent",
  data() {
    return {};
  },
  props: {},
  methods: {},
  components: {},
  mounted() {
    setTimeout(() => {
      let myChart = echarts.init(document.getElementById("chart-horizon-bar"));
      let option = {
        tooltip: {},
        legend: {},
        xAxis: {
          show: false,
        },
        yAxis: {
          data: [""],
          show: false,
        },
        series: [
          {
            name: "已审批",
            type: "bar",
            barWidth: "10%",
            data: [7290],
            itemStyle: {
              color: "#5ab1ef",
            },
            label: {
              show: true,
              position: "right",
              color: "#5ab1ef",
            },
          },
          {
            name: "已实施",
            type: "bar",
            barWidth: "10%",
            data: [2072],
            itemStyle: {
              color: "#ff8c00",
            },
            label: {
              show: true,
              position: "right",
              color: "#ff8c00",
            },
          },
        ],
      };
      myChart.setOption(option);
    }, 2000);
  },
};
</script>
<style></style>
