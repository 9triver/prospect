<template>
    <div class="common-menu-wrapper">
        <EntitiesMenu/>
    </div>
  </template>
  
  <script setup lang='ts'>
  import { ref, reactive} from 'vue'
  import EntitiesMenu from '@/entities/entities-menu.vue';

  </script>
  <style lang='scss' scoped>
    .common-menu-wrapper{
        :deep(li){
            list-style: none;
            padding: 5px 0px;
            background: black;
            border-bottom: 1px solid #565353;
            span,svg{
                color: #fff;
            }
            a:hover,:focus{
                span,svg{
                    color: #000;
                }
            }
        }
    }
  </style>