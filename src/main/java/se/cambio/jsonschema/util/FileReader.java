package se.cambio.jsonschema.util;

import java.io.InputStream;

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
}
