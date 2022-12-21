<template>

<div class="container">
  <div class="row">

    <div v-for="photo in photos" :key="photo.id">

      <div class="text-danger">
        {{ photo.id }} - {{ photo.title }}
      </div>
      <div v-for="tag in photo.tags" :key="tag.id" class="text-success">
        {{ tag.id }} - {{ tag.name }}
      </div>
      <div v-for="cat in photo.categories" :key="cat.id" class="text-primary">
        {{ cat.id }} - {{ cat.name }}
      </div>
      <div v-for="c in photo.comments" :key="c.id">
        {{ c.id }} - {{ c.comment }}
      </div>

    </div>

  </div>



</div>

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
        this.photos = res.data
      })
      .catch(e => {console.error(e)})
    }
  },
  created(){
    this.getAllPhoto();
  }
};
</script>

<style lang="scss">
@import "Bootstrap";
</style>
