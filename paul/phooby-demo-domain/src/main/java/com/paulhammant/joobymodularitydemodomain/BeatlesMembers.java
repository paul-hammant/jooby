package com.paulhammant.joobymodularitydemodomain;

import org.jooby.Dir;
import org.jooby.Router;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class BeatlesMembers extends Dir {
  {
    Map<String, Beatle> beatles = new HashMap<String, Beatle>() {{
      put("john", new Beatle("john", new Date(1940, 9, 9), new Date(1980, 11, 8), "singer, songwriter, musician, and activist"));
      put("paul", new Beatle("paul", new Date(1942, 5, 18), null, "singer-songwriter, multi-instrumentalist, and composer"));
      put("george", new Beatle("george", new Date(1943, 1, 25), new Date(2001, 10, 29), "guitarist, singer-songwriter, and producer"));
      put("ringo", new Beatle("ringo", new Date(1940, 6, 7), null, "drummer, singer, songwriter and actor"));
    }};

    get("/", () -> {
      return "John, Paul, George, Ringo";
    });
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

  public BeatlesMembers(String dirName, Router parent) {
    super(dirName, parent);
  }

}
