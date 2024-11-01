<template>
    <div class="item-plan-wrapper">
        <div class="title">计划</div>
        <div class="item-wrapper">
            <chart/>
        </div>
        <div class="item-wrapper">
            <gantt/>
        </div>
    </div>
</template>
<script>
    import chart from './items/chart/chart.vue';
    import gantt from './items/gantt/gantt.vue';
    export default{
       data(){
           return{}
       },
       props:{},
       methods:{},
       components:{chart,gantt},
}
</script>
<style scoped>
.item-plan-wrapper{
    display: flex;
    flex-direction: column;
    height: 100%;
    box-shadow: 1px 1px 10px 1px #ccc;
    padding: 10px;
    border-radius: 10px;
}
.item-wrapper{
    flex: 1;
}
.title {
    font-size: 24px;
    font-weight: bold;
}
</style>