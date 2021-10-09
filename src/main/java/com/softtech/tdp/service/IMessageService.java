package com.softtech.tdp.service;
import com.softtech.tdp.model.AppUserRole;
import com.softtech.tdp.model.Message;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface IMessageService{
    Message createPatientMessage(Integer patientId, Integer specialistId, Message message);
    Message createSpecialistMessage(Integer patientId, Integer specialistId, Message message);
    
    Message createMessage(Integer patientId,Integer specialistId,AppUserRole role,Message message);
    //Message getByIdAndPatientIdAndSpecialistId(Integer messageId, Integer patientId, Integer specialistId);
    Message getById(Integer messageId);
    Page<Message> getAllMessages(Pageable pageable);
    Message updateMessage(Integer messageId, Message message);
    ResponseEntity<?> deleteMessage(Integer messageId);
    
    List<Message> findAllBySpecialistAndPatient(Integer specialistId,Integer patientId); 
}
