package org.wei.camel.workflow;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ErrorHandingProcessor implements Processor {

	public void process(Exchange arg0) throws Exception {
		System.out.println("something is wrong");

	}

}
