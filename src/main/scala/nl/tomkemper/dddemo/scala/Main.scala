package nl.tomkemper.dddemo.scala

import nl.tomkemper.dddemo.scala.customer.{Customer, EmailAddress, UnverifiedEmail, VerifiedEmail, Email}

object Main {
  def demo(customer: Customer): Unit ={
    customer.email match {
      case VerifiedEmail(e) =>
        print("verified:" + e.value)
      case UnverifiedEmail(e) =>
        print("unverified:" + e)
    }
  }

  def main(args: Array[String]): Unit = {
    //Hier omzeilen we expliciet Scalas veiligheid om het voorbeeld klein te houden (die get aan het eind is niet ok).
    //Maar stel je voor dat een groter stuk systeem er voor zorgt dat dit netjes gebeurd
    val e: EmailAddress = EmailAddress.parse("tom@summit.nl").get
    val verifiedEmail = Email.verify(UnverifiedEmail(e)).get

    //Want we willen hier puur een demo-customer in elkaar zetten.
    val c = new Customer(verifiedEmail)
    demo(c)
  }

}
