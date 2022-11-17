package net.safety.alert;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class Mapper extends ObjectMapper {
	private static final long serialVersionUID = 10L;

	public Mapper() {
		this.registerModule(new JavaTimeModule());
	}
}
