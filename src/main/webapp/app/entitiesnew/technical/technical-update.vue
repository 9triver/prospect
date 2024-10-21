<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.technical.home.createOrEditLabel"
          data-cy="TechnicalCreateUpdateHeading"
          v-text="t$('jy1App.technical.home.createOrEditLabel')"
        ></h2>
        <div>
          <!-- <div class="form-group" v-if="technical.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="technical.id" readonly />
          </div> -->
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.technical.name')" for="technical-name"></label>
            <el-input
              type="text"
              class="form-control"
              name="name"
              id="technical-name"
              data-cy="name"
              :class="{ valid: !v$.name.$invalid, invalid: v$.name.$invalid }"
              v-model="v$.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.technical.description')" for="technical-description"></label>
            <el-input
              type="text"
              class="form-control"
              name="description"
              id="technical-description"
              data-cy="description"
              :class="{ valid: !v$.description.$invalid, invalid: v$.description.$invalid }"
              v-model="v$.description.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.technical.starttime')" for="technical-starttime"></label>
            <el-date-picker v-model="v$.starttime.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.technical.endtime')" for="technical-endtime"></label>
            <el-date-picker v-model="v$.endtime.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.technical.creatorid')" for="technical-creatorid"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="technical-creatorid"
              data-cy="creatorid"
              name="creatorid"
              v-model="technical.creatorid"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="technical.creatorid && officersOption.id === technical.creatorid.id ? technical.creatorid : officersOption"
                v-for="officersOption in officers"
                :key="officersOption.id"
                :label="officersOption.name"
                >{{ officersOption.name }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.technical.auditorid')" for="technical-auditorid"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="technical-auditorid"
              data-cy="auditorid"
              name="auditorid"
              v-model="technical.auditorid"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="technical.auditorid && officersOption.id === technical.auditorid.id ? technical.auditorid : officersOption"
                v-for="officersOption in officers"
                :key="officersOption.id"
                :label="officersOption.name"
                >{{ officersOption.name }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.technical.projectwbs')" for="technical-projectwbs"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="technical-projectwbs"
              data-cy="projectwbs"
              multiple
              name="projectwbs"
              v-if="technical.projectwbs !== undefined"
              v-model="technical.projectwbs"
            >
              <el-option
                v-bind:value="getSelected(technical.projectwbs, projectwbsOption, 'id')"
                v-for="projectwbsOption in projectwbs"
                :key="projectwbsOption.id"
                :label="projectwbsOption.wbsname"
                >{{ projectwbsOption.wbsname }}</el-option
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
<script lang="ts" src="./technical-update.component.ts"></script>
