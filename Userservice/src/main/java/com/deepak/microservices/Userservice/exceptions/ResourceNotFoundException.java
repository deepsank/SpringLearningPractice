package com.deepak.microservices.Userservice.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    //Any extra error properties that we want to add, we add as member variable
    public ResourceNotFoundException(){
        super("Resource not found on the server!!");
    }

    public ResourceNotFoundException(String message){
        super(message);
    }

}
