<template>
  <div class="container">
    <div class="row my-5">
      <div v-if="loading">
        <div class="d-flex justify-content-center">
          <div class="spinner-border" role="status">
            <span class="visually-hidden">Loading...</span>
          </div>
        </div>
      </div>
      <div v-else>
        <PostComponent
          v-for="photo in photos"
          :key="photo.id"
          :post="photo"
          @emitComment="postComment"
        />
        <div class="fs-2" v-if="photos.length <= 0">Non ci sono post.</div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import PostComponent from "./PostComponent.vue";
const apiUrl = "http://localhost:8080/api/1/photo";

export default {
  name: "MainComponent",
  components: {
    PostComponent,
  },
  props: {
    needle: {
      type: String,
      required: false,
    },
  },
  watch: {
    needle(oldne, newn) {
      if (oldne != newn) {
        this.filterSearch();
      }
    },
  },
  data() {
    return {
      photos: {},
      color: "red",
      loading: true,
    };
  },
  methods: {
    getPhotoIndexByid(id) {
      for (let i = 0; i < this.photos.length; i++) {
        let photo = this.photos[i];
        if (photo.id === id) {
          return i;
        }
      }
    },
    getPhotoIndexByidLambda(id) {
      return this.photos.findIndex((photo) => photo.id == id);
    },
    postComment(param) {
      const {
        infos: { comment, id },
      } = param;
      const photoIndex = this.getPhotoIndexByid(id);

      const sendComment = { id: 0, comment: comment };

      axios
        .post(apiUrl + "/comment/" + id, sendComment)
        .then((res) => {
          // unshift pusha in alto al contrario di .push
          this.photos[photoIndex].comments.unshift(res.data);
        })
        .catch((e) => {
          console.error(e);
        });
    },
    getAllPhoto() {
      axios
        .get(apiUrl + "/all")
        .then((res) => {
          console.log(res.data);
          this.photos = res.data;
          this.loading = false;
        })
        .catch((e) => {
          console.error(e);
        });
    },

    filterSearch() {
      axios
        .get(apiUrl + "/search", {
          params: {
            query: this.needle,
          },
        })
        .then((res) => {
          this.photos = res.data;
        })
        .catch((e) => {
          console.error(e);
        });
    },
  },
  created() {
    this.getAllPhoto();
  },
};
</script>

<style lang="scss">
@import "Bootstrap";
</style>
