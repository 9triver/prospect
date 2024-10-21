<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="customerSatisfaction">
        <h2 class="jh-entity-heading" data-cy="customerSatisfactionDetailsHeading">
          <span v-text="t$('jy1App.customerSatisfaction.detail.title')"></span> {{ customerSatisfaction.id }}
        </h2>
        <dl class="row jh-entity-details last-1">
          <dt class="field">
            <span v-text="t$('jy1App.customerSatisfaction.year')"></span>
          </dt>
          <dd class="field">
            <span>{{ customerSatisfaction.year }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.customerSatisfaction.satisfactionitem')"></span>
          </dt>
          <dd class="field">
            <span>{{ customerSatisfaction.satisfactionitem }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.customerSatisfaction.score')"></span>
          </dt>
          <dd class="field">
            <span>{{ customerSatisfaction.score }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.customerSatisfaction.opinion')"></span>
          </dt>
          <dd class="field">
            <span>{{ customerSatisfaction.opinion }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.customerSatisfaction.totalscore')"></span>
          </dt>
          <dd class="field">
            <span>{{ customerSatisfaction.totalscore }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.customerSatisfaction.surveytime')"></span>
          </dt>
          <dd class="field">
            <span>{{ customerSatisfaction.surveytime }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.customerSatisfaction.customer')"></span>
          </dt>
          <dd class="field">
            <span>{{ customerSatisfaction.customer }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.customerSatisfaction.plonenumber')"></span>
          </dt>
          <dd class="field">
            <span>{{ customerSatisfaction.plonenumber }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.customerSatisfaction.filename')"></span>
          </dt>
          <dd class="field">
            <span>{{ customerSatisfaction.filename }}</span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.customerSatisfaction.wbsid')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="customerSatisfaction.wbsid">
              <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: customerSatisfaction.wbsid.id } }">{{
                customerSatisfaction.wbsid.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="customerSatisfaction.id"
          :to="{ name: 'CustomerSatisfactionEdit', params: { customerSatisfactionId: customerSatisfaction.id } }"
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

<script lang="ts" src="./customer-satisfaction-details.component.ts"></script>
