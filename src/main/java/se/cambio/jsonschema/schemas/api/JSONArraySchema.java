package se.cambio.jsonschema.schemas.api;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class JSONArraySchema extends JSONSchema<JSONArray>
{
  protected JSONArraySchema(final JSONArray jsonSchema)
  {
    super(jsonSchema);
  }

  protected JSONObject getJSONObject(final int index)
  {
    return getJsonSchema().getJSONObject(index);
  }
}
