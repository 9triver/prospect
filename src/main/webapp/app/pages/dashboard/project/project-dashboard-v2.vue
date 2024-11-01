<template>
    <div style="display: flex;height: 100%">
        <div style="width: 200px;padding-top: 15px;">
            <wbsTree />
        </div>
        <div style="flex: 1; height: 100%;">
            <el-scrollbar>
                <div class="project-dashboard-wrapper">
                    <div class="dashboard-col col-center">
                        <div class="col-center-row col-center-row-top">
                            <div class="content-wrapper">
                                <plan />
                            </div>
                        </div>
                        <div class="col-center-row col-center-row-bottom">
                            <div class="content-wrapper">
                                <quality />
                            </div>
                        </div>
                    </div>
                    <div class="dashboard-col col-right">
                        <div class="col-right-row col-right-row-top">
                            <div class="content-wrapper">
                                <fund />
                            </div>
                        </div>
                        <div class="col-right-row col-right-row-bottom">
                            <div class="content-wrapper">
                                <staff />
                            </div>
                        </div>
                    </div>
                </div>
            </el-scrollbar>
        </div>
    </div>
</template>
<script>
import wbsTree from './items/wbsTree/wbsTree.vue'
import plan from './items/plan/plan.vue'
import quality from './items/quality/quality.vue';
import fund from './items/fund/fund.vue';
import staff from './items/staff/staff.vue';

export default {
    name: 'projectDashboard',
    data() {
        return {}
    },
    props: {},
    methods: {},
    components: { wbsTree, plan, quality, fund, staff },
}
</script>
<style scoped>
.project-dashboard-wrapper {
    display: flex;
    height: 1000px;
}


.dashboard-col {
    flex: 2;
}

.col-left {
    flex: 1;
}

.col-center,
.col-right {
    display: flex;
    flex-direction: column;
}

.col-center-row {
    flex: 1;
}

.content-wrapper {
    height: 100%;
    padding: 10px;
}

.col-right-row-top {
    flex: 3;
}

.col-right-row-bottom {
    flex: 1;
}
</style>