<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="officers">
        <h2 class="jh-entity-heading" data-cy="officersDetailsHeading">
          <span v-text="t$('jy1App.officers.detail.title')"></span> {{ officers.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jy1App.officers.officersname')"></span>
          </dt>
          <dd>
            <span>{{ officers.officersname }}</span>
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
            <span v-text="t$('jy1App.officers.department')"></span>
          </dt>
          <dd>
            <span v-for="(department, i) in officers.departments" :key="department.id"
              >{{ i > 0 ? ', ' : '' }}
              <router-link :to="{ name: 'DepartmentView', params: { departmentId: department.id } }">{{ department.id }}</router-link>
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
