package y88.kirill.multitaskback.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "messages")
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "user_id_from")
    private User userFrom;

    @ManyToOne()
    @JoinColumn(name = "user_id_to")
    private User userTo;

    @Column(name = "msg")
    private String msg;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "downloaded")
    private Boolean downloaded;

    @Column(name = "read")
    private Boolean read;

}
