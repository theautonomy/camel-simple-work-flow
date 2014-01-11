package org.wei.camel.workflow.example;

import java.util.Properties;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;
import org.wei.camel.workflow.DispatchProcessor;
import org.wei.camel.workflow.EndWorkFlowProcessor;
import org.wei.camel.workflow.ErrorHandingProcessor;
import org.wei.camel.workflow.StartWorkFlowProcessor;
import org.wei.camel.workflow.util.PropertyLoader;

public class TestWorkFlow {

	public static void main(String[] args) throws Throwable {

		Properties workflowProperties;
		workflowProperties = PropertyLoader.loadProperties("workflow.properties");

		SimpleRegistry sr = new SimpleRegistry();
		DispatchProcessor dispacther = new DispatchProcessor();
		dispacther.setWorkflowProperties(workflowProperties);
		sr.put("dispatcher", dispacther);

		CamelContext context = new DefaultCamelContext(sr);
		context.addRoutes(createRoute());
		context.start();

		for (int i = 0; i < 1; i++) {
			String p1 = "this is ok";
			ProducerTemplate template = context.createProducerTemplate();
			template.sendBody("direct:start", p1);
			Thread.sleep(1000);
		}

		Thread.sleep(15000);
		context.stop();
	}

	public static RouteBuilder createRoute() {
		return new RouteBuilder() {
			public void configure() throws Exception {

				from("direct:routing")
				.to("bean:dispatcher");

				from("direct:error")
				.process(new ErrorHandingProcessor());

				from("direct:start")
				.process(new StartWorkFlowProcessor())
				.to("direct:routing");

				from("direct:end")
				.process(new EndWorkFlowProcessor())
				// .to("direct:routing");
				;

				from("direct:step1")
				.process(new Step1Processor())
				.to("direct:routing");

				from("direct:step2")
				.process(new Step2Processor())
				.to("direct:routing");

				from("direct:step3")
				.process(new Step3Processor())
				.to("direct:routing");

				/*
				 * from("vm:step3") .process(new Step3Processor())
				 * .to("direct:routing");
				 */

				from("direct:step4")
				.process(new Step4Processor())
				.to("direct:routing");
			}
		};
	}

}
