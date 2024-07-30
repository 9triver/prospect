<template>
  <span class="title" style="margin: 10px; display: block">本年度外协经费执行情况</span>
  <div id="chart-bar" style="height: 100%; width: 100%"></div>
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
      let myChart = echarts.init(document.getElementById("chart-bar"));
      let option = {
        tooltip: {
          trigger: "axis",
        },
        legend: {},
        xAxis: {
          data: Array(12)
            .fill()
            .map((item, index) => index + 1 + "月"),
        },
        yAxis: {},
        series: [
          {
            name: "计划数量",
            type: "bar",
            data: [11, 6, 3, 2, 0, 7, 12, 0, 0, 0, 0, 0],
            itemStyle: {
              color: "#5ab1ef",
            },
            label: {
              show: false,
              position: "right",
              color: "#5ab1ef",
            },
          },
          {
            name: "完成数量",
            type: "bar",
            data: [7, 5, 5, 2, 0, 3, 7, 0, 0, 0, 0, 0],
            itemStyle: {
              color: "#ff8c00",
            },
            label: {
              show: false,
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
