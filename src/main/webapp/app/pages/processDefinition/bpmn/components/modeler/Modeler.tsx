import './modeler.css';
import 'bpmn-js/dist/assets/diagram-js.css';
import 'bpmn-js/dist/assets/bpmn-font/css/bpmn.css';
import 'bpmn-js/dist/assets/bpmn-font/css/bpmn-codes.css';
import 'bpmn-js/dist/assets/bpmn-font/css/bpmn-embedded.css';

import { defineComponent, onMounted, type PropType } from 'vue';
import createDefaultBpmnXml from '../../bpmn/defaultBpmnXml';
import activitiModdel from '../../bpmn/resources/activiti-moddel.json';
import translate from '../../bpmn/i18n';
import { BpmnStore } from '../../bpmn/store';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Modeler',
  props:{
    defaultProcessXml:{
      type: String as PropType<string|null>,
      // required:true
      default:null
    }
  },
  setup(props) {
    const bpmnContext = BpmnStore;
    onMounted(() => {
      bpmnContext.initModeler({
        container: '#modeler-container',
        additionalModules: [
          //添加翻译
          { translate: ['value', translate('zh')] },
        ],
        moddleExtensions: {
          activiti: activitiModdel,
        },
      });
      const defaultProcessIdAndName = '1';
      console.log("defaultProcessXml",props.defaultProcessXml)
      bpmnContext
        .importXML(createDefaultBpmnXml(props.defaultProcessXml))
        .then((result: Array<string>) => {
          if (result.length) {
            console.warn('importSuccess warnings', result);
          }
        })
        .catch((err: any) => {
          console.warn('importFail errors ', err);
        });
    });

    return () => <div id="modeler-container" />;
  },
});
