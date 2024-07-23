import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore
const Entities = () => import('@/entities/entities.vue');

const Officers = () => import('@/entities/officers/officers.vue');
const OfficersUpdate = () => import('@/entities/officers/officers-update.vue');
const OfficersDetails = () => import('@/entities/officers/officers-details.vue');

const Department = () => import('@/entities/department/department.vue');
const DepartmentUpdate = () => import('@/entities/department/department-update.vue');
const DepartmentDetails = () => import('@/entities/department/department-details.vue');

const Project = () => import('@/entities/project/project.vue');
const ProjectUpdate = () => import('@/entities/project/project-update.vue');
const ProjectDetails = () => import('@/entities/project/project-details.vue');

const Projectpbs = () => import('@/entities/projectpbs/projectpbs.vue');
const Projectpbs1 = () => import('@/entities/projectpbs/projectpbs1.vue');
const ProjectpbsUpdate = () => import('@/entities/projectpbs/projectpbs-update.vue');
const ProjectpbsDetails = () => import('@/entities/projectpbs/projectpbs-details.vue');

const Projectwbs = () => import('@/entities/projectwbs/projectwbs.vue');
const Projectwbs1 = () => import('@/entities/projectwbs/projectwbs1.vue');
const ProjectwbsUpdate = () => import('@/entities/projectwbs/projectwbs-update.vue');
const ProjectwbsDetails = () => import('@/entities/projectwbs/projectwbs-details.vue');
const projectwbsSelect = ()=> import('@/entities/projectwbs/projectwbsSelect.vue')

// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default {
  path: '/',
  component: Entities,
  children: [
    {
      path: 'officers',
      name: 'Officers',
      component: Officers,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'officers/new',
      name: 'OfficersCreate',
      component: OfficersUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'officers/:officersId/edit',
      name: 'OfficersEdit',
      component: OfficersUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'officers/:officersId/view',
      name: 'OfficersView',
      component: OfficersDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'department',
      name: 'Department',
      component: Department,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'department/new',
      name: 'DepartmentCreate',
      component: DepartmentUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'department/:departmentId/edit',
      name: 'DepartmentEdit',
      component: DepartmentUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'department/:departmentId/view',
      name: 'DepartmentView',
      component: DepartmentDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'project',
      name: 'Project',
      component: Project,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'project/new',
      name: 'ProjectCreate',
      component: ProjectUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'project/:projectId/edit',
      name: 'ProjectEdit',
      component: ProjectUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'project/:projectId/view',
      name: 'ProjectView',
      component: ProjectDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'projectpbs',
      name: 'Projectpbs',
      component: Projectpbs,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'projectpbs1',
      name: 'Projectpbs1',
      component: Projectpbs1,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'projectpbs/new',
      name: 'ProjectpbsCreate',
      component: ProjectpbsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'projectpbs/:projectpbsId/edit',
      name: 'ProjectpbsEdit',
      component: ProjectpbsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'projectpbs/:parentId/edit',
      name: 'ProjectpbsParentCreate',
      component: ProjectpbsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'projectpbs/:projectpbsId/view',
      name: 'ProjectpbsView',
      component: ProjectpbsDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'projectwbs',
      name: 'Projectwbs',
      component: Projectwbs,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'projectwbs1',
      name: 'Projectwbs1',
      component: Projectwbs1,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'projectwbs/new',
      name: 'ProjectwbsCreate',
      component: ProjectwbsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'projectwbs/:projectwbsId/edit',
      name: 'ProjectwbsEdit',
      component: ProjectwbsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'projectpbs/:parentId/edit',
      name: 'ProjectwbsParentCreate',
      component: ProjectwbsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'projectwbs/:projectwbsId/view',
      name: 'ProjectwbsView',
      component: ProjectwbsDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: '/projectwbsSelect',
      name: 'projectwbsSelect',
      component: projectwbsSelect,
      meta: { authorities: [Authority.USER] },
    },
    // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
  ],
};
