<template>
 <div>
    <HeaderComponent @searchNeedle="getEmit"/>
    <MainComponent :needle="needle"/>
 </div>
</template>

<script>
import MainComponent from './components/MainComponent.vue';
import HeaderComponent from './components/HeaderComponent.vue';

export default {
  name: 'App',
  components:{
    MainComponent,
    HeaderComponent,
  },
  data(){
    return{
      needle: '',
    }
  },
  methods:{
    getEmit(param){
      this.needle = param;
    }
  }
}
</script>

<style lang="scss">

</style>
