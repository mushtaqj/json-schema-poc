package se.cambio.jsonschema.util;

import java.io.Reader;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public final class JSONHelper
{
  private JSONHelper()
  {
    throw new UnsupportedOperationException("Should not be instantiated");
  }

  public static boolean isValid(final Reader schema, final Reader definition)
  {
    return true;
  }

  public static <T> T parseDefinition(final Reader definition, final TypeToken<T> token)
  {
    final Gson gson = new Gson();
    return gson.fromJson(definition, token.getType());
  }
}
