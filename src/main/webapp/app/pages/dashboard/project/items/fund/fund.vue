<template>
    <div class="item-fund-wrapper">
        <div class="title">经费</div>
        <div class="item-wrapper">
            <item1/>
        </div>
        <divider/>
        <div class="item-wrapper">
            <item2/>
        </div>
        <divider/>
        <div class="item-wrapper">
            <item3/>
        </div>
    </div>
</template>
<script>
    import item1 from './items/item1/item.vue';
    import item2 from './items/item2/item.vue';
    import item3 from './items/item3/item.vue';
    import divider from '../../component/divider.vue'
    export default{
       data(){
           return{}
       },
       props:{},
       methods:{},
       components:{item1, item2, item3, divider},
}
</script>
<style scoped>
.item-fund-wrapper{
    display: flex;
    height: 100%;
    flex-direction: column;
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