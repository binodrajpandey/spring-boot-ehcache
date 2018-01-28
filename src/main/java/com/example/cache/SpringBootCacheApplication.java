package com.example.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
@SpringBootApplication
@EnableCaching
public class SpringBootCacheApplication {
	private static final Logger logger=LoggerFactory.getLogger(SpringBootCacheApplication.class);
	 @Bean
	    public CacheManager cacheManager() {
	        return new EhCacheCacheManager(ehCacheCacheManager().getObject());
	    }
	 
	    @Bean
	    public EhCacheManagerFactoryBean ehCacheCacheManager() {
	        EhCacheManagerFactoryBean factory = new EhCacheManagerFactoryBean();
	        logger.info("loading ehcache");
	        factory.setConfigLocation(new ClassPathResource("ehcache.xml"));
	        factory.setShared(true);
	        return factory;
	    }
	public static void main(String[] args) {
		SpringApplication.run(SpringBootCacheApplication.class, args);
	}
}
