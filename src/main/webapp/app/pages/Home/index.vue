<template>
    <div class="home-wrapper">
      <div class="top">
        <Top/>
      </div>
      <div class="bottom">
        <Bottom/>
      </div>
    </div>
  </template>
  
  <script>
    import Top from './top.vue'
    import Bottom from './bottom.vue'
    export default {
      name:"MyHome",
      components:{
        Top,Bottom
      }
    }
  </script>
  
  <style>
    .home-wrapper{
      height: 800px;
      /* background-color: black; */
      display: flex;
      flex-direction: column;
    }
    .top{
      flex: 1;
    }
    .bottom{
      flex: 6.5;
    }
  </style>
  