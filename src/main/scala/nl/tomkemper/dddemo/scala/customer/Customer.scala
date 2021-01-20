package nl.tomkemper.dddemo.scala.customer

class Customer(contactInfo: Email) {
  var name: String = ""
  var email: Email = contactInfo
}
