package com.paulhammant.joobymodularitydemodiretory;

import org.jooby.Dir;
import org.jooby.Router;

public class BooksDir extends Dir {

    {
        get("/", () -> "What a great novel, thanks Frank Herbert, RIP");
    }

    public BooksDir(String dune, Router parent) {
        super(dune, parent);
    }
}
