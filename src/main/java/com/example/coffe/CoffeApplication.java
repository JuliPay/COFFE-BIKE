package com.example.coffe;

import com.example.coffe.Servicios.InventarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CoffeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoffeApplication.class, args);
	}



}
