<template>
    <div>
        <div @click="increaseCounter">Add 1</div>
        <p>{{counter}}</p>
    </div>
</template>
<script>
/* eslint-disable */
export default {
    components: {},
    data() {
        return {
            counter: 0
        }
    },
    setup() {},
    created() {},
    mounted() {},
    unmounted() {},
    methods: {
        increaseCounter() {
            this.counter += 1
        }
    }
}
</script>