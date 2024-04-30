package com.example.technicaltask.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Entity
@Builder
@Document(collection = "user")
public class User {

    @Id
    @SequenceGenerator(name = "id_seq", sequenceName = "id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "sequence")
    @Column(name = "id")
    @JsonIgnore
    private String id;

    @Column(name = "email")
    private String email;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "birthDate")
    private LocalDate birthDate;

    @Nullable
    @Column(name = "address")
    private String address;

    @Nullable
    @Column(name = "phoneNumber")
    private String phoneNumber;

}
