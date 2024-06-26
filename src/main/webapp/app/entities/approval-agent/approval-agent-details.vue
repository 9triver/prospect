<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="approvalAgent">
        <h2 class="jh-entity-heading" data-cy="approvalAgentDetailsHeading">
          <span v-text="t$('jHipster0App.approvalAgent.detail.title')"></span> {{ approvalAgent.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.approvalAgent.agentid')"></span>
          </dt>
          <dd>
            <span>{{ approvalAgent.agentid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.approvalAgent.agentname')"></span>
          </dt>
          <dd>
            <span>{{ approvalAgent.agentname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.approvalAgent.agentstarttime')"></span>
          </dt>
          <dd>
            <span>{{ approvalAgent.agentstarttime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.approvalAgent.autocanceltime')"></span>
          </dt>
          <dd>
            <span>{{ approvalAgent.autocanceltime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.approvalAgent.agentdepartment')"></span>
          </dt>
          <dd>
            <span>{{ approvalAgent.agentdepartment }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.approvalAgent.originalapprovalname')"></span>
          </dt>
          <dd>
            <span>{{ approvalAgent.originalapprovalname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.approvalAgent.originaldepartment')"></span>
          </dt>
          <dd>
            <span>{{ approvalAgent.originaldepartment }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.approvalAgent.secrecylevel')"></span>
          </dt>
          <dd>
            <span>{{ approvalAgent.secrecylevel }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.approvalAgent.originalapprovalid')"></span>
          </dt>
          <dd>
            <div v-if="approvalAgent.originalapprovalid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: approvalAgent.originalapprovalid.id } }">{{
                approvalAgent.originalapprovalid.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="approvalAgent.id"
          :to="{ name: 'ApprovalAgentEdit', params: { approvalAgentId: approvalAgent.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./approval-agent-details.component.ts"></script>
