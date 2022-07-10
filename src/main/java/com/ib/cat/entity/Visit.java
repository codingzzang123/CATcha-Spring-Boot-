package com.ib.cat.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

@Entity(name = "visit")
@Data
public class Visit {

    @Id
    @SequenceGenerator(name="seq", sequenceName="seq_visit", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seq")
    private Integer no;

    @Column(name = "v_date")
    private LocalDate reg;

}
