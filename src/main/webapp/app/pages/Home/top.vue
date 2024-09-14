<template>
    <div class="top-wrapper">
        <!-- <div class="top-message">
            <el-icon><BellFilled class="message-icon"/></el-icon>
            <span class="message-content">下午好，xxx</span>
        </div> -->
        <div class="all-cards-wrapper">
            <Card v-for="({title,value,color,icon},index) in dataSource" :title="title" :value="value" :color="color" :icon="icon"/>
        </div>
    </div>
</template>

<script>
    import Card from './card.vue'
    import {getIcon} from './iconlist.ts'
    export default{
        name:"Top",
        data(){
            return{
                dataSource:[
                    {
                        title:"审批流程",
                        value:49,
                        color:"#00c0ef",
                        icon:getIcon("审批流程")
                    },
                    {
                        title:"待办事项",
                        value:39,
                        color:"#00a65a",
                        icon:getIcon("待办事项")
                    },
                    {
                        title:"通知",
                        value:443,
                        color:"#f39c12",
                        icon:getIcon("通知")
                    },
                    {
                        title:"预警",
                        value:64,
                        color:"#dd4b39",
                        icon:getIcon("预警")
                    },
                    {
                        title:"延期提醒",
                        value:31,
                        color:"#9345ea",
                        icon:getIcon("延期提醒")
                    }
                ]
            }
        },
        components:{
            Card 
        }
    }
</script>

<style>
    .top-wrapper{
        height: 100%;
        padding: 10px;
        background-color: #fff;
        display: flex;
        flex-direction: column;
        .all-cards-wrapper{
            /* height: 100%; */
            display: flex;
        }
    }
    .message-icon{
        height: 16px;
        color: #666;
    }
</style>

