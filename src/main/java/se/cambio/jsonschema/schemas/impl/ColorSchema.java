package se.cambio.jsonschema.schemas.impl;

import org.json.JSONObject;

import se.cambio.jsonschema.schemas.api.JSONObjectSchema;

class ColorSchema extends JSONObjectSchema
{
  public ColorSchema(final JSONObject jsonSchema)
  {
    super(jsonSchema);
  }

  public void validate()
  {
    getJSONObject(Property.COLOR.toString());
  }

  private enum Property
  {
    COLOR("color");

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
