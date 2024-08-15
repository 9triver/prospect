<template>
    <div class="toolbar common-header-wrapper">
        <el-badge :value="200" :max="99" :offset="[-10, 5]">
            <el-tooltip
                effect="dark"
                content="消息提醒"
                placement="top-start"
            >
                <el-icon><Bell /></el-icon>
            </el-tooltip>
        </el-badge>
        <el-tooltip
            effect="dark"
            content="系统设置"
            placement="top-start"
        >
            <el-icon><Setting /></el-icon>
        </el-tooltip>
        <span class="divider"></span>
        <currentUser/>
        <img src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" alt="" srcset="">
    </div>
</template>

<script setup lang='ts'>
import signOut from './sign-out.vue';
import currentUser from './current-user.vue';

</script>
<style lang='scss'>
    .common-header-wrapper{
        .el-badge{
            margin-top: 4px;
        }
        .el-icon{
            font-size: 20px;
            margin: 0px 20px;
            cursor: pointer;
            &:hover{
                color: #005eff;
                transition: all .3s;
            }
        }
        .divider{
            display: block;
            height: 15px;
            width: 1px;
            background: #000;
        }
        img{
            border-radius: 100%;
        }
        .common-header-item{
            margin: 0px 10px;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 14px;
            cursor: pointer;
            // svg{
            //     height: 25px;
            //     width: 25px;
            // }
            // &:hover{
            //     color: #409eff;
            //     transition: all .2s;
            //     svg{
            //         path{
            //             stroke: #409eff;
            //         }
            //     }
            // }
        }
    }
</style>