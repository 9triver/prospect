<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.deliveryContent.home.createOrEditLabel"
          data-cy="DeliveryContentCreateUpdateHeading"
          v-text="t$('jy1App.deliveryContent.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="deliveryContent.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="deliveryContent.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.deliveryContent.warrantyrequirement')"
              for="delivery-content-warrantyrequirement"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="warrantyrequirement"
              id="delivery-content-warrantyrequirement"
              data-cy="warrantyrequirement"
              :class="{ valid: !v$.warrantyrequirement.$invalid, invalid: v$.warrantyrequirement.$invalid }"
              v-model="v$.warrantyrequirement.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.deliveryContent.purchaseplanno')"
              for="delivery-content-purchaseplanno"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="purchaseplanno"
              id="delivery-content-purchaseplanno"
              data-cy="purchaseplanno"
              :class="{ valid: !v$.purchaseplanno.$invalid, invalid: v$.purchaseplanno.$invalid }"
              v-model="v$.purchaseplanno.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.deliveryContent.purchaseplandate')"
              for="delivery-content-purchaseplandate"
            ></label>
            <el-date-picker v-model="v$.purchaseplandate.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.deliveryContent.purchaseplanamount')"
              for="delivery-content-purchaseplanamount"
            ></label>
            <el-input
              type="number"
              class="form-control"
              name="purchaseplanamount"
              id="delivery-content-purchaseplanamount"
              data-cy="purchaseplanamount"
              :class="{ valid: !v$.purchaseplanamount.$invalid, invalid: v$.purchaseplanamount.$invalid }"
              v-model.number="v$.purchaseplanamount.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.deliveryContent.purchasemethod')"
              for="delivery-content-purchasemethod"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="purchasemethod"
              id="delivery-content-purchasemethod"
              data-cy="purchasemethod"
              :class="{ valid: !v$.purchasemethod.$invalid, invalid: v$.purchasemethod.$invalid }"
              v-model="v$.purchasemethod.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.deliveryContent.purchasesecretlevel')"
              for="delivery-content-purchasesecretlevel"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="purchasesecretlevel"
              id="delivery-content-purchasesecretlevel"
              data-cy="purchasesecretlevel"
              :class="{ valid: !v$.purchasesecretlevel.$invalid, invalid: v$.purchasesecretlevel.$invalid }"
              v-model="v$.purchasesecretlevel.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.deliveryContent.reviewmethod')"
              for="delivery-content-reviewmethod"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="reviewmethod"
              id="delivery-content-reviewmethod"
              data-cy="reviewmethod"
              :class="{ valid: !v$.reviewmethod.$invalid, invalid: v$.reviewmethod.$invalid }"
              v-model="v$.reviewmethod.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.deliveryContent.requirementdepartment')"
              for="delivery-content-requirementdepartment"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="requirementdepartment"
              id="delivery-content-requirementdepartment"
              data-cy="requirementdepartment"
              :class="{ valid: !v$.requirementdepartment.$invalid, invalid: v$.requirementdepartment.$invalid }"
              v-model="v$.requirementdepartment.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.deliveryContent.requirementperson')"
              for="delivery-content-requirementperson"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="requirementperson"
              id="delivery-content-requirementperson"
              data-cy="requirementperson"
              :class="{ valid: !v$.requirementperson.$invalid, invalid: v$.requirementperson.$invalid }"
              v-model="v$.requirementperson.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.deliveryContent.undertaker')" for="delivery-content-undertaker"></label>
            <el-input
              type="text"
              class="form-control"
              name="undertaker"
              id="delivery-content-undertaker"
              data-cy="undertaker"
              :class="{ valid: !v$.undertaker.$invalid, invalid: v$.undertaker.$invalid }"
              v-model="v$.undertaker.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.deliveryContent.undertakingdepartment')"
              for="delivery-content-undertakingdepartment"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="undertakingdepartment"
              id="delivery-content-undertakingdepartment"
              data-cy="undertakingdepartment"
              :class="{ valid: !v$.undertakingdepartment.$invalid, invalid: v$.undertakingdepartment.$invalid }"
              v-model="v$.undertakingdepartment.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.deliveryContent.workbagid')" for="delivery-content-workbagid"></label>
            <el-input
              type="text"
              class="form-control"
              name="workbagid"
              id="delivery-content-workbagid"
              data-cy="workbagid"
              :class="{ valid: !v$.workbagid.$invalid, invalid: v$.workbagid.$invalid }"
              v-model="v$.workbagid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.deliveryContent.projectmanager')"
              for="delivery-content-projectmanager"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="projectmanager"
              id="delivery-content-projectmanager"
              data-cy="projectmanager"
              :class="{ valid: !v$.projectmanager.$invalid, invalid: v$.projectmanager.$invalid }"
              v-model="v$.projectmanager.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.deliveryContent.fundsource')" for="delivery-content-fundsource"></label>
            <el-input
              type="text"
              class="form-control"
              name="fundsource"
              id="delivery-content-fundsource"
              data-cy="fundsource"
              :class="{ valid: !v$.fundsource.$invalid, invalid: v$.fundsource.$invalid }"
              v-model="v$.fundsource.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.deliveryContent.thesisname')" for="delivery-content-thesisname"></label>
            <el-input
              type="text"
              class="form-control"
              name="thesisname"
              id="delivery-content-thesisname"
              data-cy="thesisname"
              :class="{ valid: !v$.thesisname.$invalid, invalid: v$.thesisname.$invalid }"
              v-model="v$.thesisname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.deliveryContent.contractauxiliaryno')"
              for="delivery-content-contractauxiliaryno"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="contractauxiliaryno"
              id="delivery-content-contractauxiliaryno"
              data-cy="contractauxiliaryno"
              :class="{ valid: !v$.contractauxiliaryno.$invalid, invalid: v$.contractauxiliaryno.$invalid }"
              v-model="v$.contractauxiliaryno.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.deliveryContent.reasonfornosuppliers')"
              for="delivery-content-reasonfornosuppliers"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="reasonfornosuppliers"
              id="delivery-content-reasonfornosuppliers"
              data-cy="reasonfornosuppliers"
              :class="{ valid: !v$.reasonfornosuppliers.$invalid, invalid: v$.reasonfornosuppliers.$invalid }"
              v-model="v$.reasonfornosuppliers.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.deliveryContent.reasonforchange')"
              for="delivery-content-reasonforchange"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="reasonforchange"
              id="delivery-content-reasonforchange"
              data-cy="reasonforchange"
              :class="{ valid: !v$.reasonforchange.$invalid, invalid: v$.reasonforchange.$invalid }"
              v-model="v$.reasonforchange.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.deliveryContent.negotiationfiletime')"
              for="delivery-content-negotiationfiletime"
            ></label>
            <el-date-picker v-model="v$.negotiationfiletime.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.deliveryContent.bidopeningtime')"
              for="delivery-content-bidopeningtime"
            ></label>
            <el-date-picker v-model="v$.bidopeningtime.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.deliveryContent.judges')" for="delivery-content-judges"></label>
            <el-input
              type="text"
              class="form-control"
              name="judges"
              id="delivery-content-judges"
              data-cy="judges"
              :class="{ valid: !v$.judges.$invalid, invalid: v$.judges.$invalid }"
              v-model="v$.judges.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.deliveryContent.responsevendorname')"
              for="delivery-content-responsevendorname"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="responsevendorname"
              id="delivery-content-responsevendorname"
              data-cy="responsevendorname"
              :class="{ valid: !v$.responsevendorname.$invalid, invalid: v$.responsevendorname.$invalid }"
              v-model="v$.responsevendorname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.deliveryContent.finalquoteandscore')"
              for="delivery-content-finalquoteandscore"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="finalquoteandscore"
              id="delivery-content-finalquoteandscore"
              data-cy="finalquoteandscore"
              :class="{ valid: !v$.finalquoteandscore.$invalid, invalid: v$.finalquoteandscore.$invalid }"
              v-model="v$.finalquoteandscore.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.deliveryContent.noticeofcompletiontime')"
              for="delivery-content-noticeofcompletiontime"
            ></label>
            <el-date-picker v-model="v$.noticeofcompletiontime.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.deliveryContent.signingdate')" for="delivery-content-signingdate"></label>
            <el-date-picker v-model="v$.signingdate.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.deliveryContent.contractenddate')"
              for="delivery-content-contractenddate"
            ></label>
            <el-date-picker v-model="v$.contractenddate.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.deliveryContent.actualcompletiontime')"
              for="delivery-content-actualcompletiontime"
            ></label>
            <el-date-picker v-model="v$.actualcompletiontime.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.deliveryContent.issubmitsecrecyagreement')"
              for="delivery-content-issubmitsecrecyagreement"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="issubmitsecrecyagreement"
              id="delivery-content-issubmitsecrecyagreement"
              data-cy="issubmitsecrecyagreement"
              :class="{ valid: !v$.issubmitsecrecyagreement.$invalid, invalid: v$.issubmitsecrecyagreement.$invalid }"
              v-model="v$.issubmitsecrecyagreement.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.deliveryContent.issubmitsecurityagreement')"
              for="delivery-content-issubmitsecurityagreement"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="issubmitsecurityagreement"
              id="delivery-content-issubmitsecurityagreement"
              data-cy="issubmitsecurityagreement"
              :class="{ valid: !v$.issubmitsecurityagreement.$invalid, invalid: v$.issubmitsecurityagreement.$invalid }"
              v-model="v$.issubmitsecurityagreement.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.deliveryContent.remark')" for="delivery-content-remark"></label>
            <el-input
              type="text"
              class="form-control"
              name="remark"
              id="delivery-content-remark"
              data-cy="remark"
              :class="{ valid: !v$.remark.$invalid, invalid: v$.remark.$invalid }"
              v-model="v$.remark.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.deliveryContent.outsourcingContract')"
              for="delivery-content-outsourcingContract"
            ></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="delivery-content-outsourcingContract"
              data-cy="outsourcingContract"
              name="outsourcingContract"
              v-model="deliveryContent.outsourcingContract"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  deliveryContent.outsourcingContract && outsourcingContractOption.id === deliveryContent.outsourcingContract.id
                    ? deliveryContent.outsourcingContract
                    : outsourcingContractOption
                "
                v-for="outsourcingContractOption in outsourcingContracts"
                :key="outsourcingContractOption.id"
                :label="outsourcingContractOption.id"
                >{{ outsourcingContractOption.id }}</el-option
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
<script lang="ts" src="./delivery-content-update.component.ts"></script>
