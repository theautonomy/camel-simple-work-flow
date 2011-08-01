package com.test.camel.workflow.example;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.test.camel.workflow.BaseProcessor;

public class Step3Processor extends BaseProcessor {

	public void process(Exchange e) throws Exception {
		System.out.println("enter step 3");
		Object p = (Object)e.getIn().getBody();
		System.out.println(p);
		setCurrentStepAndResult(e, "step3", "success");
		System.out.println("exit step 3");
	}

}
