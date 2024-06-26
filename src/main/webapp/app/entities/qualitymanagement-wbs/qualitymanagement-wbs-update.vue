<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster0App.qualitymanagementWbs.home.createOrEditLabel"
          data-cy="QualitymanagementWbsCreateUpdateHeading"
          v-text="t$('jHipster0App.qualitymanagementWbs.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="qualitymanagementWbs.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="qualitymanagementWbs.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.qualitymanagementWbs.wbsspare1')"
              for="qualitymanagement-wbs-wbsspare1"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare1"
              id="qualitymanagement-wbs-wbsspare1"
              data-cy="wbsspare1"
              :class="{ valid: !v$.wbsspare1.$invalid, invalid: v$.wbsspare1.$invalid }"
              v-model="v$.wbsspare1.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.qualitymanagementWbs.wbsspare2')"
              for="qualitymanagement-wbs-wbsspare2"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare2"
              id="qualitymanagement-wbs-wbsspare2"
              data-cy="wbsspare2"
              :class="{ valid: !v$.wbsspare2.$invalid, invalid: v$.wbsspare2.$invalid }"
              v-model="v$.wbsspare2.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.qualitymanagementWbs.wbsspare3')"
              for="qualitymanagement-wbs-wbsspare3"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare3"
              id="qualitymanagement-wbs-wbsspare3"
              data-cy="wbsspare3"
              :class="{ valid: !v$.wbsspare3.$invalid, invalid: v$.wbsspare3.$invalid }"
              v-model="v$.wbsspare3.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.qualitymanagementWbs.wbsspare4')"
              for="qualitymanagement-wbs-wbsspare4"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare4"
              id="qualitymanagement-wbs-wbsspare4"
              data-cy="wbsspare4"
              :class="{ valid: !v$.wbsspare4.$invalid, invalid: v$.wbsspare4.$invalid }"
              v-model="v$.wbsspare4.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.qualitymanagementWbs.wbsspare5')"
              for="qualitymanagement-wbs-wbsspare5"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare5"
              id="qualitymanagement-wbs-wbsspare5"
              data-cy="wbsspare5"
              :class="{ valid: !v$.wbsspare5.$invalid, invalid: v$.wbsspare5.$invalid }"
              v-model="v$.wbsspare5.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.qualitymanagementWbs.qualityobjectives')"
              for="qualitymanagement-wbs-qualityobjectives"
            ></label>
            <select
              class="form-control"
              id="qualitymanagement-wbs-qualityobjectives"
              data-cy="qualityobjectives"
              name="qualityobjectives"
              v-model="qualitymanagementWbs.qualityobjectives"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  qualitymanagementWbs.qualityobjectives && qualityobjectivesOption.id === qualitymanagementWbs.qualityobjectives.id
                    ? qualitymanagementWbs.qualityobjectives
                    : qualityobjectivesOption
                "
                v-for="qualityobjectivesOption in qualityobjectives"
                :key="qualityobjectivesOption.id"
              >
                {{ qualityobjectivesOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.qualitymanagementWbs.qualityreturns')"
              for="qualitymanagement-wbs-qualityreturns"
            ></label>
            <select
              class="form-control"
              id="qualitymanagement-wbs-qualityreturns"
              data-cy="qualityreturns"
              name="qualityreturns"
              v-model="qualitymanagementWbs.qualityreturns"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  qualitymanagementWbs.qualityreturns && qualityreturnsOption.id === qualitymanagementWbs.qualityreturns.id
                    ? qualitymanagementWbs.qualityreturns
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
              v-text="t$('jHipster0App.qualitymanagementWbs.unQualityAudit')"
              for="qualitymanagement-wbs-unQualityAudit"
            ></label>
            <select
              class="form-control"
              id="qualitymanagement-wbs-unQualityAudit"
              data-cy="unQualityAudit"
              name="unQualityAudit"
              v-model="qualitymanagementWbs.unQualityAudit"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  qualitymanagementWbs.unQualityAudit && unQualityAuditOption.id === qualitymanagementWbs.unQualityAudit.id
                    ? qualitymanagementWbs.unQualityAudit
                    : unQualityAuditOption
                "
                v-for="unQualityAuditOption in unQualityAudits"
                :key="unQualityAuditOption.id"
              >
                {{ unQualityAuditOption.id }}
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
<script lang="ts" src="./qualitymanagement-wbs-update.component.ts"></script>
