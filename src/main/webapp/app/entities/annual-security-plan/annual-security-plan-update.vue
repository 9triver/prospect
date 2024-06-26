<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster0App.annualSecurityPlan.home.createOrEditLabel"
          data-cy="AnnualSecurityPlanCreateUpdateHeading"
          v-text="t$('jHipster0App.annualSecurityPlan.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="annualSecurityPlan.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="annualSecurityPlan.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.annualSecurityPlan.securityplanname')"
              for="annual-security-plan-securityplanname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="securityplanname"
              id="annual-security-plan-securityplanname"
              data-cy="securityplanname"
              :class="{ valid: !v$.securityplanname.$invalid, invalid: v$.securityplanname.$invalid }"
              v-model="v$.securityplanname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.annualSecurityPlan.releasetime')"
              for="annual-security-plan-releasetime"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="annual-security-plan-releasetime"
                  v-model="v$.releasetime.$model"
                  name="releasetime"
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
                id="annual-security-plan-releasetime"
                data-cy="releasetime"
                type="text"
                class="form-control"
                name="releasetime"
                :class="{ valid: !v$.releasetime.$invalid, invalid: v$.releasetime.$invalid }"
                v-model="v$.releasetime.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.annualSecurityPlan.createtime')"
              for="annual-security-plan-createtime"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="annual-security-plan-createtime"
                  v-model="v$.createtime.$model"
                  name="createtime"
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
                id="annual-security-plan-createtime"
                data-cy="createtime"
                type="text"
                class="form-control"
                name="createtime"
                :class="{ valid: !v$.createtime.$invalid, invalid: v$.createtime.$invalid }"
                v-model="v$.createtime.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.annualSecurityPlan.creatorname')"
              for="annual-security-plan-creatorname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="creatorname"
              id="annual-security-plan-creatorname"
              data-cy="creatorname"
              :class="{ valid: !v$.creatorname.$invalid, invalid: v$.creatorname.$invalid }"
              v-model="v$.creatorname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.annualSecurityPlan.auditStatus')"
              for="annual-security-plan-auditStatus"
            ></label>
            <select
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="annual-security-plan-auditStatus"
              data-cy="auditStatus"
            >
              <option
                v-for="auditStatus in auditStatusValues"
                :key="auditStatus"
                v-bind:value="auditStatus"
                v-bind:label="t$('jHipster0App.AuditStatus.' + auditStatus)"
              >
                {{ auditStatus }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.annualSecurityPlan.version')"
              for="annual-security-plan-version"
            ></label>
            <input
              type="number"
              class="form-control"
              name="version"
              id="annual-security-plan-version"
              data-cy="version"
              :class="{ valid: !v$.version.$invalid, invalid: v$.version.$invalid }"
              v-model.number="v$.version.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.annualSecurityPlan.project')"
              for="annual-security-plan-project"
            ></label>
            <select
              class="form-control"
              id="annual-security-plan-project"
              data-cy="project"
              name="project"
              v-model="annualSecurityPlan.project"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  annualSecurityPlan.project && projectOption.id === annualSecurityPlan.project.id
                    ? annualSecurityPlan.project
                    : projectOption
                "
                v-for="projectOption in projects"
                :key="projectOption.id"
              >
                {{ projectOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.annualSecurityPlan.creatorid')"
              for="annual-security-plan-creatorid"
            ></label>
            <select
              class="form-control"
              id="annual-security-plan-creatorid"
              data-cy="creatorid"
              name="creatorid"
              v-model="annualSecurityPlan.creatorid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  annualSecurityPlan.creatorid && officersOption.id === annualSecurityPlan.creatorid.id
                    ? annualSecurityPlan.creatorid
                    : officersOption
                "
                v-for="officersOption in officers"
                :key="officersOption.id"
              >
                {{ officersOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.annualSecurityPlan.auditorid')"
              for="annual-security-plan-auditorid"
            ></label>
            <select
              class="form-control"
              id="annual-security-plan-auditorid"
              data-cy="auditorid"
              name="auditorid"
              v-model="annualSecurityPlan.auditorid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  annualSecurityPlan.auditorid && officersOption.id === annualSecurityPlan.auditorid.id
                    ? annualSecurityPlan.auditorid
                    : officersOption
                "
                v-for="officersOption in officers"
                :key="officersOption.id"
              >
                {{ officersOption.id }}
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
<script lang="ts" src="./annual-security-plan-update.component.ts"></script>
