package se.cambio.jsonschema.schemas.impl;

import org.json.JSONObject;

import se.cambio.jsonschema.schemas.api.JSONObjectSchema;

final class PropertiesSchema extends JSONObjectSchema
{
  protected PropertiesSchema(final JSONObject jsonSchema)
  {
    super(jsonSchema.optJSONObject(Property.PROPERTIES.toString()));
  }

  @Override
  public JSONObject getJSONObject(String key)
  {
    return super.getJSONObject(key);
  }

  @Override
  public void validate()
  {
    throw new UnsupportedOperationException("Should not be validated");
  }

  private enum Property
  {
    PROPERTIES("properties");

    private final String value;

    Property(String value)
    {
      this.value = value;
    }

    @Override
    public String toString()
    {
      return value;
    }
  }
}
