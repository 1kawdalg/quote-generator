package com.onekg.quotegenerator.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "quotes")
@Getter
@Setter
@ToString(exclude = "user")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Quote extends BaseEntity {

    @Column(name = "text", nullable = false, columnDefinition = "TEXT")
    private String text;

    @Column(name = "category", nullable = false)
    private String category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
