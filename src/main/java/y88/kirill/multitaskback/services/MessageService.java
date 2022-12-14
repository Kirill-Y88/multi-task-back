package y88.kirill.multitaskback.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import y88.kirill.multitaskback.models.Message;
import y88.kirill.multitaskback.repositories.MessageRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public List<Message> findAll(){
        return messageRepository.findAll();
    }

    public List<Message> findAllByUserFromId(Long user_id_from){
        return messageRepository.findAllByUserFromId(user_id_from);
    }



    public List<Message> findAllByUserToId(Long user_id_to){
        return messageRepository.findAllByUserToId(user_id_to);
    }

    public int insertMessage( Long user_id_from, Long user_id_to, String msg){
        return messageRepository.insertMessage(user_id_from,user_id_to,msg);
    }

    public int updateMessage( Long id, Boolean downloaded, Boolean read){
        return messageRepository.updateMessage(id,downloaded,read);
    }

    public List<Message> findCountByUserToId(Long user_id_to, int count){
        return messageRepository.findCountByUserToId(user_id_to, count);
    }

    public List<Message> findAllByUser(Long user_id){
        return messageRepository.findAllByUser(user_id);
    }

    public List<Message> findAllByUserChat(Long user_id_this, Long user_id_that){
        return messageRepository.findAllByUserChat(user_id_this,user_id_that);
    }

}
