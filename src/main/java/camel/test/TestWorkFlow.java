package camel.test;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;

public class TestWorkFlow {

	public static void main(String[] args) throws Throwable {
		test(null);
	}
	
    public static void test(String [] args) throws Throwable {
    	
    	SimpleRegistry sr = new SimpleRegistry();
    	
        //CamelContext context = new DefaultCamelContext();
        CamelContext context = new DefaultCamelContext(sr);
        context.addRoutes(createRoute());
        context.start();
        
        for (int i = 0; i < 1; i++) {
        //Object p1 = new Object();
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
                .process(new DispatchProcessor());
             
                 from("direct:error")
                .process(new ErrorHandingProcessor());
            	
                from("direct:start")
               .process(new StartWorkFlowProcessor())
                .to("direct:routing");
                 ;
                 
                from("direct:end")
               .process(new EndWorkFlowProcessor())
                //.to("direct:routing");
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
                 from("vm:step3")
                .process(new Step3Processor())
                .to("direct:routing");
                */
                 
                 from("direct:step4")
                .process(new Step4Processor())
                .to("direct:routing");
                 
          }
        };
    }

}
