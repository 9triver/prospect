<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="officers">
        <h2 class="jh-entity-heading" data-cy="officersDetailsHeading">
          <span v-text="t$('jy1App.officers.detail.title')"></span> {{ officers.id }}
        </h2>
        <dl class="row jh-entity-details last-1">
          <dt class="field">
            <span v-text="t$('jy1App.officers.name')"></span>
          </dt>
          <dd class="field">
            <span>{{ officers.name }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.officers.password')"></span>
          </dt>
          <dd class="field">
            <span>{{ officers.password }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.officers.email')"></span>
          </dt>
          <dd class="field">
            <span>{{ officers.email }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.officers.phone')"></span>
          </dt>
          <dd class="field">
            <span>{{ officers.phone }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.officers.hiredate')"></span>
          </dt>
          <dd class="field">
            <span>{{ officers.hiredate }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.officers.years')"></span>
          </dt>
          <dd class="field">
            <span>{{ officers.years }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.officers.status')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.OfficersStatus.' + officers.status)"></span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.officers.departments')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(departments, i) in officers.departments" :key="departments.id"
              >{{ i > 0 ? '' : '' }}
              <router-link :to="{ name: 'DepartmentView', params: { departmentId: departments.id } }">{{ departments.id }}</router-link>
            </span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.officers.frontline')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(frontline, i) in officers.frontlines" :key="frontline.id"
              >{{ i > 0 ? '' : '' }}
              <router-link :to="{ name: 'FrontlineView', params: { frontlineId: frontline.id } }">{{ frontline.id }}</router-link>
            </span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.officers.role')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(role, i) in officers.roles" :key="role.id"
              >{{ i > 0 ? '' : '' }}
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
