<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="officers">
        <h2 class="jh-entity-heading" data-cy="officersDetailsHeading">
          <span v-text="t$('jHipster3App.officers.detail.title')"></span> {{ officers.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster3App.officers.officersid')"></span>
          </dt>
          <dd>
            <span>{{ officers.officersid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.officers.officersname')"></span>
          </dt>
          <dd>
            <span>{{ officers.officersname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.officers.password')"></span>
          </dt>
          <dd>
            <span>{{ officers.password }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.officers.email')"></span>
          </dt>
          <dd>
            <span>{{ officers.email }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.officers.phone')"></span>
          </dt>
          <dd>
            <span>{{ officers.phone }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.officers.department')"></span>
          </dt>
          <dd>
            <div v-if="officers.department">
              <router-link :to="{ name: 'DepartmentView', params: { departmentId: officers.department.id } }">{{
                officers.department.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.officers.role')"></span>
          </dt>
          <dd>
            <div v-if="officers.role">
              <router-link :to="{ name: 'RoleView', params: { roleId: officers.role.id } }">{{ officers.role.id }}</router-link>
            </div>
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
