package br.upe.ProjectNest.domain.common.utils;

import br.upe.ProjectNest.domain.common.exceptions.JsonParsingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toPrettyJson(Object obj) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new JsonParsingException("Erro ao converter o objeto %s para Json".formatted(obj));
        }
    }

}
