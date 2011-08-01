package com.test.camel.workflow;

import java.util.Properties;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

import com.test.camel.workflow.util.PropertyLoader;


public class DispatchProcessor implements Processor {
	
	private static Properties workflowProperties;
	
	static {
		//workflowProperties = PropertyLoader.loadProperties("workflow.properties", null); 
		workflowProperties = PropertyLoader.loadProperties("workflow.properties");
	}
	
	private String getNextStep (Exchange e, String result) {
		WorkFlowContext wkc = (WorkFlowContext)e.getIn().getHeader("context");
		String currentStep = wkc.getCurrentStep();
		return workflowProperties.getProperty((currentStep + "+" + result).toLowerCase());
	}
	
	public void process(Exchange e) throws Exception {
		CamelContext context = e.getContext();
		Message m = e.getIn(); 
		Object p = new Object();
		p = (Object)m.getBody();
		System.out.println("in dispatcher name=" + p);
		String result = "success";
		String nextStep = getNextStep(e, result);
		context.createProducerTemplate().send("direct:"+nextStep, e);
	}

}
