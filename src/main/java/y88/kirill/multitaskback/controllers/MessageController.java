package y88.kirill.multitaskback.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import y88.kirill.multitaskback.dtos.MessageChat;
import y88.kirill.multitaskback.dtos.MessageDTO;
import y88.kirill.multitaskback.dtos.MessageDTOFull;
import y88.kirill.multitaskback.models.Message;
import y88.kirill.multitaskback.models.User;
import y88.kirill.multitaskback.services.MessageService;
import y88.kirill.multitaskback.services.UserService;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/messages")
public class MessageController {

    private final MessageService messageService;
    private final UserService userService;

    @GetMapping
    public List<MessageDTO> getAll() {
        return messageService.findAll()
                .stream()
                .map(MessageDTO::new)
                .collect(Collectors.toList());
    }



    @GetMapping("/userFrom")
    public List<MessageDTO> getAllByUserFromId(@RequestParam Long userFromId) {
        return messageService.findAllByUserFromId(userFromId)
                .stream()
                .map(MessageDTO::new)
                .collect(Collectors.toList());
    }

    //TODO метод под оптимизацию, однозначно))
    @GetMapping("/userTo")
    public List<MessageDTOFull> getAllByUserToId(@RequestParam Long userToId) {
        List<MessageDTO> messageDTOListUserTo = messageService.findAllByUserToId(userToId)
                .stream()
                .map(MessageDTO::new)
                .collect(Collectors.toList());

        Set<Long> userFromIdSet = messageDTOListUserTo
                .stream()
                .map(messageDTO -> {
                    return messageDTO.getUserFromId();
                })
                .collect(Collectors.toSet());

         List<MessageDTOFull> messageDTOFullList = userFromIdSet.stream()
                        .map(userFromId ->{
                            MessageDTOFull messageDTOFull = new MessageDTOFull();
                            messageDTOFull.setUserFromId(userFromId);
                            messageDTOFull.setUserFromLogin(userService.findById(userFromId).get().getLogin());
                            List<MessageDTO> temp = new ArrayList<>();
                            for (MessageDTO m: messageDTOListUserTo ) {
                                if(m.getUserFromId()==userFromId){
                                    temp.add(m);
                                }
                            }
                            messageDTOFull.setMessageDTO(temp);
                            return messageDTOFull;
                        })
                        .collect(Collectors.toList());

        List<MessageDTO> messageDTOListUserFrom = messageService.findAllByUserFromId(userToId)
                .stream()
                .map(MessageDTO::new)
                .collect(Collectors.toList());

        userFromIdSet.stream().forEach(System.out::println);
        return messageDTOFullList;
    }

    @PostMapping("/sendMsg")
    public MessageDTO sendMsg (@RequestBody MessageDTO messageDTO){
        System.out.println("---" + messageDTO.getUserFromId() + messageDTO.getUserToId() + messageDTO.getMsg());
        return new MessageDTO(messageService.insertMessage(messageDTO.getUserFromId(), messageDTO.getUserToId(), messageDTO.getMsg()).get());
    }

    @PostMapping("/updateMsg")
    public void updateMsg (@RequestBody MessageDTO messageDTO){
        messageService.updateMessage(messageDTO.getId(), messageDTO.getDownloaded(), messageDTO.getRead());
    }

    @PostMapping("/updateMsgList")
    public void updateMsgList (@RequestBody List<MessageDTO> messageDTOList){
        for (MessageDTO messageDTO: messageDTOList) {
            System.out.printf("id =%d , downloaded = %b, read = %b", messageDTO.getId(), messageDTO.getDownloaded(), messageDTO.getRead());
            messageService.updateMessage(messageDTO.getId(), messageDTO.getDownloaded(), messageDTO.getRead());
        }
    }

    @GetMapping("/countUserTo")
    public List<MessageDTOFull> getCountByUserToId(@RequestParam Long userToId,
                                                   @RequestParam int count){

        List<MessageDTO> messageDTOList = messageService.findCountByUserToId(userToId,count)
                .stream()
                .map(MessageDTO::new)
                .collect(Collectors.toList());

        Set<Long> userFromIdSet = messageDTOList
                .stream()
                .map(messageDTO -> {
                    return messageDTO.getUserFromId();
                })
                .collect(Collectors.toSet());

        List<MessageDTOFull> messageDTOFullList = userFromIdSet.stream()
                .map(userFromId ->{
                    MessageDTOFull messageDTOFull = new MessageDTOFull();
                    messageDTOFull.setUserFromId(userFromId);
                    messageDTOFull.setUserFromLogin(userService.findById(userFromId).get().getLogin());
                    List<MessageDTO> temp = new ArrayList<>();
                    for (MessageDTO m: messageDTOList ) {
                        if(m.getUserFromId()==userFromId){
                            temp.add(m);
                        }
                    }
                    messageDTOFull.setMessageDTO(temp);
                    return messageDTOFull;
                })
                .collect(Collectors.toList());

        return messageDTOFullList;
    }

    @GetMapping("/userChats")
    public List<MessageChat> getAllMessageChatByUserId(@RequestParam Long userId){
        List<MessageDTO> messageDTOList = messageService.findAllByUser(userId).stream()
                .map(MessageDTO::new)
                .collect(Collectors.toList());

        List<MessageDTO> messageDTOListTo = messageService.findAllByUserToId(userId).stream()
                .map(MessageDTO::new)
                .collect(Collectors.toList());
        messageDTOListTo.stream().forEach(System.out::println);

        Set<Long> userToIdSet = messageDTOListTo
                .stream()
                .map(messageDTO -> {
                    return messageDTO.getUserFromId();
                })
                .collect(Collectors.toSet());
        userToIdSet.stream().forEach(System.out::println);

        List<MessageDTO> messageDTOListFrom = messageService.findAllByUserFromId(userId).stream()
                .map(MessageDTO::new)
                .collect(Collectors.toList());
        messageDTOListFrom.stream().forEach(System.out::println);


        Set<Long> userFromIdSet  = messageDTOListFrom
                .stream()
                .map(messageDTO -> {
                    return messageDTO.getUserToId();
                })
                .collect(Collectors.toSet());
        userFromIdSet.stream().forEach(System.out::println);

        Set<Long> chatSetUserId = new HashSet<>();
        for (Long id: userToIdSet ) {
            chatSetUserId.add(id);
        }
        for (Long id: userFromIdSet ) {
            chatSetUserId.add(id);
        }

        chatSetUserId.stream().forEach(System.out::println);

        List<MessageChat> messageChatList = new ArrayList<>();
        for (Long id:chatSetUserId ) {
            User user = userService.findById(id).orElseThrow();
            messageChatList.add(new MessageChat(user.getId(), user.getLogin()));
        }

        return messageChatList;
    }

    @GetMapping("/userChatMessages")
    public List<MessageDTO> getAllMessagesChat(@RequestParam Long userIdThis,@RequestParam Long userIdThat ){
        return   messageService.findAllByUserChat(userIdThis, userIdThat ).stream()
                .map(MessageDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/userChatDontReadMessages")
    public List<MessageDTO> getAllDontReadByUserChat(@RequestParam Long userIdThis,@RequestParam Long userIdThat ){
        return   messageService.findAllDontReadByUserChat(userIdThis, userIdThat ).stream()
                .map(MessageDTO::new)
                .collect(Collectors.toList());
    }

}