<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster3App.unitbudget.home.createOrEditLabel"
          data-cy="UnitbudgetCreateUpdateHeading"
          v-text="t$('jHipster3App.unitbudget.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="unitbudget.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="unitbudget.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.unitbudget.unitbudgetid')" for="unitbudget-unitbudgetid"></label>
            <input
              type="number"
              class="form-control"
              name="unitbudgetid"
              id="unitbudget-unitbudgetid"
              data-cy="unitbudgetid"
              :class="{ valid: !v$.unitbudgetid.$invalid, invalid: v$.unitbudgetid.$invalid }"
              v-model.number="v$.unitbudgetid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.unitbudget.subprojectname')" for="unitbudget-subprojectname"></label>
            <input
              type="text"
              class="form-control"
              name="subprojectname"
              id="unitbudget-subprojectname"
              data-cy="subprojectname"
              :class="{ valid: !v$.subprojectname.$invalid, invalid: v$.subprojectname.$invalid }"
              v-model="v$.subprojectname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.unitbudget.unitbudgername')" for="unitbudget-unitbudgername"></label>
            <input
              type="text"
              class="form-control"
              name="unitbudgername"
              id="unitbudget-unitbudgername"
              data-cy="unitbudgername"
              :class="{ valid: !v$.unitbudgername.$invalid, invalid: v$.unitbudgername.$invalid }"
              v-model="v$.unitbudgername.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.unitbudget.billingunit')" for="unitbudget-billingunit"></label>
            <input
              type="number"
              class="form-control"
              name="billingunit"
              id="unitbudget-billingunit"
              data-cy="billingunit"
              :class="{ valid: !v$.billingunit.$invalid, invalid: v$.billingunit.$invalid }"
              v-model.number="v$.billingunit.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.unitbudget.number')" for="unitbudget-number"></label>
            <input
              type="number"
              class="form-control"
              name="number"
              id="unitbudget-number"
              data-cy="number"
              :class="{ valid: !v$.number.$invalid, invalid: v$.number.$invalid }"
              v-model.number="v$.number.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.unitbudget.totalbudget')" for="unitbudget-totalbudget"></label>
            <input
              type="number"
              class="form-control"
              name="totalbudget"
              id="unitbudget-totalbudget"
              data-cy="totalbudget"
              :class="{ valid: !v$.totalbudget.$invalid, invalid: v$.totalbudget.$invalid }"
              v-model.number="v$.totalbudget.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.unitbudget.maintainerbudget')"
              for="unitbudget-maintainerbudget"
            ></label>
            <input
              type="number"
              class="form-control"
              name="maintainerbudget"
              id="unitbudget-maintainerbudget"
              data-cy="maintainerbudget"
              :class="{ valid: !v$.maintainerbudget.$invalid, invalid: v$.maintainerbudget.$invalid }"
              v-model.number="v$.maintainerbudget.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.unitbudget.outsourcingbudget')"
              for="unitbudget-outsourcingbudget"
            ></label>
            <input
              type="number"
              class="form-control"
              name="outsourcingbudget"
              id="unitbudget-outsourcingbudget"
              data-cy="outsourcingbudget"
              :class="{ valid: !v$.outsourcingbudget.$invalid, invalid: v$.outsourcingbudget.$invalid }"
              v-model.number="v$.outsourcingbudget.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.unitbudget.earmarkedbudget')"
              for="unitbudget-earmarkedbudget"
            ></label>
            <input
              type="number"
              class="form-control"
              name="earmarkedbudget"
              id="unitbudget-earmarkedbudget"
              data-cy="earmarkedbudget"
              :class="{ valid: !v$.earmarkedbudget.$invalid, invalid: v$.earmarkedbudget.$invalid }"
              v-model.number="v$.earmarkedbudget.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.unitbudget.testbudget')" for="unitbudget-testbudget"></label>
            <input
              type="number"
              class="form-control"
              name="testbudget"
              id="unitbudget-testbudget"
              data-cy="testbudget"
              :class="{ valid: !v$.testbudget.$invalid, invalid: v$.testbudget.$invalid }"
              v-model.number="v$.testbudget.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.unitbudget.creatorid')" for="unitbudget-creatorid"></label>
            <select class="form-control" id="unitbudget-creatorid" data-cy="creatorid" name="creatorid" v-model="unitbudget.creatorid">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="unitbudget.creatorid && officersOption.id === unitbudget.creatorid.id ? unitbudget.creatorid : officersOption"
                v-for="officersOption in officers"
                :key="officersOption.id"
              >
                {{ officersOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.unitbudget.auditorid')" for="unitbudget-auditorid"></label>
            <select class="form-control" id="unitbudget-auditorid" data-cy="auditorid" name="auditorid" v-model="unitbudget.auditorid">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="unitbudget.auditorid && officersOption.id === unitbudget.auditorid.id ? unitbudget.auditorid : officersOption"
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
<script lang="ts" src="./unitbudget-update.component.ts"></script>
