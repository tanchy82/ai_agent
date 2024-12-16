package com.oldtan.aiagent

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class AiAgentApp

object AiAgentApp extends App {
  SpringApplication.run(classOf[AiAgentApp], args: _*)
}
