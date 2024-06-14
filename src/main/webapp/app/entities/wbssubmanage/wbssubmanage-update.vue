<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster3App.wbssubmanage.home.createOrEditLabel"
          data-cy="WbssubmanageCreateUpdateHeading"
          v-text="t$('jHipster3App.wbssubmanage.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="wbssubmanage.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="wbssubmanage.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.wbssubmanage.pbssubid')" for="wbssubmanage-pbssubid"></label>
            <input
              type="text"
              class="form-control"
              name="pbssubid"
              id="wbssubmanage-pbssubid"
              data-cy="pbssubid"
              :class="{ valid: !v$.pbssubid.$invalid, invalid: v$.pbssubid.$invalid }"
              v-model="v$.pbssubid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.wbssubmanage.pbssubname')" for="wbssubmanage-pbssubname"></label>
            <input
              type="text"
              class="form-control"
              name="pbssubname"
              id="wbssubmanage-pbssubname"
              data-cy="pbssubname"
              :class="{ valid: !v$.pbssubname.$invalid, invalid: v$.pbssubname.$invalid }"
              v-model="v$.pbssubname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.wbssubmanage.responsiblename')"
              for="wbssubmanage-responsiblename"
            ></label>
            <input
              type="text"
              class="form-control"
              name="responsiblename"
              id="wbssubmanage-responsiblename"
              data-cy="responsiblename"
              :class="{ valid: !v$.responsiblename.$invalid, invalid: v$.responsiblename.$invalid }"
              v-model="v$.responsiblename.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.wbssubmanage.responsibledepartment')"
              for="wbssubmanage-responsibledepartment"
            ></label>
            <input
              type="text"
              class="form-control"
              name="responsibledepartment"
              id="wbssubmanage-responsibledepartment"
              data-cy="responsibledepartment"
              :class="{ valid: !v$.responsibledepartment.$invalid, invalid: v$.responsibledepartment.$invalid }"
              v-model="v$.responsibledepartment.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.wbssubmanage.relevantdepartment')"
              for="wbssubmanage-relevantdepartment"
            ></label>
            <input
              type="text"
              class="form-control"
              name="relevantdepartment"
              id="wbssubmanage-relevantdepartment"
              data-cy="relevantdepartment"
              :class="{ valid: !v$.relevantdepartment.$invalid, invalid: v$.relevantdepartment.$invalid }"
              v-model="v$.relevantdepartment.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.wbssubmanage.type')" for="wbssubmanage-type"></label>
            <input
              type="text"
              class="form-control"
              name="type"
              id="wbssubmanage-type"
              data-cy="type"
              :class="{ valid: !v$.type.$invalid, invalid: v$.type.$invalid }"
              v-model="v$.type.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.wbssubmanage.starttime')" for="wbssubmanage-starttime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="wbssubmanage-starttime"
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
                id="wbssubmanage-starttime"
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
            <label class="form-control-label" v-text="t$('jHipster3App.wbssubmanage.endtime')" for="wbssubmanage-endtime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="wbssubmanage-endtime"
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
                id="wbssubmanage-endtime"
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
            <label class="form-control-label" v-text="t$('jHipster3App.wbssubmanage.secretlevel')" for="wbssubmanage-secretlevel"></label>
            <select
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="wbssubmanage-secretlevel"
              data-cy="secretlevel"
            >
              <option
                v-for="secretlevel in secretlevelValues"
                :key="secretlevel"
                v-bind:value="secretlevel"
                v-bind:label="t$('jHipster3App.Secretlevel.' + secretlevel)"
              >
                {{ secretlevel }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.wbssubmanage.auditStatus')" for="wbssubmanage-auditStatus"></label>
            <select
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="wbssubmanage-auditStatus"
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
              v-text="t$('jHipster3App.wbssubmanage.responsibleid')"
              for="wbssubmanage-responsibleid"
            ></label>
            <select
              class="form-control"
              id="wbssubmanage-responsibleid"
              data-cy="responsibleid"
              name="responsibleid"
              v-model="wbssubmanage.responsibleid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  wbssubmanage.responsibleid && officersOption.id === wbssubmanage.responsibleid.id
                    ? wbssubmanage.responsibleid
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
            <label class="form-control-label" v-text="t$('jHipster3App.wbssubmanage.auditorid')" for="wbssubmanage-auditorid"></label>
            <select class="form-control" id="wbssubmanage-auditorid" data-cy="auditorid" name="auditorid" v-model="wbssubmanage.auditorid">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  wbssubmanage.auditorid && officersOption.id === wbssubmanage.auditorid.id ? wbssubmanage.auditorid : officersOption
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
<script lang="ts" src="./wbssubmanage-update.component.ts"></script>
