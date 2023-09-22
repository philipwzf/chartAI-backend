package com.yupi.springbootinit.manager;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.exception.BusinessException;


import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GptManager {
    final static String API_KEY = "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";
    final static String aiPrompt = "You are a data analyst and front-end developer expert. I will provide you content in the following fixed format:\n" +
            "Goal:\n" +
            "{Data analysis goal}\n" +
            "Data:\n" +
            "{Raw data in csv format, use comma as separator}\n" +
            "Based on these two parts, please generate content in the following specified format (do not output any extra words including comments):\n" +
            "!!!!\n" +
            "{Front-end Echarts V5 option configuration object js code, reasonably visualize the data, do not generate any extra comments. do not generate the ]]]] at the end}\n" +
            "!!!!\n" +
            "{Clear data analysis conclusion, the more details the better, do not generate extra comments}";
    final static String sampleStr = "Goal: Analyze the user trend\\nData: Date,User Count\\n1,10\\n2,20\\n3,30\\n\\n";
    final static String sampleResponseStr = "!!!!\n" +
            "{\n" +
            "  'title': {\n" +
            "    'text': 'User Trend',\n" +
            "    'x': 'center'\n" +
            "  },\n" +

            "  'xAxis': {\n" +
            "    'type': 'category',\n" +
            "    'data': ['1', '2', '3']\n" +
            "  },\n" +
            "  'yAxis': {\n" +
            "    'type': 'value'\n" +
            "  },\n" +
            "  'series': [{\n" +
            "    'data': [10, 20, 30],\n" +
            "    'type': 'line'\n" +
            "  }]\n" +
            "}\n" +
            "!!!!\n" +
            "By analyzing the provided data, it can be observed that the user count on the website has been increasing over time.";


    public static String doChat(String inputStr) {
        String token = API_KEY;
        if(token == null){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"API not connected to ChatGPT");
        }
        OpenAiService service = new OpenAiService(token, Duration.ofSeconds(30));

        final List<ChatMessage> messages = new ArrayList<>();

        //adding the prompt
        final ChatMessage systemMessage = new ChatMessage(ChatMessageRole.SYSTEM.value(), aiPrompt);
        messages.add(systemMessage);

        //adding the sample data
        final ChatMessage sample = new ChatMessage(ChatMessageRole.USER.value(),sampleStr);
        messages.add(sample);

        //adding the sample response
        final ChatMessage sample_res = new ChatMessage(ChatMessageRole.ASSISTANT.value(),sampleResponseStr);
        messages.add(sample_res);

        ChatMessage input = new ChatMessage(ChatMessageRole.USER.value(),inputStr);
        messages.add(input);

        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                .builder()
                .model("gpt-3.5-turbo")
                .messages(messages)
                .n(1)
                .maxTokens(1000)
                .logitBias(new HashMap<>())
                .build();

        ChatCompletionResult chatCompleteResult = service.createChatCompletion(chatCompletionRequest);

        if(chatCompleteResult == null){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"AI Response Error");
        }
        service.shutdownExecutor();
        return chatCompleteResult.getChoices().get(0).getMessage().getContent();
    }

}