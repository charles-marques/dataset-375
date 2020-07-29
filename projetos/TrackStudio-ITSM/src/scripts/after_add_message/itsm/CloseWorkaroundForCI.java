package scripts.after_add_message.itsm;

import java.util.List;
import java.util.Map.Entry;

import scripts.itsm.CommonITSM;

import com.trackstudio.app.adapter.AdapterManager;
import com.trackstudio.exception.GranException;
import com.trackstudio.external.OperationTrigger;
import com.trackstudio.secured.SecuredMessageTriggerBean;
import com.trackstudio.secured.SecuredTaskBean;
import com.trackstudio.secured.SecuredUDFBean;
import com.trackstudio.secured.SecuredUDFValueBean;
import com.trackstudio.tools.EggBasket;



public class CloseWorkaroundForCI extends CommonITSM implements OperationTrigger {


    public SecuredMessageTriggerBean execute(SecuredMessageTriggerBean message) throws GranException {
        String text = message.getDescription();
        SecuredTaskBean task = message.getTask();
        EggBasket<SecuredUDFValueBean, SecuredTaskBean> refs = AdapterManager.getInstance().getSecuredIndexAdapterManager().getReferencedTasksForTask(task);
        SecuredUDFBean relatedUdf = AdapterManager.getInstance().getSecuredFindAdapterManager().findUDFById(task.getSecure(), WORKAROUND_PRODUCT_UDFID);
        if (refs!=null )
        {
            for (Entry<SecuredUDFValueBean, List<SecuredTaskBean>> entry : refs.entrySet()){
                if (entry.getKey().getUdfId().equals(relatedUdf.getId())){
            List<SecuredTaskBean> incidentsInvolved = entry.getValue();
                if (incidentsInvolved != null) {
                    for (SecuredTaskBean p : incidentsInvolved) {
                                    executeOperation(WORKAROUND_CLOSE_OPERATION, p, text, message.getUdfValues());
                            }
                }
                    }
                }
        }



        return message;
    }

}


