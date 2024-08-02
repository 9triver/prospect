<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="department">
        <h2 class="jh-entity-heading" data-cy="departmentDetailsHeading">
          <span v-text="t$('jy1App.department.detail.title')"></span> {{ department.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jy1App.department.name')"></span>
          </dt>
          <dd>
            <span>{{ department.name }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.department.officersnum')"></span>
          </dt>
          <dd>
            <span>{{ department.officersnum }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.department.superior')"></span>
          </dt>
          <dd>
            <div v-if="department.superior">
              <router-link :to="{ name: 'DepartmentView', params: { departmentId: department.superior.id } }">{{
                department.superior.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jy1App.department.officers')"></span>
          </dt>
          <dd>
            <span v-for="(officers, i) in department.officers" :key="officers.id"
              >{{ i > 0 ? ', ' : '' }}
              <router-link :to="{ name: 'OfficersView', params: { officersId: officers.id } }">{{ officers.id }}</router-link>
            </span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="department.id"
          :to="{ name: 'DepartmentEdit', params: { departmentId: department.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./department-details.component.ts"></script>
