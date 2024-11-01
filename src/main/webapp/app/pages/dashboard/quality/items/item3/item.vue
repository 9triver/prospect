<template>
  <div id="chart-content">
    <div class="title">质量目标</div>
    <Chart />
    <div class="more">
      <span @click="showDialog">更多</span>
    </div>
    <Dialog v-model:value="dialogVisible" title="质量目标" @confirm="handleConfirm" @input="val => dialogVisible = val">
      <div style="height: 500px;width: 100%;position: relative;">
        <Chart />
      </div>
    </Dialog>
  </div>
</template>

<script>

import Chart from './chart/chart.vue'
import Dialog from '../../components/dialog/my-dialog.vue'

export default {
  name: 'funds-basic-item-1',
  data: () => {
    return {
      dialogVisible: false
    }
  },
  components: {
    Chart, Dialog
  },
  methods: {
    showDialog() {
      this.dialogVisible = !this.dialogVisible;
    }
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

.more {
  position: absolute;
  right: 0px;
  /* top: 16px; */
  color: #5470c6;
  cursor: pointer;
}

.more:hover {
  color: #3870e0;
  font-weight: bold;
}
</style>
