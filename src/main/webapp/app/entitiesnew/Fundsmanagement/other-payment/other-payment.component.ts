import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { Check,  Delete,  Edit,  Message,  Search,  Star,} from '@element-plus/icons-vue';

import OtherPaymentService from './other-payment.service';
import { type IOtherPayment } from '@/shared/model/other-payment.model';
import { useAlertService } from '@/shared/alert/alert.service';
import type { FormInstance } from 'element-plus'

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'OtherPayment',
  setup() {
    const { t: t$ } = useI18n();
    const otherPaymentService = inject('otherPaymentService', () => new OtherPaymentService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const otherPayments: Ref<IOtherPayment[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveOtherPayments = async () => {
      isFetching.value = true;
      try {
        const res = await otherPaymentService().retrieve();
        otherPayments.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    // const handleSyncList = () => {
    //   retrieveOtherPayments();
    // };
    // 清空字段
    const queryFormRef = ref<FormInstance>()

    const handleSyncList = (formRef:FormInstance|undefined) => {
      // 清空字段
      formRef?.resetFields();
      retrieveOtherPayments();
    };

    onMounted(async () => {
      await retrieveOtherPayments();
    });

    //删除
    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IOtherPayment) => {
      removeId.value = instance.id;
      if (removeEntity.value) {
        removeEntity.value.show();
      }
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeOtherPayment = async () => {
      if (!removeId.value) {
        // 处理无效的删除 ID
        alertService.showError("无效的删除 ID");
        return;
      }
      try {
        await otherPaymentService().delete(removeId.value);
        const message = t$('jy1App.otherPayment.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        await retrieveOtherPayments();
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
      type:'',
      subjectid:'',
      subjectname:'',
      wbsid:'',
      wbsname:''
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
      otherPayments,
      handleSyncList,
      isFetching,
      retrieveOtherPayments,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeOtherPayment,
      t$,
      form,
      onSubmit,
      Edit,
      queryFormRef
    };
  },
});
