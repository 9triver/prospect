<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster0App.secrecymanagementWbs.home.createOrEditLabel"
          data-cy="SecrecymanagementWbsCreateUpdateHeading"
          v-text="t$('jHipster0App.secrecymanagementWbs.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="secrecymanagementWbs.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="secrecymanagementWbs.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.secrecymanagementWbs.wbsspare1')"
              for="secrecymanagement-wbs-wbsspare1"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare1"
              id="secrecymanagement-wbs-wbsspare1"
              data-cy="wbsspare1"
              :class="{ valid: !v$.wbsspare1.$invalid, invalid: v$.wbsspare1.$invalid }"
              v-model="v$.wbsspare1.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.secrecymanagementWbs.wbsspare2')"
              for="secrecymanagement-wbs-wbsspare2"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare2"
              id="secrecymanagement-wbs-wbsspare2"
              data-cy="wbsspare2"
              :class="{ valid: !v$.wbsspare2.$invalid, invalid: v$.wbsspare2.$invalid }"
              v-model="v$.wbsspare2.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.secrecymanagementWbs.wbsspare3')"
              for="secrecymanagement-wbs-wbsspare3"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare3"
              id="secrecymanagement-wbs-wbsspare3"
              data-cy="wbsspare3"
              :class="{ valid: !v$.wbsspare3.$invalid, invalid: v$.wbsspare3.$invalid }"
              v-model="v$.wbsspare3.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.secrecymanagementWbs.wbsspare4')"
              for="secrecymanagement-wbs-wbsspare4"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare4"
              id="secrecymanagement-wbs-wbsspare4"
              data-cy="wbsspare4"
              :class="{ valid: !v$.wbsspare4.$invalid, invalid: v$.wbsspare4.$invalid }"
              v-model="v$.wbsspare4.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.secrecymanagementWbs.wbsspare5')"
              for="secrecymanagement-wbs-wbsspare5"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare5"
              id="secrecymanagement-wbs-wbsspare5"
              data-cy="wbsspare5"
              :class="{ valid: !v$.wbsspare5.$invalid, invalid: v$.wbsspare5.$invalid }"
              v-model="v$.wbsspare5.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.secrecymanagementWbs.secrecysystem')"
              for="secrecymanagement-wbs-secrecysystem"
            ></label>
            <select
              class="form-control"
              id="secrecymanagement-wbs-secrecysystem"
              data-cy="secrecysystem"
              name="secrecysystem"
              v-model="secrecymanagementWbs.secrecysystem"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  secrecymanagementWbs.secrecysystem && secrecysystemOption.id === secrecymanagementWbs.secrecysystem.id
                    ? secrecymanagementWbs.secrecysystem
                    : secrecysystemOption
                "
                v-for="secrecysystemOption in secrecysystems"
                :key="secrecysystemOption.id"
              >
                {{ secrecysystemOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.secrecymanagementWbs.projectSecrecy')"
              for="secrecymanagement-wbs-projectSecrecy"
            ></label>
            <select
              class="form-control"
              id="secrecymanagement-wbs-projectSecrecy"
              data-cy="projectSecrecy"
              name="projectSecrecy"
              v-model="secrecymanagementWbs.projectSecrecy"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  secrecymanagementWbs.projectSecrecy && projectSecrecyOption.id === secrecymanagementWbs.projectSecrecy.id
                    ? secrecymanagementWbs.projectSecrecy
                    : projectSecrecyOption
                "
                v-for="projectSecrecyOption in projectSecrecies"
                :key="projectSecrecyOption.id"
              >
                {{ projectSecrecyOption.id }}
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
<script lang="ts" src="./secrecymanagement-wbs-update.component.ts"></script>
