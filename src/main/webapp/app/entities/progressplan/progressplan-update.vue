<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster0App.progressplan.home.createOrEditLabel"
          data-cy="ProgressplanCreateUpdateHeading"
          v-text="t$('jHipster0App.progressplan.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="progressplan.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="progressplan.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.progressplan.progressname')" for="progressplan-progressname"></label>
            <input
              type="text"
              class="form-control"
              name="progressname"
              id="progressplan-progressname"
              data-cy="progressname"
              :class="{ valid: !v$.progressname.$invalid, invalid: v$.progressname.$invalid }"
              v-model="v$.progressname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.progressplan.progresstype')" for="progressplan-progresstype"></label>
            <select
              class="form-control"
              name="progresstype"
              :class="{ valid: !v$.progresstype.$invalid, invalid: v$.progresstype.$invalid }"
              v-model="v$.progresstype.$model"
              id="progressplan-progresstype"
              data-cy="progresstype"
            >
              <option
                v-for="progresstype in progresstypeValues"
                :key="progresstype"
                v-bind:value="progresstype"
                v-bind:label="t$('jHipster0App.Progresstype.' + progresstype)"
              >
                {{ progresstype }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.progressplan.workfocus')" for="progressplan-workfocus"></label>
            <input
              type="text"
              class="form-control"
              name="workfocus"
              id="progressplan-workfocus"
              data-cy="workfocus"
              :class="{ valid: !v$.workfocus.$invalid, invalid: v$.workfocus.$invalid }"
              v-model="v$.workfocus.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.progressplan.createtime')" for="progressplan-createtime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="progressplan-createtime"
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
                id="progressplan-createtime"
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
            <label class="form-control-label" v-text="t$('jHipster0App.progressplan.creatorname')" for="progressplan-creatorname"></label>
            <input
              type="text"
              class="form-control"
              name="creatorname"
              id="progressplan-creatorname"
              data-cy="creatorname"
              :class="{ valid: !v$.creatorname.$invalid, invalid: v$.creatorname.$invalid }"
              v-model="v$.creatorname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.progressplan.responsiblename')"
              for="progressplan-responsiblename"
            ></label>
            <input
              type="text"
              class="form-control"
              name="responsiblename"
              id="progressplan-responsiblename"
              data-cy="responsiblename"
              :class="{ valid: !v$.responsiblename.$invalid, invalid: v$.responsiblename.$invalid }"
              v-model="v$.responsiblename.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.progressplan.status')" for="progressplan-status"></label>
            <select
              class="form-control"
              name="status"
              :class="{ valid: !v$.status.$invalid, invalid: v$.status.$invalid }"
              v-model="v$.status.$model"
              id="progressplan-status"
              data-cy="status"
            >
              <option
                v-for="progressstatus in progressstatusValues"
                :key="progressstatus"
                v-bind:value="progressstatus"
                v-bind:label="t$('jHipster0App.Progressstatus.' + progressstatus)"
              >
                {{ progressstatus }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.progressplan.auditStatus')" for="progressplan-auditStatus"></label>
            <select
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="progressplan-auditStatus"
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
            <label class="form-control-label" v-text="t$('jHipster0App.progressplan.department')" for="progressplan-department"></label>
            <select
              class="form-control"
              id="progressplan-department"
              data-cy="department"
              name="department"
              v-model="progressplan.department"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  progressplan.department && departmentOption.id === progressplan.department.id ? progressplan.department : departmentOption
                "
                v-for="departmentOption in departments"
                :key="departmentOption.id"
              >
                {{ departmentOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.progressplan.planreturns')" for="progressplan-planreturns"></label>
            <select
              class="form-control"
              id="progressplan-planreturns"
              data-cy="planreturns"
              name="planreturns"
              v-model="progressplan.planreturns"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  progressplan.planreturns && planreturnsOption.id === progressplan.planreturns.id
                    ? progressplan.planreturns
                    : planreturnsOption
                "
                v-for="planreturnsOption in planreturns"
                :key="planreturnsOption.id"
              >
                {{ planreturnsOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.progressplan.responsibleid')"
              for="progressplan-responsibleid"
            ></label>
            <select
              class="form-control"
              id="progressplan-responsibleid"
              data-cy="responsibleid"
              name="responsibleid"
              v-model="progressplan.responsibleid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  progressplan.responsibleid && officersOption.id === progressplan.responsibleid.id
                    ? progressplan.responsibleid
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
            <label class="form-control-label" v-text="t$('jHipster0App.progressplan.creatorid')" for="progressplan-creatorid"></label>
            <select class="form-control" id="progressplan-creatorid" data-cy="creatorid" name="creatorid" v-model="progressplan.creatorid">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  progressplan.creatorid && officersOption.id === progressplan.creatorid.id ? progressplan.creatorid : officersOption
                "
                v-for="officersOption in officers"
                :key="officersOption.id"
              >
                {{ officersOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.progressplan.auditorid')" for="progressplan-auditorid"></label>
            <select class="form-control" id="progressplan-auditorid" data-cy="auditorid" name="auditorid" v-model="progressplan.auditorid">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  progressplan.auditorid && officersOption.id === progressplan.auditorid.id ? progressplan.auditorid : officersOption
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
<script lang="ts" src="./progressplan-update.component.ts"></script>
