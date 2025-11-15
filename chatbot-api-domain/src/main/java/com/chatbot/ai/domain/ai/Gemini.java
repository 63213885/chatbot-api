package com.chatbot.ai.domain.ai;

import java.io.IOException;

public interface Gemini {

    String doGemini(String question) throws IOException;
}
