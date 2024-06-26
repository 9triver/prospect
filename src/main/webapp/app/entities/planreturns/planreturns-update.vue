<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster0App.planreturns.home.createOrEditLabel"
          data-cy="PlanreturnsCreateUpdateHeading"
          v-text="t$('jHipster0App.planreturns.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="planreturns.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="planreturns.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.planreturns.planreturnsname')"
              for="planreturns-planreturnsname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="planreturnsname"
              id="planreturns-planreturnsname"
              data-cy="planreturnsname"
              :class="{ valid: !v$.planreturnsname.$invalid, invalid: v$.planreturnsname.$invalid }"
              v-model="v$.planreturnsname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.planreturns.starttime')" for="planreturns-starttime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="planreturns-starttime"
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
                id="planreturns-starttime"
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
            <label class="form-control-label" v-text="t$('jHipster0App.planreturns.endtime')" for="planreturns-endtime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="planreturns-endtime"
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
                id="planreturns-endtime"
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
            <label class="form-control-label" v-text="t$('jHipster0App.planreturns.plantype')" for="planreturns-plantype"></label>
            <input
              type="number"
              class="form-control"
              name="plantype"
              id="planreturns-plantype"
              data-cy="plantype"
              :class="{ valid: !v$.plantype.$invalid, invalid: v$.plantype.$invalid }"
              v-model.number="v$.plantype.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.planreturns.returnstime')" for="planreturns-returnstime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="planreturns-returnstime"
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
                id="planreturns-returnstime"
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
            <label class="form-control-label" v-text="t$('jHipster0App.planreturns.returnsstatus')" for="planreturns-returnsstatus"></label>
            <select
              class="form-control"
              name="returnsstatus"
              :class="{ valid: !v$.returnsstatus.$invalid, invalid: v$.returnsstatus.$invalid }"
              v-model="v$.returnsstatus.$model"
              id="planreturns-returnsstatus"
              data-cy="returnsstatus"
            >
              <option
                v-for="returnsStatus in returnsStatusValues"
                :key="returnsStatus"
                v-bind:value="returnsStatus"
                v-bind:label="t$('jHipster0App.ReturnsStatus.' + returnsStatus)"
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
<script lang="ts" src="./planreturns-update.component.ts"></script>
