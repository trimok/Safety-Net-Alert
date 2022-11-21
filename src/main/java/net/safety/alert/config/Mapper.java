package net.safety.alert.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * @author trimok
 *
 *         The class define a ObjectMapper singleton used in all the application which may manage LocalDate in the
 *         serialization / deserialization Json operations.
 * 
 *         Need the plugin jackson-datatype-jsr310 in the pom.xml
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class Mapper extends ObjectMapper {
	/**
	 * 
	 */
	private static final long serialVersionUID = 10L;

	/**
	 * 
	 */
	public Mapper() {
		// Management for the LocalDate serialization / deserialization
		this.registerModule(new JavaTimeModule());
	}
}
