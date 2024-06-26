<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster0App.resourcemanagementWbs.home.createOrEditLabel"
          data-cy="ResourcemanagementWbsCreateUpdateHeading"
          v-text="t$('jHipster0App.resourcemanagementWbs.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="resourcemanagementWbs.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="resourcemanagementWbs.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.resourcemanagementWbs.wbsspare1')"
              for="resourcemanagement-wbs-wbsspare1"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare1"
              id="resourcemanagement-wbs-wbsspare1"
              data-cy="wbsspare1"
              :class="{ valid: !v$.wbsspare1.$invalid, invalid: v$.wbsspare1.$invalid }"
              v-model="v$.wbsspare1.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.resourcemanagementWbs.wbsspare2')"
              for="resourcemanagement-wbs-wbsspare2"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare2"
              id="resourcemanagement-wbs-wbsspare2"
              data-cy="wbsspare2"
              :class="{ valid: !v$.wbsspare2.$invalid, invalid: v$.wbsspare2.$invalid }"
              v-model="v$.wbsspare2.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.resourcemanagementWbs.wbsspare3')"
              for="resourcemanagement-wbs-wbsspare3"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare3"
              id="resourcemanagement-wbs-wbsspare3"
              data-cy="wbsspare3"
              :class="{ valid: !v$.wbsspare3.$invalid, invalid: v$.wbsspare3.$invalid }"
              v-model="v$.wbsspare3.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.resourcemanagementWbs.wbsspare4')"
              for="resourcemanagement-wbs-wbsspare4"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare4"
              id="resourcemanagement-wbs-wbsspare4"
              data-cy="wbsspare4"
              :class="{ valid: !v$.wbsspare4.$invalid, invalid: v$.wbsspare4.$invalid }"
              v-model="v$.wbsspare4.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.resourcemanagementWbs.wbsspare5')"
              for="resourcemanagement-wbs-wbsspare5"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare5"
              id="resourcemanagement-wbs-wbsspare5"
              data-cy="wbsspare5"
              :class="{ valid: !v$.wbsspare5.$invalid, invalid: v$.wbsspare5.$invalid }"
              v-model="v$.wbsspare5.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.resourcemanagementWbs.projectHumanresourcesplan')"
              for="resourcemanagement-wbs-projectHumanresourcesplan"
            ></label>
            <select
              class="form-control"
              id="resourcemanagement-wbs-projectHumanresourcesplan"
              data-cy="projectHumanresourcesplan"
              name="projectHumanresourcesplan"
              v-model="resourcemanagementWbs.projectHumanresourcesplan"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  resourcemanagementWbs.projectHumanresourcesplan &&
                  projectHumanresourcesplanOption.id === resourcemanagementWbs.projectHumanresourcesplan.id
                    ? resourcemanagementWbs.projectHumanresourcesplan
                    : projectHumanresourcesplanOption
                "
                v-for="projectHumanresourcesplanOption in projectHumanresourcesplans"
                :key="projectHumanresourcesplanOption.id"
              >
                {{ projectHumanresourcesplanOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.resourcemanagementWbs.projectremit')"
              for="resourcemanagement-wbs-projectremit"
            ></label>
            <select
              class="form-control"
              id="resourcemanagement-wbs-projectremit"
              data-cy="projectremit"
              name="projectremit"
              v-model="resourcemanagementWbs.projectremit"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  resourcemanagementWbs.projectremit && projectremitOption.id === resourcemanagementWbs.projectremit.id
                    ? resourcemanagementWbs.projectremit
                    : projectremitOption
                "
                v-for="projectremitOption in projectremits"
                :key="projectremitOption.id"
              >
                {{ projectremitOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.resourcemanagementWbs.humanresources')"
              for="resourcemanagement-wbs-humanresources"
            ></label>
            <select
              class="form-control"
              id="resourcemanagement-wbs-humanresources"
              data-cy="humanresources"
              name="humanresources"
              v-model="resourcemanagementWbs.humanresources"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  resourcemanagementWbs.humanresources && humanresourcesOption.id === resourcemanagementWbs.humanresources.id
                    ? resourcemanagementWbs.humanresources
                    : humanresourcesOption
                "
                v-for="humanresourcesOption in humanresources"
                :key="humanresourcesOption.id"
              >
                {{ humanresourcesOption.id }}
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
<script lang="ts" src="./resourcemanagement-wbs-update.component.ts"></script>
