<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.projectTotalwbs.home.createOrEditLabel"
          data-cy="ProjectTotalwbsCreateUpdateHeading"
          v-text="t$('jy1App.projectTotalwbs.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="projectTotalwbs.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="projectTotalwbs.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectTotalwbs.wbsname')" for="projecttotalwbs-wbsname"></label>
            <el-input
              type="text"
              class="form-control"
              name="wbsname"
              id="projecttotalwbs-wbsname"
              data-cy="wbsname"
              :class="{ valid: !v$.wbsname.$invalid, invalid: v$.wbsname.$invalid }"
              v-model="v$.wbsname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectTotalwbs.parentwbsid')" for="projecttotalwbs-parentwbsid"></label>
            <el-input
              type="text"
              class="form-control"
              name="parentwbsid"
              id="projecttotalwbs-parentwbsid"
              data-cy="parentwbsid"
              :class="{ valid: !v$.parentwbsid.$invalid, invalid: v$.parentwbsid.$invalid }"
              v-model="v$.parentwbsid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectTotalwbs.pbsid')" for="projecttotalwbs-pbsid"></label>
            <el-input
              type="text"
              class="form-control"
              name="pbsid"
              id="projecttotalwbs-pbsid"
              data-cy="pbsid"
              :class="{ valid: !v$.pbsid.$invalid, invalid: v$.pbsid.$invalid }"
              v-model="v$.pbsid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectTotalwbs.description')" for="projecttotalwbs-description"></label>
            <el-input
              type="text"
              class="form-control"
              name="description"
              id="projecttotalwbs-description"
              data-cy="description"
              :class="{ valid: !v$.description.$invalid, invalid: v$.description.$invalid }"
              v-model="v$.description.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectTotalwbs.belongfront')" for="projecttotalwbs-belongfront"></label>
            <el-input
              type="text"
              class="form-control"
              name="belongfront"
              id="projecttotalwbs-belongfront"
              data-cy="belongfront"
              :class="{ valid: !v$.belongfront.$invalid, invalid: v$.belongfront.$invalid }"
              v-model="v$.belongfront.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectTotalwbs.starttime')" for="projecttotalwbs-starttime"></label>
            <el-date-picker
              type="date"
              v-model="v$.starttime.$model"
            />
            <div></div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectTotalwbs.endtime')" for="projecttotalwbs-endtime"></label>
            <el-date-picker
              type="date"
              v-model="v$.endtime.$model"
            />
            <div></div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectTotalwbs.progress')" for="projecttotalwbs-progress"></label>
            <el-input
              type="number"
              class="form-control"
              name="progress"
              id="projecttotalwbs-progress"
              data-cy="progress"
              :class="{ valid: !v$.progress.$invalid, invalid: v$.progress.$invalid }"
              v-model.number="v$.progress.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectTotalwbs.type')" for="projecttotalwbs-type"></label>
            <el-input
              type="number"
              class="form-control"
              name="type"
              id="projecttotalwbs-type"
              data-cy="type"
              :class="{ valid: !v$.type.$invalid, invalid: v$.type.$invalid }"
              v-model.number="v$.type.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectTotalwbs.priorty')" for="projecttotalwbs-priorty"></label>
            <el-input
              type="number"
              class="form-control"
              name="priorty"
              id="projecttotalwbs-priorty"
              data-cy="priorty"
              :class="{ valid: !v$.priorty.$invalid, invalid: v$.priorty.$invalid }"
              v-model.number="v$.priorty.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectTotalwbs.secretlevel')" for="projecttotalwbs-secretlevel"></label>
            <el-select
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="projecttotalwbs-secretlevel"
              data-cy="secretlevel"
            >
              <el-option
                v-for="secretlevel in secretlevelValues"
                :key="secretlevel"
                v-bind:value="secretlevel"
                v-bind:label="t$('jy1App.Secretlevel.' + secretlevel)"
              >
                {{ secretlevel }}
              </el-option>
            </el-select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.projectTotalwbs.deliverables')"
              for="projecttotalwbs-deliverables"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="deliverables"
              id="projecttotalwbs-deliverables"
              data-cy="deliverables"
              :class="{ valid: !v$.deliverables.$invalid, invalid: v$.deliverables.$invalid }"
              v-model="v$.deliverables.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectTotalwbs.status')" for="projecttotalwbs-status"></label>
            <el-select
              class="form-control"
              name="status"
              :class="{ valid: !v$.status.$invalid, invalid: v$.status.$invalid }"
              v-model="v$.status.$model"
              id="projecttotalwbs-status"
              data-cy="status"
            >
              <el-option
                v-for="projectStatus in projectStatusValues"
                :key="projectStatus"
                v-bind:value="projectStatus"
                v-bind:label="t$('jy1App.ProjectStatus.' + projectStatus)"
                :label="projectStatus"
              >
              </el-option>
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectTotalwbs.auditStatus')" for="projecttotalwbs-auditStatus"></label>
            <el-select
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="projecttotalwbs-auditStatus"
              data-cy="auditStatus"
            >
              <el-option
                v-for="auditStatus in auditStatusValues"
                :key="auditStatus"
                v-bind:value="auditStatus"
                v-bind:label="t$('jy1App.AuditStatus.' + auditStatus)"
              >
              </el-option>
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectTotalwbs.workbag')" for="projecttotalwbs-workbag"></label>
            <el-input
              type="number"
              class="form-control"
              name="workbag"
              id="projecttotalwbs-workbag"
              data-cy="workbag"
              :class="{ valid: !v$.workbag.$invalid, invalid: v$.workbag.$invalid }"
              v-model.number="v$.workbag.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectTotalwbs.projectwbs')" for="projecttotalwbs-projectwbs"></label>
            <el-select
              value-key="id"
              class="form-control"
              id="projecttotalwbs-projectwbs"
              data-cy="projectwbs"
              name="projectwbs"
              v-model="projectTotalwbs.projectwbs"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  projectTotalwbs.projectwbs && projectwbsOption.id === projectTotalwbs.projectwbs.id
                    ? projectTotalwbs.projectwbs
                    : projectwbsOption
                "
                v-for="projectwbsOption in projectwbs"
                :key="projectwbsOption.id"
                :label="projectwbsOption.id"
              >
              </el-option>
            </el-select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.projectTotalwbs.responsibleperson')"
              for="projecttotalwbs-responsibleperson"
            ></label>
            <el-select
              value-key="id"
              class="form-control"
              id="projectwbs-responsibleperson"
              data-cy="responsibleperson"
              name="responsibleperson"
              v-model="projectTotalwbs.responsibleperson"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  projectTotalwbs.responsibleperson && officersOption.id === projectTotalwbs.responsibleperson.id
                    ? projectTotalwbs.responsibleperson
                    : officersOption
                "
                v-for="officersOption in officers"
                :key="officersOption.id"
                :label="officersOption.id"
                >{{ officersOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.projectTotalwbs.technicaldirector')"
              for="projecttotalwbs-technicaldirector"
            ></label>
            <el-select
              value-key="id"
              class="form-control"
              id="projecttotalwbs-technicaldirector"
              data-cy="technicaldirector"
              name="technicaldirector"
              v-model="projectTotalwbs.technicaldirector"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  projectTotalwbs.technicaldirector && officersOption.id === projectTotalwbs.technicaldirector.id
                    ? projectTotalwbs.technicaldirector
                    : officersOption
                "
                v-for="officersOption in officers"
                :key="officersOption.id"
                :label="officersOption.id"
              >
              </el-option>
            </el-select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.projectTotalwbs.administrativedirector')"
              for="projecttotalwbs-administrativedirector"
            ></label>
            <el-select
              value-key="id"
              class="form-control"
              id="projecttotalwbs-administrativedirector"
              data-cy="administrativedirector"
              name="administrativedirector"
              v-model="projectTotalwbs.administrativedirector"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  projectTotalwbs.administrativedirector && officersOption.id === projectTotalwbs.administrativedirector.id
                    ? projectTotalwbs.administrativedirector
                    : officersOption
                "
                v-for="officersOption in officers"
                :key="officersOption.id"
                :label="officersOption.id"
              >
              </el-option>
            </el-select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.projectTotalwbs.knowingpeople')"
              for="projecttotalwbs-knowingpeople"
            ></label>
            <el-select
              value-key="id"
              class="form-control"
              id="projecttotalwbs-knowingpeople"
              data-cy="knowingpeople"
              name="knowingpeople"
              v-model="projectTotalwbs.knowingpeople"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  projectTotalwbs.knowingpeople && officersOption.id === projectTotalwbs.knowingpeople.id
                    ? projectTotalwbs.knowingpeople
                    : officersOption
                "
                v-for="officersOption in officers"
                :key="officersOption.id"
                :label="officersOption.id"
              >
              </el-option>
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectTotalwbs.auditorid')" for="projecttotalwbs-auditorid"></label>
            <el-select
              value-key="id"
              class="form-control"
              id="projecttotalwbs-auditorid"
              data-cy="auditorid"
              name="auditorid"
              v-model="projectTotalwbs.auditorid"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  projectTotalwbs.auditorid && officersOption.id === projectTotalwbs.auditorid.id
                    ? projectTotalwbs.auditorid
                    : officersOption
                "
                v-for="officersOption in officers"
                :key="officersOption.id"
                :label="officersOption.id"
              >
              </el-option>
            </el-select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.projectTotalwbs.responsibledepartment')"
              for="projecttotalwbs-responsibledepartment"
            ></label>
            <el-select
              value-key="id"
              class="form-control"
              id="projecttotalwbs-responsibledepartment"
              data-cy="responsibledepartment"
              name="responsibledepartment"
              v-model="projectTotalwbs.responsibledepartment"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  projectTotalwbs.responsibledepartment && departmentOption.id === projectTotalwbs.responsibledepartment.id
                    ? projectTotalwbs.responsibledepartment
                    : departmentOption
                "
                v-for="departmentOption in departments"
                :key="departmentOption.id"
                :label="departmentOption.id"
              >
              </el-option>
            </el-select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.projectTotalwbs.relevantdepartment')"
              for="projecttotalwbs-relevantdepartment"
            ></label>
            <el-select
              value-key="id"
              class="form-control"
              id="projecttotalwbs-relevantdepartment"
              data-cy="relevantdepartment"
              name="relevantdepartment"
              v-model="projectTotalwbs.relevantdepartment"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  projectTotalwbs.relevantdepartment && departmentOption.id === projectTotalwbs.relevantdepartment.id
                    ? projectTotalwbs.relevantdepartment
                    : departmentOption
                "
                v-for="departmentOption in departments"
                :key="departmentOption.id"
                :label="departmentOption.id"
              >
              </el-option>
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectTotalwbs.department')" for="projecttotalwbs-department"></label>
            <el-select
              value-key="id"
              class="form-control"
              id="projecttotalwbs-department"
              data-cy="department"
              name="department"
              v-model="projectTotalwbs.department"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  projectTotalwbs.department && departmentOption.id === projectTotalwbs.department.id
                    ? projectTotalwbs.department
                    : departmentOption
                "
                v-for="departmentOption in departments"
                :key="departmentOption.id"
                :label="departmentOption.id"
              >
              </el-option>
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
<script lang="ts" src="./projecttotalwbs-update.component.ts"></script>
