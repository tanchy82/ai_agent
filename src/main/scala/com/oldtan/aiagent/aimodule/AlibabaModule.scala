package com.oldtan.aiagent.aimodule

import com.alibaba.dashscope.aigc.generation.{Generation, GenerationParam}
import com.alibaba.dashscope.common.{Message, Role}
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

import java.util


@Component
class AlibabaModule {

  @Value("${ai.alibaba-key}") val apiKey:String = null

  def callWithMessage(requestMsg: String): String = {
    var responseMsg:String = null
    try {
      val systemBuilder = Message.builder()
      systemBuilder.role(Role.SYSTEM.getValue)
      systemBuilder.content("You are a helpful assistant.")

      val userBuilder = Message.builder()
      userBuilder.role(Role.USER.getValue)
      userBuilder.content(requestMsg)

      val generationParamBuilder = GenerationParam.builder()
      generationParamBuilder.apiKey(apiKey)
      generationParamBuilder.model("qwen-plus")
      generationParamBuilder.messages(util.Arrays.asList(systemBuilder.build(), userBuilder.build()))
      generationParamBuilder.resultFormat(GenerationParam.ResultFormat.MESSAGE)
      responseMsg = new Generation().call(generationParamBuilder.build().asInstanceOf[GenerationParam]).getOutput.getChoices.get(0).getMessage.getContent
    }catch {
      case e: Exception =>
        print(e.getCause)
        responseMsg = e.getMessage
    }
    responseMsg
  }
}
