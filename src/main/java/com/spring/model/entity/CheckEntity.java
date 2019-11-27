package com.spring.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Builder
@Table(name = "chec")
public class CheckEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Basic(optional = false)
    @Column(name = "crtime", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date crtime;

    @Basic(optional = false)
    @Column(name = "total")
    private double total;

    @Basic(optional = false)
    @Column(name = "discount")
    private double discount;

    @Basic(optional = false)
    @Column(name = "canceled", columnDefinition = "integer default 0")
    private Integer canceled;

    @Column(name = "registration")
    private Integer registration;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "check")
    private Collection<CheckspecEntity> checkspecCollection;

    @JoinColumn(name = "creator", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UserEntity creator;
}