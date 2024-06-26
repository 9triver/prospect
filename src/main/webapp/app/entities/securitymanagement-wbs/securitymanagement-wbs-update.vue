<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster0App.securitymanagementWbs.home.createOrEditLabel"
          data-cy="SecuritymanagementWbsCreateUpdateHeading"
          v-text="t$('jHipster0App.securitymanagementWbs.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="securitymanagementWbs.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="securitymanagementWbs.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.securitymanagementWbs.wbsspare1')"
              for="securitymanagement-wbs-wbsspare1"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare1"
              id="securitymanagement-wbs-wbsspare1"
              data-cy="wbsspare1"
              :class="{ valid: !v$.wbsspare1.$invalid, invalid: v$.wbsspare1.$invalid }"
              v-model="v$.wbsspare1.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.securitymanagementWbs.wbsspare2')"
              for="securitymanagement-wbs-wbsspare2"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare2"
              id="securitymanagement-wbs-wbsspare2"
              data-cy="wbsspare2"
              :class="{ valid: !v$.wbsspare2.$invalid, invalid: v$.wbsspare2.$invalid }"
              v-model="v$.wbsspare2.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.securitymanagementWbs.wbsspare3')"
              for="securitymanagement-wbs-wbsspare3"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare3"
              id="securitymanagement-wbs-wbsspare3"
              data-cy="wbsspare3"
              :class="{ valid: !v$.wbsspare3.$invalid, invalid: v$.wbsspare3.$invalid }"
              v-model="v$.wbsspare3.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.securitymanagementWbs.wbsspare4')"
              for="securitymanagement-wbs-wbsspare4"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare4"
              id="securitymanagement-wbs-wbsspare4"
              data-cy="wbsspare4"
              :class="{ valid: !v$.wbsspare4.$invalid, invalid: v$.wbsspare4.$invalid }"
              v-model="v$.wbsspare4.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.securitymanagementWbs.wbsspare5')"
              for="securitymanagement-wbs-wbsspare5"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare5"
              id="securitymanagement-wbs-wbsspare5"
              data-cy="wbsspare5"
              :class="{ valid: !v$.wbsspare5.$invalid, invalid: v$.wbsspare5.$invalid }"
              v-model="v$.wbsspare5.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.securitymanagementWbs.annualSecurityPlan')"
              for="securitymanagement-wbs-annualSecurityPlan"
            ></label>
            <select
              class="form-control"
              id="securitymanagement-wbs-annualSecurityPlan"
              data-cy="annualSecurityPlan"
              name="annualSecurityPlan"
              v-model="securitymanagementWbs.annualSecurityPlan"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  securitymanagementWbs.annualSecurityPlan && annualSecurityPlanOption.id === securitymanagementWbs.annualSecurityPlan.id
                    ? securitymanagementWbs.annualSecurityPlan
                    : annualSecurityPlanOption
                "
                v-for="annualSecurityPlanOption in annualSecurityPlans"
                :key="annualSecurityPlanOption.id"
              >
                {{ annualSecurityPlanOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.securitymanagementWbs.safetycheck')"
              for="securitymanagement-wbs-safetycheck"
            ></label>
            <select
              class="form-control"
              id="securitymanagement-wbs-safetycheck"
              data-cy="safetycheck"
              name="safetycheck"
              v-model="securitymanagementWbs.safetycheck"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  securitymanagementWbs.safetycheck && safetycheckOption.id === securitymanagementWbs.safetycheck.id
                    ? securitymanagementWbs.safetycheck
                    : safetycheckOption
                "
                v-for="safetycheckOption in safetychecks"
                :key="safetycheckOption.id"
              >
                {{ safetycheckOption.id }}
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
<script lang="ts" src="./securitymanagement-wbs-update.component.ts"></script>
