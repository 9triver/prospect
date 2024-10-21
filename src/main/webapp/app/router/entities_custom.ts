// 这个文件用来保存自定义的路由

export default [
  {
    path: '/progress-plan-query',
    name: 'progressPlanQuery',
    component: () => import('@/entities_custom/progress-plan/query.vue'),
    meta: { authorities: ['ROLE_USER'] },
  },
  {
    path: '/progress-plan-create-custom',
    name: 'progressPlanCreateCustom',
    component: () => import('@/entities_custom/progress-plan/create.vue'),
    meta: { authorities: ['ROLE_USER'] },
  }
  
];