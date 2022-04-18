package com.nam_nguyen_03.gira.common.exception;

import com.nam_nguyen_03.gira.common.util.ResponseHelper;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHelper {

	@ExceptionHandler(UnauthorizedException.class)
	public Object handlerUnauthorizedException (UnauthorizedException ex) {
		return ResponseHelper.getResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED, true);
	}

	@ExceptionHandler(BusinessException.class)
	public Object handlerBusinessException (BusinessException ex) {
		return ResponseHelper.getResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, true);
	}

}
