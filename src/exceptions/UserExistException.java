/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exceptions;

public class UserExistException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserExistException(String errorString) {
        super(errorString);
    }
}
