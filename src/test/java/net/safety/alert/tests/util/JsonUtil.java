package net.safety.alert.tests.util;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.function.Function;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	@SafeVarargs
	public static <T> T dtoFromUrl(ObjectMapper mapper, boolean httpGet,
			Function<String, MockHttpServletRequestBuilder> method, MockMvc mockMvc, String url, Class<T> classT,
			T... dtoObject) {
		T tDTO = null;
		try {

			MvcResult result = null;
			if (httpGet) {
				result = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
			} else {
				result = mockMvc
						.perform(method.apply(url).content(mapper.writeValueAsString(dtoObject[0]))
								.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk()).andReturn();
			}

			tDTO = mapper.readValue(result.getResponse().getContentAsString(),
					mapper.getTypeFactory().constructType(classT));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tDTO;
	}

	public static String dtoFromDeleteUrl(ObjectMapper mapper, MockMvc mockMvc, String url, Object dtoObject) {
		String result = null;
		try {

			MvcResult mvcResult = null;
			mvcResult = mockMvc
					.perform(delete(url).content(mapper.writeValueAsString(dtoObject))
							.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk()).andReturn();

			result = mvcResult.getResponse().getContentAsString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}
