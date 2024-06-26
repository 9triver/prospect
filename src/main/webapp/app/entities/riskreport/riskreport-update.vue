<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster0App.riskreport.home.createOrEditLabel"
          data-cy="RiskreportCreateUpdateHeading"
          v-text="t$('jHipster0App.riskreport.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="riskreport.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="riskreport.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.riskreport.type')" for="riskreport-type"></label>
            <input
              type="text"
              class="form-control"
              name="type"
              id="riskreport-type"
              data-cy="type"
              :class="{ valid: !v$.type.$invalid, invalid: v$.type.$invalid }"
              v-model="v$.type.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.riskreport.riskreportname')" for="riskreport-riskreportname"></label>
            <input
              type="text"
              class="form-control"
              name="riskreportname"
              id="riskreport-riskreportname"
              data-cy="riskreportname"
              :class="{ valid: !v$.riskreportname.$invalid, invalid: v$.riskreportname.$invalid }"
              v-model="v$.riskreportname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.riskreport.releasetime')" for="riskreport-releasetime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="riskreport-releasetime"
                  v-model="v$.releasetime.$model"
                  name="releasetime"
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
                id="riskreport-releasetime"
                data-cy="releasetime"
                type="text"
                class="form-control"
                name="releasetime"
                :class="{ valid: !v$.releasetime.$invalid, invalid: v$.releasetime.$invalid }"
                v-model="v$.releasetime.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.riskreport.auditStatus')" for="riskreport-auditStatus"></label>
            <select
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="riskreport-auditStatus"
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
              v-text="t$('jHipster0App.riskreport.riskidentification')"
              for="riskreport-riskidentification"
            ></label>
            <select
              class="form-control"
              id="riskreport-riskidentification"
              data-cy="riskidentification"
              name="riskidentification"
              v-model="riskreport.riskidentification"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  riskreport.riskidentification && riskidentificationOption.id === riskreport.riskidentification.id
                    ? riskreport.riskidentification
                    : riskidentificationOption
                "
                v-for="riskidentificationOption in riskidentifications"
                :key="riskidentificationOption.id"
              >
                {{ riskidentificationOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.riskreport.creatorid')" for="riskreport-creatorid"></label>
            <select class="form-control" id="riskreport-creatorid" data-cy="creatorid" name="creatorid" v-model="riskreport.creatorid">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="riskreport.creatorid && officersOption.id === riskreport.creatorid.id ? riskreport.creatorid : officersOption"
                v-for="officersOption in officers"
                :key="officersOption.id"
              >
                {{ officersOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.riskreport.auditorid')" for="riskreport-auditorid"></label>
            <select class="form-control" id="riskreport-auditorid" data-cy="auditorid" name="auditorid" v-model="riskreport.auditorid">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="riskreport.auditorid && officersOption.id === riskreport.auditorid.id ? riskreport.auditorid : officersOption"
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
<script lang="ts" src="./riskreport-update.component.ts"></script>
