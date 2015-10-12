class MockMessageService extends MessageService {
  def sendMessage(msg: String, receipient: String): Boolean = {
    true
  }
}
