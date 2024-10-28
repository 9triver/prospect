<template>
  <div id="chart-content">
    <div class="title">项目经费</div>
    <Chart/>
  </div>
</template>

<script>

import Chart from './chart/chart.vue'

export default {
  name: 'funds-basic-item-1',
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
  position: relative;
}

#chart-content .title {
  font-size: 20px;
  font-weight: bold;
}

</style>
