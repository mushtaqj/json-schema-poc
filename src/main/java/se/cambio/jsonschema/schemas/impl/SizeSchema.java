package se.cambio.jsonschema.schemas.impl;

import org.json.JSONObject;

import se.cambio.jsonschema.schemas.api.JSONObjectSchema;

class SizeSchema extends JSONObjectSchema
{
  private final PropertiesSchema propertiesSchema;

  public SizeSchema(final JSONObject jsonSchema)
  {
    super(jsonSchema.getJSONObject(Property.SIZE.toString()));
    propertiesSchema = new PropertiesSchema(getJsonSchema());
  }

  private DimensionSchema getHeight()
  {
    return getDimensionSchema(Property.HEIGHT);
  }

  private DimensionSchema getWidth()
  {
    return getDimensionSchema(Property.WIDTH);
  }

  private DimensionSchema getDimensionSchema(final Property property)
  {
    final JSONObject dimensionsJson = propertiesSchema.getJSONObject(property.toString());
    return new DimensionSchema(dimensionsJson);
  }

  @Override
  public void validate()
  {
    getHeight().validate();
    getWidth().validate();
  }

  private enum Property
  {
    SIZE("size"), HEIGHT("height"), WIDTH("width");

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
