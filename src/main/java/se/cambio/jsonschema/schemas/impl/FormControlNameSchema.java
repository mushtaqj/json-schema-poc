package se.cambio.jsonschema.schemas.impl;

import org.json.JSONObject;

import se.cambio.jsonschema.schemas.api.JSONObjectSchema;

class FormControlNameSchema extends JSONObjectSchema
{
  public FormControlNameSchema(final JSONObject jsonSchema)
  {
    super(jsonSchema);
  }

  @Override
  public void validate()
  {
    getJSONObject(Property.FORM_CONTROL_NAME.toString());
  }

  private enum Property
  {
    FORM_CONTROL_NAME("formControlName");

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
