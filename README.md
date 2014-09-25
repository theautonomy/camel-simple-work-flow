Introduction
=================

This project implements a simple work flow engine using the chain of responsibility pattern. The processors and their sequence 
in the chain is configurable through configuration file. If one processor succeeds, the next processor defined in the configuration
will kick in. Otherwise, the error handling processor takes over. 

See example [configuration file](src/test/resources/workflow.properties)

* Try it out 
  * Clone the project 
  * Run org.wei.camel.workflow.example.TestWorkFlow 

