package com.softtech.tdp.controller;

import com.softtech.tdp.dto.MessageResource;
import com.softtech.tdp.dto.SaveMessageResource;
import com.softtech.tdp.model.AssignmentKey;
import com.softtech.tdp.model.Message;
import com.softtech.tdp.service.IMessageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MessageController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private IMessageService messageService;

    @PostMapping("/patients/{patientId}/specialists/{specialistId}/messages")
    public MessageResource createPatientMessage(@PathVariable(name = "patientId") Integer patientId,
                                                @PathVariable(name = "specialistId") Integer specialistId,
                                                @Valid @RequestBody SaveMessageResource resource){
        return convertToResource(messageService.createPatientMessage(patientId, specialistId,
                convertToEntity(resource)));
    }

    @PostMapping("/specialists/{specialistId}/patients/{patientId}/messages")
    public MessageResource createSpecialistMessage(@PathVariable(name = "patientId") Integer patientId,
                                                @PathVariable(name = "specialistId") Integer specialistId,
                                                @Valid @RequestBody SaveMessageResource resource){
        return convertToResource(messageService.createPatientMessage(patientId, specialistId,
                convertToEntity(resource)));
    }

    @GetMapping("messages/{messageId}")
    public MessageResource getMessageById(@PathVariable(name = "messageId") Integer messageId){
        return convertToResource(messageService.getById(messageId));
    }

    @GetMapping("/messages")
    public Page<MessageResource> getAllMessages(Pageable pageable){
        Page<Message> messages = messageService.getAllMessages(pageable);
        List<MessageResource> resourceList = messages.getContent().stream().map(this::convertToResource).
                collect(Collectors.toList());
        return new PageImpl<>(resourceList, pageable, resourceList.size());
    }

    @PutMapping("messages/{messageId}")
    public MessageResource updateMessage(@PathVariable(name = "messageId") Integer messageId,
                                         @Valid @RequestBody SaveMessageResource resource){
        return convertToResource(messageService.updateMessage(messageId, convertToEntity(resource)));
    }

    @DeleteMapping("messages/{messageId}")
    public ResponseEntity<?> deleteMessage(@PathVariable(name = "messageId") Integer messageId){
        return messageService.deleteMessage(messageId);
    }

    private Message convertToEntity(SaveMessageResource resource){ return mapper.map(resource, Message.class); }
    private MessageResource convertToResource(Message entity){ return mapper.map(entity, MessageResource.class); }
}
