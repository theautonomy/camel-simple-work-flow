package camel.test;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public abstract class BaseProcessor implements Processor {

	public abstract void process(Exchange e) throws Exception;
	
	public void setCurrentStepAndResult(Exchange e, String step, String result) throws Exception {
		WorkFlowContext wkc = (WorkFlowContext)e.getIn().getHeader("context");
		if (wkc == null) {
			wkc = new WorkFlowContext();
		}
		wkc.setCurrentStep(step);
		wkc.setResult(result);
		e.getOut().setHeader("context", wkc); 
	}
}
