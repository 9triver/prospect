import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { Check,  Delete,  Edit,  Message,  Search,  Star,} from '@element-plus/icons-vue';

import ContractService from './contract.service';
import { type IContract } from '@/shared/model/contract.model';
import { useAlertService } from '@/shared/alert/alert.service';
import type { FormInstance } from 'element-plus'

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Contract',
  setup() {
    const { t: t$ } = useI18n();
    const contractService = inject('contractService', () => new ContractService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const contracts: Ref<IContract[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveContracts = async () => {
      isFetching.value = true;
      try {
        const res = await contractService().retrieve();
        contracts.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    // 清空字段
    const queryFormRef = ref<FormInstance>()

    const handleSyncList = (formRef:FormInstance|undefined) => {
      // 清空字段
      formRef?.resetFields();
      retrieveContracts();
    };

    onMounted(async () => {
      await retrieveContracts();
    });

    //删除
    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IContract) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeContract = async () => {
      try {
        await contractService().delete(removeId.value);
        const message = t$('jy1App.contract.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveContracts();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    //条件查询
    const form = ref({
      id: '',
      contractcode: '',
      contractname: '',
      projectwbsname:'',
      contracttype:'',
      year:'',
      amount:'',
      starttime:'',
      endtime:'',
      fileurl:'',
      secretlevel:'',
      status:'',
      remark:'',
      budgetamount:'',
      estimatedamount:'',
      implementedamount:'',
      difference:''
    })   
    const onSubmit = async () => {
      isFetching.value = true;
      try {
        //整数调整
        alert("sss");
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    return {
      contracts,
      handleSyncList,
      isFetching,
      retrieveContracts,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeContract,
      t$,
      form,
      onSubmit,
      Edit,
      queryFormRef
    };
  },
});
