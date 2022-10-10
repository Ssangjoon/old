<template>
  <div>
    <Header/>
    <nav>
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link> |
      <router-link to="/databinding/String">String</router-link> |
      <router-link to="/databinding/html">Html</router-link> |
      <router-link to="/databinding/input">Input</router-link> |
      <router-link to="/databinding/select">Select</router-link> |
      <router-link to="/databinding/check">Check</router-link> |
      <router-link to="/databinding/radio">Radio</router-link> |
      <router-link to="/databinding/attr">Attr</router-link> |
      <router-link to="/databinding/list">List</router-link> |
      <router-link to="/databinding/class">Classs</router-link> |
      <router-link to="/databinding/style">Style</router-link> |
      <router-link to="/event/click">Click</router-link> |
      <router-link to="/event/change">Change</router-link> |
      <router-link to="/event/key">Key</router-link> |
      <router-link to="/test">test</router-link> |
    </nav>
    <router-view/>
  </div>
</template>
<script>
/* eslint-disable */
import Header from './components/layout/Header.vue'

export default {
  components: { Header },
  data() {
    return {
    };
  },
  
}
</script>
<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
