package se.cambio.jsonschema.schemas.impl;

import org.json.JSONObject;

import se.cambio.jsonschema.schemas.api.JSONObjectSchema;

class DimensionSchema extends JSONObjectSchema
{
  public DimensionSchema(final JSONObject jsonSchema)
  {
    super(jsonSchema);
  }

  private void hasMaximum()
  {
    throwIfNotAvailable(Property.MAXIMUM);
  }

  private void hasMinimum()
  {
    throwIfNotAvailable(Property.MINIMUM);
  }

  private void throwIfNotAvailable(final Property property)
  {
    getJsonSchema().getInt(property.toString());
  }

  @Override
  public void validate()
  {
    hasMaximum();
    hasMinimum();
  }

  private enum Property
  {
    MAXIMUM("maximum"), MINIMUM("minimum");

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
