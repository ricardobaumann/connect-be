package com.nabagagem.connectbe.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "account", indexes = {
        @Index(columnList = "created_by"),
        @Index(columnList = "modified_by")
})
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotNull
    @Column(unique = true, nullable = false)
    private String userId;
    private String firstName;

    private String lastName;

    @OneToMany
    @RestResource
    @JoinColumn(name = "account_id")
    private Set<Address> addresses;

    @OneToMany
    @RestResource
    @JoinColumn(name = "account_id")
    private Set<Gig> gigs;

    @Embedded
    @Builder.Default
    private Audit audit = new Audit();

    @RestResource
    @OneToMany
    @JoinColumn(name = "account_id")
    private Set<ConnectProfile> connectProfiles;
}