<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster0App.qualityobjectives.home.createOrEditLabel"
          data-cy="QualityobjectivesCreateUpdateHeading"
          v-text="t$('jHipster0App.qualityobjectives.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="qualityobjectives.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="qualityobjectives.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.qualityobjectives.qualityobjectivesname')"
              for="qualityobjectives-qualityobjectivesname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="qualityobjectivesname"
              id="qualityobjectives-qualityobjectivesname"
              data-cy="qualityobjectivesname"
              :class="{ valid: !v$.qualityobjectivesname.$invalid, invalid: v$.qualityobjectivesname.$invalid }"
              v-model="v$.qualityobjectivesname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.qualityobjectives.year')" for="qualityobjectives-year"></label>
            <input
              type="number"
              class="form-control"
              name="year"
              id="qualityobjectives-year"
              data-cy="year"
              :class="{ valid: !v$.year.$invalid, invalid: v$.year.$invalid }"
              v-model.number="v$.year.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.qualityobjectives.createtime')"
              for="qualityobjectives-createtime"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="qualityobjectives-createtime"
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
                id="qualityobjectives-createtime"
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
              v-text="t$('jHipster0App.qualityobjectives.creatorname')"
              for="qualityobjectives-creatorname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="creatorname"
              id="qualityobjectives-creatorname"
              data-cy="creatorname"
              :class="{ valid: !v$.creatorname.$invalid, invalid: v$.creatorname.$invalid }"
              v-model="v$.creatorname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.qualityobjectives.secretlevel')"
              for="qualityobjectives-secretlevel"
            ></label>
            <select
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="qualityobjectives-secretlevel"
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
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.qualityobjectives.auditStatus')"
              for="qualityobjectives-auditStatus"
            ></label>
            <select
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="qualityobjectives-auditStatus"
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
              v-text="t$('jHipster0App.qualityobjectives.qualityreturns')"
              for="qualityobjectives-qualityreturns"
            ></label>
            <select
              class="form-control"
              id="qualityobjectives-qualityreturns"
              data-cy="qualityreturns"
              name="qualityreturns"
              v-model="qualityobjectives.qualityreturns"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  qualityobjectives.qualityreturns && qualityreturnsOption.id === qualityobjectives.qualityreturns.id
                    ? qualityobjectives.qualityreturns
                    : qualityreturnsOption
                "
                v-for="qualityreturnsOption in qualityreturns"
                :key="qualityreturnsOption.id"
              >
                {{ qualityreturnsOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.qualityobjectives.creatorid')"
              for="qualityobjectives-creatorid"
            ></label>
            <select
              class="form-control"
              id="qualityobjectives-creatorid"
              data-cy="creatorid"
              name="creatorid"
              v-model="qualityobjectives.creatorid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  qualityobjectives.creatorid && officersOption.id === qualityobjectives.creatorid.id
                    ? qualityobjectives.creatorid
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
              v-text="t$('jHipster0App.qualityobjectives.auditorid')"
              for="qualityobjectives-auditorid"
            ></label>
            <select
              class="form-control"
              id="qualityobjectives-auditorid"
              data-cy="auditorid"
              name="auditorid"
              v-model="qualityobjectives.auditorid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  qualityobjectives.auditorid && officersOption.id === qualityobjectives.auditorid.id
                    ? qualityobjectives.auditorid
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
<script lang="ts" src="./qualityobjectives-update.component.ts"></script>
