package org.training.core.interceptor;

import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import org.training.core.model.TrainingVariantProductModel;

/**
 * The Class ProductSKUInterceptor
 *
 * @author Kris Sunu Purnandaru
 */
public class ProductSKUInterceptor implements PrepareInterceptor<TrainingVariantProductModel> {

    @Override
    public void onPrepare(TrainingVariantProductModel trainingVariantProductModel, InterceptorContext interceptorContext) throws InterceptorException {
        trainingVariantProductModel.setSku(trainingVariantProductModel.getSku().toUpperCase());
    }
}
