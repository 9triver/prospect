<template>
    <div style="position: fixed;right: 20px;">
        <el-input v-model="selectProjectName" placeholder="请选择项目" :suffix-icon="Calendar" readonly
            style="width: 200px;position: absolute; right: 0; top: -64px;">
            <template #suffix>
                <el-icon class="el-input__icon">
                    <search />
                </el-icon>
            </template>
        </el-input>
    </div>
    <div>
        <div class="plan-dashboard-wrapper">
            <div class="left-wrapper">
                <div class="left-item-wrapper">
                    <Item1/>
                </div>
                <div class="left-item-wrapper">
                    <Item2/>
                </div>
                <div class="left-item-wrapper">
                    <Item3/>
                </div>
            </div>
            <div class="right-wrapper">
                <div class="gantt-wrapper">
                    <Item4/>
                </div>
            </div>
        </div>
    </div>
</template>
<script>

import Item1 from './items/item1-v2/item.vue';
import Item2 from './items/item2/item.vue';
import Item3 from './items/item3/item.vue';
import Item4 from './items/item4/item.vue';

export default {
    name: 'planDashboard',
    data() {
        return {}
    },
    props: {},
    methods: {},
    components: {Item1,Item2,Item3,Item4},
}
</script>
<style scoped>
.plan-dashboard-wrapper{
    display: flex;
    height: 900px;
    margin: 10px;
}
.left-wrapper{
    flex: 2;
    display: flex;
    flex-direction: column;
}
.left-item-wrapper:nth-child(1){
    flex: 1;
    box-shadow: none;
}
.left-item-wrapper{
    flex: 2;
    padding: 20px;
    background-color: #fff;
    border-radius: 10px;
    box-shadow: 1px 1px 10px 1px #ccc;
    padding: 10px;
    position: relative;
    margin: 10px 0px;
}
.right-wrapper{
    flex: 3;
    padding: 10px;
}
.gantt-wrapper{
    height: 100%;
    padding: 20px;
    background-color: #fff;
    border-radius: 10px;
    box-shadow: 1px 1px 10px 1px #ccc;
    padding: 10px;
    position: relative;
}

</style>