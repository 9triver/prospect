<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.communicationDictionary.home.createOrEditLabel"
          data-cy="CommunicationDictionaryCreateUpdateHeading"
          v-text="t$('jy1App.communicationDictionary.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="communicationDictionary.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="communicationDictionary.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.communicationDictionary.partiesname')"
              for="communication-dictionary-partiesname"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="partiesname"
              id="communication-dictionary-partiesname"
              data-cy="partiesname"
              :class="{ valid: !v$.partiesname.$invalid, invalid: v$.partiesname.$invalid }"
              v-model="v$.partiesname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.communicationDictionary.partiestype')"
              for="communication-dictionary-partiestype"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="partiestype"
              id="communication-dictionary-partiestype"
              data-cy="partiestype"
              :class="{ valid: !v$.partiestype.$invalid, invalid: v$.partiestype.$invalid }"
              v-model="v$.partiestype.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.communicationDictionary.partiesduty')"
              for="communication-dictionary-partiesduty"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="partiesduty"
              id="communication-dictionary-partiesduty"
              data-cy="partiesduty"
              :class="{ valid: !v$.partiesduty.$invalid, invalid: v$.partiesduty.$invalid }"
              v-model="v$.partiesduty.$model"
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
<script lang="ts" src="./communication-dictionary-update.component.ts"></script>
