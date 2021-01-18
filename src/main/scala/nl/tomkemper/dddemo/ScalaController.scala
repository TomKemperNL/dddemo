package nl.tomkemper.dddemo

import org.springframework.web.bind.annotation.{GetMapping, RestController}

@RestController
class ScalaController {

  @GetMapping(path = Array("/scala"))
  def hello(): String = {
    "hello Scala"
  }
}
