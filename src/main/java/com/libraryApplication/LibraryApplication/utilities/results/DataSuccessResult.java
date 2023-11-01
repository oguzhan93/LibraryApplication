package com.libraryApplication.LibraryApplication.utilities.results;

public class DataSuccessResult<T> extends DataResult<T> {
    public DataSuccessResult(T data, String message) {
        super(data, true, message);
    }

    public DataSuccessResult(T data) {
        super(data, true);
    }

    public DataSuccessResult(String message) {
        super(null, true, message);
    }

    public DataSuccessResult() {
        super(null, true);
    }

    public static <T> DataSuccessResult<T> of(T data, String message) {
        return new DataSuccessResult<>(data, message);
    }

    public static <T> DataSuccessResult<T> of(T data) {
        return new DataSuccessResult<>(data);
    }
}
