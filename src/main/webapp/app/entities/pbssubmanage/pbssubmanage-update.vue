<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster0App.pbssubmanage.home.createOrEditLabel"
          data-cy="PbssubmanageCreateUpdateHeading"
          v-text="t$('jHipster0App.pbssubmanage.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="pbssubmanage.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="pbssubmanage.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.pbssubmanage.pbssubname')" for="pbssubmanage-pbssubname"></label>
            <input
              type="text"
              class="form-control"
              name="pbssubname"
              id="pbssubmanage-pbssubname"
              data-cy="pbssubname"
              :class="{ valid: !v$.pbssubname.$invalid, invalid: v$.pbssubname.$invalid }"
              v-model="v$.pbssubname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.pbssubmanage.responsiblename')"
              for="pbssubmanage-responsiblename"
            ></label>
            <input
              type="text"
              class="form-control"
              name="responsiblename"
              id="pbssubmanage-responsiblename"
              data-cy="responsiblename"
              :class="{ valid: !v$.responsiblename.$invalid, invalid: v$.responsiblename.$invalid }"
              v-model="v$.responsiblename.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.pbssubmanage.responsibledepartment')"
              for="pbssubmanage-responsibledepartment"
            ></label>
            <input
              type="text"
              class="form-control"
              name="responsibledepartment"
              id="pbssubmanage-responsibledepartment"
              data-cy="responsibledepartment"
              :class="{ valid: !v$.responsibledepartment.$invalid, invalid: v$.responsibledepartment.$invalid }"
              v-model="v$.responsibledepartment.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.pbssubmanage.relevantdepartment')"
              for="pbssubmanage-relevantdepartment"
            ></label>
            <input
              type="text"
              class="form-control"
              name="relevantdepartment"
              id="pbssubmanage-relevantdepartment"
              data-cy="relevantdepartment"
              :class="{ valid: !v$.relevantdepartment.$invalid, invalid: v$.relevantdepartment.$invalid }"
              v-model="v$.relevantdepartment.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.pbssubmanage.type')" for="pbssubmanage-type"></label>
            <input
              type="text"
              class="form-control"
              name="type"
              id="pbssubmanage-type"
              data-cy="type"
              :class="{ valid: !v$.type.$invalid, invalid: v$.type.$invalid }"
              v-model="v$.type.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.pbssubmanage.starttime')" for="pbssubmanage-starttime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="pbssubmanage-starttime"
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
                id="pbssubmanage-starttime"
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
            <label class="form-control-label" v-text="t$('jHipster0App.pbssubmanage.endtime')" for="pbssubmanage-endtime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="pbssubmanage-endtime"
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
                id="pbssubmanage-endtime"
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
            <label class="form-control-label" v-text="t$('jHipster0App.pbssubmanage.secretlevel')" for="pbssubmanage-secretlevel"></label>
            <select
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="pbssubmanage-secretlevel"
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
            <label class="form-control-label" v-text="t$('jHipster0App.pbssubmanage.auditStatus')" for="pbssubmanage-auditStatus"></label>
            <select
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="pbssubmanage-auditStatus"
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
              v-text="t$('jHipster0App.pbssubmanage.responsibleid')"
              for="pbssubmanage-responsibleid"
            ></label>
            <select
              class="form-control"
              id="pbssubmanage-responsibleid"
              data-cy="responsibleid"
              name="responsibleid"
              v-model="pbssubmanage.responsibleid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  pbssubmanage.responsibleid && officersOption.id === pbssubmanage.responsibleid.id
                    ? pbssubmanage.responsibleid
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
            <label class="form-control-label" v-text="t$('jHipster0App.pbssubmanage.auditorid')" for="pbssubmanage-auditorid"></label>
            <select class="form-control" id="pbssubmanage-auditorid" data-cy="auditorid" name="auditorid" v-model="pbssubmanage.auditorid">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  pbssubmanage.auditorid && officersOption.id === pbssubmanage.auditorid.id ? pbssubmanage.auditorid : officersOption
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
<script lang="ts" src="./pbssubmanage-update.component.ts"></script>
