package com.bci.users.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@Entity
@Table(name = "PHONES")
@AllArgsConstructor
@NoArgsConstructor
public class Phone {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NUMBER")
    private Integer number;

    @Column(name = "CITY_CODE")
    private Integer cityCode;

    @Column(name = "COUNTRY_CODE")
    private Integer countryCode;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
}
