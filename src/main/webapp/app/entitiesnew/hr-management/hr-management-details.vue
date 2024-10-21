<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="hrManagement">
        <h2 class="jh-entity-heading" data-cy="hrManagementDetailsHeading">
          <span v-text="t$('jy1App.hrManagement.detail.title')"></span> {{ hrManagement.id }}
        </h2>
        <dl class="row jh-entity-details last-2">
          <dt class="field">
            <span v-text="t$('jy1App.hrManagement.officersid')"></span>
          </dt>
          <dd class="field">
            <span>{{ hrManagement.officersid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.hrManagement.officersname')"></span>
          </dt>
          <dd class="field">
            <span>{{ hrManagement.officersname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.hrManagement.projectid')"></span>
          </dt>
          <dd class="field">
            <span>{{ hrManagement.projectid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.hrManagement.projectname')"></span>
          </dt>
          <dd class="field">
            <span>{{ hrManagement.projectname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.hrManagement.projectrole')"></span>
          </dt>
          <dd class="field">
            <span>{{ hrManagement.projectrole }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.hrManagement.jobgrade')"></span>
          </dt>
          <dd class="field">
            <span>{{ hrManagement.jobgrade }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.hrManagement.departmentid')"></span>
          </dt>
          <dd class="field">
            <span>{{ hrManagement.departmentid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.hrManagement.departmentname')"></span>
          </dt>
          <dd class="field">
            <span>{{ hrManagement.departmentname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.hrManagement.frontlineid')"></span>
          </dt>
          <dd class="field">
            <span>{{ hrManagement.frontlineid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.hrManagement.frontlinename')"></span>
          </dt>
          <dd class="field">
            <span>{{ hrManagement.frontlinename }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.hrManagement.jobduty')"></span>
          </dt>
          <dd class="field">
            <span>{{ hrManagement.jobduty }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.hrManagement.annualworktime')"></span>
          </dt>
          <dd class="field">
            <span>{{ hrManagement.annualworktime }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.hrManagement.annualtasktarget')"></span>
          </dt>
          <dd class="field">
            <span>{{ hrManagement.annualtasktarget }}</span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.hrManagement.officers')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="hrManagement.officers">
              <router-link :to="{ name: 'OfficersView', params: { officersId: hrManagement.officers.id } }">{{
                hrManagement.officers.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="hrManagement.id"
          :to="{ name: 'HrManagementEdit', params: { hrManagementId: hrManagement.id } }"
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

<script lang="ts" src="./hr-management-details.component.ts"></script>
