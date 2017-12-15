package com.paulhammant.joobymodularity;

import org.jooby.Dir;
import org.jooby.Domain;
import org.jooby.Jooby;
import org.jooby.Joobyable;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author jooby generator
 */
public class App extends Jooby {

  public static class Beatle {

    public Beatle(String name, Date dob, Date dod, String blurb) {
      this.name = name;
      this.dob = dob;
      this.dod = dod;
      this.blurb = blurb;
    }

    String name;
    Date dob;
    Date dod;
    String blurb;

    public long getAge(Date asOf) {
      Duration duration;
      if (dod == null) {
        duration = Duration.between(dob.toInstant(), asOf.toInstant());
      } else {
        duration = Duration.between(dob.toInstant(), dod.toInstant());
      }
      return duration.toDays() / 365;
    }

  }

  public static class BeatlesMembers extends Dir {
    {
      Map<String, Beatle> beatles = new HashMap<String, Beatle>() {{
        put("john", new Beatle("john", new Date(1940, 9, 9), new Date(1980, 11, 8), "singer, songwriter, musician, and activist"));
        put("paul", new Beatle("paul", new Date(1942, 5, 18), null, "singer-songwriter, multi-instrumentalist, and composer"));
        put("george", new Beatle("george", new Date(1943, 1, 25), new Date(2001, 10, 29), "guitarist, singer-songwriter, and producer"));
        put("ringo", new Beatle("ringo", new Date(1940, 6, 7), null, "drummer, singer, songwriter and actor"));
      }};

      get("/", () -> "John, Paul, George, Ringo");
      get(":id", req -> beatles.get(req.param("id").value()).blurb);
      // Directory nesting:
      dir(new Dir("stats", this) {{
        get("still-with-us", () -> {
          return beatles.entrySet().stream().filter(mapKv -> mapKv.getValue().dod == null).count() + " of " + beatles.size();
        });
        get("average-age", () -> beatles.entrySet().stream()
                .map(map -> map.getValue().getAge(new Date(2017, 11, 15)))
                .collect(Collectors.averagingDouble(Long::longValue)));
      }

      });
    }

    public BeatlesMembers(String dirName, Joobyable joobyable) {
      super(dirName, joobyable);
    }

  }

  // no-args constructor ...
  {
    domain(new Domain("books.localhost", this) {{
      dir(new Dir("dune", this) {{
        get("/", () -> "What a great novel, thanks Frank Herbert, RIP");
      }});
    }});

    domain(new Domain("music.localhost", this) {{
      dir (new Dir("beatles", this) {{
        dir(new BeatlesMembers("members", this));
        get("/", () -> "Great band from the 60's. Sadly long split up");
      }});
    }});

    port(8432);
  }

  public static void main(final String[] args) {
    run(App::new, args);
  }
}
