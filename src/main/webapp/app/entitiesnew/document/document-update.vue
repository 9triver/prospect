<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.document.home.createOrEditLabel"
          data-cy="DocumentCreateUpdateHeading"
          v-text="t$('jy1App.document.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="document.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="document.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.document.documentname')" for="document-documentname"></label>
            <el-input
              type="text"
              class="form-control"
              name="documentname"
              id="document-documentname"
              data-cy="documentname"
              :class="{ valid: !v$.documentname.$invalid, invalid: v$.documentname.$invalid }"
              v-model="v$.documentname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.document.documenttype')" for="document-documenttype"></label>
            <el-input
              type="number"
              class="form-control"
              name="documenttype"
              id="document-documenttype"
              data-cy="documenttype"
              :class="{ valid: !v$.documenttype.$invalid, invalid: v$.documenttype.$invalid }"
              v-model.number="v$.documenttype.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.document.documentsize')" for="document-documentsize"></label>
            <el-input
              type="number"
              class="form-control"
              name="documentsize"
              id="document-documentsize"
              data-cy="documentsize"
              :class="{ valid: !v$.documentsize.$invalid, invalid: v$.documentsize.$invalid }"
              v-model.number="v$.documentsize.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.document.secretlevel')" for="document-secretlevel"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="document-secretlevel"
              data-cy="secretlevel"
            >
              <el-option
                v-for="secretlevel in secretlevelValues"
                :key="secretlevel"
                v-bind:value="secretlevel"
                v-bind:label="t$('jy1App.Secretlevel.' + secretlevel)"
                >{{ secretlevel }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.document.url')" for="document-url"></label>
            <el-input
              type="text"
              class="form-control"
              name="url"
              id="document-url"
              data-cy="url"
              :class="{ valid: !v$.url.$invalid, invalid: v$.url.$invalid }"
              v-model="v$.url.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.document.createtime')" for="document-createtime"></label>
            <el-date-picker v-model="v$.createtime.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.document.creatorname')" for="document-creatorname"></label>
            <el-input
              type="text"
              class="form-control"
              name="creatorname"
              id="document-creatorname"
              data-cy="creatorname"
              :class="{ valid: !v$.creatorname.$invalid, invalid: v$.creatorname.$invalid }"
              v-model="v$.creatorname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.document.creatorid')" for="document-creatorid"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="document-creatorid"
              data-cy="creatorid"
              name="creatorid"
              v-model="document.creatorid"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  document.creatorid && hrManagementOption.id === document.creatorid.id ? document.creatorid : hrManagementOption
                "
                v-for="hrManagementOption in hrManagements"
                :key="hrManagementOption.id"
                :label="hrManagementOption.id"
                >{{ hrManagementOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.document.projectwbs')" for="document-projectwbs"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="document-projectwbs"
              data-cy="projectwbs"
              name="projectwbs"
              v-model="document.projectwbs"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  document.projectwbs && projectwbsOption.id === document.projectwbs.id ? document.projectwbs : projectwbsOption
                "
                v-for="projectwbsOption in projectwbs"
                :key="projectwbsOption.id"
                :label="projectwbsOption.id"
                >{{ projectwbsOption.id }}</el-option
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
<script lang="ts" src="./document-update.component.ts"></script>
