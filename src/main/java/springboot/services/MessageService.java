package springboot.services;

import springboot.controllers.MessageDTO;
import springboot.models.Message;
import springboot.models.Post;

import java.util.List;

public interface MessageService {

    List<Message> listAllMessages();
    Message save(MessageDTO msg);
}
