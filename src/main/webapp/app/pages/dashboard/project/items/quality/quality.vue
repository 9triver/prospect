<template>
    <div class="item-quality-wrapper">
        <div class="title">质量</div>
        <div class="item-wrapper item-wrapper-top">
            <item1/>
        </div>
        <divider/>
        <div class="item-wrapper item-wrapper-bottom">
            <div class="chart-wrapper">
                <item2/>
            </div>
            <divider orient="vertical"/>
            <div class="chart-wrapper">
                <item3/>
            </div>
        </div>
    </div>
</template>
<script>
    import item1 from './items/item1/item.vue';
    import item2 from './items/item2/item.vue';
    import item3 from './items/item3/item.vue';
    import divider from '../../component/divider.vue';
    export default{
       data(){
           return{}
       },
       props:{},
       methods:{},
       components:{item1,item2,item3,divider},
}
</script>
<style scoped>
.item-quality-wrapper{
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

.item-wrapper-bottom{
    display: flex;
}
.chart-wrapper{
    flex: 1;
}
.title {
    font-size: 24px;
    font-weight: bold;
}

</style>