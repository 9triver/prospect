import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import DocumentmenuService from './documentmenu.service';
import { type IDocumentmenu } from '@/shared/model/documentmenu.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Documentmenu',
  setup() {
    const { t: t$ } = useI18n();
    const documentmenuService = inject('documentmenuService', () => new DocumentmenuService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const documentmenus: Ref<IDocumentmenu[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveDocumentmenus = async () => {
      isFetching.value = true;
      try {
        const res = await documentmenuService().retrieve();
        //转换扁平数据为树状结构，拼接树状结构
        const treeData = convertToTree(res.data);
        documentmenus.value = treeData;
        alert(JSON.stringify(documentmenus.value));
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };


    // 转换函数：将扁平数据转换为树状结构
    const convertToTree = (flatData: IDocumentmenu[]) => {
      // 使用一个Map来存储每个节点的引用
      const map = new Map<number, IDocumentmenu>();
      flatData.forEach(item => {
        map.set(item.menuid, { ...item, children: [] });
      });

      // 构建树形结构
      const tree: IDocumentmenu[] = [];
      flatData.forEach(item => {
        const node = map.get(item.menuid);
        if (item.parentmenuid && map.has(item.parentmenuid)) {
          const parent = map.get(item.parentmenuid);
          if (parent) {
            parent.children.push(node!);
          } else {
            tree.push(node!); // 如果没有找到父节点，将其作为顶层节点
          }
        } else {
          tree.push(node!); // 没有父节点的节点作为顶层节点
        }
      });

      return tree;
    };

    // 树节点属性映射关系
    const defaultProps = {
      children: 'children',
      label: 'menuname',
    }
   
    /**
     * 树组件点击事件句柄方法
     */
    const handleNodeClick = () => {
      console.log("zzz",data.fileType)
      props.setCurSelectData&&props.setCurSelectData(data)
    }


    const handleSyncList = () => {
      retrieveDocumentmenus();
    };

    onMounted(async () => {
      await retrieveDocumentmenus();
    });

    return {
      documentmenus,
      handleSyncList,
      isFetching,
      retrieveDocumentmenus,
      clear,
      t$,
      defaultProps,
      handleNodeClick,
    };
  },
});
