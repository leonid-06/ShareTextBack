package com.leonid.sharetext.controller;

import com.leonid.sharetext.exception.ResourceNotFoundException;
import com.leonid.sharetext.model.TextBoxRequest;
import com.leonid.sharetext.model.TextBoxResponse;
import com.leonid.sharetext.model.TextBoxUrl;
import com.leonid.sharetext.service.ShareTextService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ShareTextController {

    private final ShareTextService shareTextService;

    @GetMapping("/")
    public List<TextBoxResponse> getTextBoxes(){
        return shareTextService.getPublicTextBoxesAndAlive();
    }

    @GetMapping("/{hash}")
    public ResponseEntity<?> getTextBox(@PathVariable String hash){
        Optional<TextBoxResponse> response = shareTextService.getTextBoxByHash(hash);
        if (response.isEmpty()) {
            throw new ResourceNotFoundException("Resource with hash " + hash + " not found");
        }
        return ResponseEntity.ok(response.get());

    }

    @PostMapping("/")
    public TextBoxUrl addTextBox(@RequestBody TextBoxRequest textBoxRequest){
        return shareTextService.create(textBoxRequest);
    }
}
