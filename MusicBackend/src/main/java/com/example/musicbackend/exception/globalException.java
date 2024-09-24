package com.example.musicbackend.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class globalException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> myMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> response = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(err -> {
            String fieldName = ((FieldError) err).getField();
            String message = err.getDefaultMessage();
            response.put(fieldName, message);
        });
        return new ResponseEntity<Map<String, String>>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<String> myResourceNotFoundException(ResourceNotFound e) {
        String message = e.getMessage();
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}


/* Notes::> for global exceptions
### Key Points for Learners:

1. **@RestControllerAdvice**:
   - A specialized version of `@ControllerAdvice` for REST APIs.
   - Intercepts exceptions globally in the application for centralized error handling.

2. **@ExceptionHandler**:
   - Specifies the exception type the method handles.
   - Here, it catches `MethodArgumentNotValidException`, which occurs during validation failures in request body or parameters.

3. **Handling `MethodArgumentNotValidException`**:
   - This exception is thrown when validation annotations (e.g., `@NotNull`, `@Size`, etc.) on method arguments fail.
   - The method retrieves field-specific validation errors using `FieldError` and the default error message.

4. **Building Error Response**:
   - The response is a `Map<String, String>` where:
     - **Key**: Field name causing the error.
     - **Value**: Corresponding validation error message.
   - This structured format provides a clear, user-friendly response for validation errors.

*/
