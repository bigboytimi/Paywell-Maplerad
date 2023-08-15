package com.paywell.demomaplerad.exceptions;

import com.paywell.demomaplerad.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(value = EmailExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserExistsException(EmailExistsException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setErrorCode(HttpStatus.BAD_REQUEST.value());
        response.setTimestamp(LocalDate.now());
        response.setResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = APIConnectionException.class)
    public ResponseEntity<ErrorResponse> handleApiConnectionException(APIConnectionException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setErrorCode(HttpStatus.BAD_REQUEST.value());
        response.setTimestamp(LocalDate.now());
        response.setResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = CardAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleCardExistsException(CardAlreadyExistsException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setErrorCode(HttpStatus.BAD_REQUEST.value());
        response.setTimestamp(LocalDate.now());
        response.setResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = CardNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCardNotFoundException(CardNotFoundException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setErrorCode(HttpStatus.BAD_REQUEST.value());
        response.setTimestamp(LocalDate.now());
        response.setResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = CardRequestFailedException.class)
    public ResponseEntity<ErrorResponse> handleCardRequestFailedException(CardRequestFailedException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setErrorCode(HttpStatus.BAD_REQUEST.value());
        response.setTimestamp(LocalDate.now());
        response.setResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = DisabledCardException.class)
    public ResponseEntity<ErrorResponse> handleDisabledCardException(DisabledCardException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setErrorCode(HttpStatus.BAD_REQUEST.value());
        response.setTimestamp(LocalDate.now());
        response.setResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = EventExistsException.class)
    public ResponseEntity<ErrorResponse> handleEventsExistsException(EventExistsException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setErrorCode(HttpStatus.BAD_REQUEST.value());
        response.setTimestamp(LocalDate.now());
        response.setResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = InvalidWebhookException.class)
    public ResponseEntity<ErrorResponse> handleInvalidWebhookException(InvalidWebhookException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setErrorCode(HttpStatus.BAD_REQUEST.value());
        response.setTimestamp(LocalDate.now());
        response.setResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = JwtExpiredTokenException.class)
    public ResponseEntity<ErrorResponse> handleJwtExpiredTokenException(JwtExpiredTokenException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setErrorCode(HttpStatus.BAD_REQUEST.value());
        response.setTimestamp(LocalDate.now());
        response.setResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = NoWalletFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoWalletFoundException(NoWalletFoundException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setErrorCode(HttpStatus.BAD_REQUEST.value());
        response.setTimestamp(LocalDate.now());
        response.setResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RoleNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRoleNotFoundException(RoleNotFoundException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setErrorCode(HttpStatus.BAD_REQUEST.value());
        response.setTimestamp(LocalDate.now());
        response.setResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setErrorCode(HttpStatus.BAD_REQUEST.value());
        response.setTimestamp(LocalDate.now());
        response.setResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
