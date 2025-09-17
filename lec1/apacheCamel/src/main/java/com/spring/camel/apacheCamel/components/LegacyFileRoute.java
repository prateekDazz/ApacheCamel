package com.spring.camel.apacheCamel.components;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Component
public class LegacyFileRoute extends RouteBuilder {
	private static Logger logger =  LoggerFactory.getLogger(LegacyFileRoute.class);

	@Override
	public void configure() throws Exception {

		from("file:data/input?fileName=inputfile.txt").routeId("legacyFileRoute").process(exchange->
		{
		String fileData =  exchange.getIn().getBody(String.class);
		logger.info("logging fileData {}",fileData);
		
		}
				).
		
		to("file:data/output?fileName=outputfile.txt");
	}

}
