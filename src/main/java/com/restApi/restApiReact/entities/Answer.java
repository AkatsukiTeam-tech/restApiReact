package com.restApi.restApiReact.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_answer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long ansId;

    @ManyToOne(fetch = FetchType.EAGER)
    private CardTasks ansTask;

    @Column(name = "task_text")
    private String ansText;

    @ManyToOne(fetch = FetchType.EAGER)
    private Users ansUser;

    @Column(name = "addedDate")
    private Date ansDate;

    @Column(name = "mark")
    private int mark;
}
