package com.restApi.restApiReact.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_cardTask")
public class CardTasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Cards card;

    @Column(name = "task_text")
    private String taskText;

    @Column(name = "task_desc")
    private String taskDesc;

    @Column(name = "addedDate")
    private Date addedTaskDate;

    @Column(name = "done")
    private boolean done;
}
