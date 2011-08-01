package com.test.camel.workflow;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

public class StartWorkFlowProcessor extends BaseProcessor {

	@Override
	public void process(Exchange e) throws Exception {
		Message m = e.getIn(); 
		Object p = new Object();
		p = (Object)m.getBody();
		System.out.println("in start work processor =" + p);
		setCurrentStepAndResult(e, "start", "success");
	}

}
