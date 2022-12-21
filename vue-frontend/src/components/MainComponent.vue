<template>

<div class="container">
  <div class="row my-5">

    <PostComponent v-for="photo in photos" :key="photo.id" 
      :post="photo" @emitComment=" getCommentPost"/>

  </div>

</div>

</template>

<script>
import axios from 'axios'
import PostComponent from './PostComponent.vue';
const apiUrl = 'http://localhost:8080/api/1/photo'

export default {
  name: "MainComponent",
  components:{
    PostComponent,
  },
  props:{
    needle:{
      type: String,
      required: false,
    }
  },
  watch:{
    needle(oldne, newn){
      if(oldne != newn){
        this.filterSearch()
      }
    }
  },
  data(){
    return{
      photos: {},
    }
  },
  methods:{
    getCommentPost(param){
      console.log(param)
    },
    getAllPhoto(){
      axios.get(apiUrl + "/all")
      .then(res=>{
        console.log(res.data)
        this.photos = res.data
      })
      .catch(e => {console.error(e)})
    },

    filterSearch(){
      console.log(this.needle)
      axios.get(apiUrl + "/search",{params: {
        query: this.needle
      }})
      .then(res=>{
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
