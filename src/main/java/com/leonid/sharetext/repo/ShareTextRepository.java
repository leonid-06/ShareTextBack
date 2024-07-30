package com.leonid.sharetext.repo;

import com.leonid.sharetext.model.AccessStatus;
import com.leonid.sharetext.model.TextBox;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ShareTextRepository {
    private final List<TextBox> textBoxes = new ArrayList<>();

    public List<TextBox> getPublicTextBoxesAndAlive(int limit) {
        LocalDateTime now = LocalDateTime.now();
        return textBoxes.stream()
                .filter(tb -> tb.getStatus() == AccessStatus.PUBLIC)
                .filter(tb -> tb.getLifetime().isAfter(now))
                .limit(limit)
                .toList();
    }

    public Optional<TextBox> getTextBoxByHash(String hash) {
        return textBoxes.stream()
                .filter(tb -> tb.getHash().equals(hash) && tb.getLifetime().isAfter(LocalDateTime.now()))
                .findFirst();
    }

    public void addTextBox(TextBox textBox) {
        textBoxes.add(textBox);
    }
}
