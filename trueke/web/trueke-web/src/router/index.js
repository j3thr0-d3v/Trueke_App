import MainView from '@/view/MainView.vue'
import AuthView from '@/view/AuthView.vue'
import { createRouter, createWebHistory } from 'vue-router'
import useAuth from '@/stores/auth';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'main',
      component: MainView,
      meta:{
        requireAuth: true
      }
    },
    {
      path: '/auth',
      name: 'auth',
      component: AuthView,
      meta:{
        requireAuth: false
      }
    }
  ]
})

router.beforeEach((to, from, next)=>{
  const auth = useAuth()
  const isAuth = auth.token !== null;
  const needToAuth = to.meta.requireAuth
  
  if(needToAuth && !isAuth){
    auth.user={}
    next('auth')
  }else{
    next()
  }
})

export default router
