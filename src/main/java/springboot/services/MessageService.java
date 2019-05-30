package springboot.services;

import springboot.controllers.DTO.MessageDTO;
import springboot.models.Message;

import java.util.List;

public interface MessageService {

    List<Message> listAllMessages();
    Message save(MessageDTO msg);
}
