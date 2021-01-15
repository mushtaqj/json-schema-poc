package se.cambio.jsonschema;

import java.net.URL;

import org.json.JSONObject;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        final URL resource = App.class.getClassLoader().getResource("widget.schema.json");

        final JSONObject jsonObject = new JSONObject(resource);

        System.out.println(jsonObject);
//        SchemaLoader loader = SchemaLoader.builder().schemaJson();
    }
}
