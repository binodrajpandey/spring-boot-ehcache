package com.example.cache;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
@Service
public class ProductServiceImpl implements ProductService{
	private static final Logger logger=LoggerFactory.getLogger(ProductServiceImpl.class);
	private static List<Product> products;
    
    static{
        products = getProductList();
    }
     
	/**
	 * get all the product list
	 */
	@Cacheable(value="products",key="productList")
	public static List<Product> getProductList(){
		  logger.info("<!----------Entering to getting list method ------------------->");
		  List<Product> products = new ArrayList<Product>();
	        products.add(new Product("IPhone",500));
	        products.add(new Product("Samsung",600));
	        products.add(new Product("HTC",800));
	        return products;
	}
	
	/**
	 * this method always gets executed
	 *  result will stored in Cache unless the result is null
	 *  it will just update/refresh the item in the Cache.
	 * @param product
	 * @return
	 */
	@Override
	@CachePut(value = "products", key = "#product.name" , unless="#result==null")
	public Product updateProduct(Product product) {
	    logger.info("<!----------Entering updateProduct ------------------->");
	    for(Product p : products){
	        if(p.getName().equalsIgnoreCase(product.getName()))
	            p.setPrice(product.getPrice());
	            return p;
	    }
	    return null;
	}
	
	  @Override
	    @Cacheable(value="products", key="#name", condition="#name!='HTC'" , unless="#result==null")
	    public Product getByName(String name) {
	        logger.info("<!----------Entering getByName()--------------------->");
	        for(Product p : products){
	            if(p.getName().equalsIgnoreCase(name))
	                return p;
	        }
	        return null;
	    }
	 
 
	/**
  * Removes all entries from the Cache named products
  */
    @CacheEvict(value = "products", allEntries = true)
    public void refreshAllProducts() {
    }  
    
    /**
     * Remove the entry with key name from Cache named given name 
     * @param name
     */
    @Override
    @CacheEvict(value="products",key="#name")
    public void removeFromCache(String name) {
    	logger.info("product with key {} removed from the cache",name);
    }
 
   
}
