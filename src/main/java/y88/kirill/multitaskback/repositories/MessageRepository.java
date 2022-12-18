package y88.kirill.multitaskback.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import y88.kirill.multitaskback.models.Message;
import y88.kirill.multitaskback.models.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface MessageRepository extends JpaRepository<Message, Long> {


    @Query(value = "SELECT * FROM messages WHERE user_id_from = :user_id_from", nativeQuery = true)
    List<Message> findAllByUserFromId(Long user_id_from);

    @Query(value = "SELECT * FROM messages WHERE user_id_to = :user_id_to", nativeQuery = true)
    List<Message> findAllByUserToId(Long user_id_to);

    @Query(value = "SELECT * FROM messages WHERE user_id_to = :user_id_to and downloaded = :downloaded", nativeQuery = true)
    List<Message> findAllByUserToIdByDownloaded(Long user_id_to, boolean downloaded);

//    @Query(value = "SELECT * FROM messages WHERE user_id_to = :user_id_to and downloaded = :downloaded", nativeQuery = true)
//    List<Message> findDistinctByUserFromAndUserTo(Long user_id_to);

    @Modifying
    @Query(value = "INSERT into messages (user_id_from, user_id_to, msg) VALUES (:user_id_from,:user_id_to,:msg)", nativeQuery = true)
    int insertMessage(@Param("user_id_from") Long user_id_from,
                   @Param("user_id_to")Long user_id_to,
                   @Param("msg")String msg);

    @Modifying
    @Query(value = "UPDATE messages set downloaded = :downloaded, read = :read WHERE id = :id", nativeQuery = true)
    int updateMessage(@Param("id") Long id,
                      @Param("downloaded") Boolean downloaded,
                      @Param("read")Boolean read);


    @Query(value =
            "SELECT * FROM ( select * from messages where user_id_to = :user_id_to order by id desc  limit :count) order by id ",
            nativeQuery = true)
    List<Message> findCountByUserToId(@Param("user_id_to") Long user_id_to,
                                      @Param("count") int count);



    //поиск всех отправленных и полученных юзером сообщений
    @Query(value =
            "SELECT * FROM MESSAGES where user_id_to = :user_id union SELECT * FROM MESSAGES where user_id_from = :user_id ",
            nativeQuery = true)
    List<Message> findAllByUser(@Param("user_id") Long user_id);

    //поиск всех отправленных и полученных юзером сообщений для одного чата
    @Query(value =
            "(SELECT * FROM MESSAGES where user_id_to = :user_id_this and user_id_from = :user_id_that  union SELECT * FROM MESSAGES where user_id_from = :user_id_this and user_id_to = :user_id_that)",
            nativeQuery = true)
    List<Message> findAllByUserChat(@Param("user_id_this") Long user_id_this,
                                    @Param("user_id_that") Long user_id_that);


    //поиск всех отправленных и полученных юзером сообщений для одного чата
    @Query(value =
            "(SELECT * FROM MESSAGES where user_id_to = :user_id_this and user_id_from = :user_id_that  union SELECT * FROM MESSAGES where user_id_from = :user_id_this and user_id_to = :user_id_that)",
            nativeQuery = true)
    List<Message> findAllByUserChat2222222(@Param("user_id_this") Long user_id_this,
                                    @Param("user_id_that") Long user_id_that);

    @Query(value =
            "(SELECT * FROM MESSAGES where user_id_from = :user_id_this  order by id desc limit 1)",
            nativeQuery = true)
    Optional<Message> findLastMsgUserIdFrom(@Param("user_id_this") Long user_id_this);

    @Query(value =
            "(SELECT * FROM MESSAGES where user_id_to = :user_id_this and user_id_from = :user_id_that  and read = false)",
            nativeQuery = true)
    List<Message> findAllDontReadByUserChat(@Param("user_id_this") Long user_id_this,
                                            @Param("user_id_that") Long user_id_that);


}
