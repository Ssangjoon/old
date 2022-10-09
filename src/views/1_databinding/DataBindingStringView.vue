<template>
    <div>
        <h1>Hello{{userName}}</h1>
        <p>{{message}}</p>
    </div>
</template>
<script>
/* eslint-disable */
export default {
	data() {
		return {
			userName: 'John Doe',
			message: 'Welcome',
			arr: [],
			obj: {}
		}
	}
}
</script>
<style scoped></style>
