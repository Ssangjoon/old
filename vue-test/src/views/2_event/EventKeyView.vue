<template>
    <!-- <div>
        <input type="search" @keyup="checkEnter($event)" v-model="searchText">
        <button @click="doSearch">조회</button>
    </div> -->
    <div>
        <input type="search" @keyup.enter="doSearch" v-model="searchText">
        <button @click="doSearch">조회</button>
    </div>
</template>
<script>
/* eslint-disable */
// .enter . tab .esc .space .delete
// .stop 
// .prevent
export default {
    components: {},
    data() {
        return {
            searchText: ''
        }
    },
    setup() {},
    created() {},
    mounted() {},
    unmounted() {},
    methods: {
        doSearch() {
            console.log(this.searchText)
        },
        checkEnter(event) {
            if(event.keyCode === 13) {
                this.doSearch()
            }
        }
    }
}
</script>