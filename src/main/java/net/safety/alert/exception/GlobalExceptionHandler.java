package net.safety.alert.exception;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * @author trimok
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * @param exception
	 *            : the exception which is raised by the program
	 * @param request
	 *            : the WebRequest object
	 * @return a ResponseEntity (ApiError) object
	 */
	@Order(-1)
	@ExceptionHandler({PersonNotFoundException.class, MedicalRecordNotFoundException.class,
			AddressStationNotFoundException.class, PersonAlreadyCreatedException.class,
			MedicalRecordAlreadyCreatedException.class, AddressStationAlreadyCreatedException.class,
			PersonNotValidException.class, MedicalRecordNotValidException.class, AddressStationNotValidException.class})

	public ResponseEntity<ApiError> handlePersonNotFound(SafetyNetException exception, WebRequest request) {
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

		return new ResponseEntity<ApiError>(new ApiError(request, exception), status);
	}

	/**
	 * @param exception
	 *            : the exception which is raised by the program
	 * @param request
	 *            : the WebRequest object
	 * @return a ResponseEntity (ApiError) object
	 */
	@Order(0)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> handlePersonNotFound(Exception exception, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;

		return new ResponseEntity<ApiError>(new ApiError(request, exception), status);
	}

}