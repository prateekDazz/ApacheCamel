package com.spring.camel.apacheCamel.components;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.beanio.BeanIODataFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LegacyFileRoute extends RouteBuilder {

    private static Logger logger = LoggerFactory.getLogger(LegacyFileRoute.class);

    @Override
    public void configure() throws Exception {
        // Define BeanIO data format
        BeanIODataFormat beanIoDataFormat = new BeanIODataFormat();
        beanIoDataFormat.setMapping("classpath:InboundMessageBeanIOMapping.xml");
        beanIoDataFormat.setStreamName("inboundMessageStream");

        from("file:data/input?fileName=inputfile.csv&noop=true")
            .routeId("legacyFileRoute")

            // Step 1: Unmarshal CSV -> Java objects
            .unmarshal(beanIoDataFormat)

            // Step 2: Process records (optional)
            .process(exchange -> {
                Object body = exchange.getIn().getBody();
                logger.info("Parsed records: {}", body);
            })

            // Step 3: Marshal Java objects -> CSV again
            .marshal(beanIoDataFormat)

            // Step 4: Write back to file
            .to("file:data/output?fileName=outputfile.csv&fileExist=append");
    }
}
