<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster3App.planexecute.home.createOrEditLabel"
          data-cy="PlanexecuteCreateUpdateHeading"
          v-text="t$('jHipster3App.planexecute.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="planexecute.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="planexecute.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.planexecute.planname')" for="planexecute-planname"></label>
            <input
              type="text"
              class="form-control"
              name="planname"
              id="planexecute-planname"
              data-cy="planname"
              :class="{ valid: !v$.planname.$invalid, invalid: v$.planname.$invalid }"
              v-model="v$.planname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.planexecute.planstarttime')" for="planexecute-planstarttime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="planexecute-planstarttime"
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
                id="planexecute-planstarttime"
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
            <label class="form-control-label" v-text="t$('jHipster3App.planexecute.planendtime')" for="planexecute-planendtime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="planexecute-planendtime"
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
                id="planexecute-planendtime"
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
            <label class="form-control-label" v-text="t$('jHipster3App.planexecute.planreturns')" for="planexecute-planreturns"></label>
            <select
              class="form-control"
              id="planexecute-planreturns"
              data-cy="planreturns"
              name="planreturns"
              v-model="planexecute.planreturns"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  planexecute.planreturns && planreturnsOption.id === planexecute.planreturns.id
                    ? planexecute.planreturns
                    : planreturnsOption
                "
                v-for="planreturnsOption in planreturns"
                :key="planreturnsOption.id"
              >
                {{ planreturnsOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.planexecute.responsibleid')" for="planexecute-responsibleid"></label>
            <select
              class="form-control"
              id="planexecute-responsibleid"
              data-cy="responsibleid"
              name="responsibleid"
              v-model="planexecute.responsibleid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  planexecute.responsibleid && officersOption.id === planexecute.responsibleid.id
                    ? planexecute.responsibleid
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
<script lang="ts" src="./planexecute-update.component.ts"></script>
