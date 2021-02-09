package se.cambio.jsonschema.schemas.api;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class JSONSchema<T>
{
  private final T value;

  JSONSchema(final T value)
  {
    if (value == null || value instanceof JSONObject || value instanceof JSONArray)
    {
      this.value = value;
    }
    else
    {
      throw new IllegalStateException("Schema can only be of type org.json.JSONObject or org.json.JSONArray");
    }
  }

  public final T getJsonSchema()
  {
    return value;
  }

}
