<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster0App.progressbaseline.home.createOrEditLabel"
          data-cy="ProgressbaselineCreateUpdateHeading"
          v-text="t$('jHipster0App.progressbaseline.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="progressbaseline.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="progressbaseline.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.progressbaseline.secretlevel')"
              for="progressbaseline-secretlevel"
            ></label>
            <select
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="progressbaseline-secretlevel"
              data-cy="secretlevel"
            >
              <option
                v-for="secretlevel in secretlevelValues"
                :key="secretlevel"
                v-bind:value="secretlevel"
                v-bind:label="t$('jHipster0App.Secretlevel.' + secretlevel)"
              >
                {{ secretlevel }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.progressbaseline.requestdeportment')"
              for="progressbaseline-requestdeportment"
            ></label>
            <input
              type="text"
              class="form-control"
              name="requestdeportment"
              id="progressbaseline-requestdeportment"
              data-cy="requestdeportment"
              :class="{ valid: !v$.requestdeportment.$invalid, invalid: v$.requestdeportment.$invalid }"
              v-model="v$.requestdeportment.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.progressbaseline.chargetype')"
              for="progressbaseline-chargetype"
            ></label>
            <input
              type="number"
              class="form-control"
              name="chargetype"
              id="progressbaseline-chargetype"
              data-cy="chargetype"
              :class="{ valid: !v$.chargetype.$invalid, invalid: v$.chargetype.$invalid }"
              v-model.number="v$.chargetype.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.progressbaseline.chargecontent')"
              for="progressbaseline-chargecontent"
            ></label>
            <input
              type="text"
              class="form-control"
              name="chargecontent"
              id="progressbaseline-chargecontent"
              data-cy="chargecontent"
              :class="{ valid: !v$.chargecontent.$invalid, invalid: v$.chargecontent.$invalid }"
              v-model="v$.chargecontent.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.progressbaseline.status')" for="progressbaseline-status"></label>
            <input
              type="number"
              class="form-control"
              name="status"
              id="progressbaseline-status"
              data-cy="status"
              :class="{ valid: !v$.status.$invalid, invalid: v$.status.$invalid }"
              v-model.number="v$.status.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.progressbaseline.version')" for="progressbaseline-version"></label>
            <input
              type="number"
              class="form-control"
              name="version"
              id="progressbaseline-version"
              data-cy="version"
              :class="{ valid: !v$.version.$invalid, invalid: v$.version.$invalid }"
              v-model.number="v$.version.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.progressbaseline.remark')" for="progressbaseline-remark"></label>
            <input
              type="text"
              class="form-control"
              name="remark"
              id="progressbaseline-remark"
              data-cy="remark"
              :class="{ valid: !v$.remark.$invalid, invalid: v$.remark.$invalid }"
              v-model="v$.remark.$model"
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
<script lang="ts" src="./progressbaseline-update.component.ts"></script>
