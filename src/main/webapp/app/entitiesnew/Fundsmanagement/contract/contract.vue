<template>
  <div>
      <el-form :model="form" class="demo-form-inline" label-width="auto" ref="queryFormRef">
        <el-row :gutter="10">
          <el-col :span="8">
            <el-form-item label="承沿合同" prop="contractname">
              <el-input v-model="form.contractname" ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="合同类型" prop="contracttype">
              <el-select v-model="form.contracttype" placeholder="请选择" size="moddle">
                <el-option label="技术类" value="TECHNICAL" />
                <el-option label="研究类" value="RESEARCH" />
                <el-option label="试制类" value="PILOT_PRODUCTION" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="重大项目" prop="projectwbsname">
              <el-input v-model="form.projectwbsname" ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="密级" prop="secretlevel">
              <el-select v-model="form.secretlevel" placeholder="请选择" size="moddle">
                <el-option label="公开" value="PUBLIC" />
                <el-option label="内部" value="INTERNAL" />
                <el-option label="秘密" value="SECRET" />
                <el-option label="机密" value="CONFIDENTIAL" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    
      <div class="d-flex justify-content-center">
        <el-form-item>
            <el-button type="primary" plain @click="onSubmit">查询</el-button>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" plain @click="handleSyncList(queryFormRef)">重置</el-button>
        </el-form-item>
        <el-form-item>
          <router-link :to="{ name: 'ContractCreateNew' }" custom v-slot="{ navigate }">
            <el-button type="primary" @click="navigate"
              id="jh-create-entity"
              data-cy="entityCreateButton"
              class="btn btn-primary jh-create-entity create-other-payment">创建</el-button>
          </router-link>
        </el-form-item>
      </div>     
    <br />
    <div class="alert alert-warning" v-if="!isFetching && contracts && contracts.length === 0">
      <span>未查询到符合条件的数据</span>
    </div>
    
    <div class="table-responsive" v-if="contracts && contracts.length > 0">
      <el-table :data="contracts" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="60px" show-overflow-tooltip prop="id" label="序号">
          <template #default="scope">
            <router-link :to="{ name: 'ContractView', params: { contractId: scope.row.id } }">{{ scope.row.id }}</router-link>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="contractcode"
          label="合同编号"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.contractcode }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="contractname"
          label="合同名称"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.contractname }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="projectwbsname"
          label="项目名称"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.projectwbsname }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="contracttype"
          label="合同类型"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-fieldIsEnum" v-text="t$('jy1App.ContractType.' + scope.row.contracttype)"></span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="year" label="年份" :sortable="true">
          <template #default="scope">
            <span class="field-default">{{ scope.row.year }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="amount" label="经费金额" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.amount }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="starttime" label="开始时间" :sortable="true">
          <template #default="scope">
            <span class="field-default">{{ scope.row.starttime }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="endtime" label="结束时间" :sortable="true">
          <template #default="scope">
            <span class="field-default">{{ scope.row.endtime }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="fileurl" label="文件存储地址" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.fileurl }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="secretlevel"
          label="密级"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-fieldIsEnum" v-text="t$('jy1App.Secretlevel.' + scope.row.secretlevel)"></span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="status" label="状态" :sortable="false">
          <template #default="scope">
            <span class="field-fieldIsEnum" v-text="t$('jy1App.ContractStatus.' + scope.row.status)"></span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="budgetamount"
          label="预算金额"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.budgetamount }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="estimatedamount"
          label="估算金额"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.estimatedamount }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="implementedamount"
          label="已实施金额"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.implementedamount }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="difference"
          label="差额"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.difference }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="projectwbs.id" label="重大项目">
          <template #default="scope">
            <td>
              <div v-if="scope.row.projectwbs">
                <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: scope.row.projectwbs.id } }">{{
                  scope.row.projectwbs.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip label="操作">
          <template #default="scope">
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ContractView', params: { contractId: scope.row.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'ContractEdit', params: { contractId: scope.row.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(scope.row)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="trash"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="t$('entity.action.delete')"></span>
                </b-button>
              </div>
            </td>
          </template>
        </el-table-column>
      </el-table>
      <!-- <table class="table table-striped" aria-describedby="contracts">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contract.contractcode')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contract.contractname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contract.projectwbsname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contract.contracttype')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contract.year')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contract.amount')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contract.starttime')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contract.endtime')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contract.fileurl')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contract.secretlevel')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contract.status')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contract.budgetamount')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contract.estimatedamount')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contract.implementedamount')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contract.difference')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contract.projectwbs')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contract.costControlSystem')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="contract in contracts"
                    :key="contract.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'ContractView', params: {contractId: contract.id}}">{{contract.id}}</router-link>
                    </td>
                    <td>{{contract.contractcode}}</td>
                    <td>{{contract.contractname}}</td>
                    <td>{{contract.projectwbsname}}</td>
                    <td v-text="t$('jy1App.ContractType.' + contract.contracttype)"></td>
                    <td>{{contract.year}}</td>
                    <td>{{contract.amount}}</td>
                    <td>{{contract.starttime}}</td>
                    <td>{{contract.endtime}}</td>
                    <td>{{contract.fileurl}}</td>
                    <td v-text="t$('jy1App.Secretlevel.' + contract.secretlevel)"></td>
                    <td v-text="t$('jy1App.ContractStatus.' + contract.status)"></td>
                    <td>{{contract.budgetamount}}</td>
                    <td>{{contract.estimatedamount}}</td>
                    <td>{{contract.implementedamount}}</td>
                    <td>{{contract.difference}}</td>
                    <td>
                        <div v-if="contract.projectwbs">
                            <router-link :to="{name: 'ProjectwbsView', params: {projectwbsId: contract.projectwbs.id}}">{{contract.projectwbs.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <span v-for="(costControlSystem, i) in contract.costControlSystems" :key="costControlSystem.id">{{i > 0 ? ', ' : ''}}
                            <router-link class="form-control-static" :to="{name: 'CostControlSystemView', params: {costControlSystemId: costControlSystem.id}}">{{costControlSystem.id}}</router-link>
                        </span>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'ContractView', params: {contractId: contract.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'ContractEdit', params: {contractId: contract.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(contract)"
                                   variant="danger"
                                   class="btn btn-sm"
                                   data-cy="entityDeleteButton"
                                   v-b-modal.removeEntity>
                                <font-awesome-icon icon="times"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="t$('entity.action.delete')"></span>
                            </b-button>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>-->
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <template #modal-title>
        <span id="jy1App.contract.delete.question" data-cy="contractDeleteDialogHeading" v-text="t$('entity.delete.title')"></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-contract-heading" v-text="t$('jy1App.contract.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-contract"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeContract()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./contract.component.ts"></script>
