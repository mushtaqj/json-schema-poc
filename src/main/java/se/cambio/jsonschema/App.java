package se.cambio.jsonschema;

import static se.cambio.jsonschema.optionals.WidgetSchemaProperty.PROPERTIES;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaClient;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import se.cambio.jsonschema.optionals.OptionalJSONObject;
import se.cambio.jsonschema.schemas.impl.WidgetSchema;
import se.cambio.jsonschema.util.FileReader;

/**
 * Hello world!
 */
public class App
{
  public static void main(String[] args)
  {
    final JSONObject widgetSchema = loadJSONFromResource("core/widget.schema.json");
    final JSONObject medDefinitionJSON =
      loadJSONFromResource("widgets/medication/widget-consumable-and-nutrition.json");
    final JSONObject medPropertySchema = medDefinitionJSON.getJSONObject("propertiesSchema");
    final JSONObject medPropertiesJSON = loadJSONFromResource("widgets/medication/med.properties.json");
    //    validateJSON(widgetSchema, medDefinitionJSON);
    //    validateJSON(medPropertySchema, medPropertiesJSON);
    System.out.println(contains(medDefinitionJSON, Arrays.asList("size", "color", "formControlName")));
  }

  private static Optional<JSONObject> findPropertiesSchema(final JSONObject jsonObject)
  {
    //schemaProperties can have either "properties" or "allOf"
    //allOf will be JSONArray and will have two items
    //one of them will be a reference and the other will be object with properties
    //get the properties and check if the size dimensions fit
    //load the ref and check if it has formControlName and color

    return find(jsonObject, "propertiesSchema");
  }

  private static boolean contains(final JSONObject jsonObject, final List<String> properties)
  {
    return getAllPropertiesUsingJSONOptional(jsonObject).containsAll(properties);
  }

  private static Set<String> getAllProperties(final JSONObject jsonObject)
  {
    final Optional<JSONObject> propSchemaOpt = findPropertiesSchema(jsonObject);
    final Set<String> keys = new HashSet<>();

    if (propSchemaOpt.isPresent())
    {
      final Optional<JSONObject> propertiesSchema = find(propSchemaOpt.get(), "properties");
      final Optional<JSONArray> allOfOpt = findArray(propSchemaOpt.get(), "allOf");

      if (allOfOpt.isPresent())
      {
        final JSONArray allOf = allOfOpt.get();
        final Optional<JSONObject> baseSchemaRef = findInArray(allOf, 0);
        final Optional<JSONObject> innerPropertiesSchema = findInArray(allOf, 1);

        if (baseSchemaRef.isPresent())
        {
          final Optional<String> refOpt = findString(baseSchemaRef.get(), "$ref");
          if (refOpt.isPresent())
          {
            final JSONObject referencedSchema = loadJSONFromResource(refOpt.get().replace("classpath://", ""));

            keys.addAll(findPropertyKeys(referencedSchema));
          }
        }

        innerPropertiesSchema.ifPresent(json -> keys.addAll(findPropertyKeys(json)));
      }
      else if (propertiesSchema.isPresent())
      {
        keys.addAll(findPropertyKeys(propertiesSchema.get()));
      }
      else
      {
        throw new IllegalStateException("should have properties or allOf");
      }
    }

    System.out.println("App.getAllProperties keys = " + keys);
    return keys;
  }

  private static Set<String> getAllPropertiesUsingJSONOptional(final JSONObject jsonObject)
  {

    new WidgetSchema(jsonObject).validate();


    //    final OptionalJSONObject propSchemaOpt =
//      OptionalJSONObject.of(jsonObject).getJSONObject(PROPERTIES_SCHEMA.getValue());
//
//    final Set<String> keys = new HashSet<>();
//
//    if (propSchemaOpt.isPresent())
//    {
//      final OptionalJSONObject propertiesSchema = getPropertiesNode(propSchemaOpt);
//      final OptionalJSONArray allOfOpt = propSchemaOpt.getJSONArray(ALL_OF.getValue());
//
//      if (allOfOpt.isPresent())
//      {
//        int jsonArrIdx = 0;
//        final OptionalJSONObject baseSchemaRef = allOfOpt.getJSONObject(0);
//
//        if (baseSchemaRef.isPresent())
//        {
//          final Optional<String> refOpt = baseSchemaRef.getString("$ref");
//          if (refOpt.isPresent())
//          {
//            ++jsonArrIdx;
//            final OptionalJSONObject referencedSchemaOpt =
//              OptionalJSONObject.of(loadJSONFromResource(refOpt.get().replace("classpath://", "")));
//
//            addIfPropertiesPresent(keys, referencedSchemaOpt);
//
//          }
//        }
//        addIfPropertiesPresent(keys, allOfOpt.getJSONObject(jsonArrIdx));
//      }
//      else if (propertiesSchema.isPresent())
//      {
//        addIfPropertiesPresent(keys, propertiesSchema);
//      }
//      else
//      {
//        throw new IllegalStateException("should have properties or allOf");
//      }
//
//    }
//
//    System.out.println("App.getAllPropertiesUsingJSONOptional keys = " + keys);
//    return keys;

    return Collections.emptySet();
  }

  private static void addIfPropertiesPresent(final Set<String> keys, final OptionalJSONObject jsonOpt)
  {
    jsonOpt.ifPresent(referenceOpt -> keys.addAll(getPropertiesNode(referenceOpt).getKeys()));
  }

  private static OptionalJSONObject getPropertiesNode(OptionalJSONObject referenceOpt)
  {
    return referenceOpt.getJSONObject(PROPERTIES.getValue());
  }

  private static Set<String> findPropertyKeys(final JSONObject jsonObject)
  {
    final Optional<JSONObject> referenceProperties = find(jsonObject, "properties");

    return referenceProperties.map(JSONObject::keySet).orElse(Collections.emptySet());
  }

  private static Optional<String> findString(final JSONObject jsonObject, final String key)
  {
    return Optional.ofNullable(jsonObject.optString(key));
  }

  private static Optional<JSONObject> findInArray(JSONArray jsonArray, int index)
  {
    return Optional.ofNullable(jsonArray.optJSONObject(index));
  }

  private static Optional<JSONArray> findArray(JSONObject jsonObject, String key)
  {
    return Optional.ofNullable(jsonObject.optJSONArray(key));
  }

  private static Optional<JSONObject> find(final JSONObject jsonObject, final String key)
  {
    return Optional.ofNullable(jsonObject.optJSONObject(key));
  }

  private static void validateJSON(final JSONObject jsonSchema, final JSONObject toValidateJSON)
  {
    try
    {
      final SchemaLoader schemaLoader =
        SchemaLoader.builder().schemaClient(SchemaClient.classPathAwareClient()).schemaJson(jsonSchema)
                    .resolutionScope("classpath://core") // setting the default resolution scope
                    .build();
      final Schema.Builder<?> load = schemaLoader.load();
      final Schema schema = load.build();

      schema.validate(toValidateJSON);

    }
    catch (ValidationException ex)
    {
      System.err.println(ex.getMessage());
      ex.getCausingExceptions().stream().map(ValidationException::getMessage).forEach(System.err::println);
    }
  }

  private static JSONTokener createTokenerFromPath(String resourcePath)

  {
    return new JSONTokener(FileReader.readFile(resourcePath));
  }

  private static JSONObject loadJSONFromResource(final String jsonFilePath)
  {
    return new JSONObject(createTokenerFromPath(jsonFilePath));
  }
}
