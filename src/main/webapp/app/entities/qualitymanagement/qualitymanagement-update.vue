<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster3App.qualitymanagement.home.createOrEditLabel"
          data-cy="QualitymanagementCreateUpdateHeading"
          v-text="t$('jHipster3App.qualitymanagement.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="qualitymanagement.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="qualitymanagement.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.qualitymanagement.qualityid')"
              for="qualitymanagement-qualityid"
            ></label>
            <input
              type="number"
              class="form-control"
              name="qualityid"
              id="qualitymanagement-qualityid"
              data-cy="qualityid"
              :class="{ valid: !v$.qualityid.$invalid, invalid: v$.qualityid.$invalid }"
              v-model.number="v$.qualityid.$model"
            />
            <div v-if="v$.qualityid.$anyDirty && v$.qualityid.$invalid">
              <small class="form-text text-danger" v-for="error of v$.qualityid.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.qualitymanagement.createtime')"
              for="qualitymanagement-createtime"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="qualitymanagement-createtime"
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
                id="qualitymanagement-createtime"
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
              v-text="t$('jHipster3App.qualitymanagement.creatorname')"
              for="qualitymanagement-creatorname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="creatorname"
              id="qualitymanagement-creatorname"
              data-cy="creatorname"
              :class="{ valid: !v$.creatorname.$invalid, invalid: v$.creatorname.$invalid }"
              v-model="v$.creatorname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.qualitymanagement.secretlevel')"
              for="qualitymanagement-secretlevel"
            ></label>
            <select
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="qualitymanagement-secretlevel"
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
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.qualitymanagement.auditStatus')"
              for="qualitymanagement-auditStatus"
            ></label>
            <select
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="qualitymanagement-auditStatus"
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
              v-text="t$('jHipster3App.qualitymanagement.creatorid')"
              for="qualitymanagement-creatorid"
            ></label>
            <select
              class="form-control"
              id="qualitymanagement-creatorid"
              data-cy="creatorid"
              name="creatorid"
              v-model="qualitymanagement.creatorid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  qualitymanagement.creatorid && officersOption.id === qualitymanagement.creatorid.id
                    ? qualitymanagement.creatorid
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
              v-text="t$('jHipster3App.qualitymanagement.auditorid')"
              for="qualitymanagement-auditorid"
            ></label>
            <select
              class="form-control"
              id="qualitymanagement-auditorid"
              data-cy="auditorid"
              name="auditorid"
              v-model="qualitymanagement.auditorid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  qualitymanagement.auditorid && officersOption.id === qualitymanagement.auditorid.id
                    ? qualitymanagement.auditorid
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
<script lang="ts" src="./qualitymanagement-update.component.ts"></script>
