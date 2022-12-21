package com.armycar.kyobo.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book_info")
@Entity
public class BookEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bi_seq") private Long biSeq;
    @Column(name = "bi_title") private String biTitle;
    @Column(name = "bi_sub_title") private String biSubTitle;
    @Column(name = "bi_price") private Integer biPrice ;
    @Column(name = "bi_discount") private Double biDiscount;
    @Column(name = "bi_bimg_seq") private Long biBiSeq;
    @Column(name = "bi_bw_seq") private Long biBwSeq;
    @Column(name = "bi_bt_seq") private Long biBtSeq;
    @Column(name = "bi_bp_seq") private Long biBpSeq;
}