<template>
  <div>

    <div class="col-11 col-lg-6 border border-rounded my-3 p-4 m-auto">
        <img :src="post.url" :alt="post.title + ` 's photo` " class="img-fluid">
        <div class="fs-2">
            {{ post.title }}
        </div>
        <div class="fs-6 text-muted mb-2">
            {{ post.description }}
        </div>
        <div class="my-2 border-top border-dark">
            <span v-for="tag in post.tags" :key="tag.id" class="tags">
            #{{ tag.name }}
        </span>
        </div>
        <div class="my-2 border-top border-dark">
            <span v-for="cat in post.categories" :key="cat.id" class="category">
                -{{ cat.name }}
            </span>
        </div>
        <div v-if="postIndex !== post.id" @click="postIndex = post.id" class="viewComments">
            View all {{ post.comments.length }} Comments
        </div>
        <div class="closeComments" v-else @click="postIndex = -1">
            Close comments
        </div>

       <div v-show="postIndex == post.id" class="comments">
        <div v-for="cm in reverseComments(post.comments)" :key="cm.id" >
            {{ cm.comment }}
        </div>
       </div>
        <div class="mt-3 d-flex justify-content-between">
            <input type="text" class="border rounded me-2 py-1 w-100" v-model.trim="comt"
                placeholder="Write a comment..." @keyup.enter="emitComment(post.id)">
            <button class="btn btn-success" @click="emitComment(post.id)">Send</button>
        </div>
    </div>
  </div>
</template>

<script>

export default {
name: "PostComponent",
props:{
    post:{
        type: Object,
        required : false,
    },
},
data() {
    return {
        comt: '',
        postIndex: -1,
    }
},

methods: {
    emitComment(id){
        this.$emit("emitComment", {infos: {comment: this.comt, id: id }} )
        this.comt = ''
        this.postIndex = id
    },
    reverseComments(comments){
        return comments.sort((o1,o2) => o2.id - o1.id)
    },
},
}
</script>

<style lang="scss" scoped>
@import 'Bootstrap';
.tags{
    color: blue;
    cursor: pointer;
    &:hover{
        text-decoration: underline;
    }
}
.comments{
    height: 8rem;
    overflow-y: scroll;
    border-top: 1px solid black;
    border-bottom: 1px solid black;
}
.viewComments, .closeComments{
    &:hover{
        cursor: pointer;
        color: blue;
        text-decoration: underline;
    }
}

</style>