package camel.test;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class StartWorkFlowProcessor extends BaseProcessor {

	@Override
	public void process(Exchange e) throws Exception {
		setCurrentStepAndResult(e, "start", "success");
	}

}
