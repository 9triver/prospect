<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster3App.planstrategy.home.createOrEditLabel"
          data-cy="PlanstrategyCreateUpdateHeading"
          v-text="t$('jHipster3App.planstrategy.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="planstrategy.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="planstrategy.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.planstrategy.strategyid')" for="planstrategy-strategyid"></label>
            <input
              type="text"
              class="form-control"
              name="strategyid"
              id="planstrategy-strategyid"
              data-cy="strategyid"
              :class="{ valid: !v$.strategyid.$invalid, invalid: v$.strategyid.$invalid }"
              v-model="v$.strategyid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.planstrategy.strategyname')" for="planstrategy-strategyname"></label>
            <input
              type="text"
              class="form-control"
              name="strategyname"
              id="planstrategy-strategyname"
              data-cy="strategyname"
              :class="{ valid: !v$.strategyname.$invalid, invalid: v$.strategyname.$invalid }"
              v-model="v$.strategyname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.planstrategy.number')" for="planstrategy-number"></label>
            <input
              type="number"
              class="form-control"
              name="number"
              id="planstrategy-number"
              data-cy="number"
              :class="{ valid: !v$.number.$invalid, invalid: v$.number.$invalid }"
              v-model.number="v$.number.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.planstrategy.type')" for="planstrategy-type"></label>
            <input
              type="text"
              class="form-control"
              name="type"
              id="planstrategy-type"
              data-cy="type"
              :class="{ valid: !v$.type.$invalid, invalid: v$.type.$invalid }"
              v-model="v$.type.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.planstrategy.decument')" for="planstrategy-decument"></label>
            <select class="form-control" id="planstrategy-decument" data-cy="decument" name="decument" v-model="planstrategy.decument">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  planstrategy.decument && departmentOption.id === planstrategy.decument.id ? planstrategy.decument : departmentOption
                "
                v-for="departmentOption in departments"
                :key="departmentOption.id"
              >
                {{ departmentOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.planstrategy.responsibleid')"
              for="planstrategy-responsibleid"
            ></label>
            <select
              class="form-control"
              id="planstrategy-responsibleid"
              data-cy="responsibleid"
              name="responsibleid"
              v-model="planstrategy.responsibleid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  planstrategy.responsibleid && officersOption.id === planstrategy.responsibleid.id
                    ? planstrategy.responsibleid
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
            <label class="form-control-label" v-text="t$('jHipster3App.planstrategy.auditorid')" for="planstrategy-auditorid"></label>
            <select class="form-control" id="planstrategy-auditorid" data-cy="auditorid" name="auditorid" v-model="planstrategy.auditorid">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  planstrategy.auditorid && officersOption.id === planstrategy.auditorid.id ? planstrategy.auditorid : officersOption
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
<script lang="ts" src="./planstrategy-update.component.ts"></script>
