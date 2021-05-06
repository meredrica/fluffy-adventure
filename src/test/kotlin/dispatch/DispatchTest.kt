package dispatch

import io.micronaut.runtime.EmbeddedApplication
import javax.inject.Inject
import org.junit.jupiter.api.Assertions

// @MicronautTest
class DispatchTest {

  @Inject lateinit var application: EmbeddedApplication<*>

  // @Test
  fun testItWorks() {
    Assertions.assertTrue(application.isRunning)
  }
}
