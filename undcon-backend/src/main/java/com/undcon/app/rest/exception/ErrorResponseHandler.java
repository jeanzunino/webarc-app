package com.undcon.app.rest.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.http.HttpStatus;

import com.undcon.app.enums.UndconError;
import com.undcon.app.exceptions.LoginException;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.rest.models.ErrorMessageModel;

@Provider
public class ErrorResponseHandler implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable error) {
		if (error instanceof IllegalArgumentException) {
			ErrorMessageModel bodyOfResponse = new ErrorMessageModel(UndconError.API_ERROR_INVALID_PARAMETERS,
					error.getMessage());
			return error(error, HttpStatus.BAD_REQUEST, bodyOfResponse);
		} else if (error instanceof LoginException) {
			ErrorMessageModel bodyOfResponse = new ErrorMessageModel(((UndconException) error).getError());
			return error(error, HttpStatus.UNAUTHORIZED, bodyOfResponse);
		} else if (error instanceof UndconException) {
			ErrorMessageModel bodyOfResponse = new ErrorMessageModel(((UndconException) error).getError());
			return error(error, HttpStatus.BAD_REQUEST, bodyOfResponse);
		}

		error.printStackTrace();
		ErrorMessageModel bodyOfResponse = new ErrorMessageModel(UndconError.GENERIC_ERROR, error.getMessage());
		return error(error, HttpStatus.INTERNAL_SERVER_ERROR, bodyOfResponse);
	}

	private Response error(final Throwable exception, final HttpStatus httpStatus,
			final ErrorMessageModel bodyOfResponse) {
		return Response.status(httpStatus.value()).entity(bodyOfResponse).build();
	}

}
