package se.cambio.jsonschema;

import java.io.InputStream;
import java.net.URISyntaxException;

import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import se.cambio.jsonschema.util.FileReader;

/**
 * Hello world!
 */
public class App
{
  public static void main(String[] args) throws URISyntaxException
  {
    final InputStream schemaStream = FileReader.readFile("core/widget.schema.json");
    final JSONTokener schemaTokener = new JSONTokener(schemaStream);
    final InputStream medicationStream = FileReader.readFile("widgets/medication/widget-consumable-and-nutrition.json");
    final JSONTokener medicationTokener = new JSONTokener(medicationStream);

    final InputStream medicationPropertyStream = FileReader.readFile("widgets/medication/med.properties.json");
    final JSONTokener medicationPropertyTokener = new JSONTokener(medicationPropertyStream);

    final JSONObject widgetSchemaJSON = new JSONObject(schemaTokener);
    final JSONObject medicationJSON = new JSONObject(medicationTokener);
    final JSONObject medPropertySchemaJSON = medicationJSON.getJSONObject("propertiesSchema");


    //      SchemaLoader.load(widgetSchemaJSON).validate(medicationJSON);

//    final URL resource = Thread.currentThread().getContextClassLoader().getResource("core/");
//    final SchemaLoader schemaLoader =
//      SchemaLoader.builder().resolutionScope(resource.toURI()).schemaJson(medPropertySchemaJSON).build();
    SchemaLoader.load(medPropertySchemaJSON).validate(new JSONObject(medicationPropertyTokener));
  }

}
