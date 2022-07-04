import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [

  // * navbar + footer 있는 것
  {
    path: '/',
    name: '',
    component: () => import('@/views/HomeView'),
    children: [
      {
        path: '/',
        name: '',
        component: () => import('@/components/home/ContentCom')
      },
      {
        path: '/mypage',
        name: '',
        component: () => import('@/components/mypage/MyPageCom2')
      },
      {
        path     : '/user-edit/:id',
        name     : 'user-edit',
        component: () => import('@/components/mypage/temp_edit'),
      },
      {
        path: '/faq',
        name: '',
        component: () => import('@/components/faq/FaqCom')
      },
      {
        path: '/search',
        name: '',
        component: () => import('@/components/search/SearchCom')
      },
      {
        path: '/detail',
        name: '',
        component: () => import('@/components/detail/DetailCom')
      },
      {
        path: '/all',
        name: '',
        component: () => import('@/components/category/AllProductCom')
      },
      {
        path: '/sale',
        name: '',
        component: () => import('@/components/category/SaleProductCom')
      },
      {
        path: '/casual',
        name: '',
        component: () => import('@/components/category/CasualCom')
      },
      {
        path: '/best',
        name: '',
        component: () => import('@/components/category/BestProductCom')
      },
    ]
  },
  // * navbar + footer 없는 것
  {
    path: '/',
    name: '',
    component: () => import('@/views/HomeView2'),
    children: [
      {
        path: '/login',
        name: '',
        component: () => import('@/components/log/LoginCom')
      },
      {
        path: '/signup',
        name: 'signup',
        component: () => import('@/components/log/SignUpCom')
      },
    ]
  },


]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

/*// 로그인 권한이 없는 사람과 있는 사람을 구별해서 화면을 보여주는 부분 추가
// beforeEach : 메뉴간 서로 이동할 때 중간에 가로채는 이벤트
// to : 이동할 페이지
// from : 이동하기 전 페이지
// next : 다음페이지로 이동
router.beforeEach((to, from, next) => {
  // 권한관리 : 회원, 비회원
  // 인증 없이 접근가능한 경로 (화면)
  const publicPages = ['/login', '/signup', '/']
  // 인증이 있어야만 접근 가능한 경로(화면)
  const authRequired = !publicPages.includes(to.path)
  // 웹 토큰을 가져와서 확인
  // 웹 토큰이 있으면 회원 => 다음 화면으로 넘김
  // 웹 토큰이 없으면 비회원 => 로그인 화면으로 팅굼
  const loggedIn = localStorage.getItem('user')
  // 비회원
  if (authRequired && !loggedIn) {
    next('/login')
  }
  // 회원
  else {
    next()
  }
})*/
export default router
