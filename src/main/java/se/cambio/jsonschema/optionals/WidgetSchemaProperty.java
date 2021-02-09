package se.cambio.jsonschema.optionals;

public enum WidgetSchemaProperty
{
  PROPERTIES("properties"), PROPERTIES_SCHEMA("propertiesSchema"), ALL_OF("allOf"), REF("$ref");
  private final String value;

  WidgetSchemaProperty(String value)
  {
    this.value = value;
  }

  public String getValue()
  {
    return value;
  }

  @Override
  public String toString()
  {
    return "SchemaFields{" + "value='" + value + '\'' + "} " + super.toString();
  }
}
