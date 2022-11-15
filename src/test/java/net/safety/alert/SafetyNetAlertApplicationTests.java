package net.safety.alert;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SafetyNetAlertApplicationTests {

	@Test
	void contextLoads() {
	}

	@DisplayName("Standard : ")
	@ParameterizedTest(name = "Fare for client {0}, Type vehicle {1}")
	@MethodSource("parametersProvider")
	public void test(Integer param1, Integer param2) {

		// THEN
		assertThat(param2 - param1).isEqualTo(1);
	}

	public static Stream<Arguments> parametersProvider() {
		// GIVEN
		return Stream.of(Arguments.arguments(1, 2), Arguments.arguments(3, 4));
	}
}
