<template>
    <div class="toolbar common-header-wrapper">
        <currentUser/>
        <signOut/>
    </div>
</template>

<script setup lang='ts'>
import signOut from './sign-out.vue';
import currentUser from './current-user.vue';

</script>
<style lang='scss'>
    .common-header-wrapper{
        .common-header-item{
            margin: 0px 10px;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 20px;
            cursor: pointer;
            svg{
                height: 25px;
                width: 25px;
            }
            &:hover{
                color: #409eff;
                transition: all .2s;
                svg{
                    path{
                        stroke: #409eff;
                    }
                }
            }
        }
    }
</style>