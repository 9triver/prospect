<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.qualityObjectivesDictionary.home.createOrEditLabel"
          data-cy="QualityObjectivesDictionaryCreateUpdateHeading"
          v-text="t$('jy1App.qualityObjectivesDictionary.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="qualityObjectivesDictionary.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="qualityObjectivesDictionary.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualityObjectivesDictionary.objectiveslevel')"
              for="quality-objectives-dictionary-objectiveslevel"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="objectiveslevel"
              id="quality-objectives-dictionary-objectiveslevel"
              data-cy="objectiveslevel"
              :class="{ valid: !v$.objectiveslevel.$invalid, invalid: v$.objectiveslevel.$invalid }"
              v-model="v$.objectiveslevel.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualityObjectivesDictionary.objectivestype')"
              for="quality-objectives-dictionary-objectivestype"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="objectivestype"
              id="quality-objectives-dictionary-objectivestype"
              data-cy="objectivestype"
              :class="{ valid: !v$.objectivestype.$invalid, invalid: v$.objectivestype.$invalid }"
              v-model="v$.objectivestype.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualityObjectivesDictionary.objectivesname')"
              for="quality-objectives-dictionary-objectivesname"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="objectivesname"
              id="quality-objectives-dictionary-objectivesname"
              data-cy="objectivesname"
              :class="{ valid: !v$.objectivesname.$invalid, invalid: v$.objectivesname.$invalid }"
              v-model="v$.objectivesname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualityObjectivesDictionary.objectivescontent')"
              for="quality-objectives-dictionary-objectivescontent"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="objectivescontent"
              id="quality-objectives-dictionary-objectivescontent"
              data-cy="objectivescontent"
              :class="{ valid: !v$.objectivescontent.$invalid, invalid: v$.objectivescontent.$invalid }"
              v-model="v$.objectivescontent.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualityObjectivesDictionary.calculationmethod')"
              for="quality-objectives-dictionary-calculationmethod"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="calculationmethod"
              id="quality-objectives-dictionary-calculationmethod"
              data-cy="calculationmethod"
              :class="{ valid: !v$.calculationmethod.$invalid, invalid: v$.calculationmethod.$invalid }"
              v-model="v$.calculationmethod.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualityObjectivesDictionary.frequency')"
              for="quality-objectives-dictionary-frequency"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="frequency"
              id="quality-objectives-dictionary-frequency"
              data-cy="frequency"
              :class="{ valid: !v$.frequency.$invalid, invalid: v$.frequency.$invalid }"
              v-model="v$.frequency.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualityObjectivesDictionary.evaluationcriteria')"
              for="quality-objectives-dictionary-evaluationcriteria"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="evaluationcriteria"
              id="quality-objectives-dictionary-evaluationcriteria"
              data-cy="evaluationcriteria"
              :class="{ valid: !v$.evaluationcriteria.$invalid, invalid: v$.evaluationcriteria.$invalid }"
              v-model="v$.evaluationcriteria.$model"
            />
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
<script lang="ts" src="./quality-objectives-dictionary-update.component.ts"></script>
