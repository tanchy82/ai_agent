package com.oldtan.aiagent.chat;

import com.oldtan.aiagent.modle.AlibabaModle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    @Autowired
    private AlibabaModle alibabaModle;

    @GetMapping("/chatJava")
    public String chat(String prompt) throws Exception{
        return alibabaModle.callWithMessage(prompt);
    }

}
