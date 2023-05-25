package dev.zio.quickstart.greet

import zio.*
import zio.test.*
import zhttp.http.*

object GreetingSuite extends ZIOSpecDefault:

  val greetingApp = GreetingApp()

  val spec =
    suite("GreetingApp")(
      test("/greet") {
        for
          url      <- ZIO.fromEither(URL.fromString("/greet"))
          response <- greetingApp(Request(url = url))
          body     <- response.bodyAsString
        yield assertTrue(body == "Hello World!")
      },
      test("/greet/:name") {
        check(Gen.string) { name =>
          for
            url      <- ZIO.fromEither(URL.fromString(s"/greet/$name"))
            response <- greetingApp(Request(url = url))
            body     <- response.bodyAsString
          yield assertTrue(body == s"Hello $name!")
        }
      },
      test("/greet?name=:name") {
        check(Gen.string) { name =>
          for
            url      <- ZIO.fromEither(URL.fromString(s"/greet?name=$name"))
            response <- greetingApp(Request(url = url))
            body     <- response.bodyAsString
          yield assertTrue(body == s"Hello $name!")
        }
      },
      test("/greet?name=:name1&:name2") {
        check(Gen.string, Gen.string) { (name1, name2) =>
          for
            url <- ZIO.fromEither(
              URL.fromString(s"/greet?name=$name1&name=$name2")
            )
            response <- greetingApp(Request(url = url))
            body     <- response.bodyAsString
          yield assertTrue(body == s"Hello $name1 and $name2!")
        }
      }
    )
