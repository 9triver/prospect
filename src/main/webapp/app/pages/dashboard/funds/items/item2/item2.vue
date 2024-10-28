<template>
  <div id="chart-content">
    <div class="title">承研合同</div>
    <div class="chart-content-wrapper">
      <Chart/>
    </div>
    <div class="more">
      <span @click="showDialog">更多</span>
    </div>
    <Dialog v-model:value="dialogVisible" title="承研合同" width="1600" @confirm="handleConfirm" @input="val => dialogVisible = val">
      <div style="height: 700px;width: 100%;position: relative;display: flex;">
        <DialogContent />
      </div>
    </Dialog>
  </div>
</template>

<script>

import Chart from "./chart/chart.vue";
import Dialog from '../../components/dialog/my-dialog.vue'
import DialogContent from './dialog/chart.vue'
export default {
  name: 'funds-basic-item2',
  data() {
    return {
      curDisplayItem: "",
      dialogVisible: false
    }
  },
  props: {},
  methods: {
    showDialog() {
      this.dialogVisible = !this.dialogVisible;
    }
  },
  components: {
    Chart,Dialog,DialogContent
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

.chart-content-wrapper {
  flex: 1;
  display: flex;
}

.more {
  position: absolute;
  right: 20px;
  top: 16px;
  color: #5470c6;
  cursor: pointer;
}

.more:hover {
  color: #3870e0;
  font-weight: bold;
}
</style>
