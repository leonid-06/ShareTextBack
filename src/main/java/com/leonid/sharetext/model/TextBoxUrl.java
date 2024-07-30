package com.leonid.sharetext.model;

import lombok.Data;

@Data
public class TextBoxUrl {
    private String url;

    public TextBoxUrl(String hostname, String hash) {
        this.url = hostname + "/" + hash;
    }
}
