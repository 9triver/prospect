<template>
  <div class="project-content-wrapper">
    <div class="project-content-top">
      <div class="project-content-top-left">
        <IconDisplay :title="roleViem.title" :list="roleViem.list" />
      </div>
      <div class="project-content-top-right">
        <IconDisplay :title="userCustom.title" :list="userCustom.list" />
      </div>
    </div>
    <div class="project-content-bottom">
      <div class="project-content-bottom-item">
        <HorizonBar />
      </div>
      <div class="project-content-bottom-item">
        <Bar />
      </div>
      <div class="project-content-bottom-item">
        <div class="project-content-bottom-item-top">
          <span class="title">本年度采购合同</span>
          <div style="margin: 20px 0px">
            <span>已签笔数</span>
            <span class="value">27</span>
            <span>笔</span>
          </div>
          <div style="margin: 20px 0px">
            <span>已签金额</span>
            <span class="value">￥2,072.53</span>
            <span>万元</span>
          </div>
        </div>
        <div class="project-content-bottom-item-bottom">
          <span class="title">项目文档</span>
          <div style="margin: 20px 0px">
            <span>已上传</span>
            <span class="value">16</span>
            <span>份</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import IconDisplay from "./components/iconDisplay/index.vue";
import HorizonBar from "./components/horizonBar/index.vue";
import Bar from "./components/Bar/index.vue";
export default {
  name: "ProjectContent",
  data() {
    return {
      roleViem: {
        title: "角色所需分析看板",
        list: [
          {
            icon: "PieChart",
            title: "驱动器整体看板",
            color: "#dd4b39",
          },
          {
            icon: "Male",
            title: "子项看板",
            color: "#dd4b39",
          },
          {
            icon: "TrendCharts",
            title: "外协合同看板",
            color: "#dd4b39",
          },
          {
            icon: "Orange",
            title: "综合管控",
            color: "#dd4b39",
          },
          {
            icon: "Document",
            title: "综合台账",
            color: "#dd4b39",
          },
        ],
      },
      userCustom: {
        title: "用户自定义收藏功能",
        list: [
          {
            icon: "Avatar",
            title: "保密制度管理",
            color: "#0070c0",
          },
          {
            icon: "HelpFilled",
            title: "计划检测",
            color: "#00ffcc",
          },
          {
            icon: "StarFilled",
            title: "计划回报",
            color: "#00ffcc",
          },
          {
            icon: "Comment",
            title: "外协采购实施",
            color: "#92d050",
          },
          {
            icon: "Histogram",
            title: "合同进度管理",
            color: "#92d050",
          },
          {
            icon: "Grid",
            title: "不良供应商配置",
            color: "#0070c0 ",
          },
          {
            icon: "Promotion",
            title: "进度计划执行监测",
            color: "#c79efd",
          },
          {
            icon: "DeleteFilled",
            title: "进度计划启动",
            color: "#c79efd",
          },
          {
            icon: "Ticket",
            title: "进度计划回报",
            color: "#c79efd",
          },
          {
            icon: "List",
            title: "进度确认回报",
            color: "#c79efd",
          },
          {
            icon: "Platform",
            title: "合同经费信息补充",
            color: "#0070c0",
          },
          {
            icon: "VideoCameraFilled",
            title: "合同计提费用登记",
            color: "#0070c0",
          },
          {
            icon: "GoodsFilled",
            title: "外协合同付款",
            color: "#0070c0",
          },
        ],
      },
    };
  },
  props: {},
  methods: {},
  components: {
    IconDisplay,
    HorizonBar,
    Bar,
  },
};
</script>
<style>
.project-content-wrapper {
  height: 100%;
  display: flex;
  flex-direction: column;

  .project-content-top {
    flex: 1;
    background: #fff;
    margin-bottom: 10px;
    display: flex;

    .project-content-top-left {
      flex: 1;
      position: relative;

      &::after {
        content: "";
        height: 100px;
        width: 1px;
        background: #000;
        display: block;
        position: absolute;
        right: 0;
        top: 50%;
        transform: translate(-50%, -50%);
      }
    }

    .project-content-top-right {
      flex: 3.5;
    }
  }

  .project-content-bottom {
    flex: 2;
    display: flex;
    .project-content-bottom-item {
      flex: 1;
      background: #fff;
      &:nth-child(2) {
        flex: 3;
        margin: 0px 10px;
      }
      &:nth-child(3) {
        background: unset;
        display: flex;
        flex-direction: column;
        .value {
          font-size: 20px;
          color: #f3a11e;
          font-weight: bold;
        }
        .project-content-bottom-item-top {
          flex: 2;
          margin-bottom: 10px;
          background: #fff;
          padding: 10px;
        }
        .project-content-bottom-item-bottom {
          flex: 1;
          background: #fff;
          padding: 10px;
        }
      }
    }
  }
}
</style>
