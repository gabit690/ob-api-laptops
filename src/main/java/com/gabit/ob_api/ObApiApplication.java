package com.gabit.ob_api;

import com.gabit.ob_api.entities.LaptopEntity;
import com.gabit.ob_api.repositories.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ObApiApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(ObApiApplication.class, args);

		LaptopRepository repo = context.getBean(LaptopRepository.class);

		LaptopEntity laptop1 = new LaptopEntity(null, "Intel i5", 50000.00);

		LaptopEntity laptop2 = new LaptopEntity(null, "AMD Razen5", 45700.00);

		repo.save(laptop1);

		repo.save(laptop2);

		System.out.println("Laptops stock: " + repo.findAll().size());
	}

}
