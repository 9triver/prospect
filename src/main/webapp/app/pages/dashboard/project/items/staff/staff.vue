<template>
    <div class="item-staff-wrapper">
        <div class="title">工时统计</div>
        <div class="item-wrapper">
            <item/>
        </div>
    </div>
</template>
<script>
    import item from './items/item1/item.vue';
    export default{
       data(){
           return{}
       },
       props:{},
       methods:{},
       components:{item},
}
</script>
<style scoped>
.item-staff-wrapper{
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