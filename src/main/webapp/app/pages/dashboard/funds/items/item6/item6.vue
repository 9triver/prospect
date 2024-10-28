<template>
    <div id="chart-content">
        <div class="title">外协合同</div>
        <div class="main-content">
            <div class="left-wrapper">
                <div class="left-wrapper-title">
                    <span>应签合同</span>
                </div>
                <div class="left-wrapper-value">
                    <span>20</span>
                    <span class="unit">（个）</span>
                </div>
                <div class="left-wrapper-title">
                    <span>已签合同</span>
                </div>
                <div class="left-wrapper-value">
                    <span>10</span>
                    <span class="unit">（个）</span>
                </div>
                <div class="left-wrapper-title">
                    <span>已支付合同</span>
                </div>
                <div class="left-wrapper-value">
                    <span>8</span>
                    <span class="unit">（个）</span>
                </div>
            </div>
            <Chart/>
        </div>
    </div>
</template>

<script>

import Chart from './chart/chart.vue'

export default {
    name: 'funds-basic-item6',
    data() {
        return {}
    },
    props: {},
    methods: {},
    components: {
        Chart
    },

}

</script>

<style scoped>
#chart-content {
    height: 100%;
    width: 100%;
    display: flex;
    flex-direction: column;
}

#chart-content .title {
  font-size: 20px;
  font-weight: bold;
}

#chart-content .main-content{
    flex: 1;
    display: flex;
}

#chart-content .left-wrapper {
    margin-left: 30px;
}

#chart-content .left-wrapper .left-wrapper-value {
    text-align: right;
    font-size: 30px;
    font-weight: bold;
    margin-left: 70px;
}

#chart-content .left-wrapper .left-wrapper-value .unit {
    font-size: 14px;
}
</style>