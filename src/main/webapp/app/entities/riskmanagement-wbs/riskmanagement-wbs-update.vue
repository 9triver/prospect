<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster0App.riskmanagementWbs.home.createOrEditLabel"
          data-cy="RiskmanagementWbsCreateUpdateHeading"
          v-text="t$('jHipster0App.riskmanagementWbs.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="riskmanagementWbs.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="riskmanagementWbs.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.riskmanagementWbs.wbsspare1')"
              for="riskmanagement-wbs-wbsspare1"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare1"
              id="riskmanagement-wbs-wbsspare1"
              data-cy="wbsspare1"
              :class="{ valid: !v$.wbsspare1.$invalid, invalid: v$.wbsspare1.$invalid }"
              v-model="v$.wbsspare1.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.riskmanagementWbs.wbsspare2')"
              for="riskmanagement-wbs-wbsspare2"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare2"
              id="riskmanagement-wbs-wbsspare2"
              data-cy="wbsspare2"
              :class="{ valid: !v$.wbsspare2.$invalid, invalid: v$.wbsspare2.$invalid }"
              v-model="v$.wbsspare2.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.riskmanagementWbs.wbsspare3')"
              for="riskmanagement-wbs-wbsspare3"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare3"
              id="riskmanagement-wbs-wbsspare3"
              data-cy="wbsspare3"
              :class="{ valid: !v$.wbsspare3.$invalid, invalid: v$.wbsspare3.$invalid }"
              v-model="v$.wbsspare3.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.riskmanagementWbs.wbsspare4')"
              for="riskmanagement-wbs-wbsspare4"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare4"
              id="riskmanagement-wbs-wbsspare4"
              data-cy="wbsspare4"
              :class="{ valid: !v$.wbsspare4.$invalid, invalid: v$.wbsspare4.$invalid }"
              v-model="v$.wbsspare4.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.riskmanagementWbs.wbsspare5')"
              for="riskmanagement-wbs-wbsspare5"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare5"
              id="riskmanagement-wbs-wbsspare5"
              data-cy="wbsspare5"
              :class="{ valid: !v$.wbsspare5.$invalid, invalid: v$.wbsspare5.$invalid }"
              v-model="v$.wbsspare5.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.riskmanagementWbs.riskidentification')"
              for="riskmanagement-wbs-riskidentification"
            ></label>
            <select
              class="form-control"
              id="riskmanagement-wbs-riskidentification"
              data-cy="riskidentification"
              name="riskidentification"
              v-model="riskmanagementWbs.riskidentification"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  riskmanagementWbs.riskidentification && riskidentificationOption.id === riskmanagementWbs.riskidentification.id
                    ? riskmanagementWbs.riskidentification
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
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.riskmanagementWbs.riskreport')"
              for="riskmanagement-wbs-riskreport"
            ></label>
            <select
              class="form-control"
              id="riskmanagement-wbs-riskreport"
              data-cy="riskreport"
              name="riskreport"
              v-model="riskmanagementWbs.riskreport"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  riskmanagementWbs.riskreport && riskreportOption.id === riskmanagementWbs.riskreport.id
                    ? riskmanagementWbs.riskreport
                    : riskreportOption
                "
                v-for="riskreportOption in riskreports"
                :key="riskreportOption.id"
              >
                {{ riskreportOption.id }}
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
<script lang="ts" src="./riskmanagement-wbs-update.component.ts"></script>
