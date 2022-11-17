package net.safety.alert.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiInfo {
	private String url;
	private String errorMessage;
	private String operation;
	private Object payload;

	public boolean equalsMetadata(ApiInfo apiInfo) {
		boolean ret = true;

		if (url == null) {
			ret = ret & (apiInfo.getUrl() == null);
		} else {
			ret = ret & url.equals(apiInfo.getUrl());
		}

		if (errorMessage == null) {
			ret = ret & (apiInfo.getErrorMessage() == null);
		} else {
			ret = ret & errorMessage.equals(apiInfo.getErrorMessage());
		}

		if (operation == null) {
			ret = ret & (apiInfo.getOperation() == null);
		} else {
			ret = ret & operation.equals(apiInfo.getOperation());
		}

		return ret;
	}

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
