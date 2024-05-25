package dev.zio.quickstart.greet

import zio.*
import zio.test.*
import zio.http.*

object GreetingSuite extends ZIOSpecDefault:

  // Request => ZIO[Any, Option[Nothing], Response]
  val greetingRoutes: Routes[Any, Response] = GreetingRoutes()
  val nonEmptyNameGen: Gen[Any, String]     = Gen.string1(Gen.unicodeChar)

  val spec: Spec[Any, Any] =
    suite("GreetingApp")(
      test("/greet") {
        for
          response <- greetingRoutes.run(Request.get(URL.root / "greet"))
          body     <- response.body.asString
        yield assertTrue(body == "Hello World!")
      },
      test("/greet/:name") {
        check(nonEmptyNameGen) { name =>
          for
            response <- greetingRoutes.run(
              Request.get(URL.root / "greet" / name)
            )
            body <- response.body.asString
          yield assertTrue(body == s"Hello $name!")
        }
      },
      test("/greet?name=:name") {
        check(nonEmptyNameGen) { name =>
          val url =
            (URL.root / "greet").addQueryParams(QueryParams("name" -> name))
          for
            response <- greetingRoutes.run(
              Request.get(url)
            )
            body <- response.body.asString
          yield assertTrue(body == s"Hello $name!")
        }
      },
      test("/greet?name=:name1&name=:name2") {
        check(nonEmptyNameGen, nonEmptyNameGen) { (name1, name2) =>
          val url = (URL.root / "greet").addQueryParams(
            QueryParams("name" -> name1, "name" -> name2)
          )
          for
            response <- greetingRoutes.run(
              Request.get(url)
            )
            body <- response.body.asString
          yield assertTrue(body == s"Hello $name1 and $name2!")
        }
      }
    )
