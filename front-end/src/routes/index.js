import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: "/",
            name: "main",
            // component: () => import("@/components/MainPage.vue")
            component: () => import("@/components/WelcomePage.vue")
        },
        // {
        //     path: "/test",
        //     name: "test",
        //     component: () => import("@/components/TestPage.vue")
        // },
        {
            path: "/join",
            name: "join",
            component: () => import("@/components/JoinPage.vue")
        },
        {
            path: "/lend",
            name: "lend",
            component: () => import("@/components/LendPage.vue")
        },
        {
            path: "/return",
            name: "return",
            component: () => import("@/components/ReturnPage.vue")
        },
        {
            path: "/confirm",
            name: "confirm",
            component: () => import("@/components/ConfirmPage.vue")
        }
    ]
})

export default router
