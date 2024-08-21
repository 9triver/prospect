<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.projectpbs.home.createOrEditLabel"
          data-cy="ProjectpbsCreateUpdateHeading"
          v-text="t$('jy1App.projectpbs.home.createOrEditLabel')"
        ></h2>
        <div>
          <!-- <div class="form-group" v-if="projectpbs.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="projectpbs.id" readonly />
          </div> -->


          <el-form :model="formInline" label-width="auto">
            <el-row>
              <el-col :span="8" v-if="projectpbs.updatetype === 1 ">
                <el-form-item label="更新">
                  <el-input v-model="projectpbs.updatetype" placeholder="更新" clearable  disabled/>
                </el-form-item>
              </el-col>
              <!-- <el-col :span="8" v-if="projectpbs.id">
                <el-form-item label="PBS编号">
                  <el-input v-model="projectpbs.id" placeholder="ID" clearable  disabled/>
                </el-form-item>
              </el-col> -->
              <el-col :span="8" >
                <el-form-item label="PBS编号">
                  <el-input v-model="v$.id.$model" placeholder="编号" clearable />
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="PBS名称">
                  <el-input v-model="v$.pbsname.$model" placeholder="名称" clearable/>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="父级PBS">
                  <el-input v-model="v$.parentpbsid.$model" placeholder="父级PBS" clearable/>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="起止时间">
                  <el-date-picker
                    v-model="timeRange"
                    type="daterange"
                    range-separator="-"
                    start-placeholder="开始时间"
                    end-placeholder="结束时间"
                  />
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="描述">
                  <el-input v-model="v$.description.$model" placeholder="描述" clearable/>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="进度">
                  <el-input v-model="v$.progress.$model" placeholder="进度" clearable/>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="类型">
                  <el-select v-model="v$.type.$model" placeholder="请选择">
                    <el-option label="科研技术类" :value="1" />
                    <el-option label="生产管理类" :value="2" />
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="优先级">
                  <el-select v-model="v$.priorty.$model" placeholder="请选择">
                    <el-option label="普通" :value="1" />
                    <el-option label="中等" :value="2" />
                    <el-option label="重要" :value="3" />
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="密级">
                  <el-select v-model="v$.secretlevel.$model" placeholder="请选择">
                    <el-option label="机密" value="SECRET" />
                    <el-option label="非保密-内部" value="NOSECTET_INTERNAL" />
                    <el-option label="公开" value="PUBLIC" />
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="状态">
                  <el-select v-model="v$.status.$model" placeholder="请选择">
                    <el-option label="未开始" value="NOTSTART" />
                    <el-option label="进行中" value="IN_PROGRESS" />
                    <el-option label="已完成" value="COMPLETED" />
                    <el-option label="已取消" value="CANCELED" />
                    <el-option label="已超期" value="EXPIRED" />
                  </el-select>
                </el-form-item>
              </el-col>
              <!-- <el-col :span="8">
                <el-form-item label="审核状态">
                  <el-select v-model="v$.auditStatus.$model" placeholder="请选择">
                    <el-option label="未审核" value="Not_Audited" />
                    <el-option label="审核中" value="In_Audit" />
                    <el-option label="已通过" value="Approved" />
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="关联wbs">
                  <el-input v-model="selectedWbsId" readonly>
                    <template #append>
                      <el-button :icon="Search" @click="dialogVisible = true"/>
                    </template>
                  </el-input>
                </el-form-item>
              </el-col> -->
              
            </el-row>
          </el-form>
          
          <el-dialog
            v-model="dialogVisible"
            title="选择WBS"
            width="60%"
            :before-close="handleClose"
          >
            <my-content-component />
            <!-- <my-content-component /> -->
            <!-- <template #footer>
              <div class="dialog-footer">
                <el-button @click="dialogVisible = false">取消</el-button>
                <el-button type="primary" @click="dialogVisible = false">确认</el-button>
              </div>
            </template> -->
          </el-dialog>
          
          <!-- 以下是生成器生成的代码，暂时不显示 -->
          <div style="display: none;">
            <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.productlevel')" for="projectpbs-productlevel"></label>
            <input
              type="number"
              class="form-control"
              name="productlevel"
              id="projectpbs-productlevel"
              data-cy="productlevel"
              :class="{ valid: !v$.productlevel.$invalid, invalid: v$.productlevel.$invalid }"
              v-model.number="v$.productlevel.$model"
            />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="t$('jy1App.projectpbs.ifkey')" for="projectpbs-ifkey"></label>
              <input
                type="number"
                class="form-control"
                name="ifkey"
                id="projectpbs-ifkey"
                data-cy="ifkey"
                :class="{ valid: !v$.ifkey.$invalid, invalid: v$.ifkey.$invalid }"
                v-model.number="v$.ifkey.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="t$('jy1App.projectpbs.ifimporttant')" for="projectpbs-ifimporttant"></label>
              <input
                type="number"
                class="form-control"
                name="ifimporttant"
                id="projectpbs-ifimporttant"
                data-cy="ifimporttant"
                :class="{ valid: !v$.ifimporttant.$invalid, invalid: v$.ifimporttant.$invalid }"
                v-model.number="v$.ifimporttant.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="t$('jy1App.projectpbs.type')" for="projectpbs-type"></label>
              <input
                type="number"
                class="form-control"
                name="type"
                id="projectpbs-type"
                data-cy="type"
                :class="{ valid: !v$.type.$invalid, invalid: v$.type.$invalid }"
                v-model.number="v$.type.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="t$('jy1App.projectpbs.status')" for="projectpbs-status"></label>
              <select
                class="form-control"
                name="status"
                :class="{ valid: !v$.status.$invalid, invalid: v$.status.$invalid }"
                v-model="v$.status.$model"
                id="projectpbs-status"
                data-cy="status"
              >
                <option
                  v-for="projectStatus in projectStatusValues"
                  :key="projectStatus"
                  v-bind:value="projectStatus"
                  v-bind:label="t$('jy1App.ProjectStatus.' + projectStatus)"
                >
                  {{ projectStatus }}
                </option>
              </select>
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="t$('jy1App.projectpbs.auditStatus')" for="projectpbs-auditStatus"></label>
              <select
                class="form-control"
                name="auditStatus"
                :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
                v-model="v$.auditStatus.$model"
                id="projectpbs-auditStatus"
                data-cy="auditStatus"
              >
                <option
                  v-for="auditStatus in auditStatusValues"
                  :key="auditStatus"
                  v-bind:value="auditStatus"
                  v-bind:label="t$('jy1App.AuditStatus.' + auditStatus)"
                >
                  {{ auditStatus }}
                </option>
              </select>
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="t$('jy1App.projectpbs.technicaldirector')" for="projectpbs-technicaldirector"></label>
              <select
                class="form-control"
                id="projectpbs-technicaldirector"
                data-cy="technicaldirector"
                name="technicaldirector"
                v-model="projectpbs.technicaldirector"
              >
                <option v-bind:value="null"></option>
                <option
                  v-bind:value="
                    projectpbs.technicaldirector && officersOption.id === projectpbs.technicaldirector.id
                      ? projectpbs.technicaldirector
                      : officersOption
                  "
                  v-for="officersOption in officers"
                  :key="officersOption.id"
                >
                  {{ officersOption.id }}
                </option>
              </select>
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="t$('jy1App.projectpbs.administrativedirector')"
                for="projectpbs-administrativedirector"
              ></label>
              <select
                class="form-control"
                id="projectpbs-administrativedirector"
                data-cy="administrativedirector"
                name="administrativedirector"
                v-model="projectpbs.administrativedirector"
              >
                <option v-bind:value="null"></option>
                <option
                  v-bind:value="
                    projectpbs.administrativedirector && officersOption.id === projectpbs.administrativedirector.id
                      ? projectpbs.administrativedirector
                      : officersOption
                  "
                  v-for="officersOption in officers"
                  :key="officersOption.id"
                >
                  {{ officersOption.id }}
                </option>
              </select>
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="t$('jy1App.projectpbs.knowingpeople')" for="projectpbs-knowingpeople"></label>
              <select
                class="form-control"
                id="projectpbs-knowingpeople"
                data-cy="knowingpeople"
                name="knowingpeople"
                v-model="projectpbs.knowingpeople"
              >
                <option v-bind:value="null"></option>
                <option
                  v-bind:value="
                    projectpbs.knowingpeople && officersOption.id === projectpbs.knowingpeople.id ? projectpbs.knowingpeople : officersOption
                  "
                  v-for="officersOption in officers"
                  :key="officersOption.id"
                >
                  {{ officersOption.id }}
                </option>
              </select>
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="t$('jy1App.projectpbs.auditorid')" for="projectpbs-auditorid"></label>
              <select class="form-control" id="projectpbs-auditorid" data-cy="auditorid" name="auditorid" v-model="projectpbs.auditorid">
                <option v-bind:value="null"></option>
                <option
                  v-bind:value="projectpbs.auditorid && officersOption.id === projectpbs.auditorid.id ? projectpbs.auditorid : officersOption"
                  v-for="officersOption in officers"
                  :key="officersOption.id"
                >
                  {{ officersOption.id }}
                </option>
              </select>
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="t$('jy1App.projectpbs.responsibledepartment')"
                for="projectpbs-responsibledepartment"
              ></label>
              <select
                class="form-control"
                id="projectpbs-responsibledepartment"
                data-cy="responsibledepartment"
                name="responsibledepartment"
                v-model="projectpbs.responsibledepartment"
              >
                <option v-bind:value="null"></option>
                <option
                  v-bind:value="
                    projectpbs.responsibledepartment && departmentOption.id === projectpbs.responsibledepartment.id
                      ? projectpbs.responsibledepartment
                      : departmentOption
                  "
                  v-for="departmentOption in departments"
                  :key="departmentOption.id"
                >
                  {{ departmentOption.id }}
                </option>
              </select>
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="t$('jy1App.projectpbs.relevantdepartment')"
                for="projectpbs-relevantdepartment"
              ></label>
              <select
                class="form-control"
                id="projectpbs-relevantdepartment"
                data-cy="relevantdepartment"
                name="relevantdepartment"
                v-model="projectpbs.relevantdepartment"
              >
                <option v-bind:value="null"></option>
                <option
                  v-bind:value="
                    projectpbs.relevantdepartment && departmentOption.id === projectpbs.relevantdepartment.id
                      ? projectpbs.relevantdepartment
                      : departmentOption
                  "
                  v-for="departmentOption in departments"
                  :key="departmentOption.id"
                >
                  {{ departmentOption.id }}
                </option>
              </select>
            </div>
            <div class="form-group">
              <label v-text="t$('jy1App.projectpbs.projectwbs')" for="projectpbs-projectwbs"></label>
              <select
                class="form-control"
                id="projectpbs-projectwbs"
                data-cy="projectwbs"
                multiple
                name="projectwbs"
                v-if="projectpbs.projectwbs !== undefined"
                v-model="projectpbs.projectwbs"
              >
                <option
                  v-bind:value="getSelected(projectpbs.projectwbs, projectwbsOption, 'id')"
                  v-for="projectwbsOption in projectwbs"
                  :key="projectwbsOption.id"
                >
                  {{ projectwbsOption.id }}
                </option>
              </select>
            </div>
            <div class="form-group">
              <label v-text="t$('jy1App.projectpbs.project')" for="projectpbs-project"></label>
              <select
                class="form-control"
                id="projectpbs-projects"
                data-cy="project"
                multiple
                name="project"
                v-if="projectpbs.projects !== undefined"
                v-model="projectpbs.projects"
              >
                <option
                  v-bind:value="getSelected(projectpbs.projects, projectOption, 'id')"
                  v-for="projectOption in projects"
                  :key="projectOption.id"
                >
                  {{ projectOption.id }}
                </option>
              </select>
            </div>
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
<script lang="ts" src="./projectpbs-update.component.ts"></script>
