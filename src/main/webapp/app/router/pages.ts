/* tslint:disable */
// prettier-ignore

// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here
import { Authority } from '@/shared/security/authority';
export default [
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
  {
    path: '/processDeployment',
    name: 'processDeployment',
    component: () => import('@/pages/processDeployment/process-deployment.vue'),
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/processDeploymentNew',
    name: 'processDeploymentNew',
    component: () => import('@/pages/processDeployment/process-deployment-new.vue'),
    meta: { authorities: [Authority.USER] },
  },
]
