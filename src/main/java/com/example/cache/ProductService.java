package com.example.cache;

public interface ProductService {
	 Product getByName(String name);
	void refreshAllProducts();
}
