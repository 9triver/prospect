<template>
    <div style="position: fixed;right: 20px;">
        <el-input v-model="selectProjectName" placeholder="请选择项目" :suffix-icon="Calendar" readonly style="width: 200px;position: absolute; right: 0; top: -53px;">
            <template #suffix>
                <el-icon class="el-input__icon">
                    <search />
                </el-icon>
            </template>
        </el-input>
        <!-- vue2.x -->
        <!-- <el-input placeholder="请选择项目" :prefix-icon="Search" v-model="selectProjectName"
            style="width: 200px;position: absolute; right: 0; top: -53px;" readonly>
        </el-input> -->
    </div>
    <div class="funds-dashboard-wrapper">
        <div class="funds-dashboard-item">
            <div class="funds-dashboard-item-content-wrapper">
                <Item1 />
            </div>
        </div>
        <div class="funds-dashboard-item">
            <div class="funds-dashboard-item-content-wrapper">
                <Item2 />
            </div>
        </div>
        <div class="funds-dashboard-item">
            <div class="funds-dashboard-item-content-wrapper">
                <Item3 />
            </div>
        </div>
        <div class="funds-dashboard-item">
            <div class="funds-dashboard-item-content-wrapper">
                <Item4 />
            </div>
        </div>
        <div class="funds-dashboard-item">
            <div class="funds-dashboard-item-content-wrapper">
                <Item5 />
            </div>
        </div>
        <div class="funds-dashboard-item">
            <div class="funds-dashboard-item-content-wrapper">
                <Item6 />
            </div>
        </div>
    </div>
</template>
<script>
import Item1 from './items/item1/item1.vue'
import Item2 from './items/item2/item2.vue'
import Item3 from './items/item3/item3.vue'
import Item4 from './items/item4/item4.vue'
import Item5 from './items/item5/item5.vue'
import Item6 from './items/item6/item6.vue'
import { Search, Calendar } from '@element-plus/icons-vue'
export default {
    name: 'funds-basic',
    data() {
        return {
            selectProjectName: '项目xxx'
        }
    },
    props: {},
    methods: {},
    components: {
        Item1,
        Item2,
        Item3,
        Item4,
        Item5,
        Item6
    },
}
</script>
<style>
/* 使用纯css语法 */
.funds-dashboard-wrapper {
    width: 100%;
    float: left;
}

.funds-dashboard-wrapper .funds-dashboard-item {
    height: 300px;
    width: 50%;
    /* border: 1px solid #ccc; */
    float: left;
    padding: 10px;
}

.funds-dashboard-wrapper .funds-dashboard-item:nth-child(1) {
    width: 25%;
}

.funds-dashboard-wrapper .funds-dashboard-item:nth-child(2) {
    width: 75%;
}

/* .funds-dashboard-wrapper .funds-dashboard-item:nth-child(6) {
    width: 25%;
}

.funds-dashboard-wrapper .funds-dashboard-item:nth-child(7) {
    width: 25%;
} */

.funds-dashboard-wrapper .funds-dashboard-item .funds-dashboard-item-content-wrapper {
    height: 100%;
    width: 100%;
    background-color: #fff;
    border-radius: 10px;
    box-shadow: 1px 1px 10px 1px #ccc;
    padding: 10px;
}
</style>
