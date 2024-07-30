package com.leonid.sharetext.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TextBoxResponse {
    private String text;
    public AccessStatus status;
}
