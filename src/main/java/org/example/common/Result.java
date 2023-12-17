package org.example.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.function.Function;

@Getter
@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Result<T> {

    private final ServerError error;
    private final T data;

    public static <T> Result<T> success(T data) {
        return new Result<>(null, data);
    }

    public static <T> Result<T> error(ServerError error) {
        return new Result<>(error, null);
    }

    public static <T1, T2, T3> Result<Tuple3<T1, T2, T3>> zip(Result<T1> t1, Result<T2> t2, Result<T3> t3) {
        List<Result<?>> res = List.of(t1, t2, t3);
        if (res.stream().noneMatch(Result::isFailure)) {
            return Result.success(Tuple3.of(t1.getData(), t2.getData(), t3.getData()));
        } else {
            return Result.error(res.stream().filter(Result::isFailure).findFirst().get().getError());
        }
    }

    public <R> Result<R> map(Function<T, R> mapper) {
        if (isSuccess()) {
            return Result.success(mapper.apply(data));
        }
        return Result.error(error);
    }

    public ResponseEntity<Result<T>> toRestResponse() {
        if (isSuccess()) return ResponseEntity.ok(this);
        else return ResponseEntity.status(error.getHttpCode()).body(this);
    }

    @JsonIgnore
    public boolean isSuccess() {
        return data != null && error == null;
    }

    @JsonIgnore
    public boolean isFailure() {
        return data == null && error != null;
    }

}
