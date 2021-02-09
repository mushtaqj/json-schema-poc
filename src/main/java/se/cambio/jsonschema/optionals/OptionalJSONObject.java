package se.cambio.jsonschema.optionals;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

import org.json.JSONObject;

public class OptionalJSONObject implements OptionalJSON
{
  private final JSONObject value;

  private OptionalJSONObject(final JSONObject jsonObject)
  {
    this.value = jsonObject;
  }

  public static OptionalJSONObject of(final JSONObject jsonObject)
  {
    return new OptionalJSONObject(jsonObject);
  }

  public OptionalJSONObject getJSONObject(final String key)
  {
    return new OptionalJSONObject(this.value.optJSONObject(key));
  }

  public OptionalJSONArray getJSONArray(final String key)
  {
    return OptionalJSONArray.of(this.value.optJSONArray(key));
  }

  public Optional<String> getString(final String key)
  {
    return key.isEmpty() ? Optional.empty() : Optional.of(this.value.optString(key));
  }

  public Set<String> getKeys()
  {
    if (isPresent())
    {
      return this.value.keySet();
    }

    return Collections.emptySet();
  }

  public void ifPresent(Consumer<OptionalJSONObject> consumer)
  {
    if (isPresent())
    {
      consumer.accept(this);
    }
  }

  public JSONObject get()
  {
    return this.value;
  }

  public final boolean isPresent()
  {
    return this.value != null;
  }
}
