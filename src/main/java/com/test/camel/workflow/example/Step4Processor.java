package com.test.camel.workflow.example;

import org.apache.camel.Exchange;

import com.test.camel.workflow.BaseProcessor;

public class Step4Processor extends BaseProcessor {

	public void process(Exchange e) throws Exception {
		System.out.println("enter step 4");
		Object p = (Object)e.getIn().getBody();
		System.out.println(p);
		setCurrentStepAndResult(e, "step4", "success");
		System.out.println("exit step 4");
	}

}
