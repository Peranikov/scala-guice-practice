import javax.inject.Inject

import com.google.inject.{AbstractModule, Guice, Injector}

trait MessageService {
  def sendMessage(msg: String, receipient: String): Boolean
}

class EmailService extends MessageService{
  def sendMessage(msg: String, receipient: String): Boolean = {
    //some fancy code to send email
    println(s"Email Message sent to $receipient with message= $msg")
    true
  }
}

class FacebookService extends MessageService {
  def sendMessage(msg: String, receipient: String): Boolean = {
    //some complex code to send Facebook message
    println(s"Message sent to Facebook user $receipient with message= $msg")
    true
  }
}

class AppInjector extends AbstractModule {

  override protected def configure {
        //bind the service to implementation class
        //bind(MessageService.class).to(EmailService.class);

        //bind MessageService to Facebook Message implementation
        bind(classOf[MessageService]).to(classOf[FacebookService]);
    }
}

class MyApplication {
  var service: MessageService = null

  @Inject
  def setService(svc: MessageService) {
    service = svc
  }

  def sendMessage(msg: String, rec: String): Boolean = {
    //some business logic here
    service.sendMessage(msg, rec)
  }
}

object ClientApplication extends App {
  val injector: Injector = Guice.createInjector(new AppInjector())
  val app = injector.getInstance(classOf[MyApplication])
  app.sendMessage("Hi Pankaj", "pankaj@abc.com")
}