package net.safety.alert.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author trimok
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
	/**
	 * 
	 */
	private String url;
	/**
	 * 
	 */
	private String errorMessage;
	/**
	 * 
	 */
	private String operation;
	/**
	 * 
	 */
	private Object payload;

	/**
	 * @param apiError
	 *            : the error to be compared
	 * @return : indicates if the error are equals (modulo the payload)
	 */
	public boolean equalsMetadata(ApiError apiError) {
		boolean ret = true;

		if (url == null) {
			ret = ret & (apiError.getUrl() == null);
		} else {
			ret = ret & url.equals(apiError.getUrl());
		}

		if (errorMessage == null) {
			ret = ret & (apiError.getErrorMessage() == null);
		} else {
			ret = ret & errorMessage.equals(apiError.getErrorMessage());
		}

		if (operation == null) {
			ret = ret & (apiError.getOperation() == null);
		} else {
			ret = ret & operation.equals(apiError.getOperation());
		}

		return ret;
	}

	/**
	 * @param request
	 *            : the WebRequest Object
	 * @param exception
	 *            : the SafetyNetException exception
	 */
	public ApiError(WebRequest request, SafetyNetException exception) {
		this.url = uriFromWebRequest(request);
		this.operation = exception.getOperation();
		this.payload = exception.getPayload();
		this.errorMessage = exception.getErrorMessage();
	}

	/**
	 * @param request
	 *            : the WebRequest Object
	 * @param exception
	 *            : the Exception object
	 */
	public ApiError(WebRequest request, Exception exception) {
		this.url = uriFromWebRequest(request);
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		exception.printStackTrace(pw);
		this.errorMessage = sw.toString();
		pw.close();
		try {
			sw.close();
		} catch (Exception e) {

		}
	}

	/**
	 * @param request
	 *            : the WebRequest
	 * @return : the request url
	 */
	public String uriFromWebRequest(WebRequest request) {
		return ((ServletWebRequest) request).getRequest().getRequestURI().toString();
	}

}
