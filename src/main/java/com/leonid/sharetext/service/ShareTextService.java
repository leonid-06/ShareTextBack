package com.leonid.sharetext.service;

import com.leonid.sharetext.exception.ResourceNotFoundException;
import com.leonid.sharetext.model.TextBox;
import com.leonid.sharetext.model.TextBoxRequest;
import com.leonid.sharetext.model.TextBoxResponse;
import com.leonid.sharetext.model.TextBoxUrl;
import com.leonid.sharetext.repo.ShareTextRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class ShareTextService {
    private final ShareTextRepository shareTextRepository;
    private final AtomicInteger idGenerator = new AtomicInteger(100);
    private String hostname = "localhost:8080";
    private int limit = 10;

    public TextBoxUrl create(TextBoxRequest textBoxRequest) {
        TextBox textBox = new TextBox();
        textBox.setText(textBoxRequest.getText());
        textBox.setStatus(textBoxRequest.getStatus());
        textBox.setHash(generateHash());
        textBox.setLifetime(LocalDateTime.now().plusSeconds(textBoxRequest.getExpirationTimeSeconds()));
        shareTextRepository.addTextBox(textBox);
        return new TextBoxUrl(hostname, textBox.getHash());
    }

    private String generateHash() {
        return Integer.toHexString(idGenerator.incrementAndGet());
    }

    public List<TextBoxResponse> getPublicTextBoxesAndAlive() {
        List<TextBox> list = shareTextRepository.getPublicTextBoxesAndAlive(limit);
        return list.stream().map(tb -> new TextBoxResponse(tb.getText(), tb.getStatus())).toList();
    }

    public Optional<TextBoxResponse> getTextBoxByHash(String hash) {
        Optional<TextBox> textBoxOptional = shareTextRepository.getTextBoxByHash(hash);
        return textBoxOptional.map(textBox ->
                new TextBoxResponse(textBox.getText(), textBox.getStatus())
        );
    }
}
