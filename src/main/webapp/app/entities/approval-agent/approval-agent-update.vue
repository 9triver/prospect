<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster3App.approvalAgent.home.createOrEditLabel"
          data-cy="ApprovalAgentCreateUpdateHeading"
          v-text="t$('jHipster3App.approvalAgent.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="approvalAgent.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="approvalAgent.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.approvalAgent.agentid')" for="approval-agent-agentid"></label>
            <input
              type="number"
              class="form-control"
              name="agentid"
              id="approval-agent-agentid"
              data-cy="agentid"
              :class="{ valid: !v$.agentid.$invalid, invalid: v$.agentid.$invalid }"
              v-model.number="v$.agentid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.approvalAgent.agentname')" for="approval-agent-agentname"></label>
            <input
              type="text"
              class="form-control"
              name="agentname"
              id="approval-agent-agentname"
              data-cy="agentname"
              :class="{ valid: !v$.agentname.$invalid, invalid: v$.agentname.$invalid }"
              v-model="v$.agentname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.approvalAgent.agentstarttime')"
              for="approval-agent-agentstarttime"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="approval-agent-agentstarttime"
                  v-model="v$.agentstarttime.$model"
                  name="agentstarttime"
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
                id="approval-agent-agentstarttime"
                data-cy="agentstarttime"
                type="text"
                class="form-control"
                name="agentstarttime"
                :class="{ valid: !v$.agentstarttime.$invalid, invalid: v$.agentstarttime.$invalid }"
                v-model="v$.agentstarttime.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.approvalAgent.autocanceltime')"
              for="approval-agent-autocanceltime"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="approval-agent-autocanceltime"
                  v-model="v$.autocanceltime.$model"
                  name="autocanceltime"
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
                id="approval-agent-autocanceltime"
                data-cy="autocanceltime"
                type="text"
                class="form-control"
                name="autocanceltime"
                :class="{ valid: !v$.autocanceltime.$invalid, invalid: v$.autocanceltime.$invalid }"
                v-model="v$.autocanceltime.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.approvalAgent.agentdepartment')"
              for="approval-agent-agentdepartment"
            ></label>
            <input
              type="text"
              class="form-control"
              name="agentdepartment"
              id="approval-agent-agentdepartment"
              data-cy="agentdepartment"
              :class="{ valid: !v$.agentdepartment.$invalid, invalid: v$.agentdepartment.$invalid }"
              v-model="v$.agentdepartment.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.approvalAgent.originalapprovalname')"
              for="approval-agent-originalapprovalname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="originalapprovalname"
              id="approval-agent-originalapprovalname"
              data-cy="originalapprovalname"
              :class="{ valid: !v$.originalapprovalname.$invalid, invalid: v$.originalapprovalname.$invalid }"
              v-model="v$.originalapprovalname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.approvalAgent.originaldepartment')"
              for="approval-agent-originaldepartment"
            ></label>
            <input
              type="text"
              class="form-control"
              name="originaldepartment"
              id="approval-agent-originaldepartment"
              data-cy="originaldepartment"
              :class="{ valid: !v$.originaldepartment.$invalid, invalid: v$.originaldepartment.$invalid }"
              v-model="v$.originaldepartment.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.approvalAgent.secrecylevel')"
              for="approval-agent-secrecylevel"
            ></label>
            <input
              type="number"
              class="form-control"
              name="secrecylevel"
              id="approval-agent-secrecylevel"
              data-cy="secrecylevel"
              :class="{ valid: !v$.secrecylevel.$invalid, invalid: v$.secrecylevel.$invalid }"
              v-model.number="v$.secrecylevel.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.approvalAgent.originalapprovalid')"
              for="approval-agent-originalapprovalid"
            ></label>
            <select
              class="form-control"
              id="approval-agent-originalapprovalid"
              data-cy="originalapprovalid"
              name="originalapprovalid"
              v-model="approvalAgent.originalapprovalid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  approvalAgent.originalapprovalid && officersOption.id === approvalAgent.originalapprovalid.id
                    ? approvalAgent.originalapprovalid
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
<script lang="ts" src="./approval-agent-update.component.ts"></script>
