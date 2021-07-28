package com.ligx.utils;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @author: ligongxing.
 * @date: 2021年07月27日.
 */
public class FutureUtil {

    public static <T> CompletableFuture<List<T>> sequence(Collection<CompletableFuture<T>> completableFutures) {
        // allOf: 当所有参数中的CompletableFuture完成后，allOf方法返回的CompletableFuture是完成的
        // allOf方法返回的CompletableFuture计算完成后，开始执行thenApply方法中的Function
        // thenApply中的CompletableFuture::join并不会阻塞，因为thenApply是在前面所有的CompletableFuture全部完成时，才会执行
        return CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0]))
                .thenApply(v -> completableFutures.stream()
                        .map(CompletableFuture::join)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList()));
    }
}
