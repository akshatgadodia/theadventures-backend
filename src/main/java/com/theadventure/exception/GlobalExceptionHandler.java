package com.theadventure.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorDetails> handleAccessDeniedException(AccessDeniedException exception, WebRequest webRequest){
		ErrorDetails  errorDetails = new ErrorDetails(new Date(), exception.getMessage(),webRequest.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
		ErrorDetails  errorDetails = new ErrorDetails(new Date(), exception.getMessage(),webRequest.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<ErrorDetails> handleUserAlreadyExistsException(UserAlreadyExistsException exception, WebRequest webRequest){
		ErrorDetails  errorDetails = new ErrorDetails(new Date(), exception.getMessage(),webRequest.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, WebRequest webRequest){
		Map<String,String> errors = new HashMap<>();
		exception.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName = ((FieldError)error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName,message);
		});
		return new ResponseEntity<Object>(errors,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ErrorDetails> handleBadCredentialsException(BadCredentialsException exception, WebRequest webRequest){
		ErrorDetails  errorDetails = new ErrorDetails(new Date(), exception.getMessage(),webRequest.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleException(ResourceNotFoundException exception, WebRequest webRequest){
		ErrorDetails  errorDetails = new ErrorDetails(new Date(), exception.getMessage(),webRequest.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
