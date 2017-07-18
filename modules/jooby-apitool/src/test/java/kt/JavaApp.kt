package kt

import org.jooby.*

class JavaApp : Kooby({
    put ("/java") {req ->
      req.param("p1").value
    }
})

fun main(args: Array<String>) {
    run(::HelloWorld, *args)
}

