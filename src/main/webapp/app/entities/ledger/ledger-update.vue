<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster3App.ledger.home.createOrEditLabel"
          data-cy="LedgerCreateUpdateHeading"
          v-text="t$('jHipster3App.ledger.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="ledger.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="ledger.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.ledger.officers')" for="ledger-officers"></label>
            <select class="form-control" id="ledger-officers" data-cy="officers" name="officers" v-model="ledger.officers">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="ledger.officers && officersOption.id === ledger.officers.id ? ledger.officers : officersOption"
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
<script lang="ts" src="./ledger-update.component.ts"></script>
