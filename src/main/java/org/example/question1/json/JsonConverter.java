package org.example.question1.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 객체를 JSON 문자열로 변환합니다.
     * @param object 변환할 객체
     * @return JSON 문자열
     * @throws RuntimeException JSON 변환 중 오류 발생 시
     */
    public static String toJson(final Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new JsonConversionException("Error converting object to JSON", e);
        }
    }

    /**
     * JSON 문자열을 객체로 변환합니다.
     * @param json JSON 문자열
     * @param clazz 변환할 객체의 클래스
     * @param <T> 변환할 객체의 타입
     * @return 변환된 객체
     * @throws JsonProcessingException JSON 변환 중 오류 발생 시
     */
    public static <T> T fromJson(final String json, final Class<T> clazz) throws JsonProcessingException {
        return objectMapper.readValue(json, clazz);
    }
}
