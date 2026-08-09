package com.example.LRUCache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LruCacheApplication {

	public static void main(String[] args) {

		SpringApplication.run(LruCacheApplication.class, args);

		LRUCache<Integer,String> cache = new LRUCache<>(5);
		cache.get(1);
		System.out.println(cache.toString());
		cache.put(1,"Manasi");
		System.out.println(cache.toString());
		cache.put(2, "Bittu");
		cache.put(3,"Tesco");
		cache.put(4,"UK");
		cache.get(1);
		System.out.println(cache.toString());
		cache.put(5,"Manasi");
		cache.put(6,"Tesco");
		System.out.println(cache.toString());

	}

}
