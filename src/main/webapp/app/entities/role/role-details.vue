<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="role">
        <h2 class="jh-entity-heading" data-cy="roleDetailsHeading"><span v-text="t$('jy1App.role.detail.title')"></span> {{ role.id }}</h2>
        <dl class="row jh-entity-details last-1">
          <dt class="field">
            <span v-text="t$('jy1App.role.rolename')"></span>
          </dt>
          <dd class="field">
            <span>{{ role.rolename }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.role.description')"></span>
          </dt>
          <dd class="field">
            <span>{{ role.description }}</span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.role.permission')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(permission, i) in role.permissions" :key="permission.id"
              >{{ i > 0 ? '' : '' }}
              <router-link :to="{ name: 'PermissionView', params: { permissionId: permission.id } }">{{ permission.id }}</router-link>
            </span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.role.officers')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(officers, i) in role.officers" :key="officers.id"
              >{{ i > 0 ? '' : '' }}
              <router-link :to="{ name: 'OfficersView', params: { officersId: officers.id } }">{{ officers.id }}</router-link>
            </span>
          </dd>
        </dl>
        <el-button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </el-button>
        <router-link v-if="role.id" :to="{ name: 'RoleEdit', params: { roleId: role.id } }" custom v-slot="{ navigate }">
          <el-button @click="navigate" class="btn btn-primary" type="primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </el-button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./role-details.component.ts"></script>
