package com.light.rain.exception;

import java.io.IOException;

/**
 * @Author: LightRain
 * @Description: 文件路径错误异常类
 * @DateTime: 2023-02-22 23:23
 * @Version：1.0
 **/
public class FilePathErrorException extends IOException {
    /**
     * Constructs an {@code IOException} with {@code null}
     * as its error detail message.
     */
    public FilePathErrorException() {
    }

    /**
     * Constructs an {@code IOException} with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method)
     */
    public FilePathErrorException(String message) {
        super(message);
    }
}
