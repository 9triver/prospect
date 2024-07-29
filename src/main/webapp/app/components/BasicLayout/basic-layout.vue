<template>
  <el-container class="layout-container-cvicse" style="height: 100vh">
    <el-aside width="200px">
      <div class="logo-wrapper">项目管理系统</div>
      <el-scrollbar>
        <commonMenu/>
      </el-scrollbar>
    </el-aside>

    <el-container>
      <el-header style="text-align: right; font-size: 12px">
        <commonHeader/>
      </el-header>
      <el-main class="main-content-wrapper">
        <el-scrollbar>
          <commonTab/>
        </el-scrollbar>
      </el-main>
    </el-container>
  </el-container>
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import { Menu as IconMenu, Message, Setting } from '@element-plus/icons-vue'
import commonMenu from './common/common-menu/common-menu.vue';
import commonHeader from './common/common-header/common-header.vue';
import commonTab from './common/common-tab/common-tab.vue';

const item = {
  date: '2016-05-02',
  name: 'Tom',
  address: 'No. 189, Grove St, Los Angeles',
}
const tableData = ref(Array.from({ length: 20 }).fill(item))
</script>

<style lang="scss" scoped>
.layout-container-cvicse .el-header {
  position: relative;
  background-color: #fff;
  color: var(--el-text-color-primary);
  box-shadow: 7px 0px 10px 0px #ccc;
}
.layout-container-cvicse .el-aside {
  color: var(--el-text-color-primary);
  background: #000;
  padding-top: 60px;
}
.layout-container-cvicse .el-menu {
  border-right: none;
}
.layout-container-cvicse .el-main {
  padding: 0;
}
.layout-container-cvicse .toolbar {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  height: 100%;
  right: 20px;
}
.layout-container-cvicse{
  .logo-wrapper{
    height: 60px;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 30px;
    background: #fff;
    position: absolute;
    top: 0;
    color: #000;
    width: 200px;
    border-bottom: 1px solid #565353;
  }
  .main-content-wrapper{
    padding: 20px 30px;
    margin: 10px;
    border-radius: 5px;
    background-color: #fff;
  }
}
</style>
