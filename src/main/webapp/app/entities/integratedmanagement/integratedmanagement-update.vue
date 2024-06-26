<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster0App.integratedmanagement.home.createOrEditLabel"
          data-cy="IntegratedmanagementCreateUpdateHeading"
          v-text="t$('jHipster0App.integratedmanagement.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="integratedmanagement.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="integratedmanagement.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.integratedmanagement.name')" for="integratedmanagement-name"></label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="integratedmanagement-name"
              data-cy="name"
              :class="{ valid: !v$.name.$invalid, invalid: v$.name.$invalid }"
              v-model="v$.name.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.integratedmanagement.description')"
              for="integratedmanagement-description"
            ></label>
            <input
              type="text"
              class="form-control"
              name="description"
              id="integratedmanagement-description"
              data-cy="description"
              :class="{ valid: !v$.description.$invalid, invalid: v$.description.$invalid }"
              v-model="v$.description.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.integratedmanagement.wbs')" for="integratedmanagement-wbs"></label>
            <select class="form-control" id="integratedmanagement-wbs" data-cy="wbs" name="wbs" v-model="integratedmanagement.wbs">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  integratedmanagement.wbs && integratedmanagementWbsOption.id === integratedmanagement.wbs.id
                    ? integratedmanagement.wbs
                    : integratedmanagementWbsOption
                "
                v-for="integratedmanagementWbsOption in integratedmanagementWbs"
                :key="integratedmanagementWbsOption.id"
              >
                {{ integratedmanagementWbsOption.id }}
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
<script lang="ts" src="./integratedmanagement-update.component.ts"></script>
