import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import GiphyView from '../views/GiphyView.vue'
import DetailView from '../views/DetailView.vue'
import FavoritesView from '../views/FavoritesView.vue'
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: HomeView
  },
  {
    path: '/favoritesView',
    name: FavoritesView,
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/FavoritesView.vue')
  },
  {
    path: '/giphyView',
    name: 'Giphy',
    component: GiphyView
  },
  {
    path: '/giphy/:id', // : <- is a changing variable
    name: 'DetailView',
    component: DetailView
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
