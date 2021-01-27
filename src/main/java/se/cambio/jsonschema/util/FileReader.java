package se.cambio.jsonschema.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public final class FileReader
{
  private FileReader()
  {
    throw new IllegalArgumentException("Should not be instantiated");
  }

  public static Reader read(final String path)
  {
    final InputStream stream = readFile(path);

    if (stream != null)
    {
      return new InputStreamReader(stream);
    }

    throw new IllegalStateException("Stream cannot be null to be read");
  }

  public static InputStream readFile(final String resourcePath)
  {
    return Thread.currentThread().getContextClassLoader().getResourceAsStream(resourcePath);
  }

  public static Map<String, List<String>> getWidgetsByFeatureArea(final String widgetDefinitionsPath)
  {
    final List<String> featureAreas = getFilesInFolder(widgetDefinitionsPath);
    final Map<String, List<String>> featureAreaWidgets = new HashMap<>();

    if (!featureAreas.isEmpty())
    {
      featureAreas.stream().filter(Objects::nonNull)
        .forEach(feature -> featureAreaWidgets.put(feature, getFilesInFolder(widgetDefinitionsPath + feature + "/")));
    }

    return featureAreaWidgets;
  }

  public static List<String> getFilesInFolder(final String folderPath)
  {
    final URI resourceAsStream;
    try
    {
      resourceAsStream = Thread.currentThread().getContextClassLoader().getResource(folderPath).toURI();
      return Files.list(Paths.get(resourceAsStream)).map(path -> path.getFileName().toString()).collect(Collectors.toList());
    }
    catch (URISyntaxException | IOException e)
    {
      e.printStackTrace();
    }

    return Collections.emptyList();

  }
}
