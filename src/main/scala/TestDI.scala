import javax.inject._

import com.google.inject.AbstractModule

trait Service {
  def doSomething(): Unit
}

class RealService extends Service {
  def doSomething() { println("This is RealService") }
}

class FakeService extends Service {
  def doSomething() { println("This is FakeService") }
}

class ServiceUser @Inject() (service: Service) {
  def use() { service.doSomething() }
}

class ServiceModule extends AbstractModule {
  override protected def configure() {
    bind(classOf[Service]).to(classOf[RealService])
  }
}

object TestDI extends App {
  import com.google.inject._
  val injector: Injector = Guice.createInjector(new ServiceModule)
  val user: ServiceUser = injector.getInstance(classOf[ServiceUser])
  user.use //=> "This is RealService"
}