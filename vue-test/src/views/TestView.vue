<template>
    <div>
        <div>
            <h1>Study ing... {{title2}}</h1>
            <input type="text" v-model="input1">
            <button @click="getData">Get</button>
            <button @click="setData">Set</button>
        </div>
    </div>
</template>
<script>
/* eslint-disable */
export default {
    components: {},
    data() {
        return {
            title: 'ssangTitle',
            title2: 'ssangTitle2',
            input1: 'abc',
        }
    },
    setup() {console.log("setup")},
    created() {console.log("created")},
    mounted() {
        console.log("mounted")
        console.log("=====================================")
        },
    unmounted() {console.log("unmounted")},
    methods: {
        getData() {
            alert(this.input1)
        },
        setData() {
            this.input1=1234
        }
    },
    beforeCreate() {
        console.log("beforeCreate")
    },
    beforeMount() {
        console.log("beforeMount")
    },
    beforeUpdate() {
        console.log("beforeUpdate")
    }, 
    updated() {
        console.log("updated")
        console.log("-------------------------------------------")
    }, 
    beforeDestroy() {
        console.log("beforeDestroy")
    },
    destroyed() {
        console.log("destroyed")
    },
    beforeUnmount() {
        console.log("beforeUnmount")
    },
    unmounted() {
        console.log("unmounted")
    },
}
</script>