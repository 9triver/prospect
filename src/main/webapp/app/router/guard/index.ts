import router from '@/router'
import nProgress from 'nprogress'
import 'nprogress/nprogress.css'

nProgress.configure({
  showSpinner: false
})

// 全局前置守卫
router.beforeEach((to, from) => {
    nProgress.start()
    return true
})

// 全局后置钩子
router.afterEach(() => {
    nProgress.done(true)
})