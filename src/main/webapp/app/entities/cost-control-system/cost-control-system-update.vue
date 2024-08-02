<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.costControlSystem.home.createOrEditLabel"
          data-cy="CostControlSystemCreateUpdateHeading"
          v-text="t$('jy1App.costControlSystem.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="costControlSystem.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="costControlSystem.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.costControlSystem.type')" for="cost-control-system-type"></label>
            <input
              type="number"
              class="form-control"
              name="type"
              id="cost-control-system-type"
              data-cy="type"
              :class="{ valid: !v$.type.$invalid, invalid: v$.type.$invalid }"
              v-model.number="v$.type.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.costControlSystem.subject')" for="cost-control-system-subject"></label>
            <select
              class="form-control"
              name="subject"
              :class="{ valid: !v$.subject.$invalid, invalid: v$.subject.$invalid }"
              v-model="v$.subject.$model"
              id="cost-control-system-subject"
              data-cy="subject"
            >
              <option
                v-for="contractSubject in contractSubjectValues"
                :key="contractSubject"
                v-bind:value="contractSubject"
                v-bind:label="t$('jy1App.ContractSubject.' + contractSubject)"
              >
                {{ contractSubject }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.costControlSystem.implementedamount')"
              for="cost-control-system-implementedamount"
            ></label>
            <input
              type="number"
              class="form-control"
              name="implementedamount"
              id="cost-control-system-implementedamount"
              data-cy="implementedamount"
              :class="{ valid: !v$.implementedamount.$invalid, invalid: v$.implementedamount.$invalid }"
              v-model.number="v$.implementedamount.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.costControlSystem.approvedamount')"
              for="cost-control-system-approvedamount"
            ></label>
            <input
              type="number"
              class="form-control"
              name="approvedamount"
              id="cost-control-system-approvedamount"
              data-cy="approvedamount"
              :class="{ valid: !v$.approvedamount.$invalid, invalid: v$.approvedamount.$invalid }"
              v-model.number="v$.approvedamount.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.costControlSystem.pendingimplementationamount')"
              for="cost-control-system-pendingimplementationamount"
            ></label>
            <input
              type="number"
              class="form-control"
              name="pendingimplementationamount"
              id="cost-control-system-pendingimplementationamount"
              data-cy="pendingimplementationamount"
              :class="{ valid: !v$.pendingimplementationamount.$invalid, invalid: v$.pendingimplementationamount.$invalid }"
              v-model.number="v$.pendingimplementationamount.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.costControlSystem.contractpaymentamount')"
              for="cost-control-system-contractpaymentamount"
            ></label>
            <input
              type="number"
              class="form-control"
              name="contractpaymentamount"
              id="cost-control-system-contractpaymentamount"
              data-cy="contractpaymentamount"
              :class="{ valid: !v$.contractpaymentamount.$invalid, invalid: v$.contractpaymentamount.$invalid }"
              v-model.number="v$.contractpaymentamount.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.costControlSystem.managementregistrationnumber')"
              for="cost-control-system-managementregistrationnumber"
            ></label>
            <input
              type="number"
              class="form-control"
              name="managementregistrationnumber"
              id="cost-control-system-managementregistrationnumber"
              data-cy="managementregistrationnumber"
              :class="{ valid: !v$.managementregistrationnumber.$invalid, invalid: v$.managementregistrationnumber.$invalid }"
              v-model.number="v$.managementregistrationnumber.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.costControlSystem.financialregistrationnumber')"
              for="cost-control-system-financialregistrationnumber"
            ></label>
            <input
              type="number"
              class="form-control"
              name="financialregistrationnumber"
              id="cost-control-system-financialregistrationnumber"
              data-cy="financialregistrationnumber"
              :class="{ valid: !v$.financialregistrationnumber.$invalid, invalid: v$.financialregistrationnumber.$invalid }"
              v-model.number="v$.financialregistrationnumber.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.costControlSystem.contractbudgetamount')"
              for="cost-control-system-contractbudgetamount"
            ></label>
            <input
              type="number"
              class="form-control"
              name="contractbudgetamount"
              id="cost-control-system-contractbudgetamount"
              data-cy="contractbudgetamount"
              :class="{ valid: !v$.contractbudgetamount.$invalid, invalid: v$.contractbudgetamount.$invalid }"
              v-model.number="v$.contractbudgetamount.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.costControlSystem.contractsigningamount')"
              for="cost-control-system-contractsigningamount"
            ></label>
            <input
              type="number"
              class="form-control"
              name="contractsigningamount"
              id="cost-control-system-contractsigningamount"
              data-cy="contractsigningamount"
              :class="{ valid: !v$.contractsigningamount.$invalid, invalid: v$.contractsigningamount.$invalid }"
              v-model.number="v$.contractsigningamount.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.costControlSystem.contractsettlementamount')"
              for="cost-control-system-contractsettlementamount"
            ></label>
            <input
              type="number"
              class="form-control"
              name="contractsettlementamount"
              id="cost-control-system-contractsettlementamount"
              data-cy="contractsettlementamount"
              :class="{ valid: !v$.contractsettlementamount.$invalid, invalid: v$.contractsettlementamount.$invalid }"
              v-model.number="v$.contractsettlementamount.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.costControlSystem.unforeseeableamount')"
              for="cost-control-system-unforeseeableamount"
            ></label>
            <input
              type="number"
              class="form-control"
              name="unforeseeableamount"
              id="cost-control-system-unforeseeableamount"
              data-cy="unforeseeableamount"
              :class="{ valid: !v$.unforeseeableamount.$invalid, invalid: v$.unforeseeableamount.$invalid }"
              v-model.number="v$.unforeseeableamount.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.costControlSystem.invoicepaymentamount')"
              for="cost-control-system-invoicepaymentamount"
            ></label>
            <input
              type="number"
              class="form-control"
              name="invoicepaymentamount"
              id="cost-control-system-invoicepaymentamount"
              data-cy="invoicepaymentamount"
              :class="{ valid: !v$.invoicepaymentamount.$invalid, invalid: v$.invoicepaymentamount.$invalid }"
              v-model.number="v$.invoicepaymentamount.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.costControlSystem.loanpaymentamount')"
              for="cost-control-system-loanpaymentamount"
            ></label>
            <input
              type="number"
              class="form-control"
              name="loanpaymentamount"
              id="cost-control-system-loanpaymentamount"
              data-cy="loanpaymentamount"
              :class="{ valid: !v$.loanpaymentamount.$invalid, invalid: v$.loanpaymentamount.$invalid }"
              v-model.number="v$.loanpaymentamount.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.costControlSystem.accountoutstandingamount')"
              for="cost-control-system-accountoutstandingamount"
            ></label>
            <input
              type="number"
              class="form-control"
              name="accountoutstandingamount"
              id="cost-control-system-accountoutstandingamount"
              data-cy="accountoutstandingamount"
              :class="{ valid: !v$.accountoutstandingamount.$invalid, invalid: v$.accountoutstandingamount.$invalid }"
              v-model.number="v$.accountoutstandingamount.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.costControlSystem.pendingpaymentamount')"
              for="cost-control-system-pendingpaymentamount"
            ></label>
            <input
              type="number"
              class="form-control"
              name="pendingpaymentamount"
              id="cost-control-system-pendingpaymentamount"
              data-cy="pendingpaymentamount"
              :class="{ valid: !v$.pendingpaymentamount.$invalid, invalid: v$.pendingpaymentamount.$invalid }"
              v-model.number="v$.pendingpaymentamount.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.costControlSystem.pendinginvoiceamount')"
              for="cost-control-system-pendinginvoiceamount"
            ></label>
            <input
              type="number"
              class="form-control"
              name="pendinginvoiceamount"
              id="cost-control-system-pendinginvoiceamount"
              data-cy="pendinginvoiceamount"
              :class="{ valid: !v$.pendinginvoiceamount.$invalid, invalid: v$.pendinginvoiceamount.$invalid }"
              v-model.number="v$.pendinginvoiceamount.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.costControlSystem.responsibleperson')"
              for="cost-control-system-responsibleperson"
            ></label>
            <select
              class="form-control"
              id="cost-control-system-responsibleperson"
              data-cy="responsibleperson"
              name="responsibleperson"
              v-model="costControlSystem.responsibleperson"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  costControlSystem.responsibleperson && officersOption.id === costControlSystem.responsibleperson.id
                    ? costControlSystem.responsibleperson
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
            <label class="form-control-label" v-text="t$('jy1App.costControlSystem.auditorid')" for="cost-control-system-auditorid"></label>
            <select
              class="form-control"
              id="cost-control-system-auditorid"
              data-cy="auditorid"
              name="auditorid"
              v-model="costControlSystem.auditorid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  costControlSystem.auditorid && officersOption.id === costControlSystem.auditorid.id
                    ? costControlSystem.auditorid
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
            <label v-text="t$('jy1App.costControlSystem.projectwbs')" for="cost-control-system-projectwbs"></label>
            <select
              class="form-control"
              id="cost-control-system-projectwbs"
              data-cy="projectwbs"
              multiple
              name="projectwbs"
              v-if="costControlSystem.projectwbs !== undefined"
              v-model="costControlSystem.projectwbs"
            >
              <option
                v-bind:value="getSelected(costControlSystem.projectwbs, projectwbsOption, 'id')"
                v-for="projectwbsOption in projectwbs"
                :key="projectwbsOption.id"
              >
                {{ projectwbsOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.costControlSystem.contract')" for="cost-control-system-contract"></label>
            <select
              class="form-control"
              id="cost-control-system-contracts"
              data-cy="contract"
              multiple
              name="contract"
              v-if="costControlSystem.contracts !== undefined"
              v-model="costControlSystem.contracts"
            >
              <option
                v-bind:value="getSelected(costControlSystem.contracts, contractOption, 'id')"
                v-for="contractOption in contracts"
                :key="contractOption.id"
              >
                {{ contractOption.id }}
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
<script lang="ts" src="./cost-control-system-update.component.ts"></script>
