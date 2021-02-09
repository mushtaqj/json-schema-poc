package se.cambio.jsonschema.schemas.impl;

import org.json.JSONObject;

import se.cambio.jsonschema.schemas.api.JSONObjectSchema;

public class WidgetSchemaRequiredProperties extends JSONObjectSchema
{
  private final FormControlNameSchema formControlNameSchema;
  private final SizeSchema sizeSchema;
  private final ColorSchema colorSchema;

  public WidgetSchemaRequiredProperties(final JSONObject jsonSchema)
  {
    super(jsonSchema);
    formControlNameSchema = new FormControlNameSchema(getJsonSchema());
    sizeSchema = new SizeSchema(getJsonSchema());
    colorSchema = new ColorSchema(getJsonSchema());
  }

  @Override
  public void validate()
  {
    formControlNameSchema.validate();
    sizeSchema.validate();
    colorSchema.validate();
  }
}
