package se.cambio.jsonschema;

import java.io.InputStream;
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
        final InputStream resource = App.class.getClassLoader().getResourceAsStream("widget.schema.json");

        final JSONObject jsonObject = new JSONObject(resource);

        System.out.println(jsonObject.toString());
//        SchemaLoader loader = SchemaLoader.builder().schemaJson();
    }
}
