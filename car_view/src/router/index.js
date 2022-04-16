import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
    //用户路由--登陆注册
    {path:'/',name: 'userLogin', component:()=>import('../views/user/login/UserLogin'),},
    {path: '/userRegister',name:'userRegister',component:()=>import('../views/user/login/UserRegister')},
    //忘记密码
    {
        path:'/userForget',name: 'userForget',redirect: '/userForget/one', component: ()=>import('../views/user/forget/ForgetPass'),
        children: [
            {path:'one',name: 'one', component: ()=>import('../views/user/forget/UserForgetPassOne')},
            {path:'two',name: 'two', component: ()=>import('../views/user/forget/UserForgetPassTwo')},
            {path:'three',name: 'three', component: ()=>import('../views/user/forget/UserForgetPassThree')}
        ]
    },
    //用户主页
    {
        path:'/user',name:'userHome',redirect: '/user/index',
        component:()=>import('../views/user/UserHome'),
        meta:{cheakIsLogin:true},
        children: [
            {path: 'index',name:'userIndex',meta:{cheakIsLogin:true},component:()=>import('../views/user/UserIndex')},
            {path: 'store',name:'userStore',meta:{cheakIsLogin:true},component:()=>import('../views/user/UserStore')},
            {path: 'news',name:'userNews',meta:{cheakIsLogin:true},component:()=>import('../views/user/UserNews')},
            {path: 'order',name:'userOrder',meta:{cheakIsLogin:true},component:()=>import('../views/user/UserOrder')},
            {path: 'info',name:'userInfo',meta:{cheakIsLogin:true},component:()=>import('../views/user/UserInfo')},
        ]
    },
    //管理员主页
    {
        path:'/manage', name:'manage', redirect:'/manage/home',
        component: ()=>import('../views/manage/ManageHome'),
        meta:{requireAuth:true},
        children: [
            {path:'home', name:'home',meta:{requireAuth:true}, component:()=>import('../views/manage/Home')},
            {path:'file', name:'file',meta:{requireAuth:true}, component:()=>import('../views/manage/File')},
            {path:'info', name:'info',meta:{requireAuth:true}, component:()=>import('../views/manage/Information')},
            {path:'users', name:'users',meta:{requireAuth:true}, component:()=>import('../views/manage/Users')},
            {path:'order', name:'order',meta:{requireAuth:true}, component:()=>import('../views/manage/Order')},
            {path:'package', name:'package',meta:{requireAuth:true}, component:()=>import('../views/manage/Package')},
            {path:'wiki', name:'wiki',meta:{requireAuth:true}, component:()=>import('../views/manage/News')},
        ]
    },
    //登录
    {path:'/index',name: 'login', component: ()=>import('../views/manage/login/Login'),},
    //忘记密码
    {
        path:'/forget',name: 'forget',redirect: '/forget/one', component: ()=>import('../views/manage/login/ForgetPass'),
        children: [
            {path:'one',name: 'one', component: ()=>import('../views/manage/login/StepOne')},
            {path:'two',name: 'two', component: ()=>import('../views/manage/login/StepTwo')},
            {path:'three',name: 'three', component: ()=>import('../views/manage/login/StepThree')}
        ]
    },
]
const router = new VueRouter({
    mode: 'history',
    //base: process.env.BASE_URL,
    routes
})
//前置路由守卫
router.beforeEach((to, from, next)=>{
    if (to.meta.cheakIsLogin){
        const user=JSON.parse(localStorage.getItem("user"))
        if(user!==null){
            if(user.token===localStorage.getItem("token")){
                next()
            }else {
                router.back('/')
            }
        }else{
            router.back('/')
        }
    } else if(to.meta.requireAuth){
        const manager=JSON.parse(localStorage.getItem("manager"))
        if(manager!==null){
            if(manager.token===localStorage.getItem("token")){
                next()
            }else {
                router.back('/index')
            }
        }else{
            router.back('/index')
        }
    }else{
        next()
    }
})

export default router