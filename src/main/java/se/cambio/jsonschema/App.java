package se.cambio.jsonschema;

import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaClient;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import se.cambio.jsonschema.util.FileReader;

/**
 * Hello world!
 */
public class App
{
  public static void main(String[] args)
  {
    final JSONObject widgetSchema = loadJSONFromResource("core/widget.schema.json");
    final JSONObject medDefinitionJSON = loadJSONFromResource("widgets/medication/widget-consumable-and-nutrition.json");
    final JSONObject medPropertySchema = medDefinitionJSON.getJSONObject("propertiesSchema");
    final JSONObject medPropertiesJSON = loadJSONFromResource("widgets/medication/med.properties.json");

    System.out.println("Validating Widget Schema for Medication");
    validateJSON(widgetSchema, medDefinitionJSON);
    System.out.println("Validating Medication Widget propertiesSchema");
    validateJSON(medPropertySchema, medPropertiesJSON);
  }

  private static void validateJSON(final JSONObject jsonSchema, final JSONObject toValidateJSON)
  {
    final SchemaLoader schemaLoader =
      SchemaLoader.builder()
                  .schemaClient(SchemaClient.classPathAwareClient())
                  .schemaJson(jsonSchema)
                  .resolutionScope("classpath://core") // setting the default resolution scope
                  .build();

    try
    {
      schemaLoader.load().build().validate(toValidateJSON);
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
