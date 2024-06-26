<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster0App.wbsmanage.home.createOrEditLabel"
          data-cy="WbsmanageCreateUpdateHeading"
          v-text="t$('jHipster0App.wbsmanage.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="wbsmanage.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="wbsmanage.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.wbsmanage.wbsname')" for="wbsmanage-wbsname"></label>
            <input
              type="text"
              class="form-control"
              name="wbsname"
              id="wbsmanage-wbsname"
              data-cy="wbsname"
              :class="{ valid: !v$.wbsname.$invalid, invalid: v$.wbsname.$invalid }"
              v-model="v$.wbsname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.wbsmanage.description')" for="wbsmanage-description"></label>
            <input
              type="text"
              class="form-control"
              name="description"
              id="wbsmanage-description"
              data-cy="description"
              :class="{ valid: !v$.description.$invalid, invalid: v$.description.$invalid }"
              v-model="v$.description.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.wbsmanage.result')" for="wbsmanage-result"></label>
            <input
              type="text"
              class="form-control"
              name="result"
              id="wbsmanage-result"
              data-cy="result"
              :class="{ valid: !v$.result.$invalid, invalid: v$.result.$invalid }"
              v-model="v$.result.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.wbsmanage.administratorname')"
              for="wbsmanage-administratorname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="administratorname"
              id="wbsmanage-administratorname"
              data-cy="administratorname"
              :class="{ valid: !v$.administratorname.$invalid, invalid: v$.administratorname.$invalid }"
              v-model="v$.administratorname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.wbsmanage.responsiblename')" for="wbsmanage-responsiblename"></label>
            <input
              type="text"
              class="form-control"
              name="responsiblename"
              id="wbsmanage-responsiblename"
              data-cy="responsiblename"
              :class="{ valid: !v$.responsiblename.$invalid, invalid: v$.responsiblename.$invalid }"
              v-model="v$.responsiblename.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.wbsmanage.department')" for="wbsmanage-department"></label>
            <input
              type="text"
              class="form-control"
              name="department"
              id="wbsmanage-department"
              data-cy="department"
              :class="{ valid: !v$.department.$invalid, invalid: v$.department.$invalid }"
              v-model="v$.department.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.wbsmanage.secretlevel')" for="wbsmanage-secretlevel"></label>
            <select
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="wbsmanage-secretlevel"
              data-cy="secretlevel"
            >
              <option
                v-for="secretlevel in secretlevelValues"
                :key="secretlevel"
                v-bind:value="secretlevel"
                v-bind:label="t$('jHipster0App.Secretlevel.' + secretlevel)"
              >
                {{ secretlevel }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.wbsmanage.auditStatus')" for="wbsmanage-auditStatus"></label>
            <select
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="wbsmanage-auditStatus"
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
            <label class="form-control-label" v-text="t$('jHipster0App.wbsmanage.wbssubmanage')" for="wbsmanage-wbssubmanage"></label>
            <select
              class="form-control"
              id="wbsmanage-wbssubmanage"
              data-cy="wbssubmanage"
              name="wbssubmanage"
              v-model="wbsmanage.wbssubmanage"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  wbsmanage.wbssubmanage && wbssubmanageOption.id === wbsmanage.wbssubmanage.id
                    ? wbsmanage.wbssubmanage
                    : wbssubmanageOption
                "
                v-for="wbssubmanageOption in wbssubmanages"
                :key="wbssubmanageOption.id"
              >
                {{ wbssubmanageOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.wbsmanage.pbssubmanage')" for="wbsmanage-pbssubmanage"></label>
            <select
              class="form-control"
              id="wbsmanage-pbssubmanage"
              data-cy="pbssubmanage"
              name="pbssubmanage"
              v-model="wbsmanage.pbssubmanage"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  wbsmanage.pbssubmanage && pbssubmanageOption.id === wbsmanage.pbssubmanage.id
                    ? wbsmanage.pbssubmanage
                    : pbssubmanageOption
                "
                v-for="pbssubmanageOption in pbssubmanages"
                :key="pbssubmanageOption.id"
              >
                {{ pbssubmanageOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.wbsmanage.project')" for="wbsmanage-project"></label>
            <select class="form-control" id="wbsmanage-project" data-cy="project" name="project" v-model="wbsmanage.project">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="wbsmanage.project && projectOption.id === wbsmanage.project.id ? wbsmanage.project : projectOption"
                v-for="projectOption in projects"
                :key="projectOption.id"
              >
                {{ projectOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.wbsmanage.administratorid')" for="wbsmanage-administratorid"></label>
            <select
              class="form-control"
              id="wbsmanage-administratorid"
              data-cy="administratorid"
              name="administratorid"
              v-model="wbsmanage.administratorid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  wbsmanage.administratorid && officersOption.id === wbsmanage.administratorid.id
                    ? wbsmanage.administratorid
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
            <label class="form-control-label" v-text="t$('jHipster0App.wbsmanage.auditorid')" for="wbsmanage-auditorid"></label>
            <select class="form-control" id="wbsmanage-auditorid" data-cy="auditorid" name="auditorid" v-model="wbsmanage.auditorid">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="wbsmanage.auditorid && officersOption.id === wbsmanage.auditorid.id ? wbsmanage.auditorid : officersOption"
                v-for="officersOption in officers"
                :key="officersOption.id"
              >
                {{ officersOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.wbsmanage.responsibleid')" for="wbsmanage-responsibleid"></label>
            <select
              class="form-control"
              id="wbsmanage-responsibleid"
              data-cy="responsibleid"
              name="responsibleid"
              v-model="wbsmanage.responsibleid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  wbsmanage.responsibleid && officersOption.id === wbsmanage.responsibleid.id ? wbsmanage.responsibleid : officersOption
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
<script lang="ts" src="./wbsmanage-update.component.ts"></script>
