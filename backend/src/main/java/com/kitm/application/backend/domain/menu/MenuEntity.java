package com.kitm.application.backend.domain.menu;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MenuEntity {
    @Id
    @GeneratedValue()
    @Column(updatable = false, nullable = false, length = 16)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;
}
