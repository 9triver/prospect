<template>
    <div :class="`customer-divider ${orient}`"></div>
</template>
<script>
export default {
    data() {
        return {}
    },
    props: {
        orient: {
            type: String,
            default: 'horizontal'
        }
    },
    methods: {},
    components: {},
}
</script>
<style scoped>
.customer-divider.horizontal {
    width: 100%;
    height: 2px;
    background: -webkit-linear-gradient(left,
            #fff -4%,
            #bdd0e8  50%,
            #fff 100%);
}
.customer-divider.vertical {
    width: 2px;
    height: 100%;
    background: -webkit-linear-gradient(top,
            #fff -4%,
            #bdd0e8  50%,
            #fff 100%);
}
</style>