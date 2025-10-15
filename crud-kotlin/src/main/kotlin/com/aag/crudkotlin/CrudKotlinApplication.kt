package com.aag.crudkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients(basePackages = ["com.aag.crudkotlin.infrastructure.client"])
class CrudKotlinApplication

fun main(args: Array<String>) {
	runApplication<CrudKotlinApplication>(*args)
}
