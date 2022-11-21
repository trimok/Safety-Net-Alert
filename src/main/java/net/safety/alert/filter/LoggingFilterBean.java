package net.safety.alert.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author trimok
 *
 */
/**
 * 
 */
@Slf4j
@Component
public class LoggingFilterBean extends GenericFilterBean {

	/**
	 *
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		ContentCachingRequestWrapper requestWrapper = requestWrapper(request);
		ContentCachingResponseWrapper responseWrapper = responseWrapper(response);

		chain.doFilter(requestWrapper, responseWrapper);

		logRequest(requestWrapper);
		logResponse(responseWrapper);
	}

	/**
	 * @param request
	 *            : the ContentCachingRequestWrapper request
	 */
	private void logRequest(ContentCachingRequestWrapper request) {
		StringBuilder builder = new StringBuilder();
		builder.append(request.getMethod() + " ");
		builder.append(request.getRequestURI() + " ");
		builder.append(new String(request.getContentAsByteArray()));
		// Parameters for GET
		int sizeParameterMap = request.getParameterMap().size();
		if (sizeParameterMap > 0) {
			int iParameter = 0;
			builder.append("{");
			for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
				builder.append("\"" + entry.getKey() + "\"" + ":");
				for (String info : entry.getValue()) {
					builder.append("\"" + info + "\"");
				}
				if (iParameter < sizeParameterMap - 1) {
					builder.append(",");
				} else {
					builder.append("}");
				}

				++iParameter;
			}
		}
		log.info("Request: {}", builder);
	}

	/**
	 * @param response
	 *            : the ContentCachingResponseWrapper response
	 * @throws IOException
	 *             : a possible IOException
	 */
	private void logResponse(ContentCachingResponseWrapper response) throws IOException {
		StringBuilder builder = new StringBuilder();
		builder.append(new String(response.getContentAsByteArray()));

		if (builder.lastIndexOf("error") > -1) {
			log.error("Response: {}", builder);
		} else {
			log.info("Response: {}", builder);
		}
		response.copyBodyToResponse();
	}

	/**
	 * @param request
	 *            : a Servlet request
	 * @return : the ContentCachingRequestWrapper object
	 */
	private ContentCachingRequestWrapper requestWrapper(ServletRequest request) {
		if (request instanceof ContentCachingRequestWrapper requestWrapper) {
			return requestWrapper;
		}
		return new ContentCachingRequestWrapper((HttpServletRequest) request);
	}

	/**
	 * @param response
	 *            : a ServletResponse object
	 * @return : a ContentCachingResponseWrapper object
	 */
	private ContentCachingResponseWrapper responseWrapper(ServletResponse response) {
		if (response instanceof ContentCachingResponseWrapper responseWrapper) {
			return responseWrapper;
		}
		return new ContentCachingResponseWrapper((HttpServletResponse) response);
	}
}