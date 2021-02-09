package se.cambio.jsonschema.schemas.api;

import org.json.JSONArray;
import org.json.JSONObject;

import se.cambio.jsonschema.schemas.Validator;

public abstract class JSONObjectSchema extends JSONSchema<JSONObject> implements Validator
{
  protected JSONObjectSchema(final JSONObject jsonSchema)
  {
    super(jsonSchema);
  }

  protected JSONObject getJSONObject(final String key)
  {
    return getJsonSchema().getJSONObject(key);
  }

  protected JSONArray getJSONArray(final String key)
  {
    return getJsonSchema().getJSONArray(key);
  }

}

