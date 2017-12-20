package ru.cg.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ru.cg.model.JsonId;

/**
 * @author Rinat Suleymanov
 */
public class FiasIdUtils {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  public static String convertToString(JsonId jsonId) {
    try {
      return MAPPER.writeValueAsString(jsonId);
    }
    catch (JsonProcessingException e) {
      System.err.println("Can not convert JsonId [" + jsonId + "]");
      throw new RuntimeException(e);
    }
  }

  public static JsonId convertToObject(String id) {
    try {
      return MAPPER.readValue(id, JsonId.class);
    }
    catch (IOException e) {
      System.err.println("Can not convert string [" + id + "]");
      throw new RuntimeException(e);
    }
  }
}
