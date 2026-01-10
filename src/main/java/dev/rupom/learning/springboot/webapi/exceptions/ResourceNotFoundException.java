package dev.rupom.learning.springboot.webapi.exceptions;

//import lombok.AllArgsConstructor;

//@AllArgsConstructor -ALT-
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }
}
