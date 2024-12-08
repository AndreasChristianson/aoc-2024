package com.pessimistic.aoc2024.util;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.function.Function;

public class FileUtils {
    private FileUtils() {
    }

    public static List<String> readFile(String fileName) {
        return readFile(fileName, Function.identity());
    }

    public static <K> List<K> readFile(String fileName, Function<String, K> postProcessor) {
        try (var inputStream = new FileInputStream(fileName);
             var inputStreamReader = new InputStreamReader(inputStream, Charsets.toCharset(StandardCharsets.UTF_8));
        ) {
            return IOUtils.toBufferedReader(inputStreamReader)
                    .lines()
                    .map(postProcessor)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> readTestFile(String fileName) {
        return readFile("src/test/resources/%s".formatted(fileName));
    }

    public static String readWholeFile(String fileName) {
        try (var inputStream = new FileInputStream(fileName);) {
            return IOUtils.toString(inputStream, Charset.defaultCharset());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readWholeTestFile(String fileName) {
        return readWholeFile("src/test/resources/%s".formatted(fileName));
    }
}
