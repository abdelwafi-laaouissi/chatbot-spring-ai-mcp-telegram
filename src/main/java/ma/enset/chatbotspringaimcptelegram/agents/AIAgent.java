package ma.enset.chatbotspringaimcptelegram.agents;

import org.springframework.ai.chat.client.ChatClient;

import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class AIAgent {
    private ChatClient chatClient;

    public AIAgent(ChatClient.Builder builder , ChatMemory memory, ToolCallbackProvider tools) {
        this.chatClient = builder
                .defaultSystem("""
                        Vous un assistant qui se charge de répondre aux question de l'utilisateur en fonction du contexte fourni.
                        Si aucun contexte n'est fourni répond avec **** JE NE SAIS PAS ****
                        """)
                .defaultAdvisors(
                        MessageChatMemoryAdvisor.builder(memory).build())
                .defaultToolCallbacks(tools)
                .build();
    }

    public String askAgent(String query) {
        return chatClient.prompt()
                .user(query)
                .call().content();
    }
}
