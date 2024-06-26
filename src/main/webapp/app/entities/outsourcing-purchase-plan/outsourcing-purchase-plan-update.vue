<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster0App.outsourcingPurchasePlan.home.createOrEditLabel"
          data-cy="OutsourcingPurchasePlanCreateUpdateHeading"
          v-text="t$('jHipster0App.outsourcingPurchasePlan.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="outsourcingPurchasePlan.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="outsourcingPurchasePlan.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.outsourcingPurchasePlan.matarialname')"
              for="outsourcing-purchase-plan-matarialname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="matarialname"
              id="outsourcing-purchase-plan-matarialname"
              data-cy="matarialname"
              :class="{ valid: !v$.matarialname.$invalid, invalid: v$.matarialname.$invalid }"
              v-model="v$.matarialname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.outsourcingPurchasePlan.purchasingmethod')"
              for="outsourcing-purchase-plan-purchasingmethod"
            ></label>
            <input
              type="number"
              class="form-control"
              name="purchasingmethod"
              id="outsourcing-purchase-plan-purchasingmethod"
              data-cy="purchasingmethod"
              :class="{ valid: !v$.purchasingmethod.$invalid, invalid: v$.purchasingmethod.$invalid }"
              v-model.number="v$.purchasingmethod.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.outsourcingPurchasePlan.budgit')"
              for="outsourcing-purchase-plan-budgit"
            ></label>
            <input
              type="number"
              class="form-control"
              name="budgit"
              id="outsourcing-purchase-plan-budgit"
              data-cy="budgit"
              :class="{ valid: !v$.budgit.$invalid, invalid: v$.budgit.$invalid }"
              v-model.number="v$.budgit.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.outsourcingPurchasePlan.needtime')"
              for="outsourcing-purchase-plan-needtime"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="outsourcing-purchase-plan-needtime"
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
                id="outsourcing-purchase-plan-needtime"
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
              v-text="t$('jHipster0App.outsourcingPurchasePlan.planusetime')"
              for="outsourcing-purchase-plan-planusetime"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="outsourcing-purchase-plan-planusetime"
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
                id="outsourcing-purchase-plan-planusetime"
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
              v-text="t$('jHipster0App.outsourcingPurchasePlan.supplierid')"
              for="outsourcing-purchase-plan-supplierid"
            ></label>
            <input
              type="number"
              class="form-control"
              name="supplierid"
              id="outsourcing-purchase-plan-supplierid"
              data-cy="supplierid"
              :class="{ valid: !v$.supplierid.$invalid, invalid: v$.supplierid.$invalid }"
              v-model.number="v$.supplierid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.outsourcingPurchasePlan.price')"
              for="outsourcing-purchase-plan-price"
            ></label>
            <input
              type="number"
              class="form-control"
              name="price"
              id="outsourcing-purchase-plan-price"
              data-cy="price"
              :class="{ valid: !v$.price.$invalid, invalid: v$.price.$invalid }"
              v-model.number="v$.price.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.outsourcingPurchasePlan.secretlevel')"
              for="outsourcing-purchase-plan-secretlevel"
            ></label>
            <select
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="outsourcing-purchase-plan-secretlevel"
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
              v-text="t$('jHipster0App.outsourcingPurchasePlan.auditStatus')"
              for="outsourcing-purchase-plan-auditStatus"
            ></label>
            <select
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="outsourcing-purchase-plan-auditStatus"
              data-cy="auditStatus"
            >
              <option
                v-for="auditStatus in auditStatusValues"
                :key="auditStatus"
                v-bind:value="auditStatus"
                v-bind:label="t$('jHipster0App.AuditStatus.' + auditStatus)"
              >
                {{ auditStatus }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.outsourcingPurchasePlan.project')"
              for="outsourcing-purchase-plan-project"
            ></label>
            <select
              class="form-control"
              id="outsourcing-purchase-plan-project"
              data-cy="project"
              name="project"
              v-model="outsourcingPurchasePlan.project"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  outsourcingPurchasePlan.project && projectOption.id === outsourcingPurchasePlan.project.id
                    ? outsourcingPurchasePlan.project
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
              v-text="t$('jHipster0App.outsourcingPurchasePlan.responsibleid')"
              for="outsourcing-purchase-plan-responsibleid"
            ></label>
            <select
              class="form-control"
              id="outsourcing-purchase-plan-responsibleid"
              data-cy="responsibleid"
              name="responsibleid"
              v-model="outsourcingPurchasePlan.responsibleid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  outsourcingPurchasePlan.responsibleid && officersOption.id === outsourcingPurchasePlan.responsibleid.id
                    ? outsourcingPurchasePlan.responsibleid
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
              v-text="t$('jHipster0App.outsourcingPurchasePlan.auditorid')"
              for="outsourcing-purchase-plan-auditorid"
            ></label>
            <select
              class="form-control"
              id="outsourcing-purchase-plan-auditorid"
              data-cy="auditorid"
              name="auditorid"
              v-model="outsourcingPurchasePlan.auditorid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  outsourcingPurchasePlan.auditorid && officersOption.id === outsourcingPurchasePlan.auditorid.id
                    ? outsourcingPurchasePlan.auditorid
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
<script lang="ts" src="./outsourcing-purchase-plan-update.component.ts"></script>
