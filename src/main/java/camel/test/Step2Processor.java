package camel.test;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class Step2Processor extends BaseProcessor { 

	public void process(Exchange e) throws Exception {
		System.out.println("enter step 2");
		Object p = (Object)e.getIn().getBody();
		System.out.println(p);
		setCurrentStepAndResult(e, "step2", "success");
		System.out.println("exit step 2");
	}
}
