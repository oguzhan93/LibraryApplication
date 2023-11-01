package com.libraryApplication.LibraryApplication.utilities.results;

public class DataErrorResult<T> extends DataResult<T> {
    public DataErrorResult(T data, String message) {
        super(data, false, message);
    }

    public DataErrorResult(T data) {
        super(data, false);
    }

    public DataErrorResult(String message) {
        super(null, false, message);
    }

    public DataErrorResult() {
        super(null, false);
    }

    public static <T> DataErrorResult<T> of(T data, String message) {
        return new DataErrorResult<>(data, message);
    }

    public static <T> DataErrorResult<T> of(T data) {
        return new DataErrorResult<>(data);
    }
}
