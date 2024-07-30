package com.leonid.sharetext.model;

import lombok.Data;

@Data
public class TextBoxRequest {
    private String text;
    private long expirationTimeSeconds;
    public AccessStatus status;
}
