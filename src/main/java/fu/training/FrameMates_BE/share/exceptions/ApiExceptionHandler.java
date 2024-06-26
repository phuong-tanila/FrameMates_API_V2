package fu.training.FrameMates_BE.share.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.security.SignatureException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
//@Configuration
public class ApiExceptionHandler {
    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleRecordNotFoundException(
            RecordNotFoundException ex, WebRequest request
    ){
        return new ResponseEntity<ExceptionResponse>(
                new ExceptionResponse(
                    "Record not found",
                        ex.getMessage()
                ),
                HttpStatus.NOT_FOUND
        );
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(
            IllegalArgumentException ex, WebRequest request
    ){
        ex.printStackTrace();
        return new ResponseEntity<>(
                new ExceptionResponse(
                    "Illegal argument",
                        ex.getMessage()
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(DupplicatedUserInfoException.class)
    public ResponseEntity<ExceptionResponse> handleDuppicatedUserInfoExceptionException(
            DupplicatedUserInfoException ex, WebRequest request
    ){
//        List<String>  messages = new ObjectMapper().convertValue(ex.getMessage(), ArrayList.class);
        return new ResponseEntity<ExceptionResponse>(
                new ExceptionResponse(
                        "Dupplicated user info exception",
                        ex.getMessages()
                ),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, WebRequest request
    ){
        List<String> messages = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(x -> x.getField() + ": " + x.getDefaultMessage())
                .collect(Collectors.toList());

        return new ResponseEntity<ExceptionResponse>(
                new ExceptionResponse(
                        "Invalid argument",
                        messages.toArray(new String[0])
                ),
                HttpStatus.BAD_REQUEST
        );
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ExceptionResponse> handleException(
//            Exception ex, WebRequest request
//    ){
//
//        return new ResponseEntity<ExceptionResponse>(
//                new ExceptionResponse(
//                        "Exception",
//                    ex.getMessage()
//                ),
//                HttpStatus.BAD_REQUEST
//        );
//    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleNullPointerException(
            NullPointerException ex, WebRequest request
    ){
        if(ex.getMessage().contains("\"authentication\" is null"))
            return new ExceptionResponse(
                    "Missing bearer token",
                    "Bearer token is required"
            );
        ex.printStackTrace();
        return new ExceptionResponse(
                "Null pointer exception",
                "Null pointer exception, check console for more information"
        );
    }


    @ExceptionHandler(InvalidStatusStringException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity handleInvalidEnumString(
            InvalidStatusStringException ex
    ){
        return new ResponseEntity(new ExceptionResponse("Invalid status", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity  handleBadCredentialsException(
            BadCredentialsException ex
    ){
        return  new ResponseEntity(new ExceptionResponse("Invalid credentials exception", ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MissingBearerTokenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleMissingBearerTokenException(
            MissingBearerTokenException ex, WebRequest request
    ){
        return new ExceptionResponse(
                "Bad request",
                ex.getMessage()
        );
    }
    @ExceptionHandler(IllegalAccessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleIllegalAccessException(
            IllegalAccessException ex
    ){
        return new ExceptionResponse("Illegal access", ex.getMessage());
    }

    @ExceptionHandler(InvalidOtpException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleInvalidOtpException(
            InvalidOtpException ex
    ){
        return new ExceptionResponse("Invalid otp", ex.getMessage());
    }

    @ExceptionHandler(DisabledException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionResponse handleDisabledException(
            DisabledException ex
    ){
        return new ExceptionResponse("Disabled account", "Account was banned");
    }

    @ExceptionHandler(SignatureException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionResponse handleSignatureException(
            SignatureException ex
    ){
        return new ExceptionResponse("Invalid jwt token", "Invalid jwt token");
    }
}
