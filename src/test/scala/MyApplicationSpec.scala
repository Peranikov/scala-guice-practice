import com.google.inject.{AbstractModule, Guice, Injector}
import org.specs2.mutable.Specification
import org.specs2.specification.{AfterExample, BeforeExample}

class MyApplicationSpec extends Specification with BeforeExample with AfterExample {
  var injector: Injector = null

  def before = {
    injector = Guice.createInjector(new AbstractModule {
      def configure {
        bind(classOf[MessageService]).to(classOf[MockMessageService])
      }
    })
  }

  def after = {
    injector = null
  }

  "Guice Spec" >> {
    val appTest = injector.getInstance(classOf[MyApplication])
    appTest.sendMessage("Hi Pankaj", "pankaj@abc.com") must beTrue
  }
}
