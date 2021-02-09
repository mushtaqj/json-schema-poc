package se.cambio.jsonschema.schemas.impl;

import org.json.JSONObject;

import se.cambio.jsonschema.schemas.api.JSONObjectSchema;

public class WidgetSchema extends JSONObjectSchema
{
  public WidgetSchema(final JSONObject schema)
  {
    super(schema);
  }

  private WidgetPropertiesSchema getPropertiesSchemaNode()
  {
    return new WidgetPropertiesSchema(getJSONObject(Property.PROPERTIES_SCHEMA.toString()));
  }

  @Override
  public void validate()
  {
    getPropertiesSchemaNode().validate();
  }

  private enum Property
  {
    PROPERTIES_SCHEMA("propertiesSchema");

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
