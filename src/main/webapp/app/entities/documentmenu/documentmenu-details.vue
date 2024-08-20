<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="documentmenu">
        <h2 class="jh-entity-heading" data-cy="documentmenuDetailsHeading">
          <span v-text="t$('jy1App.documentmenu.detail.title')"></span> {{ documentmenu.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jy1App.documentmenu.menuid')"></span>
          </dt>
          <dd>
            <span>{{ documentmenu.menuid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.documentmenu.belongtype')"></span>
          </dt>
          <dd>
            <span>{{ documentmenu.belongtype }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.documentmenu.menuname')"></span>
          </dt>
          <dd>
            <span>{{ documentmenu.menuname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.documentmenu.parentmenuid')"></span>
          </dt>
          <dd>
            <span>{{ documentmenu.parentmenuid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.documentmenu.createtime')"></span>
          </dt>
          <dd>
            <span>{{ documentmenu.createtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.documentmenu.creatorid')"></span>
          </dt>
          <dd>
            <span>{{ documentmenu.creatorid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.documentmenu.creatorname')"></span>
          </dt>
          <dd>
            <span>{{ documentmenu.creatorname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.documentmenu.type')"></span>
          </dt>
          <dd>
            <span>{{ documentmenu.type }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.documentmenu.filenum')"></span>
          </dt>
          <dd>
            <span>{{ documentmenu.filenum }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.documentmenu.departmentid')"></span>
          </dt>
          <dd>
            <span>{{ documentmenu.departmentid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.documentmenu.departmentname')"></span>
          </dt>
          <dd>
            <span>{{ documentmenu.departmentname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.documentmenu.spare1')"></span>
          </dt>
          <dd>
            <span>{{ documentmenu.spare1 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.documentmenu.spare2')"></span>
          </dt>
          <dd>
            <span>{{ documentmenu.spare2 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.documentmenu.spare3')"></span>
          </dt>
          <dd>
            <span>{{ documentmenu.spare3 }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="documentmenu.id"
          :to="{ name: 'DocumentmenuEdit', params: { documentmenuId: documentmenu.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./documentmenu-details.component.ts"></script>
