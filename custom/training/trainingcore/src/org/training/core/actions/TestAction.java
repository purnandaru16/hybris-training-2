package org.training.core.actions;

import de.hybris.platform.commerceservices.model.process.TestActionProcessModel;
import de.hybris.platform.processengine.action.AbstractSimpleDecisionAction;
import org.apache.log4j.Logger;

/**
 * The Class TestAction
 *
 * @author Kris Sunu Purnandaru
 */
public class TestAction extends AbstractSimpleDecisionAction<TestActionProcessModel> {

    private static final Logger LOGGER = Logger.getLogger(TestAction.class);

    @Override
    public Transition executeAction(TestActionProcessModel testActionProcessModel) {
        //Do something here

        //If everything goes well return OK, else NOK
        if (Boolean.TRUE) {
            LOGGER.info("Test Action 1 OK!");
            return Transition.OK;
        }
        else return Transition.NOK;
    }
}
