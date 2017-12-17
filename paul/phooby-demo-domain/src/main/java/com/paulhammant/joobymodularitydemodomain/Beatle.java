package com.paulhammant.joobymodularitydemodomain;

import java.time.Duration;
import java.util.Date;

public class Beatle {

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
