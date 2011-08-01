package camel.test;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class EndWorkFlowProcessor implements Processor {

	@Override
	public void process(Exchange e) throws Exception {
		System.out.println("we are done!!!!!!!!!!!!!!!");
	}

}
