import Vue from 'vue'
import VueRouter from 'vue-router'

// router : 메뉴달기 비교) springboot : controller (메뉴달기)
Vue.use(VueRouter)

const routes = [
  // home
  {
    path: '/',
    alias: "/home",
    name: 'home',
    component: () => import('@/views/HomeView')
  },
  // /upload
  {
    path: '/upload',
    name: 'upload',
    component: () => import('@/components/UploadImage')
  },  
  // /, /tutorials 메뉴를 클릭하면 TutorialsList.vue 로딩
  // TutorialsList.vue : Tutorial 목록 화면
  {
    path: "/tutorials",
    name: 'tutorials',
    component: () => import('@/components/tutoritals/TutorialsList')
  },
  // Tutorial.vue : Tutorial 상세 화면
  { 
    // :id => params
    path: '/tutorials/:id',
    name: 'tutorial-details',
    component: () => import('@/components/tutoritals/Tutorial')
  },  
  // AddTutorial.vue : Tutorial 에 목록 추가 화면
  {
    path: '/add',
    name: 'add',
    component: () => import('@/components/tutoritals/AddTutorial')
  },
  //  Todo: login 수정
  {
    path: '/login',
    component: () => import('@/views/Login.vue')
  },
  {
    path: '/register',
    component: () => import('@/views/Register.vue')
  },
  {
    path: '/profile',
    name: 'profile',
    // lazy-loaded
    component: () => import('@/views/Profile.vue')
  },
  {
    path: '/admin',
    name: 'admin',
    // lazy-loaded
    component: () => import('@/views/BoardAdmin.vue')
  },
  {
    path: '/user',
    name: 'user',
    // lazy-loaded
    component: () => import('@/views/BoardUser.vue')
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})


router.beforeEach((to, from, next) => {
  // 인증 없이 접근가능한 경로
  const publicPages = ['/login', '/register', '/tutorials', '/home', '/upload'];
  const authRequired = !publicPages.includes(to.path);
  const loggedIn = localStorage.getItem('user');

  // trying to access a restricted page + not logged in
  // redirect to login page
  // 인증된 사용자만 볼수 있는 페이지에 무단으로 들어오면
  // login 페이지로 리다이렉트함
  if (authRequired && !loggedIn) {
    next('/login');
  } else {
    // 인증된 사용자이면 다음 페이지롤 이동
    next();
  }
});

export default router
