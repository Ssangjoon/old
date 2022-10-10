<template>
    <div>
        <div :class="{'text-red':hasError, active:isActive}">클래스 바인딩</div>
        <div :class="class2">클바2</div>
    </div>
</template>
<script>
/* eslint-disable */
export default {
    components: {},
    data() {
        return {
            isActive: false,
            hasError: false,
            class2: ['active', 'hasError']
        }
    },
    setup() {},
    created() {},
    mounted() {},
    unmounted() {},
    methods: {}
}
</script>
<style scoped>
    .active {
        background-color: yellow;
        font-weight: bold;
    }
    .text-red {
        color: red;
    }
</style>