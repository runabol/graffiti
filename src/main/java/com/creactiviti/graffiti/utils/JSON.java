package com.creactiviti.graffiti.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Arik Cohen
 * @since Dec 16, 2017
 */
public abstract class JSON {

  private static final ObjectMapper mapper = new ObjectMapper ();
  
  public static String write (Object aValue) {
    try {
      return mapper.writeValueAsString(aValue);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
  
  public static <T> T read (String aValue, Class<T> aType) {
    try {
      return mapper.readValue(aValue, aType);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  
}
