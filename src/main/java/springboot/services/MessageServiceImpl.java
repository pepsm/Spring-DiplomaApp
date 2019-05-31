/*
package springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.controllers.dto.MessageDTO;
import springboot.models.Message;
import springboot.repositories.MessageRepository;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public List<Message> listAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public Message save(MessageDTO msg) {
        Message m = new Message();
        m.setContent(msg.getContent());
        return messageRepository.save(m);
    }
}
*/
