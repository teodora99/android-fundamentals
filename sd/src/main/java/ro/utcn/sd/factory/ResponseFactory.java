package ro.utcn.sd.factory;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ro.utcn.sd.dto.ResponseDto;
import ro.utcn.sd.dto.ResponseLoginDto;

public abstract class ResponseFactory {

    public static ResponseEntity createWarningMessage(String message, HttpHeaders httpHeaders){

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .headers(httpHeaders)
                .body(new ResponseDto(message, SeverityEnumA.WARNING.name()));

    }

    public static ResponseEntity createWarningMessage(String message, HttpStatus status, HttpHeaders httpHeaders){

        return ResponseEntity
                .status(status)
                .headers(httpHeaders)
                .body(new ResponseDto(message, SeverityEnumA.WARNING.name()));

    }

    public static ResponseEntity createSuccessMessage(String message, HttpHeaders httpHeaders){

        return ResponseEntity.status(HttpStatus.OK)
                .headers(httpHeaders)
                .body(new ResponseDto(message, SeverityEnumA.SUCCESS.name()));

    }

    public static ResponseEntity createSuccessLoginMessage(String message, String role, String userId, HttpHeaders httpHeaders){

        return ResponseEntity.status(HttpStatus.OK)
                .headers(httpHeaders)
                .body(new ResponseLoginDto(message, SeverityEnumA.SUCCESS.name(), role, userId));

    }

    public static ResponseEntity createSuccessMessage(String message, HttpStatus status, HttpHeaders httpHeaders){

        return ResponseEntity.status(status)
                .headers(httpHeaders)
                .body(new ResponseDto(message, SeverityEnumA.SUCCESS.name()));

    }

    public static ResponseEntity createErrorMessage(String message, HttpHeaders httpHeaders){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .headers(httpHeaders)
                .body(new ResponseDto(message, SeverityEnumA.ERROR.name()));

    }

    public static ResponseEntity createErrorMessage(String message, HttpStatus status, HttpHeaders httpHeaders){

        return ResponseEntity.status(status)
                .headers(httpHeaders)
                .body(new ResponseDto(message, SeverityEnumA.ERROR.name()));

    }

}
