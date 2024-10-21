<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="department">
        <h2 class="jh-entity-heading" data-cy="departmentDetailsHeading">
          <span v-text="t$('jy1App.department.detail.title')"></span> {{ department.id }}
        </h2>
        <dl class="row jh-entity-details last-0">
          <dt class="field">
            <span v-text="t$('jy1App.department.name')"></span>
          </dt>
          <dd class="field">
            <span>{{ department.name }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.department.description')"></span>
          </dt>
          <dd class="field">
            <span>{{ department.description }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.department.role')"></span>
          </dt>
          <dd class="field">
            <span>{{ department.role }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.department.officersnum')"></span>
          </dt>
          <dd class="field">
            <span>{{ department.officersnum }}</span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.department.superior')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="department.superior">
              <router-link :to="{ name: 'DepartmentView', params: { departmentId: department.superior.id } }">{{
                department.superior.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.department.officers')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(officers, i) in department.officers" :key="officers.id"
              >{{ i > 0 ? '' : '' }}
              <router-link :to="{ name: 'OfficersView', params: { officersId: officers.id } }">{{ officers.id }}</router-link>
            </span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.department.pbs')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(pbs, i) in department.pbs" :key="pbs.id"
              >{{ i > 0 ? '' : '' }}
              <router-link :to="{ name: 'ProjectpbsView', params: { projectpbsId: pbs.id } }">{{ pbs.id }}</router-link>
            </span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.department.wbs')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(wbs, i) in department.wbs" :key="wbs.id"
              >{{ i > 0 ? '' : '' }}
              <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: wbs.id } }">{{ wbs.id }}</router-link>
            </span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.department.workbag')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(workbag, i) in department.workbags" :key="workbag.id"
              >{{ i > 0 ? '' : '' }}
              <router-link :to="{ name: 'WorkbagView', params: { workbagId: workbag.id } }">{{ workbag.id }}</router-link>
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
