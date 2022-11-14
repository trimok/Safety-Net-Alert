package net.safety.alert.exception;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@Order(-1)
	@ExceptionHandler({PersonNotFoundException.class, MedicalRecordNotFoundException.class,
			AddressStationNotFoundException.class, PersonAlreadyCreatedException.class,
			MedicalRecordAlreadyCreatedException.class, AddressStationAlreadyCreatedException.class,
			PersonNotValidException.class, MedicalRecordNotValidException.class, AddressStationNotValidException.class})

	public ResponseEntity<ApiInfo> handlePersonNotFound(SafetyNetException exception, WebRequest request) {
		HttpStatus status = null;
		if (exception instanceof PersonNotFoundException || exception instanceof MedicalRecordNotFoundException
				|| exception instanceof AddressStationNotFoundException

		) {
			status = HttpStatus.NOT_FOUND;
		} else if (exception instanceof PersonAlreadyCreatedException
				|| exception instanceof MedicalRecordAlreadyCreatedException
				|| exception instanceof AddressStationAlreadyCreatedException) {
			status = HttpStatus.FOUND;
		} else if (exception instanceof PersonNotValidException || exception instanceof MedicalRecordNotValidException
				|| exception instanceof AddressStationNotValidException) {
			status = HttpStatus.BAD_REQUEST;
		}

		return new ResponseEntity<ApiInfo>(new ApiInfo(request, exception), status);
	}

	@Order(0)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiInfo> handlePersonNotFound(Exception exception, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;

		return new ResponseEntity<ApiInfo>(new ApiInfo(request, exception), status);
	}

}