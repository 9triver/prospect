<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.keyNodeInspection.home.createOrEditLabel"
          data-cy="KeyNodeInspectionCreateUpdateHeading"
          v-text="t$('jy1App.keyNodeInspection.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="keyNodeInspection.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="keyNodeInspection.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.keyNodeInspection.name')" for="key-node-inspection-name"></label>
            <el-input
              type="text"
              class="form-control"
              name="name"
              id="key-node-inspection-name"
              data-cy="name"
              :class="{ valid: !v$.name.$invalid, invalid: v$.name.$invalid }"
              v-model="v$.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.keyNodeInspection.workbagid')" for="key-node-inspection-workbagid"></label>
            <el-input
              type="text"
              class="form-control"
              name="workbagid"
              id="key-node-inspection-workbagid"
              data-cy="workbagid"
              :class="{ valid: !v$.workbagid.$invalid, invalid: v$.workbagid.$invalid }"
              v-model="v$.workbagid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.keyNodeInspection.workbagname')"
              for="key-node-inspection-workbagname"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="workbagname"
              id="key-node-inspection-workbagname"
              data-cy="workbagname"
              :class="{ valid: !v$.workbagname.$invalid, invalid: v$.workbagname.$invalid }"
              v-model="v$.workbagname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.keyNodeInspection.belongwbsid')"
              for="key-node-inspection-belongwbsid"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="belongwbsid"
              id="key-node-inspection-belongwbsid"
              data-cy="belongwbsid"
              :class="{ valid: !v$.belongwbsid.$invalid, invalid: v$.belongwbsid.$invalid }"
              v-model="v$.belongwbsid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.keyNodeInspection.projectlevel')"
              for="key-node-inspection-projectlevel"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="projectlevel"
              id="key-node-inspection-projectlevel"
              data-cy="projectlevel"
              :class="{ valid: !v$.projectlevel.$invalid, invalid: v$.projectlevel.$invalid }"
              v-model="v$.projectlevel.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.keyNodeInspection.iskey')" for="key-node-inspection-iskey"></label>
            <el-input
              type="text"
              class="form-control"
              name="iskey"
              id="key-node-inspection-iskey"
              data-cy="iskey"
              :class="{ valid: !v$.iskey.$invalid, invalid: v$.iskey.$invalid }"
              v-model="v$.iskey.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.keyNodeInspection.isimplementationplan')"
              for="key-node-inspection-isimplementationplan"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="isimplementationplan"
              id="key-node-inspection-isimplementationplan"
              data-cy="isimplementationplan"
              :class="{ valid: !v$.isimplementationplan.$invalid, invalid: v$.isimplementationplan.$invalid }"
              v-model="v$.isimplementationplan.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.keyNodeInspection.isqualityplan')"
              for="key-node-inspection-isqualityplan"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="isqualityplan"
              id="key-node-inspection-isqualityplan"
              data-cy="isqualityplan"
              :class="{ valid: !v$.isqualityplan.$invalid, invalid: v$.isqualityplan.$invalid }"
              v-model="v$.isqualityplan.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.keyNodeInspection.istechniqueplan')"
              for="key-node-inspection-istechniqueplan"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="istechniqueplan"
              id="key-node-inspection-istechniqueplan"
              data-cy="istechniqueplan"
              :class="{ valid: !v$.istechniqueplan.$invalid, invalid: v$.istechniqueplan.$invalid }"
              v-model="v$.istechniqueplan.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.keyNodeInspection.implementationplanstatus')"
              for="key-node-inspection-implementationplanstatus"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="implementationplanstatus"
              id="key-node-inspection-implementationplanstatus"
              data-cy="implementationplanstatus"
              :class="{ valid: !v$.implementationplanstatus.$invalid, invalid: v$.implementationplanstatus.$invalid }"
              v-model="v$.implementationplanstatus.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.keyNodeInspection.isimplementationplanmaterial')"
              for="key-node-inspection-isimplementationplanmaterial"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="isimplementationplanmaterial"
              id="key-node-inspection-isimplementationplanmaterial"
              data-cy="isimplementationplanmaterial"
              :class="{ valid: !v$.isimplementationplanmaterial.$invalid, invalid: v$.isimplementationplanmaterial.$invalid }"
              v-model="v$.isimplementationplanmaterial.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.keyNodeInspection.technologyplanstatus')"
              for="key-node-inspection-technologyplanstatus"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="technologyplanstatus"
              id="key-node-inspection-technologyplanstatus"
              data-cy="technologyplanstatus"
              :class="{ valid: !v$.technologyplanstatus.$invalid, invalid: v$.technologyplanstatus.$invalid }"
              v-model="v$.technologyplanstatus.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.keyNodeInspection.istechnologymaterial')"
              for="key-node-inspection-istechnologymaterial"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="istechnologymaterial"
              id="key-node-inspection-istechnologymaterial"
              data-cy="istechnologymaterial"
              :class="{ valid: !v$.istechnologymaterial.$invalid, invalid: v$.istechnologymaterial.$invalid }"
              v-model="v$.istechnologymaterial.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.keyNodeInspection.firstcheckstatus')"
              for="key-node-inspection-firstcheckstatus"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="firstcheckstatus"
              id="key-node-inspection-firstcheckstatus"
              data-cy="firstcheckstatus"
              :class="{ valid: !v$.firstcheckstatus.$invalid, invalid: v$.firstcheckstatus.$invalid }"
              v-model="v$.firstcheckstatus.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.keyNodeInspection.isfirstcheckmaterial')"
              for="key-node-inspection-isfirstcheckmaterial"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="isfirstcheckmaterial"
              id="key-node-inspection-isfirstcheckmaterial"
              data-cy="isfirstcheckmaterial"
              :class="{ valid: !v$.isfirstcheckmaterial.$invalid, invalid: v$.isfirstcheckmaterial.$invalid }"
              v-model="v$.isfirstcheckmaterial.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.keyNodeInspection.productioncheckstatus')"
              for="key-node-inspection-productioncheckstatus"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="productioncheckstatus"
              id="key-node-inspection-productioncheckstatus"
              data-cy="productioncheckstatus"
              :class="{ valid: !v$.productioncheckstatus.$invalid, invalid: v$.productioncheckstatus.$invalid }"
              v-model="v$.productioncheckstatus.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.keyNodeInspection.isproductioncheckmaterial')"
              for="key-node-inspection-isproductioncheckmaterial"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="isproductioncheckmaterial"
              id="key-node-inspection-isproductioncheckmaterial"
              data-cy="isproductioncheckmaterial"
              :class="{ valid: !v$.isproductioncheckmaterial.$invalid, invalid: v$.isproductioncheckmaterial.$invalid }"
              v-model="v$.isproductioncheckmaterial.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.keyNodeInspection.status')" for="key-node-inspection-status"></label>
            <el-input
              type="text"
              class="form-control"
              name="status"
              id="key-node-inspection-status"
              data-cy="status"
              :class="{ valid: !v$.status.$invalid, invalid: v$.status.$invalid }"
              v-model="v$.status.$model"
            />
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
<script lang="ts" src="./key-node-inspection-update.component.ts"></script>
