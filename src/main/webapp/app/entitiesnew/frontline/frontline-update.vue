<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.frontline.home.createOrEditLabel"
          data-cy="FrontlineCreateUpdateHeading"
          v-text="t$('jy1App.frontline.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="frontline.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="frontline.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.frontline.name')" for="frontline-name"></label>
            <el-input
              type="text"
              class="form-control"
              name="name"
              id="frontline-name"
              data-cy="name"
              :class="{ valid: !v$.name.$invalid, invalid: v$.name.$invalid }"
              v-model="v$.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.frontline.description')" for="frontline-description"></label>
            <el-input
              type="text"
              class="form-control"
              name="description"
              id="frontline-description"
              data-cy="description"
              :class="{ valid: !v$.description.$invalid, invalid: v$.description.$invalid }"
              v-model="v$.description.$model"
            />
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.frontline.officers')" for="frontline-officers"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="frontline-officers"
              data-cy="officers"
              multiple
              name="officers"
              v-if="frontline.officers !== undefined"
              v-model="frontline.officers"
            >
              <el-option
                v-bind:value="getSelected(frontline.officers, officersOption, 'id')"
                v-for="officersOption in officers"
                :key="officersOption.id"
                :label="officersOption.id"
                >{{ officersOption.id }}</el-option
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
<script lang="ts" src="./frontline-update.component.ts"></script>
