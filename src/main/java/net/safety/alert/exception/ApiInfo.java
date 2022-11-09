package net.safety.alert.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiInfo {
	private String url;
	private String errorMessage;
	private String operation;
	private Object payload;

	public ApiInfo(WebRequest request, SafetyNetException exception) {
		this.url = uriFromWebRequest(request);
		this.operation = exception.getOperation();
		this.payload = exception.getPayload();
		this.errorMessage = exception.getErrorMessage();
	}

	public ApiInfo(WebRequest request, Exception exception) {
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

	public String uriFromWebRequest(WebRequest request) {
		return ((ServletWebRequest) request).getRequest().getRequestURI().toString();
	}

}
