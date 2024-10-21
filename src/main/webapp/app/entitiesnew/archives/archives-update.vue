<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.archives.home.createOrEditLabel"
          data-cy="ArchivesCreateUpdateHeading"
          v-text="t$('jy1App.archives.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="archives.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="archives.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.archives.title')" for="archives-title"></label>
            <el-input
              type="text"
              class="form-control"
              name="title"
              id="archives-title"
              data-cy="title"
              :class="{ valid: !v$.title.$invalid, invalid: v$.title.$invalid }"
              v-model="v$.title.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.archives.content')" for="archives-content"></label>
            <el-input
              type="text"
              class="form-control"
              name="content"
              id="archives-content"
              data-cy="content"
              :class="{ valid: !v$.content.$invalid, invalid: v$.content.$invalid }"
              v-model="v$.content.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.archives.date')" for="archives-date"></label>
            <el-date-picker v-model="v$.date.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.archives.page')" for="archives-page"></label>
            <el-input
              type="number"
              class="form-control"
              name="page"
              id="archives-page"
              data-cy="page"
              :class="{ valid: !v$.page.$invalid, invalid: v$.page.$invalid }"
              v-model.number="v$.page.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.archives.secretlevel')" for="archives-secretlevel"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="archives-secretlevel"
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
            <label
              class="form-control-label"
              v-text="t$('jy1App.archives.confidentialityperiod')"
              for="archives-confidentialityperiod"
            ></label>
            <el-date-picker v-model="v$.confidentialityperiod.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.archives.confidentialnumber')" for="archives-confidentialnumber"></label>
            <el-input
              type="text"
              class="form-control"
              name="confidentialnumber"
              id="archives-confidentialnumber"
              data-cy="confidentialnumber"
              :class="{ valid: !v$.confidentialnumber.$invalid, invalid: v$.confidentialnumber.$invalid }"
              v-model="v$.confidentialnumber.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.archives.storageperiod')" for="archives-storageperiod"></label>
            <el-date-picker v-model="v$.storageperiod.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.archives.plannumber')" for="archives-plannumber"></label>
            <el-input
              type="text"
              class="form-control"
              name="plannumber"
              id="archives-plannumber"
              data-cy="plannumber"
              :class="{ valid: !v$.plannumber.$invalid, invalid: v$.plannumber.$invalid }"
              v-model="v$.plannumber.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.archives.remarks')" for="archives-remarks"></label>
            <el-input
              type="text"
              class="form-control"
              name="remarks"
              id="archives-remarks"
              data-cy="remarks"
              :class="{ valid: !v$.remarks.$invalid, invalid: v$.remarks.$invalid }"
              v-model="v$.remarks.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.archives.receivingnumber')" for="archives-receivingnumber"></label>
            <el-input
              type="text"
              class="form-control"
              name="receivingnumber"
              id="archives-receivingnumber"
              data-cy="receivingnumber"
              :class="{ valid: !v$.receivingnumber.$invalid, invalid: v$.receivingnumber.$invalid }"
              v-model="v$.receivingnumber.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.archives.responsibleid')" for="archives-responsibleid"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="archives-responsibleid"
              data-cy="responsibleid"
              name="responsibleid"
              v-model="archives.responsibleid"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  archives.responsibleid && hrManagementOption.id === archives.responsibleid.id
                    ? archives.responsibleid
                    : hrManagementOption
                "
                v-for="hrManagementOption in hrManagements"
                :key="hrManagementOption.id"
                :label="hrManagementOption.id"
                >{{ hrManagementOption.id }}</el-option
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
<script lang="ts" src="./archives-update.component.ts"></script>
