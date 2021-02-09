package se.cambio.jsonschema.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.json.JSONObject;
import org.json.JSONTokener;

public final class FileReader
{
  private FileReader()
  {
    throw new IllegalArgumentException("Should not be instantiated");
  }

  public static InputStream readFile(final String resourcePath)
  {
    return Thread.currentThread().getContextClassLoader().getResourceAsStream(resourcePath);
  }

  public static JSONObject readJSON(final String filePath)
  {
    final InputStream stream = getFromResourcesAsStream(filePath);

    final JSONObject jsonObject = getJsonObject(stream);
    if (jsonObject != null)
    {
      return jsonObject;
    }

    throw new IllegalStateException("The schema cannot be read as it is points to a empty reference");
  }

  private static JSONObject getJsonObject(InputStream stream)
  {
    if (stream != null)
    {
      final InputStreamReader reader = new InputStreamReader(stream);
      final JSONObject jsonObject = new JSONObject(new JSONTokener(reader));
      closeStream(reader);
      return jsonObject;
    }
    return null;
  }

  private static InputStream getFromResourcesAsStream(final String resourcePath)
  {
    return Thread.currentThread().getContextClassLoader().getResourceAsStream(resourcePath);
  }

  private static void closeStream(final Reader reader)
  {
    try
    {
      reader.close();
    }
    catch (IOException exception)
    {
     exception.printStackTrace();
    }
  }
}
