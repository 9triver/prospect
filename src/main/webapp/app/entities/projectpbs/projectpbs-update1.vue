<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.projectpbs.home.createOrEditLabel"
          data-cy="ProjectpbsCreateUpdateHeading"
          v-text="t$('jy1App.projectpbs.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="projectpbs.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="projectpbs.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.pbsname')" for="projectpbs-pbsname"></label>
            <input
              type="text"
              class="form-control"
              name="pbsname"
              id="projectpbs-pbsname"
              data-cy="pbsname"
              :class="{ valid: !v$.pbsname.$invalid, invalid: v$.pbsname.$invalid }"
              v-model="v$.pbsname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.parentpbsid')" for="projectpbs-parentpbsid"></label>
            <input
              type="text"
              class="form-control"
              name="parentpbsid"
              id="projectpbs-parentpbsid"
              data-cy="parentpbsid"
              :class="{ valid: !v$.parentpbsid.$invalid, invalid: v$.parentpbsid.$invalid }"
              v-model="v$.parentpbsid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.secretlevel')" for="projectpbs-secretlevel"></label>
            <select
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="projectpbs-secretlevel"
              data-cy="secretlevel"
            >
              <option
                v-for="secretlevel in secretlevelValues"
                :key="secretlevel"
                v-bind:value="secretlevel"
                v-bind:label="t$('jy1App.Secretlevel.' + secretlevel)"
              >
                {{ secretlevel }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.starttime')" for="projectpbs-starttime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="projectpbs-starttime"
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
                id="projectpbs-starttime"
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
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.endtime')" for="projectpbs-endtime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="projectpbs-endtime"
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
                id="projectpbs-endtime"
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
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.productlevel')" for="projectpbs-productlevel"></label>
            <input
              type="number"
              class="form-control"
              name="productlevel"
              id="projectpbs-productlevel"
              data-cy="productlevel"
              :class="{ valid: !v$.productlevel.$invalid, invalid: v$.productlevel.$invalid }"
              v-model.number="v$.productlevel.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.ifkey')" for="projectpbs-ifkey"></label>
            <input
              type="number"
              class="form-control"
              name="ifkey"
              id="projectpbs-ifkey"
              data-cy="ifkey"
              :class="{ valid: !v$.ifkey.$invalid, invalid: v$.ifkey.$invalid }"
              v-model.number="v$.ifkey.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.ifimporttant')" for="projectpbs-ifimporttant"></label>
            <input
              type="number"
              class="form-control"
              name="ifimporttant"
              id="projectpbs-ifimporttant"
              data-cy="ifimporttant"
              :class="{ valid: !v$.ifimporttant.$invalid, invalid: v$.ifimporttant.$invalid }"
              v-model.number="v$.ifimporttant.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.description')" for="projectpbs-description"></label>
            <input
              type="text"
              class="form-control"
              name="description"
              id="projectpbs-description"
              data-cy="description"
              :class="{ valid: !v$.description.$invalid, invalid: v$.description.$invalid }"
              v-model="v$.description.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.progress')" for="projectpbs-progress"></label>
            <input
              type="number"
              class="form-control"
              name="progress"
              id="projectpbs-progress"
              data-cy="progress"
              :class="{ valid: !v$.progress.$invalid, invalid: v$.progress.$invalid }"
              v-model.number="v$.progress.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.type')" for="projectpbs-type"></label>
            <input
              type="number"
              class="form-control"
              name="type"
              id="projectpbs-type"
              data-cy="type"
              :class="{ valid: !v$.type.$invalid, invalid: v$.type.$invalid }"
              v-model.number="v$.type.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.priorty')" for="projectpbs-priorty"></label>
            <input
              type="number"
              class="form-control"
              name="priorty"
              id="projectpbs-priorty"
              data-cy="priorty"
              :class="{ valid: !v$.priorty.$invalid, invalid: v$.priorty.$invalid }"
              v-model.number="v$.priorty.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.status')" for="projectpbs-status"></label>
            <select
              class="form-control"
              name="status"
              :class="{ valid: !v$.status.$invalid, invalid: v$.status.$invalid }"
              v-model="v$.status.$model"
              id="projectpbs-status"
              data-cy="status"
            >
              <option
                v-for="projectStatus in projectStatusValues"
                :key="projectStatus"
                v-bind:value="projectStatus"
                v-bind:label="t$('jy1App.ProjectStatus.' + projectStatus)"
              >
                {{ projectStatus }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.auditStatus')" for="projectpbs-auditStatus"></label>
            <select
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="projectpbs-auditStatus"
              data-cy="auditStatus"
            >
              <option
                v-for="auditStatus in auditStatusValues"
                :key="auditStatus"
                v-bind:value="auditStatus"
                v-bind:label="t$('jy1App.AuditStatus.' + auditStatus)"
              >
                {{ auditStatus }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.technicaldirector')" for="projectpbs-technicaldirector"></label>
            <select
              class="form-control"
              id="projectpbs-technicaldirector"
              data-cy="technicaldirector"
              name="technicaldirector"
              v-model="projectpbs.technicaldirector"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  projectpbs.technicaldirector && officersOption.id === projectpbs.technicaldirector.id
                    ? projectpbs.technicaldirector
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
              v-text="t$('jy1App.projectpbs.administrativedirector')"
              for="projectpbs-administrativedirector"
            ></label>
            <select
              class="form-control"
              id="projectpbs-administrativedirector"
              data-cy="administrativedirector"
              name="administrativedirector"
              v-model="projectpbs.administrativedirector"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  projectpbs.administrativedirector && officersOption.id === projectpbs.administrativedirector.id
                    ? projectpbs.administrativedirector
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
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.knowingpeople')" for="projectpbs-knowingpeople"></label>
            <select
              class="form-control"
              id="projectpbs-knowingpeople"
              data-cy="knowingpeople"
              name="knowingpeople"
              v-model="projectpbs.knowingpeople"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  projectpbs.knowingpeople && officersOption.id === projectpbs.knowingpeople.id ? projectpbs.knowingpeople : officersOption
                "
                v-for="officersOption in officers"
                :key="officersOption.id"
              >
                {{ officersOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.auditorid')" for="projectpbs-auditorid"></label>
            <select class="form-control" id="projectpbs-auditorid" data-cy="auditorid" name="auditorid" v-model="projectpbs.auditorid">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="projectpbs.auditorid && officersOption.id === projectpbs.auditorid.id ? projectpbs.auditorid : officersOption"
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
              v-text="t$('jy1App.projectpbs.responsibledepartment')"
              for="projectpbs-responsibledepartment"
            ></label>
            <select
              class="form-control"
              id="projectpbs-responsibledepartment"
              data-cy="responsibledepartment"
              name="responsibledepartment"
              v-model="projectpbs.responsibledepartment"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  projectpbs.responsibledepartment && departmentOption.id === projectpbs.responsibledepartment.id
                    ? projectpbs.responsibledepartment
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
              v-text="t$('jy1App.projectpbs.relevantdepartment')"
              for="projectpbs-relevantdepartment"
            ></label>
            <select
              class="form-control"
              id="projectpbs-relevantdepartment"
              data-cy="relevantdepartment"
              name="relevantdepartment"
              v-model="projectpbs.relevantdepartment"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  projectpbs.relevantdepartment && departmentOption.id === projectpbs.relevantdepartment.id
                    ? projectpbs.relevantdepartment
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
            <label v-text="t$('jy1App.projectpbs.projectwbs')" for="projectpbs-projectwbs"></label>
            <select
              class="form-control"
              id="projectpbs-projectwbs"
              data-cy="projectwbs"
              multiple
              name="projectwbs"
              v-if="projectpbs.projectwbs !== undefined"
              v-model="projectpbs.projectwbs"
            >
              <option
                v-bind:value="getSelected(projectpbs.projectwbs, projectwbsOption, 'id')"
                v-for="projectwbsOption in projectwbs"
                :key="projectwbsOption.id"
              >
                {{ projectwbsOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.projectpbs.project')" for="projectpbs-project"></label>
            <select
              class="form-control"
              id="projectpbs-projects"
              data-cy="project"
              multiple
              name="project"
              v-if="projectpbs.projects !== undefined"
              v-model="projectpbs.projects"
            >
              <option
                v-bind:value="getSelected(projectpbs.projects, projectOption, 'id')"
                v-for="projectOption in projects"
                :key="projectOption.id"
              >
                {{ projectOption.id }}
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
<script lang="ts" src="./projectpbs-update.component.ts"></script>
