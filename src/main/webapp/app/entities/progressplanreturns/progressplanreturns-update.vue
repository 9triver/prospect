<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster0App.progressplanreturns.home.createOrEditLabel"
          data-cy="ProgressplanreturnsCreateUpdateHeading"
          v-text="t$('jHipster0App.progressplanreturns.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="progressplanreturns.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="progressplanreturns.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.progressplanreturns.planstarttime')"
              for="progressplanreturns-planstarttime"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="progressplanreturns-planstarttime"
                  v-model="v$.planstarttime.$model"
                  name="planstarttime"
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
                id="progressplanreturns-planstarttime"
                data-cy="planstarttime"
                type="text"
                class="form-control"
                name="planstarttime"
                :class="{ valid: !v$.planstarttime.$invalid, invalid: v$.planstarttime.$invalid }"
                v-model="v$.planstarttime.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.progressplanreturns.planendtime')"
              for="progressplanreturns-planendtime"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="progressplanreturns-planendtime"
                  v-model="v$.planendtime.$model"
                  name="planendtime"
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
                id="progressplanreturns-planendtime"
                data-cy="planendtime"
                type="text"
                class="form-control"
                name="planendtime"
                :class="{ valid: !v$.planendtime.$invalid, invalid: v$.planendtime.$invalid }"
                v-model="v$.planendtime.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.progressplanreturns.returnstime')"
              for="progressplanreturns-returnstime"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="progressplanreturns-returnstime"
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
                id="progressplanreturns-returnstime"
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
              v-text="t$('jHipster0App.progressplanreturns.progressplan')"
              for="progressplanreturns-progressplan"
            ></label>
            <select
              class="form-control"
              id="progressplanreturns-progressplan"
              data-cy="progressplan"
              name="progressplan"
              v-model="progressplanreturns.progressplan"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  progressplanreturns.progressplan && progressplanOption.id === progressplanreturns.progressplan.id
                    ? progressplanreturns.progressplan
                    : progressplanOption
                "
                v-for="progressplanOption in progressplans"
                :key="progressplanOption.id"
              >
                {{ progressplanOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.progressplanreturns.document')"
              for="progressplanreturns-document"
            ></label>
            <select
              class="form-control"
              id="progressplanreturns-document"
              data-cy="document"
              name="document"
              v-model="progressplanreturns.document"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  progressplanreturns.document && documentOption.id === progressplanreturns.document.id
                    ? progressplanreturns.document
                    : documentOption
                "
                v-for="documentOption in documents"
                :key="documentOption.id"
              >
                {{ documentOption.id }}
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
<script lang="ts" src="./progressplanreturns-update.component.ts"></script>
