package com.guru.Parser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ParserApplication {

	public static void main(String[] args) {

        try {
            SQLInsertGeneratorFromJSON.begin();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SpringApplication.run(ParserApplication.class, args);
	}

}
