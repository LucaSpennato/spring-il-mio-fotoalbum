<template>

  <div>MAIN</div>

</template>

<script>
import axios from 'axios'
const apiUrl = 'http://localhost:8080/api/1/photo'

export default {
  name: "MainComponent",
  data(){
    return{
      photos: {},
    }
  },
  methods:{
    getAllPhoto(){
      axios.get(apiUrl + "/all")
      .then(res=>{
        console.log(res.data)
      })
    }
  },
  created(){
    this.getAllPhoto();
  }
};
</script>

<style lang="scss">
</style>
