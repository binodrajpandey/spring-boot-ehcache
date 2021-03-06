package com.example.cache;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

@SpringBootTest(classes = { SpringBootCacheApplication.class })
@RunWith(SpringRunner.class)
public class ProductServiceImplTest {
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImplTest.class);
	@Autowired
	private ProductService service;

	/**
	 * Clear Cache before you run every test
	 */
	@Before
	public void setUp() {
		service.refreshAllProducts();
	}

	/**
	 * Samsung is cached so the reference should be equal
	 */
	@Test
	public void whenCachedShouldReturnEqualReference() {
		int size = CacheManager.ALL_CACHE_MANAGERS.get(0)
				  .getCache("products").getSize();
				assertTrue(size==0);
		Product product1 = service.getByName("Samsung");
		Product product2 = service.getByName("Samsung");
		assertEquals(product1, product2);
		 size = CacheManager.ALL_CACHE_MANAGERS.get(0)
				  .getCache("products").getSize();
				assertTrue(size>0);
	}

	/**
	 * we have not cached for HTC so the referencee shouldn't be equal
	 */
	@Test
	public void whenNotCachedShouldReturnDifferentReference() {
		Product product1 = service.getByName("HTC");
		Product product2 = service.getByName("HTC");
		assertNotEquals(product1, product2);
	}

	/**
	 * 
	 */
	@Test
	public void whenCacheUpdateReturnEqualReference() {
		Product product = new Product("IPhone", 550);
		service.updateProduct(product);
		Element cachedProduct = CacheManager.ALL_CACHE_MANAGERS.get(0)
				  .getCache("products").get("IPhone");
				assertNotNull(cachedProduct.getValue());
				
	}

	@Test
	public void whenCacheEvictedForGivenKeyReferenceShouldNotBeEqualForSuccessiveCall() {

		Product product1 = service.getByName("Samsung");
		Product product2 = service.getByName("Samsung");
		assertEquals(product1, product2);
		Product product = new Product("Samsung", 550);
		service.removeFromCache(product.getName());
		Product product3 = service.getByName("Samsung");
		assertNotEquals(product1, product3);
	}

}
