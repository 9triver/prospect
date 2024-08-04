<template>
  {{openMenus}}
  <router-view v-slot="{ Component }">
    <keep-alive :include="openMenus">
      <component :is="Component" :key="$route.fullPath"/>
    </keep-alive>
  </router-view>
</template>

<script lang="ts" src="./entities.component.ts"></script>
