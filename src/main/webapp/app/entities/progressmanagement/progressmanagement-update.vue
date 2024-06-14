<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster3App.progressmanagement.home.createOrEditLabel"
          data-cy="ProgressmanagementCreateUpdateHeading"
          v-text="t$('jHipster3App.progressmanagement.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="progressmanagement.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="progressmanagement.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.progressmanagement.progressid')"
              for="progressmanagement-progressid"
            ></label>
            <input
              type="number"
              class="form-control"
              name="progressid"
              id="progressmanagement-progressid"
              data-cy="progressid"
              :class="{ valid: !v$.progressid.$invalid, invalid: v$.progressid.$invalid }"
              v-model.number="v$.progressid.$model"
            />
            <div v-if="v$.progressid.$anyDirty && v$.progressid.$invalid">
              <small class="form-text text-danger" v-for="error of v$.progressid.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.progressmanagement.progressname')"
              for="progressmanagement-progressname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="progressname"
              id="progressmanagement-progressname"
              data-cy="progressname"
              :class="{ valid: !v$.progressname.$invalid, invalid: v$.progressname.$invalid }"
              v-model="v$.progressname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.progressmanagement.progresstype')"
              for="progressmanagement-progresstype"
            ></label>
            <select
              class="form-control"
              name="progresstype"
              :class="{ valid: !v$.progresstype.$invalid, invalid: v$.progresstype.$invalid }"
              v-model="v$.progresstype.$model"
              id="progressmanagement-progresstype"
              data-cy="progresstype"
            >
              <option
                v-for="progresstype in progresstypeValues"
                :key="progresstype"
                v-bind:value="progresstype"
                v-bind:label="t$('jHipster3App.Progresstype.' + progresstype)"
              >
                {{ progresstype }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.progressmanagement.workfocus')"
              for="progressmanagement-workfocus"
            ></label>
            <input
              type="text"
              class="form-control"
              name="workfocus"
              id="progressmanagement-workfocus"
              data-cy="workfocus"
              :class="{ valid: !v$.workfocus.$invalid, invalid: v$.workfocus.$invalid }"
              v-model="v$.workfocus.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.progressmanagement.createtime')"
              for="progressmanagement-createtime"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="progressmanagement-createtime"
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
                id="progressmanagement-createtime"
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
              v-text="t$('jHipster3App.progressmanagement.creatorname')"
              for="progressmanagement-creatorname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="creatorname"
              id="progressmanagement-creatorname"
              data-cy="creatorname"
              :class="{ valid: !v$.creatorname.$invalid, invalid: v$.creatorname.$invalid }"
              v-model="v$.creatorname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.progressmanagement.responsiblename')"
              for="progressmanagement-responsiblename"
            ></label>
            <input
              type="text"
              class="form-control"
              name="responsiblename"
              id="progressmanagement-responsiblename"
              data-cy="responsiblename"
              :class="{ valid: !v$.responsiblename.$invalid, invalid: v$.responsiblename.$invalid }"
              v-model="v$.responsiblename.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.progressmanagement.status')" for="progressmanagement-status"></label>
            <select
              class="form-control"
              name="status"
              :class="{ valid: !v$.status.$invalid, invalid: v$.status.$invalid }"
              v-model="v$.status.$model"
              id="progressmanagement-status"
              data-cy="status"
            >
              <option
                v-for="progressstatus in progressstatusValues"
                :key="progressstatus"
                v-bind:value="progressstatus"
                v-bind:label="t$('jHipster3App.Progressstatus.' + progressstatus)"
              >
                {{ progressstatus }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.progressmanagement.baselineid')"
              for="progressmanagement-baselineid"
            ></label>
            <input
              type="number"
              class="form-control"
              name="baselineid"
              id="progressmanagement-baselineid"
              data-cy="baselineid"
              :class="{ valid: !v$.baselineid.$invalid, invalid: v$.baselineid.$invalid }"
              v-model.number="v$.baselineid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.progressmanagement.auditStatus')"
              for="progressmanagement-auditStatus"
            ></label>
            <select
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="progressmanagement-auditStatus"
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
              v-text="t$('jHipster3App.progressmanagement.department')"
              for="progressmanagement-department"
            ></label>
            <select
              class="form-control"
              id="progressmanagement-department"
              data-cy="department"
              name="department"
              v-model="progressmanagement.department"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  progressmanagement.department && departmentOption.id === progressmanagement.department.id
                    ? progressmanagement.department
                    : departmentOption
                "
                v-for="departmentOption in departments"
                :key="departmentOption.id"
              >
                {{ departmentOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.progressmanagement.planreturns')"
              for="progressmanagement-planreturns"
            ></label>
            <select
              class="form-control"
              id="progressmanagement-planreturns"
              data-cy="planreturns"
              name="planreturns"
              v-model="progressmanagement.planreturns"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  progressmanagement.planreturns && planreturnsOption.id === progressmanagement.planreturns.id
                    ? progressmanagement.planreturns
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
              v-text="t$('jHipster3App.progressmanagement.responsibleid')"
              for="progressmanagement-responsibleid"
            ></label>
            <select
              class="form-control"
              id="progressmanagement-responsibleid"
              data-cy="responsibleid"
              name="responsibleid"
              v-model="progressmanagement.responsibleid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  progressmanagement.responsibleid && officersOption.id === progressmanagement.responsibleid.id
                    ? progressmanagement.responsibleid
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
              v-text="t$('jHipster3App.progressmanagement.creatorid')"
              for="progressmanagement-creatorid"
            ></label>
            <select
              class="form-control"
              id="progressmanagement-creatorid"
              data-cy="creatorid"
              name="creatorid"
              v-model="progressmanagement.creatorid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  progressmanagement.creatorid && officersOption.id === progressmanagement.creatorid.id
                    ? progressmanagement.creatorid
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
              v-text="t$('jHipster3App.progressmanagement.auditorid')"
              for="progressmanagement-auditorid"
            ></label>
            <select
              class="form-control"
              id="progressmanagement-auditorid"
              data-cy="auditorid"
              name="auditorid"
              v-model="progressmanagement.auditorid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  progressmanagement.auditorid && officersOption.id === progressmanagement.auditorid.id
                    ? progressmanagement.auditorid
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
<script lang="ts" src="./progressmanagement-update.component.ts"></script>
