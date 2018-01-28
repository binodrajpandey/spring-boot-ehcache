package com.example.cache;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@SpringBootTest(classes={SpringBootCacheApplication.class})
@RunWith(SpringRunner.class)
public class ProductServiceImplTest {
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImplTest.class);
@Autowired
private ProductService service;
	@Test
	public void test() {
		 logger.info("IPhone ->" + service.getByName("IPhone"));
	        logger.info("IPhone ->" + service.getByName("IPhone"));
	        logger.info("IPhone ->" + service.getByName("IPhone"));
	        logger.info("Refreshing all products");
	        service.refreshAllProducts();
	        logger.info("IPhone [after refresh]->" + service.getByName("IPhone"));
	        logger.info("IPhone [after refresh]->" + service.getByName("IPhone"));
	        logger.info("IPhone [after refresh]->" + service.getByName("IPhone"));
	}

}
