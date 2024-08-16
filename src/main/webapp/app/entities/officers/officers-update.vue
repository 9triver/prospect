<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.officers.home.createOrEditLabel"
          data-cy="OfficersCreateUpdateHeading"
          v-text="t$('jy1App.officers.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="officers.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="officers.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.officers.name')" for="officers-name"></label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="officers-name"
              data-cy="name"
              :class="{ valid: !v$.name.$invalid, invalid: v$.name.$invalid }"
              v-model="v$.name.$model"
              required
            />
            <div v-if="v$.name.$anyDirty && v$.name.$invalid">
              <small class="form-text text-danger" v-for="error of v$.name.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.officers.password')" for="officers-password"></label>
            <input
              type="text"
              class="form-control"
              name="password"
              id="officers-password"
              data-cy="password"
              :class="{ valid: !v$.password.$invalid, invalid: v$.password.$invalid }"
              v-model="v$.password.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.officers.email')" for="officers-email"></label>
            <input
              type="text"
              class="form-control"
              name="email"
              id="officers-email"
              data-cy="email"
              :class="{ valid: !v$.email.$invalid, invalid: v$.email.$invalid }"
              v-model="v$.email.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.officers.phone')" for="officers-phone"></label>
            <input
              type="number"
              class="form-control"
              name="phone"
              id="officers-phone"
              data-cy="phone"
              :class="{ valid: !v$.phone.$invalid, invalid: v$.phone.$invalid }"
              v-model.number="v$.phone.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.officers.hiredate')" for="officers-hiredate"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="officers-hiredate"
                  v-model="v$.hiredate.$model"
                  name="hiredate"
                  class="form-control"
                  :locale="currentLanguage"
                  button-only
                  today-button
                  reset-button
                  close-button
                >
                </b-form-datepicker>
              </b-input-group-prepend>
              <b-form-input
                id="officers-hiredate"
                data-cy="hiredate"
                type="text"
                class="form-control"
                name="hiredate"
                :class="{ valid: !v$.hiredate.$invalid, invalid: v$.hiredate.$invalid }"
                v-model="v$.hiredate.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.officers.years')" for="officers-years"></label>
            <input
              type="number"
              class="form-control"
              name="years"
              id="officers-years"
              data-cy="years"
              :class="{ valid: !v$.years.$invalid, invalid: v$.years.$invalid }"
              v-model.number="v$.years.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.officers.status')" for="officers-status"></label>
            <select
              class="form-control"
              name="status"
              :class="{ valid: !v$.status.$invalid, invalid: v$.status.$invalid }"
              v-model="v$.status.$model"
              id="officers-status"
              data-cy="status"
            >
              <option
                v-for="officersStatus in officersStatusValues"
                :key="officersStatus"
                v-bind:value="officersStatus"
                v-bind:label="t$('jy1App.OfficersStatus.' + officersStatus)"
              >
                {{ officersStatus }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.officers.departments')" for="officers-departments"></label>
            <select
              class="form-control"
              id="officers-departments"
              data-cy="departments"
              multiple
              name="departments"
              v-if="officers.departments !== undefined"
              v-model="officers.departments"
            >
              <option
                v-bind:value="getSelected(officers.departments, departmentOption, 'id')"
                v-for="departmentOption in departments"
                :key="departmentOption.id"
              >
                {{ departmentOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.officers.role')" for="officers-role"></label>
            <select
              class="form-control"
              id="officers-roles"
              data-cy="role"
              multiple
              name="role"
              v-if="officers.roles !== undefined"
              v-model="officers.roles"
            >
              <option v-bind:value="getSelected(officers.roles, roleOption, 'id')" v-for="roleOption in roles" :key="roleOption.id">
                {{ roleOption.id }}
              </option>
            </select>
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" data-cy="entityCreateCancelButton" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.cancel')"></span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="v$.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.save')"></span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./officers-update.component.ts"></script>
