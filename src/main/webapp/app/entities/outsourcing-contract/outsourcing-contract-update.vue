<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.outsourcingContract.home.createOrEditLabel"
          data-cy="OutsourcingContractCreateUpdateHeading"
          v-text="t$('jy1App.outsourcingContract.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="outsourcingContract.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="outsourcingContract.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.contractid')"
              for="outsourcing-contract-contractid"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="contractid"
              id="outsourcing-contract-contractid"
              data-cy="contractid"
              :class="{ valid: !v$.contractid.$invalid, invalid: v$.contractid.$invalid }"
              v-model="v$.contractid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.contractcode')"
              for="outsourcing-contract-contractcode"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="contractcode"
              id="outsourcing-contract-contractcode"
              data-cy="contractcode"
              :class="{ valid: !v$.contractcode.$invalid, invalid: v$.contractcode.$invalid }"
              v-model="v$.contractcode.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.contractname')"
              for="outsourcing-contract-contractname"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="contractname"
              id="outsourcing-contract-contractname"
              data-cy="contractname"
              :class="{ valid: !v$.contractname.$invalid, invalid: v$.contractname.$invalid }"
              v-model="v$.contractname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.contractqualityid')"
              for="outsourcing-contract-contractqualityid"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="contractqualityid"
              id="outsourcing-contract-contractqualityid"
              data-cy="contractqualityid"
              :class="{ valid: !v$.contractqualityid.$invalid, invalid: v$.contractqualityid.$invalid }"
              v-model="v$.contractqualityid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.contractcostid')"
              for="outsourcing-contract-contractcostid"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="contractcostid"
              id="outsourcing-contract-contractcostid"
              data-cy="contractcostid"
              :class="{ valid: !v$.contractcostid.$invalid, invalid: v$.contractcostid.$invalid }"
              v-model="v$.contractcostid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.contractfinanceid')"
              for="outsourcing-contract-contractfinanceid"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="contractfinanceid"
              id="outsourcing-contract-contractfinanceid"
              data-cy="contractfinanceid"
              :class="{ valid: !v$.contractfinanceid.$invalid, invalid: v$.contractfinanceid.$invalid }"
              v-model="v$.contractfinanceid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.projectid')"
              for="outsourcing-contract-projectid"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="projectid"
              id="outsourcing-contract-projectid"
              data-cy="projectid"
              :class="{ valid: !v$.projectid.$invalid, invalid: v$.projectid.$invalid }"
              v-model="v$.projectid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.projectsecretlevel')"
              for="outsourcing-contract-projectsecretlevel"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="projectsecretlevel"
              id="outsourcing-contract-projectsecretlevel"
              data-cy="projectsecretlevel"
              :class="{ valid: !v$.projectsecretlevel.$invalid, invalid: v$.projectsecretlevel.$invalid }"
              v-model="v$.projectsecretlevel.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.counterpartyunit')"
              for="outsourcing-contract-counterpartyunit"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="counterpartyunit"
              id="outsourcing-contract-counterpartyunit"
              data-cy="counterpartyunit"
              :class="{ valid: !v$.counterpartyunit.$invalid, invalid: v$.counterpartyunit.$invalid }"
              v-model="v$.counterpartyunit.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.negotiationdate')"
              for="outsourcing-contract-negotiationdate"
            ></label>
            <el-date-picker v-model="v$.negotiationdate.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.negotiationlocation')"
              for="outsourcing-contract-negotiationlocation"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="negotiationlocation"
              id="outsourcing-contract-negotiationlocation"
              data-cy="negotiationlocation"
              :class="{ valid: !v$.negotiationlocation.$invalid, invalid: v$.negotiationlocation.$invalid }"
              v-model="v$.negotiationlocation.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.negotiator')"
              for="outsourcing-contract-negotiator"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="negotiator"
              id="outsourcing-contract-negotiator"
              data-cy="negotiator"
              :class="{ valid: !v$.negotiator.$invalid, invalid: v$.negotiator.$invalid }"
              v-model="v$.negotiator.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.budgetamount')"
              for="outsourcing-contract-budgetamount"
            ></label>
            <el-input
              type="number"
              class="form-control"
              name="budgetamount"
              id="outsourcing-contract-budgetamount"
              data-cy="budgetamount"
              :class="{ valid: !v$.budgetamount.$invalid, invalid: v$.budgetamount.$invalid }"
              v-model.number="v$.budgetamount.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.contractamount')"
              for="outsourcing-contract-contractamount"
            ></label>
            <el-input
              type="number"
              class="form-control"
              name="contractamount"
              id="outsourcing-contract-contractamount"
              data-cy="contractamount"
              :class="{ valid: !v$.contractamount.$invalid, invalid: v$.contractamount.$invalid }"
              v-model.number="v$.contractamount.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.approver')"
              for="outsourcing-contract-approver"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="approver"
              id="outsourcing-contract-approver"
              data-cy="approver"
              :class="{ valid: !v$.approver.$invalid, invalid: v$.approver.$invalid }"
              v-model="v$.approver.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.approvaldate')"
              for="outsourcing-contract-approvaldate"
            ></label>
            <el-date-picker v-model="v$.approvaldate.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.contractsecretlevel')"
              for="outsourcing-contract-contractsecretlevel"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="contractsecretlevel"
              id="outsourcing-contract-contractsecretlevel"
              data-cy="contractsecretlevel"
              :class="{ valid: !v$.contractsecretlevel.$invalid, invalid: v$.contractsecretlevel.$invalid }"
              v-model="v$.contractsecretlevel.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.deliverycontent')"
              for="outsourcing-contract-deliverycontent"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="deliverycontent"
              id="outsourcing-contract-deliverycontent"
              data-cy="deliverycontent"
              :class="{ valid: !v$.deliverycontent.$invalid, invalid: v$.deliverycontent.$invalid }"
              v-model="v$.deliverycontent.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.warrantyrequirement')"
              for="outsourcing-contract-warrantyrequirement"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="warrantyrequirement"
              id="outsourcing-contract-warrantyrequirement"
              data-cy="warrantyrequirement"
              :class="{ valid: !v$.warrantyrequirement.$invalid, invalid: v$.warrantyrequirement.$invalid }"
              v-model="v$.warrantyrequirement.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.purchaseplanno')"
              for="outsourcing-contract-purchaseplanno"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="purchaseplanno"
              id="outsourcing-contract-purchaseplanno"
              data-cy="purchaseplanno"
              :class="{ valid: !v$.purchaseplanno.$invalid, invalid: v$.purchaseplanno.$invalid }"
              v-model="v$.purchaseplanno.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.purchaseplandate')"
              for="outsourcing-contract-purchaseplandate"
            ></label>
            <el-date-picker v-model="v$.purchaseplandate.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.purchaseplanamount')"
              for="outsourcing-contract-purchaseplanamount"
            ></label>
            <el-input
              type="number"
              class="form-control"
              name="purchaseplanamount"
              id="outsourcing-contract-purchaseplanamount"
              data-cy="purchaseplanamount"
              :class="{ valid: !v$.purchaseplanamount.$invalid, invalid: v$.purchaseplanamount.$invalid }"
              v-model.number="v$.purchaseplanamount.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.purchasemethod')"
              for="outsourcing-contract-purchasemethod"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="purchasemethod"
              id="outsourcing-contract-purchasemethod"
              data-cy="purchasemethod"
              :class="{ valid: !v$.purchasemethod.$invalid, invalid: v$.purchasemethod.$invalid }"
              v-model="v$.purchasemethod.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.purchasesecretlevel')"
              for="outsourcing-contract-purchasesecretlevel"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="purchasesecretlevel"
              id="outsourcing-contract-purchasesecretlevel"
              data-cy="purchasesecretlevel"
              :class="{ valid: !v$.purchasesecretlevel.$invalid, invalid: v$.purchasesecretlevel.$invalid }"
              v-model="v$.purchasesecretlevel.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.reviewmethod')"
              for="outsourcing-contract-reviewmethod"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="reviewmethod"
              id="outsourcing-contract-reviewmethod"
              data-cy="reviewmethod"
              :class="{ valid: !v$.reviewmethod.$invalid, invalid: v$.reviewmethod.$invalid }"
              v-model="v$.reviewmethod.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.requirementdepartment')"
              for="outsourcing-contract-requirementdepartment"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="requirementdepartment"
              id="outsourcing-contract-requirementdepartment"
              data-cy="requirementdepartment"
              :class="{ valid: !v$.requirementdepartment.$invalid, invalid: v$.requirementdepartment.$invalid }"
              v-model="v$.requirementdepartment.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.requirementperson')"
              for="outsourcing-contract-requirementperson"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="requirementperson"
              id="outsourcing-contract-requirementperson"
              data-cy="requirementperson"
              :class="{ valid: !v$.requirementperson.$invalid, invalid: v$.requirementperson.$invalid }"
              v-model="v$.requirementperson.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.undertaker')"
              for="outsourcing-contract-undertaker"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="undertaker"
              id="outsourcing-contract-undertaker"
              data-cy="undertaker"
              :class="{ valid: !v$.undertaker.$invalid, invalid: v$.undertaker.$invalid }"
              v-model="v$.undertaker.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.undertakingdepartment')"
              for="outsourcing-contract-undertakingdepartment"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="undertakingdepartment"
              id="outsourcing-contract-undertakingdepartment"
              data-cy="undertakingdepartment"
              :class="{ valid: !v$.undertakingdepartment.$invalid, invalid: v$.undertakingdepartment.$invalid }"
              v-model="v$.undertakingdepartment.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.workbagid')"
              for="outsourcing-contract-workbagid"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="workbagid"
              id="outsourcing-contract-workbagid"
              data-cy="workbagid"
              :class="{ valid: !v$.workbagid.$invalid, invalid: v$.workbagid.$invalid }"
              v-model="v$.workbagid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.projectmanager')"
              for="outsourcing-contract-projectmanager"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="projectmanager"
              id="outsourcing-contract-projectmanager"
              data-cy="projectmanager"
              :class="{ valid: !v$.projectmanager.$invalid, invalid: v$.projectmanager.$invalid }"
              v-model="v$.projectmanager.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.fundsource')"
              for="outsourcing-contract-fundsource"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="fundsource"
              id="outsourcing-contract-fundsource"
              data-cy="fundsource"
              :class="{ valid: !v$.fundsource.$invalid, invalid: v$.fundsource.$invalid }"
              v-model="v$.fundsource.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.thesisname')"
              for="outsourcing-contract-thesisname"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="thesisname"
              id="outsourcing-contract-thesisname"
              data-cy="thesisname"
              :class="{ valid: !v$.thesisname.$invalid, invalid: v$.thesisname.$invalid }"
              v-model="v$.thesisname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.contractauxiliaryno')"
              for="outsourcing-contract-contractauxiliaryno"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="contractauxiliaryno"
              id="outsourcing-contract-contractauxiliaryno"
              data-cy="contractauxiliaryno"
              :class="{ valid: !v$.contractauxiliaryno.$invalid, invalid: v$.contractauxiliaryno.$invalid }"
              v-model="v$.contractauxiliaryno.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.reasonfornosuppliers')"
              for="outsourcing-contract-reasonfornosuppliers"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="reasonfornosuppliers"
              id="outsourcing-contract-reasonfornosuppliers"
              data-cy="reasonfornosuppliers"
              :class="{ valid: !v$.reasonfornosuppliers.$invalid, invalid: v$.reasonfornosuppliers.$invalid }"
              v-model="v$.reasonfornosuppliers.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.reasonforchange')"
              for="outsourcing-contract-reasonforchange"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="reasonforchange"
              id="outsourcing-contract-reasonforchange"
              data-cy="reasonforchange"
              :class="{ valid: !v$.reasonforchange.$invalid, invalid: v$.reasonforchange.$invalid }"
              v-model="v$.reasonforchange.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.negotiationfiletime')"
              for="outsourcing-contract-negotiationfiletime"
            ></label>
            <el-date-picker v-model="v$.negotiationfiletime.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.bidopeningtime')"
              for="outsourcing-contract-bidopeningtime"
            ></label>
            <el-date-picker v-model="v$.bidopeningtime.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.outsourcingContract.judges')" for="outsourcing-contract-judges"></label>
            <el-input
              type="text"
              class="form-control"
              name="judges"
              id="outsourcing-contract-judges"
              data-cy="judges"
              :class="{ valid: !v$.judges.$invalid, invalid: v$.judges.$invalid }"
              v-model="v$.judges.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.responsevendorname')"
              for="outsourcing-contract-responsevendorname"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="responsevendorname"
              id="outsourcing-contract-responsevendorname"
              data-cy="responsevendorname"
              :class="{ valid: !v$.responsevendorname.$invalid, invalid: v$.responsevendorname.$invalid }"
              v-model="v$.responsevendorname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.finalquoteandscore')"
              for="outsourcing-contract-finalquoteandscore"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="finalquoteandscore"
              id="outsourcing-contract-finalquoteandscore"
              data-cy="finalquoteandscore"
              :class="{ valid: !v$.finalquoteandscore.$invalid, invalid: v$.finalquoteandscore.$invalid }"
              v-model="v$.finalquoteandscore.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.noticeofcompletiontime')"
              for="outsourcing-contract-noticeofcompletiontime"
            ></label>
            <el-date-picker v-model="v$.noticeofcompletiontime.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.signingdate')"
              for="outsourcing-contract-signingdate"
            ></label>
            <el-date-picker v-model="v$.signingdate.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.contractenddate')"
              for="outsourcing-contract-contractenddate"
            ></label>
            <el-date-picker v-model="v$.contractenddate.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.actualcompletiontime')"
              for="outsourcing-contract-actualcompletiontime"
            ></label>
            <el-date-picker v-model="v$.actualcompletiontime.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.issubmitsecrecyagreement')"
              for="outsourcing-contract-issubmitsecrecyagreement"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="issubmitsecrecyagreement"
              id="outsourcing-contract-issubmitsecrecyagreement"
              data-cy="issubmitsecrecyagreement"
              :class="{ valid: !v$.issubmitsecrecyagreement.$invalid, invalid: v$.issubmitsecrecyagreement.$invalid }"
              v-model="v$.issubmitsecrecyagreement.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.issubmitsecurityagreement')"
              for="outsourcing-contract-issubmitsecurityagreement"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="issubmitsecurityagreement"
              id="outsourcing-contract-issubmitsecurityagreement"
              data-cy="issubmitsecurityagreement"
              :class="{ valid: !v$.issubmitsecurityagreement.$invalid, invalid: v$.issubmitsecurityagreement.$invalid }"
              v-model="v$.issubmitsecurityagreement.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.outsourcingContract.remark')" for="outsourcing-contract-remark"></label>
            <el-input
              type="text"
              class="form-control"
              name="remark"
              id="outsourcing-contract-remark"
              data-cy="remark"
              :class="{ valid: !v$.remark.$invalid, invalid: v$.remark.$invalid }"
              v-model="v$.remark.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.outsourcingContract.workbag')" for="outsourcing-contract-workbag"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="outsourcing-contract-workbag"
              data-cy="workbag"
              name="workbag"
              v-model="outsourcingContract.workbag"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  outsourcingContract.workbag && workbagOption.id === outsourcingContract.workbag.id
                    ? outsourcingContract.workbag
                    : workbagOption
                "
                v-for="workbagOption in workbags"
                :key="workbagOption.id"
                :label="workbagOption.id"
                >{{ workbagOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.outsourcingContract.milestoneNode')" for="outsourcing-contract-milestoneNode"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="outsourcing-contract-milestoneNodes"
              data-cy="milestoneNode"
              multiple
              name="milestoneNode"
              v-if="outsourcingContract.milestoneNodes !== undefined"
              v-model="outsourcingContract.milestoneNodes"
            >
              <el-option
                v-bind:value="getSelected(outsourcingContract.milestoneNodes, milestoneNodeOption, 'id')"
                v-for="milestoneNodeOption in milestoneNodes"
                :key="milestoneNodeOption.id"
                :label="milestoneNodeOption.id"
                >{{ milestoneNodeOption.id }}</el-option
              >
            </el-select>
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
<script lang="ts" src="./outsourcing-contract-update.component.ts"></script>
