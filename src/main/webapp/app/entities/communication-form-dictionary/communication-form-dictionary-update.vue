<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.communicationFormDictionary.home.createOrEditLabel"
          data-cy="CommunicationFormDictionaryCreateUpdateHeading"
          v-text="t$('jy1App.communicationFormDictionary.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="communicationFormDictionary.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="communicationFormDictionary.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.communicationFormDictionary.communicationformname')"
              for="communication-form-dictionary-communicationformname"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="communicationformname"
              id="communication-form-dictionary-communicationformname"
              data-cy="communicationformname"
              :class="{ valid: !v$.communicationformname.$invalid, invalid: v$.communicationformname.$invalid }"
              v-model="v$.communicationformname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.communicationFormDictionary.communicationformtype')"
              for="communication-form-dictionary-communicationformtype"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="communicationformtype"
              id="communication-form-dictionary-communicationformtype"
              data-cy="communicationformtype"
              :class="{ valid: !v$.communicationformtype.$invalid, invalid: v$.communicationformtype.$invalid }"
              v-model="v$.communicationformtype.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.communicationFormDictionary.status')"
              for="communication-form-dictionary-status"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="status"
              id="communication-form-dictionary-status"
              data-cy="status"
              :class="{ valid: !v$.status.$invalid, invalid: v$.status.$invalid }"
              v-model="v$.status.$model"
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
<script lang="ts" src="./communication-form-dictionary-update.component.ts"></script>
