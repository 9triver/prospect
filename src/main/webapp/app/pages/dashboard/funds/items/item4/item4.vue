<template>
    <div class="item-content-wrapper">
        <div class="title">系统经费</div>
        <div class="table-content">
            <MyTable/>
        </div>
    </div>
</template>
<script>
    import MyTable from './table.vue'
    export default{
       name:'myComponent',
       data(){
           return{}
       },
       props:{},
       methods:{},
       components:{
        MyTable
       },
}
</script>
<style scoped>
    .item-content-wrapper{
        width: 100%;
        height: 100%;
        display: flex;
        flex-direction: column;
        overflow-y: scroll;
    }
    .item-content-wrapper .title{
        font-size: 20px;
        font-weight: bold;
    }

    .item-content-wrapper .table-content{
        float: 1;
    }

</style>