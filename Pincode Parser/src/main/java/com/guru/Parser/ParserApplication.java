package com.guru.Parser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ParserApplication {

	public static void main(String[] args) {

		SQLInsertGeneratorFromJSON.begin();

		SpringApplication.run(ParserApplication.class, args);
	}

}
