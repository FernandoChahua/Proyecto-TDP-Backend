package com.softtech.tdp.service.impl;

import com.softtech.tdp.exception.ResourceNotFoundException;
import com.softtech.tdp.model.*;
import com.softtech.tdp.repository.AppUserRepository;
import com.softtech.tdp.repository.AssignmentRepository;
import com.softtech.tdp.repository.MessageRepository;
import com.softtech.tdp.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class MessageServiceImpl implements IMessageService {
    @Autowired
    MessageRepository messageRepository;

    @Autowired
    AssignmentRepository assignmentRepository;

    @Autowired
    AppUserRepository appUserRepository;

    public Message createPatientMessage(Integer patientId, Integer specialistId, Message message){
        if (!assignmentRepository.existsByPatientIdPatientAndSpecialistIdSpecialist(patientId, specialistId)){
            throw new ResourceNotFoundException("Assignment not found with Patient Id "+patientId+" and " +
                    "Specialist Id "+specialistId);
        }
        else {
            message.setSentBy(AppUserRole.PATIENT);
            message.setRegisterDate(LocalDate.now());
            Assignment assignment = assignmentRepository.findByPatientIdPatientAndSpecialistIdSpecialist(patientId, specialistId);
            message.setAssignment(assignment);
            return messageRepository.save(message);
        }
    }

    @Override
    public Message createSpecialistMessage(Integer patientId, Integer specialistId, Message message) {
        if (!assignmentRepository.existsByPatientIdPatientAndSpecialistIdSpecialist(patientId, specialistId)){
            throw new ResourceNotFoundException("Assignment not found with Patient Id "+patientId+" and " +
                    "Specialist Id "+specialistId);
        }
        else {
            message.setSentBy(AppUserRole.SPECIALIST);
            message.setRegisterDate(LocalDate.now());
            return messageRepository.save(message);
        }
    }

    @Override
    public Message getById(Integer messageId) {
        return messageRepository.findById(messageId).
                orElseThrow(() -> new ResourceNotFoundException("Message", "Id", messageId));
    }

    @Override
    public Page<Message> getAllMessages(Pageable pageable) {
        return messageRepository.findAll(pageable);
    }

    @Override
    public Message updateMessage(Integer messageId, Message messageDetails) {
        Message message = messageRepository.findById(messageId).
                orElseThrow(() -> new ResourceNotFoundException("Message", "Id", messageId));
        message.setContent(messageDetails.getContent());
        message.setRegisterDate(messageDetails.getRegisterDate());
        return messageRepository.save(message);
    }

    @Override
    public ResponseEntity<?> deleteMessage(Integer messageId) {
        Message message = messageRepository.findById(messageId).
                orElseThrow(() -> new ResourceNotFoundException("Message", "Id", messageId));
        messageRepository.delete(message);
        return ResponseEntity.ok().build();
    }
}
