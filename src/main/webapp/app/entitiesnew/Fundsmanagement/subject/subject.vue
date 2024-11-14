<template>
  <div>
    <h2 id="page-heading" data-cy="SubjectHeading">
      <div class="d-flex justify-content-end">
        <el-button type="primary"  class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span>刷新</span>
        </el-button>
        <router-link :to="{ name: 'SubjectCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-subject"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span>创建</span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && subjects && subjects.length === 0">
      <span>未查询到符合条件的数据</span>
    </div>

    <div class="table-responsive" v-if="subjects && subjects.length > 0">
      <el-table :data="subjects" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" label="编号">
          <template #default="scope">
            <router-link :to="{ name: 'SubjectView', params: { subjectId: scope.row.id } }">{{ scope.row.id }}</router-link>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="name" label="科目名称" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.name }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="100px" show-overflow-tooltip prop="type" label="类型" :sortable="false">
          <template #default="scope">
              <span>
                <template v-if="scope.row.type === '1'">材料/外协/专用</template>
                <template v-if="scope.row.type === '2'">其他科目</template>
              </span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="status" label="状态" :sortable="true">
          <template #default="scope">
              <span>
                <template v-if="scope.row.status === 1 ">有效</template>
                <template v-if="scope.row.status === 2 ">无效</template>
              </span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="remark" label="备注" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.remark }}</span>
          </template>
        </el-table-column>

        <el-table-column min-width="150px" show-overflow-tooltip label="操作">
          <template #default="scope">
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'SubjectView', params: { subjectId: scope.row.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>

                <router-link :to="{ name: 'SubjectEdit', params: { subjectId: scope.row.id } }" custom v-slot="{ navigate }">
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
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <template #modal-title>
        <span id="jy1App.subject.delete.question" data-cy="subjectDeleteDialogHeading" v-text="t$('entity.delete.title')"></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-subject-heading" v-text="t$('jy1App.subject.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-subject"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeSubject()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./subject.component.ts"></script>
