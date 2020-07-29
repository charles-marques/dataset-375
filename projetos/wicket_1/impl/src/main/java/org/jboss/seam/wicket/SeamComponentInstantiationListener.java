package org.jboss.seam.wicket;

import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

import org.apache.wicket.Component;
import org.apache.wicket.application.IComponentInstantiationListener;
import org.jboss.seam.wicket.util.NonContextual;

/**
 * This listener uses the BeanManager to handle injections for all wicket components.
 *
 * @author cpopetz
 */
public class SeamComponentInstantiationListener implements IComponentInstantiationListener {

    @Inject
    private BeanManager manager;

    public void onInstantiation(Component component) {
        /*
        * The manager could be null in unit testing environments
        */
        if (manager != null) {
            NonContextual.of(component.getClass(), manager)
                    .existingInstance(component).inject();
        }
    }
}
