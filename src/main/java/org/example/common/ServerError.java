package org.example.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ServerError {

    @JsonIgnore
    private final int httpCode;
    private final int serverCode;
    private final String message;

    public static ServerError unprocessableEntity(int serverCode, String message) {
        return new ServerError(HttpStatus.UNPROCESSABLE_ENTITY.value(), serverCode, message);
    }

    public static ServerError notFound(int serverCode, String message) {
        return new ServerError(HttpStatus.NOT_FOUND.value(), serverCode, message);
    }

    public static ServerError internationalize(ServerError serverError, String message) {
        return new ServerError(serverError.httpCode, serverError.getServerCode(), message);
    }

}
