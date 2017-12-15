package com.paulhammant.joobymodularity;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import org.jooby.test.JoobyRule;
import org.jooby.test.MockRouter;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * @author jooby generator
 */
public class AppTest {

  /**
   * One app/server for all the test of this class. If you want to start/stop a new server per test,
   * remove the static modifier and replace the {@link ClassRule} annotation with {@link Rule}.
   */
  @ClassRule
  public static JoobyRule app = new JoobyRule(new App());

  @Test
  public void integrationTests() {

    get("http://music.localhost:8432/beatles/").then().assertThat().body(equalTo("Great band from the 60's. Sadly long split up"));
    get("http://music.localhost:8432/beatles/members/").then().assertThat().body(equalTo("John, Paul, George, Ringo"));
    get("http://music.localhost:8432/beatles/members/john").then().assertThat().body(equalTo("singer, songwriter, musician, and activist"));
    get("http://music.localhost:8432/beatles/members/stats/still-with-us").then().assertThat().body(equalTo("2 of 4"));

    get("http://books.localhost:8432/dune").then().assertThat().body(equalTo("What a great novel, thanks Frank Herbert, RIP"));

  }

}
