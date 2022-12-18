package y88.kirill.multitaskback.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import y88.kirill.multitaskback.models.Message;
import y88.kirill.multitaskback.repositories.MessageRepository;

import java.util.List;
import java.util.Optional;

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

    public Optional<Message> insertMessage(Long user_id_from, Long user_id_to, String msg){
        int operation = messageRepository.insertMessage(user_id_from,user_id_to,msg);
        return messageRepository.findLastMsgUserIdFrom(user_id_from);
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

    public List<Message> findAllDontReadByUserChat(Long user_id_this, Long user_id_that){
        return messageRepository.findAllDontReadByUserChat(user_id_this,user_id_that);
    }

}
