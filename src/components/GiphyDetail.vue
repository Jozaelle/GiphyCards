<template>
  <div id="main">
      <h1>Giphy Detail</h1>
      <img :src="giphy.url" alt= "" />
      <div><button id="favorites" v-on:click="saveGiphy">Save to Favorites?</button></div>
      <h2>Title: {{ giphy.title }}</h2> 
      <h2>Description{{ giphy.description }}</h2>
      <h2>Rating {{ giphy.rating }}</h2>
  </div>
</template>

<script>
import backendService from "../service/BackendService.js";
export default {
    name: 'giphy-detail',
    data(){
        return {
            giphy: Object
        }
    },
    created() {
        backendService.getGiphyDetail(this.$route.params.id)
        .then((response) => {
            console.log(response)
            this.giphy = response.data;
        }) 
    },
    methods: {
        saveGiphy() {
            backendService.saveGiphy(this.giphy)
            .then((response) =>{
                if (response.status === 200){
                    alert(this.giphy.title + "has been saved!");
                    this.$router.push("/giphy");
                }
            });
        }
    }
}
</script>

<style scoped>
#main {
    text-align: center;
    
}
#favorites{
    border-radius: 50%;
    height: 6em;
    width: 6em;
    top: 0;
    left: 0;
    margin-top: 5px;
    margin-left: 22px;
    margin-bottom: 15px;
    color: black;
    font-size: .9em;
    background-color: ivory;
}


</style>