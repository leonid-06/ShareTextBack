package com.leonid.sharetext.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TextBox {
    private String text;
    private String hash;
    private LocalDateTime lifetime;
    private AccessStatus status;
}
