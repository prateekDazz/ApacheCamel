//package com.spring.camel.apacheCamel.components;
//
//import org.apache.camel.LoggingLevel;
//import org.apache.camel.builder.RouteBuilder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class SimpleTimer extends RouteBuilder {
//
//	@Override
//	public void configure() throws Exception {
//
////		from("timer:simpleTimer?period=2000").log(LoggingLevel.INFO, "Hello World");
//		from("timer:simpleTimer?period=2000").
//		routeId("simpleTimerId").
//		setBody(constant("Hello World")).
//		log(LoggingLevel.INFO, "${body}");
//	}
//
//}
