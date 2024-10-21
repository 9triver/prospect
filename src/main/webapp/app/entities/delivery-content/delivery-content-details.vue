<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="deliveryContent">
        <h2 class="jh-entity-heading" data-cy="deliveryContentDetailsHeading">
          <span v-text="t$('jy1App.deliveryContent.detail.title')"></span> {{ deliveryContent.id }}
        </h2>
        <dl class="row jh-entity-details last-1">
          <dt class="field">
            <span v-text="t$('jy1App.deliveryContent.warrantyrequirement')"></span>
          </dt>
          <dd class="field">
            <span>{{ deliveryContent.warrantyrequirement }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deliveryContent.purchaseplanno')"></span>
          </dt>
          <dd class="field">
            <span>{{ deliveryContent.purchaseplanno }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deliveryContent.purchaseplandate')"></span>
          </dt>
          <dd class="field">
            <span>{{ deliveryContent.purchaseplandate }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deliveryContent.purchaseplanamount')"></span>
          </dt>
          <dd class="field">
            <span>{{ deliveryContent.purchaseplanamount }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deliveryContent.purchasemethod')"></span>
          </dt>
          <dd class="field">
            <span>{{ deliveryContent.purchasemethod }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deliveryContent.purchasesecretlevel')"></span>
          </dt>
          <dd class="field">
            <span>{{ deliveryContent.purchasesecretlevel }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deliveryContent.reviewmethod')"></span>
          </dt>
          <dd class="field">
            <span>{{ deliveryContent.reviewmethod }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deliveryContent.requirementdepartment')"></span>
          </dt>
          <dd class="field">
            <span>{{ deliveryContent.requirementdepartment }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deliveryContent.requirementperson')"></span>
          </dt>
          <dd class="field">
            <span>{{ deliveryContent.requirementperson }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deliveryContent.undertaker')"></span>
          </dt>
          <dd class="field">
            <span>{{ deliveryContent.undertaker }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deliveryContent.undertakingdepartment')"></span>
          </dt>
          <dd class="field">
            <span>{{ deliveryContent.undertakingdepartment }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deliveryContent.workbagid')"></span>
          </dt>
          <dd class="field">
            <span>{{ deliveryContent.workbagid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deliveryContent.projectmanager')"></span>
          </dt>
          <dd class="field">
            <span>{{ deliveryContent.projectmanager }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deliveryContent.fundsource')"></span>
          </dt>
          <dd class="field">
            <span>{{ deliveryContent.fundsource }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deliveryContent.thesisname')"></span>
          </dt>
          <dd class="field">
            <span>{{ deliveryContent.thesisname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deliveryContent.contractauxiliaryno')"></span>
          </dt>
          <dd class="field">
            <span>{{ deliveryContent.contractauxiliaryno }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deliveryContent.reasonfornosuppliers')"></span>
          </dt>
          <dd class="field">
            <span>{{ deliveryContent.reasonfornosuppliers }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deliveryContent.reasonforchange')"></span>
          </dt>
          <dd class="field">
            <span>{{ deliveryContent.reasonforchange }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deliveryContent.negotiationfiletime')"></span>
          </dt>
          <dd class="field">
            <span>{{ deliveryContent.negotiationfiletime }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deliveryContent.bidopeningtime')"></span>
          </dt>
          <dd class="field">
            <span>{{ deliveryContent.bidopeningtime }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deliveryContent.judges')"></span>
          </dt>
          <dd class="field">
            <span>{{ deliveryContent.judges }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deliveryContent.responsevendorname')"></span>
          </dt>
          <dd class="field">
            <span>{{ deliveryContent.responsevendorname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deliveryContent.finalquoteandscore')"></span>
          </dt>
          <dd class="field">
            <span>{{ deliveryContent.finalquoteandscore }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deliveryContent.noticeofcompletiontime')"></span>
          </dt>
          <dd class="field">
            <span>{{ deliveryContent.noticeofcompletiontime }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deliveryContent.signingdate')"></span>
          </dt>
          <dd class="field">
            <span>{{ deliveryContent.signingdate }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deliveryContent.contractenddate')"></span>
          </dt>
          <dd class="field">
            <span>{{ deliveryContent.contractenddate }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deliveryContent.actualcompletiontime')"></span>
          </dt>
          <dd class="field">
            <span>{{ deliveryContent.actualcompletiontime }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deliveryContent.issubmitsecrecyagreement')"></span>
          </dt>
          <dd class="field">
            <span>{{ deliveryContent.issubmitsecrecyagreement }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deliveryContent.issubmitsecurityagreement')"></span>
          </dt>
          <dd class="field">
            <span>{{ deliveryContent.issubmitsecurityagreement }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.deliveryContent.remark')"></span>
          </dt>
          <dd class="field">
            <span>{{ deliveryContent.remark }}</span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.deliveryContent.outsourcingContract')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="deliveryContent.outsourcingContract">
              <router-link
                :to="{ name: 'OutsourcingContractView', params: { outsourcingContractId: deliveryContent.outsourcingContract.id } }"
                >{{ deliveryContent.outsourcingContract.id }}</router-link
              >
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="deliveryContent.id"
          :to="{ name: 'DeliveryContentEdit', params: { deliveryContentId: deliveryContent.id } }"
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

<script lang="ts" src="./delivery-content-details.component.ts"></script>
