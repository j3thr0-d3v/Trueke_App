import MainView from '@/view/MainView.vue'
import LoginView from '@/view/LoginView.vue'
import RegisterView from '@/view/RegisterView.vue'
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
      path: '/auth/register',
      name: 'register',
      component: RegisterView,
      meta: {
        requireAuth: false
      }
    },
    {
      path: '/auth/login',
      name: 'login',
      component: LoginView,
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
    next({name: 'login'})
  }else{
    next()
  }
})

export default router
