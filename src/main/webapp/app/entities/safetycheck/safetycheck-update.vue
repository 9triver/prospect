<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster0App.safetycheck.home.createOrEditLabel"
          data-cy="SafetycheckCreateUpdateHeading"
          v-text="t$('jHipster0App.safetycheck.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="safetycheck.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="safetycheck.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.safetycheck.safetycheckname')"
              for="safetycheck-safetycheckname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="safetycheckname"
              id="safetycheck-safetycheckname"
              data-cy="safetycheckname"
              :class="{ valid: !v$.safetycheckname.$invalid, invalid: v$.safetycheckname.$invalid }"
              v-model="v$.safetycheckname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.safetycheck.checksource')" for="safetycheck-checksource"></label>
            <input
              type="text"
              class="form-control"
              name="checksource"
              id="safetycheck-checksource"
              data-cy="checksource"
              :class="{ valid: !v$.checksource.$invalid, invalid: v$.checksource.$invalid }"
              v-model="v$.checksource.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.safetycheck.checktime')" for="safetycheck-checktime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="safetycheck-checktime"
                  v-model="v$.checktime.$model"
                  name="checktime"
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
                id="safetycheck-checktime"
                data-cy="checktime"
                type="text"
                class="form-control"
                name="checktime"
                :class="{ valid: !v$.checktime.$invalid, invalid: v$.checktime.$invalid }"
                v-model="v$.checktime.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.safetycheck.effectivetime')" for="safetycheck-effectivetime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="safetycheck-effectivetime"
                  v-model="v$.effectivetime.$model"
                  name="effectivetime"
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
                id="safetycheck-effectivetime"
                data-cy="effectivetime"
                type="text"
                class="form-control"
                name="effectivetime"
                :class="{ valid: !v$.effectivetime.$invalid, invalid: v$.effectivetime.$invalid }"
                v-model="v$.effectivetime.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.safetycheck.operatinglocation')"
              for="safetycheck-operatinglocation"
            ></label>
            <input
              type="text"
              class="form-control"
              name="operatinglocation"
              id="safetycheck-operatinglocation"
              data-cy="operatinglocation"
              :class="{ valid: !v$.operatinglocation.$invalid, invalid: v$.operatinglocation.$invalid }"
              v-model="v$.operatinglocation.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.safetycheck.deprotment')" for="safetycheck-deprotment"></label>
            <input
              type="text"
              class="form-control"
              name="deprotment"
              id="safetycheck-deprotment"
              data-cy="deprotment"
              :class="{ valid: !v$.deprotment.$invalid, invalid: v$.deprotment.$invalid }"
              v-model="v$.deprotment.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.safetycheck.phonenumber')" for="safetycheck-phonenumber"></label>
            <input
              type="number"
              class="form-control"
              name="phonenumber"
              id="safetycheck-phonenumber"
              data-cy="phonenumber"
              :class="{ valid: !v$.phonenumber.$invalid, invalid: v$.phonenumber.$invalid }"
              v-model.number="v$.phonenumber.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.safetycheck.risklevel')" for="safetycheck-risklevel"></label>
            <select
              class="form-control"
              name="risklevel"
              :class="{ valid: !v$.risklevel.$invalid, invalid: v$.risklevel.$invalid }"
              v-model="v$.risklevel.$model"
              id="safetycheck-risklevel"
              data-cy="risklevel"
            >
              <option
                v-for="risklevel in risklevelValues"
                :key="risklevel"
                v-bind:value="risklevel"
                v-bind:label="t$('jHipster0App.Risklevel.' + risklevel)"
              >
                {{ risklevel }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.safetycheck.auditStatus')" for="safetycheck-auditStatus"></label>
            <select
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="safetycheck-auditStatus"
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
            <label class="form-control-label" v-text="t$('jHipster0App.safetycheck.auditorid')" for="safetycheck-auditorid"></label>
            <select class="form-control" id="safetycheck-auditorid" data-cy="auditorid" name="auditorid" v-model="safetycheck.auditorid">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  safetycheck.auditorid && officersOption.id === safetycheck.auditorid.id ? safetycheck.auditorid : officersOption
                "
                v-for="officersOption in officers"
                :key="officersOption.id"
              >
                {{ officersOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.safetycheck.responsibleid')" for="safetycheck-responsibleid"></label>
            <select
              class="form-control"
              id="safetycheck-responsibleid"
              data-cy="responsibleid"
              name="responsibleid"
              v-model="safetycheck.responsibleid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  safetycheck.responsibleid && officersOption.id === safetycheck.responsibleid.id
                    ? safetycheck.responsibleid
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
<script lang="ts" src="./safetycheck-update.component.ts"></script>
