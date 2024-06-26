<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster0App.comprehensiveledger.home.createOrEditLabel"
          data-cy="ComprehensiveledgerCreateUpdateHeading"
          v-text="t$('jHipster0App.comprehensiveledger.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="comprehensiveledger.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="comprehensiveledger.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.comprehensiveledger.fundsname')"
              for="comprehensiveledger-fundsname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="fundsname"
              id="comprehensiveledger-fundsname"
              data-cy="fundsname"
              :class="{ valid: !v$.fundsname.$invalid, invalid: v$.fundsname.$invalid }"
              v-model="v$.fundsname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.comprehensiveledger.wbsname')"
              for="comprehensiveledger-wbsname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsname"
              id="comprehensiveledger-wbsname"
              data-cy="wbsname"
              :class="{ valid: !v$.wbsname.$invalid, invalid: v$.wbsname.$invalid }"
              v-model="v$.wbsname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.comprehensiveledger.unitname')"
              for="comprehensiveledger-unitname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="unitname"
              id="comprehensiveledger-unitname"
              data-cy="unitname"
              :class="{ valid: !v$.unitname.$invalid, invalid: v$.unitname.$invalid }"
              v-model="v$.unitname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.comprehensiveledger.budgetsection')"
              for="comprehensiveledger-budgetsection"
            ></label>
            <input
              type="text"
              class="form-control"
              name="budgetsection"
              id="comprehensiveledger-budgetsection"
              data-cy="budgetsection"
              :class="{ valid: !v$.budgetsection.$invalid, invalid: v$.budgetsection.$invalid }"
              v-model="v$.budgetsection.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.comprehensiveledger.purpose')"
              for="comprehensiveledger-purpose"
            ></label>
            <input
              type="text"
              class="form-control"
              name="purpose"
              id="comprehensiveledger-purpose"
              data-cy="purpose"
              :class="{ valid: !v$.purpose.$invalid, invalid: v$.purpose.$invalid }"
              v-model="v$.purpose.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.comprehensiveledger.unit')" for="comprehensiveledger-unit"></label>
            <input
              type="text"
              class="form-control"
              name="unit"
              id="comprehensiveledger-unit"
              data-cy="unit"
              :class="{ valid: !v$.unit.$invalid, invalid: v$.unit.$invalid }"
              v-model="v$.unit.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.comprehensiveledger.number')"
              for="comprehensiveledger-number"
            ></label>
            <input
              type="number"
              class="form-control"
              name="number"
              id="comprehensiveledger-number"
              data-cy="number"
              :class="{ valid: !v$.number.$invalid, invalid: v$.number.$invalid }"
              v-model.number="v$.number.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.comprehensiveledger.unitprice')"
              for="comprehensiveledger-unitprice"
            ></label>
            <input
              type="number"
              class="form-control"
              name="unitprice"
              id="comprehensiveledger-unitprice"
              data-cy="unitprice"
              :class="{ valid: !v$.unitprice.$invalid, invalid: v$.unitprice.$invalid }"
              v-model.number="v$.unitprice.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.comprehensiveledger.foreignexchange')"
              for="comprehensiveledger-foreignexchange"
            ></label>
            <input
              type="number"
              class="form-control"
              name="foreignexchange"
              id="comprehensiveledger-foreignexchange"
              data-cy="foreignexchange"
              :class="{ valid: !v$.foreignexchange.$invalid, invalid: v$.foreignexchange.$invalid }"
              v-model.number="v$.foreignexchange.$model"
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
<script lang="ts" src="./comprehensiveledger-update.component.ts"></script>
