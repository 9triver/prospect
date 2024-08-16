<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="officers">
        <h2 class="jh-entity-heading" data-cy="officersDetailsHeading">
          <span v-text="t$('jy1App.officers.detail.title')"></span> {{ officers.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jy1App.officers.name')"></span>
          </dt>
          <dd>
            <span>{{ officers.name }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.officers.password')"></span>
          </dt>
          <dd>
            <span>{{ officers.password }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.officers.email')"></span>
          </dt>
          <dd>
            <span>{{ officers.email }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.officers.phone')"></span>
          </dt>
          <dd>
            <span>{{ officers.phone }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.officers.hiredate')"></span>
          </dt>
          <dd>
            <span>{{ officers.hiredate }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.officers.years')"></span>
          </dt>
          <dd>
            <span>{{ officers.years }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.officers.status')"></span>
          </dt>
          <dd>
            <span v-text="t$('jy1App.OfficersStatus.' + officers.status)"></span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.officers.departments')"></span>
          </dt>
          <dd>
            <span v-for="(departments, i) in officers.departments" :key="departments.id"
              >{{ i > 0 ? ', ' : '' }}
              <router-link :to="{ name: 'DepartmentView', params: { departmentId: departments.id } }">{{ departments.id }}</router-link>
            </span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.officers.role')"></span>
          </dt>
          <dd>
            <span v-for="(role, i) in officers.roles" :key="role.id"
              >{{ i > 0 ? ', ' : '' }}
              <router-link :to="{ name: 'RoleView', params: { roleId: role.id } }">{{ role.id }}</router-link>
            </span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link v-if="officers.id" :to="{ name: 'OfficersEdit', params: { officersId: officers.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./officers-details.component.ts"></script>
