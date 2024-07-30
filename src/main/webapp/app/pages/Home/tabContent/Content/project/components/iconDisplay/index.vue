<template>
  <div style="padding: 10px; height: 100%">
    <span class="title">{{ title }}</span>
    <div class="item-wrapper">
      <div class="item-content" v-for="{ icon, title, color } in list">
        <el-icon>
          <component :color="color" :is="icon"></component>
        </el-icon>
        <span class="item-title" :style="{ color: color }">{{ title }}</span>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "RoleComponent",
  data() {
    return {};
  },
  props: {
    title: String,
    list: Array,
    color: String,
  },
  methods: {},
  components: {},
};
</script>

<style>
.title {
  color: var(--el-color-primary);
}

.item-wrapper {
  display: flex;
  height: 100%;

  .item-content {
    flex: 1;
    display: flex;
    flex-direction: column;
    text-align: center;
    align-items: center;
    margin: 25px 0px;
    cursor: pointer;

    .el-icon,
    svg {
      height: 35px !important;
      width: 35px !important;
    }
    &:hover {
      /* 鼠标悬浮变色 */
      filter: brightness(0.1);
      transition: all 0.3s ease;
    }
  }
}
</style>
