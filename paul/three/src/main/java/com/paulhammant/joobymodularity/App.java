package com.paulhammant.joobymodularity;

import com.paulhammant.joobymodularitydemodiretory.BooksDir;
import com.paulhammant.joobymodularitydemodomain.MusicDomain;
import org.jooby.Dir;
import org.jooby.Domain;
import org.jooby.Err;
import org.jooby.Jooby;
import org.jooby.Router;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author jooby generator
 */
public class App extends Jooby {

  // no-args constructor ...
  {
    domain(new Domain("books.localhost", this) {{
      dir(new BooksDir("dune", this));
    }});
    domain(new MusicDomain("music.localhost", this));
    err(404, (req, rsp, ex) -> {
      rsp.status(404);
      rsp.send("nope");
    });
    port(8432);
  }

  public static void main(final String[] args) {
    run(App::new, args);
  }
}
