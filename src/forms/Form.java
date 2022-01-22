package forms;

import java.util.HashMap;

import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class Form {
	protected boolean status;
	protected String statusMessage;
	protected HttpServletRequest req;
	protected Map<String, String> valids;
	protected Map<String, String> errors;
	protected static final String INVALID_INPUT = "is-invalid";
	protected static final String VALID_INPUT = "is-valid";
	
	public Form(HttpServletRequest req) {
		this.status = false;
		this.req = req;
		this.errors = new HashMap<String, String>();
		this.valids = new HashMap<String, String>(); 
	}
	
	static class EmailValidation {
	    public static boolean isValid(String email) {
	        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
	                + "A-Z]{2,7}$";
	        Pattern pat = Pattern.compile(emailRegex);
	        if (email == null)
	            return false;
	        return pat.matcher(email).matches();
	    }
	}

	
	public String getParameter(String param) {
		String value = req.getParameter(param);
		return (value != null && !value.trim().isEmpty()) ? value.trim() : null;
	}
        
	public Map<String, String> getErrors() {
		return errors;
	}
	public Map<String, String> getValids() {
		return valids;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public boolean getStatus() {
		return status;
	}
}
