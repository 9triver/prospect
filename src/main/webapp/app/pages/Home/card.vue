<template>
    <div class="card-wrapper" :style="{backgroundColor:color}">
        <div class="left-icon">
            <img :src="icon" :alt="index" srcset="">
        </div>
        <div class="right-wrapper">
            <span class="card-title">{{title}}</span>
            <span class="card-value">{{value}}</span>
        </div>
    </div>
</template>

<script>
    export default {
        name:"Card",
        props:{
            title:String,
            value:Number,
            color:String,
            icon:String
        }
    }
</script>

<style>
    .card-wrapper{
        flex: 1;
        margin: 0px 10px;
        height: 100%;
        display: flex;
    }
    .right-wrapper{
        flex: 1;
        position: relative;
    }
    img{
        height: 100%;
    }
    .card-title,.card-value{
        color: #fff;
    }
    .card-title{
        padding: 10px;
    }
    .card-value{
        position: absolute;
        transform: translate(-50%, -50%);
        left: 50%;
        top: 50%;
    }
</style>