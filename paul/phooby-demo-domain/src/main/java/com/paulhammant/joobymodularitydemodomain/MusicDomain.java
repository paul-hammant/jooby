package com.paulhammant.joobymodularitydemodomain;

import org.jooby.Dir;
import org.jooby.Domain;
import org.jooby.Router;

public class MusicDomain extends Domain {
    {
      dir (new Dir("beatles", this) {{
        dir(new BeatlesMembers("members", this));
        get("/", () -> "Great band from the 60's. Sadly long split up");
      }});
    }

    public MusicDomain(String domainName, Router parent) {
        super(domainName, parent);
    }
}
