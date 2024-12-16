package com.oldtan.aiagent.api

import com.oldtan.aiagent.aimodule.AlibabaModule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{GetMapping, RestController}

@RestController
class ChatApi {
    @Autowired
    val alibabaModule: AlibabaModule = null

    @GetMapping(value = Array("/cha"))
    def chat(prompt: String): String = alibabaModule.callWithMessage(prompt)
}
