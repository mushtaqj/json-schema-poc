package se.cambio.jsonschema.json;

import com.google.gson.JsonObject;

public class WidgetJSON
{

  private String widgetId;
  private String name;
  private String version;
  private String description;
  private String featureArea;
  private JsonObject propertiesSchema;

  public String getWidgetId()
  {
    return widgetId;
  }

  public void setWidgetId(String widgetId)
  {
    this.widgetId = widgetId;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getVersion()
  {
    return version;
  }

  public void setVersion(String version)
  {
    this.version = version;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public String getFeatureArea()
  {
    return featureArea;
  }

  public void setFeatureArea(String featureArea)
  {
    this.featureArea = featureArea;
  }

  public JsonObject getPropertiesSchema()
  {
    return propertiesSchema;
  }

  public void setPropertiesSchema(JsonObject propertiesSchema)
  {
    this.propertiesSchema = propertiesSchema;
  }
}
