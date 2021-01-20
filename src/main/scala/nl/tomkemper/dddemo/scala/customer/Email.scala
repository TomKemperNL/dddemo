package nl.tomkemper.dddemo.scala.customer

import scala.util.matching.Regex


final case class EmailAddress private(value: String)

object EmailAddress {
  val emailPattern = "^(.+)@(.+)$".r

  def parse(input: String): Option[EmailAddress] = {
    emailPattern.findFirstIn(input) match {
      case Some(raw) => Some(new EmailAddress(raw))
      case None => None
    }

    //of equivalent:
    //    emailPattern.findFirstIn(input)
    //      .map(e => new EmailAddress(e))
  }

  //De apply methode is standaard public en kan ook nieuwe EmailAdressen maken, door 'm private
  //te overschrijven verbergen we dat, zodat de parse methode echt de enige manier is om aan een email-adres te komen
  private def apply(value: String): EmailAddress = new EmailAddress(value)
}

sealed abstract class Email

final case class VerifiedEmail(email: EmailAddress) extends Email

final case class UnverifiedEmail(email: EmailAddress) extends Email

object Email {
  def verify(email: Email): Option[VerifiedEmail] = {
    email match {
      case VerifiedEmail(e) =>
        Some(VerifiedEmail(e))
      case UnverifiedEmail(e) =>
        println("Even doen alsof het een geaccepteerd email adres is...")
        Some(VerifiedEmail(e))
    }
  }
}
