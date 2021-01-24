package org.training.facades.product;

import de.hybris.platform.commercefacades.product.data.ProductData;

import java.util.List;

/**
 * The Class TrainingProductFacade
 *
 * @author Kris Sunu Purnandaru
 */
public interface TrainingProductFacade {

    List<ProductData> getAllProducts();
}
