<template>
  <div id="chart-content">
    <div class="title">费用支付详情</div>
    <Chart/>
  </div>
</template>

<script>

import Chart from './chart/chart.vue';

export default {
  name: 'funds-basic-item5',
  data() {
    return {}
  },
  props: {},
  methods: {},
  components: {
    Chart
  },

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
