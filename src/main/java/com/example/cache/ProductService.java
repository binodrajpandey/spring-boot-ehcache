package com.example.cache;

import java.util.List;

public interface ProductService {
	 Product getByName(String name);
	void refreshAllProducts();
	Product updateProduct(Product product);
	void removeFromCache(String name);
}
