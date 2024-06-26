<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster0App.comprehensivecontrol.home.createOrEditLabel"
          data-cy="ComprehensivecontrolCreateUpdateHeading"
          v-text="t$('jHipster0App.comprehensivecontrol.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="comprehensivecontrol.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="comprehensivecontrol.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.comprehensivecontrol.description')"
              for="comprehensivecontrol-description"
            ></label>
            <input
              type="text"
              class="form-control"
              name="description"
              id="comprehensivecontrol-description"
              data-cy="description"
              :class="{ valid: !v$.description.$invalid, invalid: v$.description.$invalid }"
              v-model="v$.description.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.comprehensivecontrol.number')"
              for="comprehensivecontrol-number"
            ></label>
            <input
              type="number"
              class="form-control"
              name="number"
              id="comprehensivecontrol-number"
              data-cy="number"
              :class="{ valid: !v$.number.$invalid, invalid: v$.number.$invalid }"
              v-model.number="v$.number.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.comprehensivecontrol.type')" for="comprehensivecontrol-type"></label>
            <input
              type="number"
              class="form-control"
              name="type"
              id="comprehensivecontrol-type"
              data-cy="type"
              :class="{ valid: !v$.type.$invalid, invalid: v$.type.$invalid }"
              v-model.number="v$.type.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.comprehensivecontrol.starttime')"
              for="comprehensivecontrol-starttime"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="comprehensivecontrol-starttime"
                  v-model="v$.starttime.$model"
                  name="starttime"
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
                id="comprehensivecontrol-starttime"
                data-cy="starttime"
                type="text"
                class="form-control"
                name="starttime"
                :class="{ valid: !v$.starttime.$invalid, invalid: v$.starttime.$invalid }"
                v-model="v$.starttime.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.comprehensivecontrol.endtime')"
              for="comprehensivecontrol-endtime"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="comprehensivecontrol-endtime"
                  v-model="v$.endtime.$model"
                  name="endtime"
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
                id="comprehensivecontrol-endtime"
                data-cy="endtime"
                type="text"
                class="form-control"
                name="endtime"
                :class="{ valid: !v$.endtime.$invalid, invalid: v$.endtime.$invalid }"
                v-model="v$.endtime.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.comprehensivecontrol.actualstarttime')"
              for="comprehensivecontrol-actualstarttime"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="comprehensivecontrol-actualstarttime"
                  v-model="v$.actualstarttime.$model"
                  name="actualstarttime"
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
                id="comprehensivecontrol-actualstarttime"
                data-cy="actualstarttime"
                type="text"
                class="form-control"
                name="actualstarttime"
                :class="{ valid: !v$.actualstarttime.$invalid, invalid: v$.actualstarttime.$invalid }"
                v-model="v$.actualstarttime.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.comprehensivecontrol.actualendtime')"
              for="comprehensivecontrol-actualendtime"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="comprehensivecontrol-actualendtime"
                  v-model="v$.actualendtime.$model"
                  name="actualendtime"
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
                id="comprehensivecontrol-actualendtime"
                data-cy="actualendtime"
                type="text"
                class="form-control"
                name="actualendtime"
                :class="{ valid: !v$.actualendtime.$invalid, invalid: v$.actualendtime.$invalid }"
                v-model="v$.actualendtime.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.comprehensivecontrol.result')"
              for="comprehensivecontrol-result"
            ></label>
            <input
              type="text"
              class="form-control"
              name="result"
              id="comprehensivecontrol-result"
              data-cy="result"
              :class="{ valid: !v$.result.$invalid, invalid: v$.result.$invalid }"
              v-model="v$.result.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.comprehensivecontrol.acceptancetype')"
              for="comprehensivecontrol-acceptancetype"
            ></label>
            <input
              type="text"
              class="form-control"
              name="acceptancetype"
              id="comprehensivecontrol-acceptancetype"
              data-cy="acceptancetype"
              :class="{ valid: !v$.acceptancetype.$invalid, invalid: v$.acceptancetype.$invalid }"
              v-model="v$.acceptancetype.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.comprehensivecontrol.status')"
              for="comprehensivecontrol-status"
            ></label>
            <select
              class="form-control"
              name="status"
              :class="{ valid: !v$.status.$invalid, invalid: v$.status.$invalid }"
              v-model="v$.status.$model"
              id="comprehensivecontrol-status"
              data-cy="status"
            >
              <option
                v-for="projectStatus in projectStatusValues"
                :key="projectStatus"
                v-bind:value="projectStatus"
                v-bind:label="t$('jHipster0App.ProjectStatus.' + projectStatus)"
              >
                {{ projectStatus }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.comprehensivecontrol.auditStatus')"
              for="comprehensivecontrol-auditStatus"
            ></label>
            <select
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="comprehensivecontrol-auditStatus"
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
              v-text="t$('jHipster0App.comprehensivecontrol.responsiblename')"
              for="comprehensivecontrol-responsiblename"
            ></label>
            <input
              type="text"
              class="form-control"
              name="responsiblename"
              id="comprehensivecontrol-responsiblename"
              data-cy="responsiblename"
              :class="{ valid: !v$.responsiblename.$invalid, invalid: v$.responsiblename.$invalid }"
              v-model="v$.responsiblename.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.comprehensivecontrol.progress')"
              for="comprehensivecontrol-progress"
            ></label>
            <select
              class="form-control"
              id="comprehensivecontrol-progress"
              data-cy="progress"
              name="progress"
              v-model="comprehensivecontrol.progress"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  comprehensivecontrol.progress && progressplanOption.id === comprehensivecontrol.progress.id
                    ? comprehensivecontrol.progress
                    : progressplanOption
                "
                v-for="progressplanOption in progressplans"
                :key="progressplanOption.id"
              >
                {{ progressplanOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.comprehensivecontrol.project')"
              for="comprehensivecontrol-project"
            ></label>
            <select
              class="form-control"
              id="comprehensivecontrol-project"
              data-cy="project"
              name="project"
              v-model="comprehensivecontrol.project"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  comprehensivecontrol.project && projectOption.id === comprehensivecontrol.project.id
                    ? comprehensivecontrol.project
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
              v-text="t$('jHipster0App.comprehensivecontrol.funds')"
              for="comprehensivecontrol-funds"
            ></label>
            <select class="form-control" id="comprehensivecontrol-funds" data-cy="funds" name="funds" v-model="comprehensivecontrol.funds">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  comprehensivecontrol.funds && fundsmanagementOption.id === comprehensivecontrol.funds.id
                    ? comprehensivecontrol.funds
                    : fundsmanagementOption
                "
                v-for="fundsmanagementOption in fundsmanagements"
                :key="fundsmanagementOption.id"
              >
                {{ fundsmanagementOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.comprehensivecontrol.totalbudget')"
              for="comprehensivecontrol-totalbudget"
            ></label>
            <select
              class="form-control"
              id="comprehensivecontrol-totalbudget"
              data-cy="totalbudget"
              name="totalbudget"
              v-model="comprehensivecontrol.totalbudget"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  comprehensivecontrol.totalbudget && totalbudgetOption.id === comprehensivecontrol.totalbudget.id
                    ? comprehensivecontrol.totalbudget
                    : totalbudgetOption
                "
                v-for="totalbudgetOption in totalbudgets"
                :key="totalbudgetOption.id"
              >
                {{ totalbudgetOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.comprehensivecontrol.unitbudget')"
              for="comprehensivecontrol-unitbudget"
            ></label>
            <select
              class="form-control"
              id="comprehensivecontrol-unitbudget"
              data-cy="unitbudget"
              name="unitbudget"
              v-model="comprehensivecontrol.unitbudget"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  comprehensivecontrol.unitbudget && unitbudgetOption.id === comprehensivecontrol.unitbudget.id
                    ? comprehensivecontrol.unitbudget
                    : unitbudgetOption
                "
                v-for="unitbudgetOption in unitbudgets"
                :key="unitbudgetOption.id"
              >
                {{ unitbudgetOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.comprehensivecontrol.responsibleid')"
              for="comprehensivecontrol-responsibleid"
            ></label>
            <select
              class="form-control"
              id="comprehensivecontrol-responsibleid"
              data-cy="responsibleid"
              name="responsibleid"
              v-model="comprehensivecontrol.responsibleid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  comprehensivecontrol.responsibleid && officersOption.id === comprehensivecontrol.responsibleid.id
                    ? comprehensivecontrol.responsibleid
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
              v-text="t$('jHipster0App.comprehensivecontrol.auditorid')"
              for="comprehensivecontrol-auditorid"
            ></label>
            <select
              class="form-control"
              id="comprehensivecontrol-auditorid"
              data-cy="auditorid"
              name="auditorid"
              v-model="comprehensivecontrol.auditorid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  comprehensivecontrol.auditorid && officersOption.id === comprehensivecontrol.auditorid.id
                    ? comprehensivecontrol.auditorid
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
              v-text="t$('jHipster0App.comprehensivecontrol.decument')"
              for="comprehensivecontrol-decument"
            ></label>
            <select
              class="form-control"
              id="comprehensivecontrol-decument"
              data-cy="decument"
              name="decument"
              v-model="comprehensivecontrol.decument"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  comprehensivecontrol.decument && departmentOption.id === comprehensivecontrol.decument.id
                    ? comprehensivecontrol.decument
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
              v-text="t$('jHipster0App.comprehensivecontrol.coordinationdepartment')"
              for="comprehensivecontrol-coordinationdepartment"
            ></label>
            <select
              class="form-control"
              id="comprehensivecontrol-coordinationdepartment"
              data-cy="coordinationdepartment"
              name="coordinationdepartment"
              v-model="comprehensivecontrol.coordinationdepartment"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  comprehensivecontrol.coordinationdepartment && departmentOption.id === comprehensivecontrol.coordinationdepartment.id
                    ? comprehensivecontrol.coordinationdepartment
                    : departmentOption
                "
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
<script lang="ts" src="./comprehensivecontrol-update.component.ts"></script>
