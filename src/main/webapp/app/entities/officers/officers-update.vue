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
            <label class="form-control-label" v-text="t$('jy1App.officers.officersname')" for="officers-officersname"></label>
            <input
              type="text"
              class="form-control"
              name="officersname"
              id="officers-officersname"
              data-cy="officersname"
              :class="{ valid: !v$.officersname.$invalid, invalid: v$.officersname.$invalid }"
              v-model="v$.officersname.$model"
            />
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
            <label v-text="t$('jy1App.officers.department')" for="officers-department"></label>
            <select
              class="form-control"
              id="officers-departments"
              data-cy="department"
              multiple
              name="department"
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
