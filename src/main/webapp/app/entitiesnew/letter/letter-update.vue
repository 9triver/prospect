<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.letter.home.createOrEditLabel"
          data-cy="LetterCreateUpdateHeading"
          v-text="t$('jy1App.letter.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="letter.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="letter.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.letter.lettername')" for="letter-lettername"></label>
            <el-input
              type="text"
              class="form-control"
              name="lettername"
              id="letter-lettername"
              data-cy="lettername"
              :class="{ valid: !v$.lettername.$invalid, invalid: v$.lettername.$invalid }"
              v-model="v$.lettername.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.letter.letternumber')" for="letter-letternumber"></label>
            <el-input
              type="text"
              class="form-control"
              name="letternumber"
              id="letter-letternumber"
              data-cy="letternumber"
              :class="{ valid: !v$.letternumber.$invalid, invalid: v$.letternumber.$invalid }"
              v-model="v$.letternumber.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.letter.lettertype')" for="letter-lettertype"></label>
            <el-input
              type="text"
              class="form-control"
              name="lettertype"
              id="letter-lettertype"
              data-cy="lettertype"
              :class="{ valid: !v$.lettertype.$invalid, invalid: v$.lettertype.$invalid }"
              v-model="v$.lettertype.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.letter.secretlevel')" for="letter-secretlevel"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="letter-secretlevel"
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
            <label class="form-control-label" v-text="t$('jy1App.letter.lettercontent')" for="letter-lettercontent"></label>
            <el-input
              type="text"
              class="form-control"
              name="lettercontent"
              id="letter-lettercontent"
              data-cy="lettercontent"
              :class="{ valid: !v$.lettercontent.$invalid, invalid: v$.lettercontent.$invalid }"
              v-model="v$.lettercontent.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.letter.letterstatus')" for="letter-letterstatus"></label>
            <el-input
              type="text"
              class="form-control"
              name="letterstatus"
              id="letter-letterstatus"
              data-cy="letterstatus"
              :class="{ valid: !v$.letterstatus.$invalid, invalid: v$.letterstatus.$invalid }"
              v-model="v$.letterstatus.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.letter.lettertime')" for="letter-lettertime"></label>
            <el-date-picker v-model="v$.lettertime.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.letter.previousfile')" for="letter-previousfile"></label>
            <el-input
              type="text"
              class="form-control"
              name="previousfile"
              id="letter-previousfile"
              data-cy="previousfile"
              :class="{ valid: !v$.previousfile.$invalid, invalid: v$.previousfile.$invalid }"
              v-model="v$.previousfile.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.letter.datarecordstatus')" for="letter-datarecordstatus"></label>
            <el-input
              type="text"
              class="form-control"
              name="datarecordstatus"
              id="letter-datarecordstatus"
              data-cy="datarecordstatus"
              :class="{ valid: !v$.datarecordstatus.$invalid, invalid: v$.datarecordstatus.$invalid }"
              v-model="v$.datarecordstatus.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.letter.wbsid')" for="letter-wbsid"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="letter-wbsid"
              data-cy="wbsid"
              name="wbsid"
              v-model="letter.wbsid"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="letter.wbsid && projectwbsOption.id === letter.wbsid.id ? letter.wbsid : projectwbsOption"
                v-for="projectwbsOption in projectwbs"
                :key="projectwbsOption.id"
                :label="projectwbsOption.id"
                >{{ projectwbsOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.letter.workbagid')" for="letter-workbagid"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="letter-workbagid"
              data-cy="workbagid"
              name="workbagid"
              v-model="letter.workbagid"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="letter.workbagid && workbagOption.id === letter.workbagid.id ? letter.workbagid : workbagOption"
                v-for="workbagOption in workbags"
                :key="workbagOption.id"
                :label="workbagOption.id"
                >{{ workbagOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.letter.frontlineid')" for="letter-frontlineid"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="letter-frontlineid"
              data-cy="frontlineid"
              name="frontlineid"
              v-model="letter.frontlineid"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="letter.frontlineid && frontlineOption.id === letter.frontlineid.id ? letter.frontlineid : frontlineOption"
                v-for="frontlineOption in frontlines"
                :key="frontlineOption.id"
                :label="frontlineOption.id"
                >{{ frontlineOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.letter.receivingunit')" for="letter-receivingunit"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="letter-receivingunit"
              data-cy="receivingunit"
              name="receivingunit"
              v-model="letter.receivingunit"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  letter.receivingunit && departmentOption.id === letter.receivingunit.id ? letter.receivingunit : departmentOption
                "
                v-for="departmentOption in departments"
                :key="departmentOption.id"
                :label="departmentOption.id"
                >{{ departmentOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.letter.sendingunit')" for="letter-sendingunit"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="letter-sendingunit"
              data-cy="sendingunit"
              name="sendingunit"
              v-model="letter.sendingunit"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="letter.sendingunit && departmentOption.id === letter.sendingunit.id ? letter.sendingunit : departmentOption"
                v-for="departmentOption in departments"
                :key="departmentOption.id"
                :label="departmentOption.id"
                >{{ departmentOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.letter.lettermaker')" for="letter-lettermaker"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="letter-lettermaker"
              data-cy="lettermaker"
              name="lettermaker"
              v-model="letter.lettermaker"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  letter.lettermaker && hrManagementOption.id === letter.lettermaker.id ? letter.lettermaker : hrManagementOption
                "
                v-for="hrManagementOption in hrManagements"
                :key="hrManagementOption.id"
                :label="hrManagementOption.id"
                >{{ hrManagementOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.letter.letterreceiver')" for="letter-letterreceiver"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="letter-letterreceiver"
              data-cy="letterreceiver"
              name="letterreceiver"
              v-model="letter.letterreceiver"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  letter.letterreceiver && hrManagementOption.id === letter.letterreceiver.id ? letter.letterreceiver : hrManagementOption
                "
                v-for="hrManagementOption in hrManagements"
                :key="hrManagementOption.id"
                :label="hrManagementOption.id"
                >{{ hrManagementOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.letter.letterhandler')" for="letter-letterhandler"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="letter-letterhandler"
              data-cy="letterhandler"
              name="letterhandler"
              v-model="letter.letterhandler"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  letter.letterhandler && hrManagementOption.id === letter.letterhandler.id ? letter.letterhandler : hrManagementOption
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
<script lang="ts" src="./letter-update.component.ts"></script>
