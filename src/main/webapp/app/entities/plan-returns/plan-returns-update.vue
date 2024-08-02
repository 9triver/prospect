<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.planReturns.home.createOrEditLabel"
          data-cy="PlanReturnsCreateUpdateHeading"
          v-text="t$('jy1App.planReturns.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="planReturns.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="planReturns.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.planReturns.planreturnsname')" for="plan-returns-planreturnsname"></label>
            <input
              type="text"
              class="form-control"
              name="planreturnsname"
              id="plan-returns-planreturnsname"
              data-cy="planreturnsname"
              :class="{ valid: !v$.planreturnsname.$invalid, invalid: v$.planreturnsname.$invalid }"
              v-model="v$.planreturnsname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.planReturns.plantype')" for="plan-returns-plantype"></label>
            <input
              type="number"
              class="form-control"
              name="plantype"
              id="plan-returns-plantype"
              data-cy="plantype"
              :class="{ valid: !v$.plantype.$invalid, invalid: v$.plantype.$invalid }"
              v-model.number="v$.plantype.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.planReturns.planlevel')" for="plan-returns-planlevel"></label>
            <select
              class="form-control"
              name="planlevel"
              :class="{ valid: !v$.planlevel.$invalid, invalid: v$.planlevel.$invalid }"
              v-model="v$.planlevel.$model"
              id="plan-returns-planlevel"
              data-cy="planlevel"
            >
              <option
                v-for="planLevel in planLevelValues"
                :key="planLevel"
                v-bind:value="planLevel"
                v-bind:label="t$('jy1App.PlanLevel.' + planLevel)"
              >
                {{ planLevel }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.planReturns.description')" for="plan-returns-description"></label>
            <input
              type="text"
              class="form-control"
              name="description"
              id="plan-returns-description"
              data-cy="description"
              :class="{ valid: !v$.description.$invalid, invalid: v$.description.$invalid }"
              v-model="v$.description.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.planReturns.actualstarttime')" for="plan-returns-actualstarttime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="plan-returns-actualstarttime"
                  v-model="v$.actualstarttime.$model"
                  name="actualstarttime"
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
                id="plan-returns-actualstarttime"
                data-cy="actualstarttime"
                type="text"
                class="form-control"
                name="actualstarttime"
                :class="{ valid: !v$.actualstarttime.$invalid, invalid: v$.actualstarttime.$invalid }"
                v-model="v$.actualstarttime.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.planReturns.actualendtime')" for="plan-returns-actualendtime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="plan-returns-actualendtime"
                  v-model="v$.actualendtime.$model"
                  name="actualendtime"
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
                id="plan-returns-actualendtime"
                data-cy="actualendtime"
                type="text"
                class="form-control"
                name="actualendtime"
                :class="{ valid: !v$.actualendtime.$invalid, invalid: v$.actualendtime.$invalid }"
                v-model="v$.actualendtime.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.planReturns.deliverables')" for="plan-returns-deliverables"></label>
            <input
              type="text"
              class="form-control"
              name="deliverables"
              id="plan-returns-deliverables"
              data-cy="deliverables"
              :class="{ valid: !v$.deliverables.$invalid, invalid: v$.deliverables.$invalid }"
              v-model="v$.deliverables.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.planReturns.progress')" for="plan-returns-progress"></label>
            <input
              type="number"
              class="form-control"
              name="progress"
              id="plan-returns-progress"
              data-cy="progress"
              :class="{ valid: !v$.progress.$invalid, invalid: v$.progress.$invalid }"
              v-model.number="v$.progress.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.planReturns.status')" for="plan-returns-status"></label>
            <select
              class="form-control"
              name="status"
              :class="{ valid: !v$.status.$invalid, invalid: v$.status.$invalid }"
              v-model="v$.status.$model"
              id="plan-returns-status"
              data-cy="status"
            >
              <option
                v-for="planstatus in planstatusValues"
                :key="planstatus"
                v-bind:value="planstatus"
                v-bind:label="t$('jy1App.Planstatus.' + planstatus)"
              >
                {{ planstatus }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.planReturns.impactanalysis')" for="plan-returns-impactanalysis"></label>
            <input
              type="text"
              class="form-control"
              name="impactanalysis"
              id="plan-returns-impactanalysis"
              data-cy="impactanalysis"
              :class="{ valid: !v$.impactanalysis.$invalid, invalid: v$.impactanalysis.$invalid }"
              v-model="v$.impactanalysis.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.planReturns.returnstime')" for="plan-returns-returnstime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="plan-returns-returnstime"
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
                id="plan-returns-returnstime"
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
            <label class="form-control-label" v-text="t$('jy1App.planReturns.rejectionreason')" for="plan-returns-rejectionreason"></label>
            <input
              type="text"
              class="form-control"
              name="rejectionreason"
              id="plan-returns-rejectionreason"
              data-cy="rejectionreason"
              :class="{ valid: !v$.rejectionreason.$invalid, invalid: v$.rejectionreason.$invalid }"
              v-model="v$.rejectionreason.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.planReturns.returnsstatus')" for="plan-returns-returnsstatus"></label>
            <select
              class="form-control"
              name="returnsstatus"
              :class="{ valid: !v$.returnsstatus.$invalid, invalid: v$.returnsstatus.$invalid }"
              v-model="v$.returnsstatus.$model"
              id="plan-returns-returnsstatus"
              data-cy="returnsstatus"
            >
              <option
                v-for="returnsStatus in returnsStatusValues"
                :key="returnsStatus"
                v-bind:value="returnsStatus"
                v-bind:label="t$('jy1App.ReturnsStatus.' + returnsStatus)"
              >
                {{ returnsStatus }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.planReturns.responsibleperson')"
              for="plan-returns-responsibleperson"
            ></label>
            <select
              class="form-control"
              id="plan-returns-responsibleperson"
              data-cy="responsibleperson"
              name="responsibleperson"
              v-model="planReturns.responsibleperson"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  planReturns.responsibleperson && officersOption.id === planReturns.responsibleperson.id
                    ? planReturns.responsibleperson
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
            <label class="form-control-label" v-text="t$('jy1App.planReturns.auditorid')" for="plan-returns-auditorid"></label>
            <select class="form-control" id="plan-returns-auditorid" data-cy="auditorid" name="auditorid" v-model="planReturns.auditorid">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  planReturns.auditorid && officersOption.id === planReturns.auditorid.id ? planReturns.auditorid : officersOption
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
<script lang="ts" src="./plan-returns-update.component.ts"></script>
