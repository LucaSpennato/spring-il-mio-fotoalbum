<template>

    <nav class="container-fluid bg-light py-3">

        <div class="row d-flex justify-content-around">
            <h3 class="col-4">
                <a class="navbar-brand" href="/admin/photo/home">FotoAlbum</a>
            </h3>
            <div class="col-4 d-flex" role="search">
                <input class="form-control mx-2" type="search" placeholder="Search" @keyup="emitNeedle()" v-model.trim="needle">
                <button class="btn btn-outline-success" type="submit" @click="emitNeedle()">Search</button>
            </div>
        </div>
    </nav>

</template>

<script>
export default {
 name: 'HeaderComponent',
 data(){
    return{
        needle: '',
    }
 },
 methods:{
    emitNeedle(){
        this.$emit("searchNeedle", this.needle)
    }
 }
}
</script>

<style lang="scss">
@import 'Bootstrap';
</style>