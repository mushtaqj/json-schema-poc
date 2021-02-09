package se.cambio.jsonschema.schemas.impl;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

import org.json.JSONArray;
import org.json.JSONObject;

import se.cambio.jsonschema.schemas.api.JSONArraySchema;
import se.cambio.jsonschema.util.FileReader;

class WidgetPropertiesAllOfSchema extends JSONArraySchema
{

  public static final UnaryOperator<String> REPLACE_CLASSPATH_PROTOCOL = s -> s.replace("classpath://", "");

  public WidgetPropertiesAllOfSchema(final JSONArray schema)
  {
    super(schema);
  }

  /**
   * Flattens the JSONArray into a single JSONObject
   *
   * @return JSONObject
   */
  private JSONObject flattenAllOfJSONArray()
  {
    final JSONObject mergedAllOf = new JSONObject();

    final Consumer<JSONObject> copyToMergeAllOf = properties -> copyTo(mergedAllOf, properties);

    for (int index = 0; index < getJsonSchema().length(); index++)
    {
      final JSONObject jsonObject = getJSONObject(index);

      loadJSONFromJsonPointer(jsonObject).flatMap(this::getPropertiesSchema).ifPresent(copyToMergeAllOf);

      getPropertiesSchema(jsonObject).ifPresent(copyToMergeAllOf);
    }

    return mergedAllOf;
  }

  private void copyTo(final JSONObject to, final JSONObject from)
  {
    from.keySet().forEach(key -> to.put(key, from.opt(key)));
  }

  private Optional<JSONObject> loadJSONFromJsonPointer(final JSONObject jsonObject)
  {
    return getReferenceUrl(jsonObject).map(FileReader::readJSON);
  }

  private Optional<String> getReferenceUrl(final JSONObject jsonObject)
  {
    return Optional.ofNullable(jsonObject.optString(Property.REF.toString())).filter(ref -> !ref.isEmpty())
                   .map(REPLACE_CLASSPATH_PROTOCOL);
  }

  private Optional<JSONObject> getPropertiesSchema(final JSONObject jsonObject)
  {
    return Optional.ofNullable(new PropertiesSchema(jsonObject).getJsonSchema());
  }

  protected WidgetSchemaRequiredProperties getWidgetSchemaRequiredProperties()
  {
    return new WidgetSchemaRequiredProperties(flattenAllOfJSONArray());
  }

  private enum Property
  {
    REF("$ref");

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
