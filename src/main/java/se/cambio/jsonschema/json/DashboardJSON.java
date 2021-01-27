package se.cambio.jsonschema.json;

public class DashboardJSON
{

  private String dashboardId;
  private String namespace;
  private LocaleStringJSON visiblePath;
  private LocaleStringJSON name;
  private String description;

  public String getDashboardId()
  {
    return dashboardId;
  }

  public void setDashboardId(String dashboardId)
  {
    this.dashboardId = dashboardId;
  }

  public String getNamespace()
  {
    return namespace;
  }

  public void setNamespace(String namespace)
  {
    this.namespace = namespace;
  }

  public LocaleStringJSON getVisiblePath()
  {
    return visiblePath;
  }

  public void setVisiblePath(LocaleStringJSON visiblePath)
  {
    this.visiblePath = visiblePath;
  }

  public LocaleStringJSON getName()
  {
    return name;
  }

  public void setName(LocaleStringJSON name)
  {
    this.name = name;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }
}
