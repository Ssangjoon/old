<template>
    <div>
        <input type="text" name="" id="" v-bind:value="userId" readonly />
        <input type="text" name="" id="" :value="userId" readonly />
        <br>
        <img :src="imgSrc" alt="" style="width:100px; height:auto;">
        <br>
        <input type="search" name="" id="" v-model="txt1">
        <button>조회</button>
    </div>
</template>
<script>
/* eslint-disable */
export default {
    components: {},
    data() {
        return {
            userId: 'ssang',
            imgSrc: 'https://bimage.interpark.com/partner/goods_image/0/9/6/9/350530969g.jpg',
            txt1: ''
        }
    },
    setup() {},
    created() {},
    mounted() {},
    unmounted() {},
    methods: {}
}
</script>