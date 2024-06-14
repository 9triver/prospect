<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster3App.qualityreturns.home.createOrEditLabel"
          data-cy="QualityreturnsCreateUpdateHeading"
          v-text="t$('jHipster3App.qualityreturns.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="qualityreturns.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="qualityreturns.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.qualityreturns.qualityreturnsid')"
              for="qualityreturns-qualityreturnsid"
            ></label>
            <input
              type="number"
              class="form-control"
              name="qualityreturnsid"
              id="qualityreturns-qualityreturnsid"
              data-cy="qualityreturnsid"
              :class="{ valid: !v$.qualityreturnsid.$invalid, invalid: v$.qualityreturnsid.$invalid }"
              v-model.number="v$.qualityreturnsid.$model"
            />
            <div v-if="v$.qualityreturnsid.$anyDirty && v$.qualityreturnsid.$invalid">
              <small class="form-text text-danger" v-for="error of v$.qualityreturnsid.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.qualityreturns.qualityreturnsname')"
              for="qualityreturns-qualityreturnsname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="qualityreturnsname"
              id="qualityreturns-qualityreturnsname"
              data-cy="qualityreturnsname"
              :class="{ valid: !v$.qualityreturnsname.$invalid, invalid: v$.qualityreturnsname.$invalid }"
              v-model="v$.qualityreturnsname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.qualityreturns.starttime')" for="qualityreturns-starttime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="qualityreturns-starttime"
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
                id="qualityreturns-starttime"
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
            <label class="form-control-label" v-text="t$('jHipster3App.qualityreturns.endtime')" for="qualityreturns-endtime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="qualityreturns-endtime"
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
                id="qualityreturns-endtime"
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
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.qualityreturns.qualitytype')"
              for="qualityreturns-qualitytype"
            ></label>
            <input
              type="number"
              class="form-control"
              name="qualitytype"
              id="qualityreturns-qualitytype"
              data-cy="qualitytype"
              :class="{ valid: !v$.qualitytype.$invalid, invalid: v$.qualitytype.$invalid }"
              v-model.number="v$.qualitytype.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.qualityreturns.returnstime')"
              for="qualityreturns-returnstime"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="qualityreturns-returnstime"
                  v-model="v$.returnstime.$model"
                  name="returnstime"
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
                id="qualityreturns-returnstime"
                data-cy="returnstime"
                type="text"
                class="form-control"
                name="returnstime"
                :class="{ valid: !v$.returnstime.$invalid, invalid: v$.returnstime.$invalid }"
                v-model="v$.returnstime.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.qualityreturns.returnsstatus')"
              for="qualityreturns-returnsstatus"
            ></label>
            <select
              class="form-control"
              name="returnsstatus"
              :class="{ valid: !v$.returnsstatus.$invalid, invalid: v$.returnsstatus.$invalid }"
              v-model="v$.returnsstatus.$model"
              id="qualityreturns-returnsstatus"
              data-cy="returnsstatus"
            >
              <option
                v-for="returnsStatus in returnsStatusValues"
                :key="returnsStatus"
                v-bind:value="returnsStatus"
                v-bind:label="t$('jHipster3App.ReturnsStatus.' + returnsStatus)"
              >
                {{ returnsStatus }}
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
<script lang="ts" src="./qualityreturns-update.component.ts"></script>
