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
    def messageBuilders(role: String, content: String): Message.MessageBuilder[_, _] = {
      val builder = Message.builder()
      builder.role(role)
      builder.content(content)
      builder
    }
    try {
      val generationParamBuilder = GenerationParam.builder()
      generationParamBuilder.apiKey(apiKey)
      generationParamBuilder.model("qwen-plus")
      generationParamBuilder.messages(util.Arrays.asList(
        messageBuilders(Role.SYSTEM.getValue, "You are a helpful assistant.").build(),
        messageBuilders(Role.USER.getValue, requestMsg).build()))
      generationParamBuilder.resultFormat(GenerationParam.ResultFormat.MESSAGE)
      responseMsg = new Generation().call(generationParamBuilder.build().asInstanceOf[GenerationParam]).getOutput.getChoices.get(0).getMessage.getContent
    }catch {
      case e: Exception =>
        println(e.getMessage, e.getCause)
        println("请参考文档：https://help.aliyun.com/zh/model-studio/developer-reference/error-code")
        responseMsg = e.getMessage
    }
    responseMsg
  }
}