<template>
  <div class="dialog-overlay" v-if="visible" @click="handleOverlayClick">
    <div class="dialog" :style="`width: ${width}px`" @click.stop>
      <div class="dialog-header">
        <h3>{{ title }}</h3>
        <button class="close-button" @click="close">×</button>
      </div>
      <div class="dialog-body">
        <slot></slot>
      </div>
      <div class="dialog-footer">
        <el-button @click="close">关闭</el-button>
        <el-button @click="confirm">确认</el-button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    title: {
      type: String,
      default: 'Dialog Title'
    },
    value: {
      type: Boolean,
      default: false
    },
    width: {
      type: Number,
      default: 800
    }
  },
  data() {
    return {
      visible: this.value
    };
  },
  watch: {
    value(val) {
      this.visible = val;
    },
    visible(val) {
      this.$emit('input', val);
    }
  },
  methods: {
    close() {
      this.visible = false;
    },
    confirm() {
      this.$emit('confirm');
      this.close();
    },
    handleOverlayClick() {
      this.close(); // 点击遮罩层时关闭弹窗
    }
  }
};
</script>
  
  <style scoped>
  .dialog-overlay {
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background: rgba(0, 0, 0, 0.5);
    display: flex;
    align-items: center;
    justify-content: center;
    z-index: 9999;
  }
  .dialog {
    background: white;
    border-radius: 5px;
    width: 800px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  }
  .dialog-header {
    display: flex;
    justify-content: space-between;
    padding: 10px;
    border-bottom: 1px solid #ccc;
  }
  .dialog-body {
    padding: 20px;
  }
  .dialog-footer {
    display: flex;
    justify-content: flex-end;
    padding: 10px;
    border-top: 1px solid #ccc;
  }
  .close-button {
    border: none;
    background: none;
    cursor: pointer;
    font-size: 24px;
  }
  </style>
  