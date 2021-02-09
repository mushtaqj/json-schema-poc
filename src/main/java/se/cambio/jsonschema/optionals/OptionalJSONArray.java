package se.cambio.jsonschema.optionals;

import java.util.Optional;
import java.util.function.Consumer;

import org.json.JSONArray;

public class OptionalJSONArray implements OptionalJSON
{
  private final JSONArray value;

  private OptionalJSONArray(final JSONArray jsonArray)
  {
    this.value = jsonArray;
  }

  public static OptionalJSONArray of(final JSONArray jsonArray)
  {
    return new OptionalJSONArray(jsonArray);
  }

  public OptionalJSONObject getJSONObject(final Integer index)
  {
    return OptionalJSONObject.of(value.optJSONObject(index));
  }

  public OptionalJSONArray getJSONArray(final Integer index)
  {
    return OptionalJSONArray.of(this.value.optJSONArray(index));
  }

  public Optional<String> getString(final Integer index)
  {
    return Optional.ofNullable(this.value.optString(index));
  }

  public void ifPresent(Consumer<OptionalJSONArray> consumer)
  {
    if (isPresent())
    {
      consumer.accept(this);
    }
  }

  public JSONArray get()
  {
    return this.value;
  }

  public final boolean isPresent()
  {
    return this.value != null;
  }
}
