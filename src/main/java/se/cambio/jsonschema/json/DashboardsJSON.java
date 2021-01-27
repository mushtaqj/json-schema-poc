package se.cambio.jsonschema.json;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.reflect.TypeToken;

public class DashboardsJSON extends TypeToken<DashboardsJSON>
{
  private List<DashboardJSON> dashboards = new ArrayList<>();

  public List<DashboardJSON> getDashboards()
  {
    return dashboards;
  }

  public void setDashboards(List<DashboardJSON> dashboards)
  {
    this.dashboards = dashboards;
  }
}
