package dispatch

import io.micronaut.rabbitmq.annotation.Queue
import io.micronaut.rabbitmq.annotation.RabbitListener
import org.slf4j.LoggerFactory

@RabbitListener
public class Listener {
  val log = LoggerFactory.getLogger(javaClass)

  @Queue("dispatch")
  fun listen(message: InternalMessage) {
    log.info("got $message")
  }
}
