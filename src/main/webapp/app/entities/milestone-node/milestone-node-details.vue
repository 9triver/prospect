<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="milestoneNode">
        <h2 class="jh-entity-heading" data-cy="milestoneNodeDetailsHeading">
          <span v-text="t$('jy1App.milestoneNode.detail.title')"></span> {{ milestoneNode.id }}
        </h2>
        <dl class="row jh-entity-details last-0">
          <dt class="field">
            <span v-text="t$('jy1App.milestoneNode.outsourcingcontractid')"></span>
          </dt>
          <dd class="field">
            <span>{{ milestoneNode.outsourcingcontractid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.milestoneNode.outsourcingcontractname')"></span>
          </dt>
          <dd class="field">
            <span>{{ milestoneNode.outsourcingcontractname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.milestoneNode.name')"></span>
          </dt>
          <dd class="field">
            <span>{{ milestoneNode.name }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.milestoneNode.planpaymenttime')"></span>
          </dt>
          <dd class="field">
            <span>{{ milestoneNode.planpaymenttime }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.milestoneNode.planpaymentamount')"></span>
          </dt>
          <dd class="field">
            <span>{{ milestoneNode.planpaymentamount }}</span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.milestoneNode.outsourcingContract')"></span>
          </dt>
          <dd class="relationship">
            <span v-for="(outsourcingContract, i) in milestoneNode.outsourcingContracts" :key="outsourcingContract.id"
              >{{ i > 0 ? '' : '' }}
              <router-link :to="{ name: 'OutsourcingContractView', params: { outsourcingContractId: outsourcingContract.id } }">{{
                outsourcingContract.id
              }}</router-link>
            </span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="milestoneNode.id"
          :to="{ name: 'MilestoneNodeEdit', params: { milestoneNodeId: milestoneNode.id } }"
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

<script lang="ts" src="./milestone-node-details.component.ts"></script>
