<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster3App.document.home.createOrEditLabel"
          data-cy="DocumentCreateUpdateHeading"
          v-text="t$('jHipster3App.document.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="document.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="document.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.document.documentid')" for="document-documentid"></label>
            <input
              type="number"
              class="form-control"
              name="documentid"
              id="document-documentid"
              data-cy="documentid"
              :class="{ valid: !v$.documentid.$invalid, invalid: v$.documentid.$invalid }"
              v-model.number="v$.documentid.$model"
            />
            <div v-if="v$.documentid.$anyDirty && v$.documentid.$invalid">
              <small class="form-text text-danger" v-for="error of v$.documentid.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.document.documentname')" for="document-documentname"></label>
            <input
              type="text"
              class="form-control"
              name="documentname"
              id="document-documentname"
              data-cy="documentname"
              :class="{ valid: !v$.documentname.$invalid, invalid: v$.documentname.$invalid }"
              v-model="v$.documentname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.document.documenttype')" for="document-documenttype"></label>
            <input
              type="number"
              class="form-control"
              name="documenttype"
              id="document-documenttype"
              data-cy="documenttype"
              :class="{ valid: !v$.documenttype.$invalid, invalid: v$.documenttype.$invalid }"
              v-model.number="v$.documenttype.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.document.documentsize')" for="document-documentsize"></label>
            <input
              type="number"
              class="form-control"
              name="documentsize"
              id="document-documentsize"
              data-cy="documentsize"
              :class="{ valid: !v$.documentsize.$invalid, invalid: v$.documentsize.$invalid }"
              v-model.number="v$.documentsize.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.document.secretlevel')" for="document-secretlevel"></label>
            <select
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="document-secretlevel"
              data-cy="secretlevel"
            >
              <option
                v-for="secretlevel in secretlevelValues"
                :key="secretlevel"
                v-bind:value="secretlevel"
                v-bind:label="t$('jHipster3App.Secretlevel.' + secretlevel)"
              >
                {{ secretlevel }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.document.createtime')" for="document-createtime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="document-createtime"
                  v-model="v$.createtime.$model"
                  name="createtime"
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
                id="document-createtime"
                data-cy="createtime"
                type="text"
                class="form-control"
                name="createtime"
                :class="{ valid: !v$.createtime.$invalid, invalid: v$.createtime.$invalid }"
                v-model="v$.createtime.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.document.creatorname')" for="document-creatorname"></label>
            <input
              type="text"
              class="form-control"
              name="creatorname"
              id="document-creatorname"
              data-cy="creatorname"
              :class="{ valid: !v$.creatorname.$invalid, invalid: v$.creatorname.$invalid }"
              v-model="v$.creatorname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.document.creatorid')" for="document-creatorid"></label>
            <select class="form-control" id="document-creatorid" data-cy="creatorid" name="creatorid" v-model="document.creatorid">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="document.creatorid && officersOption.id === document.creatorid.id ? document.creatorid : officersOption"
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
<script lang="ts" src="./document-update.component.ts"></script>
