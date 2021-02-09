package se.cambio.jsonschema.schemas.impl;

import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;

import se.cambio.jsonschema.schemas.api.JSONObjectSchema;

public class WidgetPropertiesSchema extends JSONObjectSchema
{
  private final PropertiesSchema propertiesSchema;

  public WidgetPropertiesSchema(final JSONObject schema)
  {
    super(schema);
    propertiesSchema = new PropertiesSchema(getJsonSchema());
  }

  private WidgetSchemaRequiredProperties getWidgetSchemaRequiredProperties()
  {
    final Optional<JSONObject> propertiesOpt = Optional.ofNullable(propertiesSchema.getJsonSchema());
    final Optional<JSONArray> allOfOpt = Optional.ofNullable(getJSONArray(Property.ALL_OF.toString()));

    if (propertiesOpt.isPresent())
    {
      return new WidgetSchemaRequiredProperties(propertiesOpt.get());
    }
    else if (allOfOpt.isPresent())
    {
      return new WidgetPropertiesAllOfSchema(allOfOpt.get()).getWidgetSchemaRequiredProperties();
    }

    throw new IllegalStateException("should have properties or allOf");
  }

  @Override
  public void validate()
  {
    getWidgetSchemaRequiredProperties().validate();
  }

  private enum Property
  {
    ALL_OF("allOf");

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
