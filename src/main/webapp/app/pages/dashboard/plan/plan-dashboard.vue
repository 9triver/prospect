<template>
    <div style="position: fixed;right: 20px;">
        <el-input v-model="selectProjectName" placeholder="请选择项目" :suffix-icon="Calendar" readonly
            style="width: 200px;position: absolute; right: 0; top: -53px;">
            <template #suffix>
                <el-icon class="el-input__icon">
                    <search />
                </el-icon>
            </template>
        </el-input>
    </div>
    <div class="plan-dashboard-wrapper">
        <div class="plan-dashboard-item">
            <div class="plan-dashboard-item-content-wrapper">
                <Item1/>
            </div>
        </div>
        <div class="plan-dashboard-item">
            <div class="plan-dashboard-item-content-wrapper">
                <Item2/>
            </div>
        </div>
        <div class="plan-dashboard-item">
            <div class="plan-dashboard-item-content-wrapper">
                <Item3/>
            </div>
        </div>
        <div class="plan-dashboard-item">
            <div class="plan-dashboard-item-content-wrapper">
                <Item4/>
            </div>
        </div>
    </div>
</template>
<script>
import Item1 from './items/item1/item.vue';
import Item2 from './items/item2/item.vue';
import Item3 from './items/item3/item.vue';
import Item4 from './items/item4/item.vue';
export default {
    name: 'plan-dashboard',
    data() {
        return {}
    },
    props: {},
    methods: {},
    components: {
        Item1,Item2,Item3,Item4
    },
}
</script>
<style scoped>
.plan-dashboard-wrapper .plan-dashboard-item {
    height: 300px;
    width: 50%;
    /* border: 1px solid #ccc; */
    float: left;
    padding: 10px;
}
.plan-dashboard-wrapper .plan-dashboard-item:nth-child(1){
    height: 200px;
    width: 100%;
}
.plan-dashboard-wrapper .plan-dashboard-item:nth-child(4){
    width: 100%;
}
.plan-dashboard-item:nth-child(1) .plan-dashboard-item-content-wrapper{
    box-shadow: none;
}
.plan-dashboard-item-content-wrapper{
    height: 100%;
    width: 100%;
    background-color: #fff;
    border-radius: 10px;
    box-shadow: 1px 1px 10px 1px #ccc;
    padding: 10px;
    position: relative;
}
</style>