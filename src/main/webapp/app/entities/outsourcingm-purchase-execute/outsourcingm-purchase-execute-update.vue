<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster3App.outsourcingmPurchaseExecute.home.createOrEditLabel"
          data-cy="OutsourcingmPurchaseExecuteCreateUpdateHeading"
          v-text="t$('jHipster3App.outsourcingmPurchaseExecute.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="outsourcingmPurchaseExecute.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="outsourcingmPurchaseExecute.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.outsourcingmPurchaseExecute.outsourcingexecuteid')"
              for="outsourcingm-purchase-execute-outsourcingexecuteid"
            ></label>
            <input
              type="number"
              class="form-control"
              name="outsourcingexecuteid"
              id="outsourcingm-purchase-execute-outsourcingexecuteid"
              data-cy="outsourcingexecuteid"
              :class="{ valid: !v$.outsourcingexecuteid.$invalid, invalid: v$.outsourcingexecuteid.$invalid }"
              v-model.number="v$.outsourcingexecuteid.$model"
            />
            <div v-if="v$.outsourcingexecuteid.$anyDirty && v$.outsourcingexecuteid.$invalid">
              <small class="form-text text-danger" v-for="error of v$.outsourcingexecuteid.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.outsourcingmPurchaseExecute.matarialname')"
              for="outsourcingm-purchase-execute-matarialname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="matarialname"
              id="outsourcingm-purchase-execute-matarialname"
              data-cy="matarialname"
              :class="{ valid: !v$.matarialname.$invalid, invalid: v$.matarialname.$invalid }"
              v-model="v$.matarialname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.outsourcingmPurchaseExecute.purchasingmethod')"
              for="outsourcingm-purchase-execute-purchasingmethod"
            ></label>
            <input
              type="number"
              class="form-control"
              name="purchasingmethod"
              id="outsourcingm-purchase-execute-purchasingmethod"
              data-cy="purchasingmethod"
              :class="{ valid: !v$.purchasingmethod.$invalid, invalid: v$.purchasingmethod.$invalid }"
              v-model.number="v$.purchasingmethod.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.outsourcingmPurchaseExecute.budgit')"
              for="outsourcingm-purchase-execute-budgit"
            ></label>
            <input
              type="number"
              class="form-control"
              name="budgit"
              id="outsourcingm-purchase-execute-budgit"
              data-cy="budgit"
              :class="{ valid: !v$.budgit.$invalid, invalid: v$.budgit.$invalid }"
              v-model.number="v$.budgit.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.outsourcingmPurchaseExecute.needtime')"
              for="outsourcingm-purchase-execute-needtime"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="outsourcingm-purchase-execute-needtime"
                  v-model="v$.needtime.$model"
                  name="needtime"
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
                id="outsourcingm-purchase-execute-needtime"
                data-cy="needtime"
                type="text"
                class="form-control"
                name="needtime"
                :class="{ valid: !v$.needtime.$invalid, invalid: v$.needtime.$invalid }"
                v-model="v$.needtime.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.outsourcingmPurchaseExecute.planusetime')"
              for="outsourcingm-purchase-execute-planusetime"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="outsourcingm-purchase-execute-planusetime"
                  v-model="v$.planusetime.$model"
                  name="planusetime"
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
                id="outsourcingm-purchase-execute-planusetime"
                data-cy="planusetime"
                type="text"
                class="form-control"
                name="planusetime"
                :class="{ valid: !v$.planusetime.$invalid, invalid: v$.planusetime.$invalid }"
                v-model="v$.planusetime.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.outsourcingmPurchaseExecute.supplierid')"
              for="outsourcingm-purchase-execute-supplierid"
            ></label>
            <input
              type="number"
              class="form-control"
              name="supplierid"
              id="outsourcingm-purchase-execute-supplierid"
              data-cy="supplierid"
              :class="{ valid: !v$.supplierid.$invalid, invalid: v$.supplierid.$invalid }"
              v-model.number="v$.supplierid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.outsourcingmPurchaseExecute.price')"
              for="outsourcingm-purchase-execute-price"
            ></label>
            <input
              type="number"
              class="form-control"
              name="price"
              id="outsourcingm-purchase-execute-price"
              data-cy="price"
              :class="{ valid: !v$.price.$invalid, invalid: v$.price.$invalid }"
              v-model.number="v$.price.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.outsourcingmPurchaseExecute.secretlevel')"
              for="outsourcingm-purchase-execute-secretlevel"
            ></label>
            <select
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="outsourcingm-purchase-execute-secretlevel"
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
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.outsourcingmPurchaseExecute.outsourcingplanid')"
              for="outsourcingm-purchase-execute-outsourcingplanid"
            ></label>
            <select
              class="form-control"
              id="outsourcingm-purchase-execute-outsourcingplanid"
              data-cy="outsourcingplanid"
              name="outsourcingplanid"
              v-model="outsourcingmPurchaseExecute.outsourcingplanid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  outsourcingmPurchaseExecute.outsourcingplanid &&
                  outsourcingmPurchasePlanOption.id === outsourcingmPurchaseExecute.outsourcingplanid.id
                    ? outsourcingmPurchaseExecute.outsourcingplanid
                    : outsourcingmPurchasePlanOption
                "
                v-for="outsourcingmPurchasePlanOption in outsourcingmPurchasePlans"
                :key="outsourcingmPurchasePlanOption.id"
              >
                {{ outsourcingmPurchasePlanOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.outsourcingmPurchaseExecute.responsibleid')"
              for="outsourcingm-purchase-execute-responsibleid"
            ></label>
            <select
              class="form-control"
              id="outsourcingm-purchase-execute-responsibleid"
              data-cy="responsibleid"
              name="responsibleid"
              v-model="outsourcingmPurchaseExecute.responsibleid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  outsourcingmPurchaseExecute.responsibleid && officersOption.id === outsourcingmPurchaseExecute.responsibleid.id
                    ? outsourcingmPurchaseExecute.responsibleid
                    : officersOption
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
<script lang="ts" src="./outsourcingm-purchase-execute-update.component.ts"></script>
