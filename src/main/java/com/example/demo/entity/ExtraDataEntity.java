package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="extradata")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ExtraDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer interaccion=0;
    private Integer aforum=0;
    private Integer actual=0;
    private Integer male=0;
    private Integer female=0;
    private float averageAge=0.0f;

    @OneToOne
    @JoinColumn(name = "event_id",referencedColumnName = "id")
    private Event event;
}
