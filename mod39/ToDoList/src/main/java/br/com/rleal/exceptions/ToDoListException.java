package br.com.rleal.exceptions;

public class ToDoListException extends RuntimeException {
    
	private static final long serialVersionUID = -4604607967383152464L;

	public ToDoListException(String message) {
        super(message);
    }
}