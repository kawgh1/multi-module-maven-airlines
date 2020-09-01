package com.airline.webservices.rest;

import javax.ws.rs.ApplicationPath;

// we make RestApplicationConfig a child class of java.ws.rs.core.Application by extending it below
// By extending it, our application is now aware of Java RESTful Web Services and can implement its features

// setting the application path means that when we go to localhost:xxxx/airlineservices/rest thats the entry point where JAXRS will be implemented
// the name is arbitrary we make it up
@ApplicationPath("/airlineservices/rest")
public class RestApplicationConfig extends javax.ws.rs.core.Application {
	
	
	

}
