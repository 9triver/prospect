import { defineComponent, provide } from 'vue';

import OfficersService from './officers/officers.service';
import DepartmentService from './department/department.service';
import ProjectService from './project/project.service';
import ProjectpbsService from './projectpbs/projectpbs.service';
import ProjectwbsService from './projectwbs/projectwbs.service';
import UserService from '@/entities/user/user.service';
// jhipster-needle-add-entity-service-to-entities-component-import - JHipster will import entities services here

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Entities',
  setup() {
    provide('userService', () => new UserService());
    provide('officersService', () => new OfficersService());
    provide('departmentService', () => new DepartmentService());
    provide('projectService', () => new ProjectService());
    provide('projectpbsService', () => new ProjectpbsService());
    provide('projectwbsService', () => new ProjectwbsService());
    // jhipster-needle-add-entity-service-to-entities-component - JHipster will import entities services here
  },
});
