package sk.upjs.paz1c.project.FlightBookerrest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sk.upjs.paz1c.project.storage.EntityNotFoundException;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleEntityNotFoundException(EntityNotFoundException e) {
        return new ApiError(e.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleEntityNullPointerException(NullPointerException e) {
        return new ApiError(e.getMessage());
    }
}
