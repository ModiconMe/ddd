package org.example.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Locale;

@Getter
@ToString
@RequiredArgsConstructor
public class Request<T> {

    private final T data;
    private final Locale locale;

}
