<template>
    <div style="display:flex">
        <Card title="质量策划（已发布）" value="120" notStart="10" isEnd="20" overDue="30" >
            <Flag/>
        </Card>
        <Card title="质量目标总数" value="120" notStart="10" isEnd="20" overDue="30" >
            <TrendCharts/>
        </Card>
        <Card title="包含关键点任务包" value="120" notStart="10" isEnd="20" overDue="30" >
            <BellFilled/>
        </Card>
        <Card title="任务包质量检查" value="120" notStart="10" isEnd="20" overDue="30" >
            <Stamp/>
        </Card>
        
    </div>
</template>
<script>
import Card from './Card/card.vue'
import Flag from './assets/Flag.vue' //计划总数图标
import TrendCharts from './assets/TrendCharts.vue';
import BellFilled from './assets/BellFilled.vue';
import Stamp from './assets/Stamp.vue';
export default {
    data() {
        return {}
    },
    props: {},
    methods: {},
    components: {Card,Flag,TrendCharts,BellFilled,Stamp},
}
</script>
<style></style>