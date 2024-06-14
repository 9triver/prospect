<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster3App.technicalCondition.home.createOrEditLabel"
          data-cy="TechnicalConditionCreateUpdateHeading"
          v-text="t$('jHipster3App.technicalCondition.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="technicalCondition.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="technicalCondition.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.technicalCondition.caption')"
              for="technical-condition-caption"
            ></label>
            <input
              type="text"
              class="form-control"
              name="caption"
              id="technical-condition-caption"
              data-cy="caption"
              :class="{ valid: !v$.caption.$invalid, invalid: v$.caption.$invalid }"
              v-model="v$.caption.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.technicalCondition.projectname')"
              for="technical-condition-projectname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="projectname"
              id="technical-condition-projectname"
              data-cy="projectname"
              :class="{ valid: !v$.projectname.$invalid, invalid: v$.projectname.$invalid }"
              v-model="v$.projectname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.technicalCondition.decumentid')"
              for="technical-condition-decumentid"
            ></label>
            <input
              type="number"
              class="form-control"
              name="decumentid"
              id="technical-condition-decumentid"
              data-cy="decumentid"
              :class="{ valid: !v$.decumentid.$invalid, invalid: v$.decumentid.$invalid }"
              v-model.number="v$.decumentid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.technicalCondition.claimant')"
              for="technical-condition-claimant"
            ></label>
            <input
              type="text"
              class="form-control"
              name="claimant"
              id="technical-condition-claimant"
              data-cy="claimant"
              :class="{ valid: !v$.claimant.$invalid, invalid: v$.claimant.$invalid }"
              v-model="v$.claimant.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.technicalCondition.applicant')"
              for="technical-condition-applicant"
            ></label>
            <input
              type="text"
              class="form-control"
              name="applicant"
              id="technical-condition-applicant"
              data-cy="applicant"
              :class="{ valid: !v$.applicant.$invalid, invalid: v$.applicant.$invalid }"
              v-model="v$.applicant.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.technicalCondition.applicanttime')"
              for="technical-condition-applicanttime"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="technical-condition-applicanttime"
                  v-model="v$.applicanttime.$model"
                  name="applicanttime"
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
                id="technical-condition-applicanttime"
                data-cy="applicanttime"
                type="text"
                class="form-control"
                name="applicanttime"
                :class="{ valid: !v$.applicanttime.$invalid, invalid: v$.applicanttime.$invalid }"
                v-model="v$.applicanttime.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.technicalCondition.validrange')"
              for="technical-condition-validrange"
            ></label>
            <input
              type="text"
              class="form-control"
              name="validrange"
              id="technical-condition-validrange"
              data-cy="validrange"
              :class="{ valid: !v$.validrange.$invalid, invalid: v$.validrange.$invalid }"
              v-model="v$.validrange.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.technicalCondition.createtime')"
              for="technical-condition-createtime"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="technical-condition-createtime"
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
                id="technical-condition-createtime"
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
              v-text="t$('jHipster3App.technicalCondition.auditStatus')"
              for="technical-condition-auditStatus"
            ></label>
            <select
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="technical-condition-auditStatus"
              data-cy="auditStatus"
            >
              <option
                v-for="auditStatus in auditStatusValues"
                :key="auditStatus"
                v-bind:value="auditStatus"
                v-bind:label="t$('jHipster3App.AuditStatus.' + auditStatus)"
              >
                {{ auditStatus }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.technicalCondition.creatorid')"
              for="technical-condition-creatorid"
            ></label>
            <select
              class="form-control"
              id="technical-condition-creatorid"
              data-cy="creatorid"
              name="creatorid"
              v-model="technicalCondition.creatorid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  technicalCondition.creatorid && officersOption.id === technicalCondition.creatorid.id
                    ? technicalCondition.creatorid
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
              v-text="t$('jHipster3App.technicalCondition.auditorid')"
              for="technical-condition-auditorid"
            ></label>
            <select
              class="form-control"
              id="technical-condition-auditorid"
              data-cy="auditorid"
              name="auditorid"
              v-model="technicalCondition.auditorid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  technicalCondition.auditorid && officersOption.id === technicalCondition.auditorid.id
                    ? technicalCondition.auditorid
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
<script lang="ts" src="./technical-condition-update.component.ts"></script>
