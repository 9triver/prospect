<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster3App.outsourcingmPurchasePlan.home.createOrEditLabel"
          data-cy="OutsourcingmPurchasePlanCreateUpdateHeading"
          v-text="t$('jHipster3App.outsourcingmPurchasePlan.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="outsourcingmPurchasePlan.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="outsourcingmPurchasePlan.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.outsourcingmPurchasePlan.outsourcingplanid')"
              for="outsourcingm-purchase-plan-outsourcingplanid"
            ></label>
            <input
              type="number"
              class="form-control"
              name="outsourcingplanid"
              id="outsourcingm-purchase-plan-outsourcingplanid"
              data-cy="outsourcingplanid"
              :class="{ valid: !v$.outsourcingplanid.$invalid, invalid: v$.outsourcingplanid.$invalid }"
              v-model.number="v$.outsourcingplanid.$model"
            />
            <div v-if="v$.outsourcingplanid.$anyDirty && v$.outsourcingplanid.$invalid">
              <small class="form-text text-danger" v-for="error of v$.outsourcingplanid.$errors" :key="error.$uid">{{
                error.$message
              }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.outsourcingmPurchasePlan.matarialname')"
              for="outsourcingm-purchase-plan-matarialname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="matarialname"
              id="outsourcingm-purchase-plan-matarialname"
              data-cy="matarialname"
              :class="{ valid: !v$.matarialname.$invalid, invalid: v$.matarialname.$invalid }"
              v-model="v$.matarialname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.outsourcingmPurchasePlan.purchasingmethod')"
              for="outsourcingm-purchase-plan-purchasingmethod"
            ></label>
            <input
              type="number"
              class="form-control"
              name="purchasingmethod"
              id="outsourcingm-purchase-plan-purchasingmethod"
              data-cy="purchasingmethod"
              :class="{ valid: !v$.purchasingmethod.$invalid, invalid: v$.purchasingmethod.$invalid }"
              v-model.number="v$.purchasingmethod.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.outsourcingmPurchasePlan.budgit')"
              for="outsourcingm-purchase-plan-budgit"
            ></label>
            <input
              type="number"
              class="form-control"
              name="budgit"
              id="outsourcingm-purchase-plan-budgit"
              data-cy="budgit"
              :class="{ valid: !v$.budgit.$invalid, invalid: v$.budgit.$invalid }"
              v-model.number="v$.budgit.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.outsourcingmPurchasePlan.needtime')"
              for="outsourcingm-purchase-plan-needtime"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="outsourcingm-purchase-plan-needtime"
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
                id="outsourcingm-purchase-plan-needtime"
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
              v-text="t$('jHipster3App.outsourcingmPurchasePlan.planusetime')"
              for="outsourcingm-purchase-plan-planusetime"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="outsourcingm-purchase-plan-planusetime"
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
                id="outsourcingm-purchase-plan-planusetime"
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
              v-text="t$('jHipster3App.outsourcingmPurchasePlan.supplierid')"
              for="outsourcingm-purchase-plan-supplierid"
            ></label>
            <input
              type="number"
              class="form-control"
              name="supplierid"
              id="outsourcingm-purchase-plan-supplierid"
              data-cy="supplierid"
              :class="{ valid: !v$.supplierid.$invalid, invalid: v$.supplierid.$invalid }"
              v-model.number="v$.supplierid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.outsourcingmPurchasePlan.price')"
              for="outsourcingm-purchase-plan-price"
            ></label>
            <input
              type="number"
              class="form-control"
              name="price"
              id="outsourcingm-purchase-plan-price"
              data-cy="price"
              :class="{ valid: !v$.price.$invalid, invalid: v$.price.$invalid }"
              v-model.number="v$.price.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.outsourcingmPurchasePlan.secretlevel')"
              for="outsourcingm-purchase-plan-secretlevel"
            ></label>
            <select
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="outsourcingm-purchase-plan-secretlevel"
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
              v-text="t$('jHipster3App.outsourcingmPurchasePlan.auditStatus')"
              for="outsourcingm-purchase-plan-auditStatus"
            ></label>
            <select
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="outsourcingm-purchase-plan-auditStatus"
              data-cy="auditStatus"
            >
              <option
                v-for="auditStatus in auditStatusValues"
                :key="auditStatus"
                v-bind:value="auditStatus"
                v-bind:label="t$('jHipster3App.AuditStatus.' + auditStatus)"
              >
                {{ auditStatus }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.outsourcingmPurchasePlan.project')"
              for="outsourcingm-purchase-plan-project"
            ></label>
            <select
              class="form-control"
              id="outsourcingm-purchase-plan-project"
              data-cy="project"
              name="project"
              v-model="outsourcingmPurchasePlan.project"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  outsourcingmPurchasePlan.project && projectOption.id === outsourcingmPurchasePlan.project.id
                    ? outsourcingmPurchasePlan.project
                    : projectOption
                "
                v-for="projectOption in projects"
                :key="projectOption.id"
              >
                {{ projectOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.outsourcingmPurchasePlan.responsibleid')"
              for="outsourcingm-purchase-plan-responsibleid"
            ></label>
            <select
              class="form-control"
              id="outsourcingm-purchase-plan-responsibleid"
              data-cy="responsibleid"
              name="responsibleid"
              v-model="outsourcingmPurchasePlan.responsibleid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  outsourcingmPurchasePlan.responsibleid && officersOption.id === outsourcingmPurchasePlan.responsibleid.id
                    ? outsourcingmPurchasePlan.responsibleid
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
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.outsourcingmPurchasePlan.auditorid')"
              for="outsourcingm-purchase-plan-auditorid"
            ></label>
            <select
              class="form-control"
              id="outsourcingm-purchase-plan-auditorid"
              data-cy="auditorid"
              name="auditorid"
              v-model="outsourcingmPurchasePlan.auditorid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  outsourcingmPurchasePlan.auditorid && officersOption.id === outsourcingmPurchasePlan.auditorid.id
                    ? outsourcingmPurchasePlan.auditorid
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
<script lang="ts" src="./outsourcingm-purchase-plan-update.component.ts"></script>
