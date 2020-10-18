package com.anz.cam.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.anz.cam.model.modelutils.Currency;
import com.anz.cam.model.modelutils.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
/**
 * Transaction Bean.
 * @author Keerthi Gadde
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "ACC_TRANSACTION")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionId;
	private BigDecimal amount;
	@Enumerated(EnumType.STRING)
	private Currency currency;
	private TransactionType transType;
	private String description;
	@CreationTimestamp
	private Date transactionDate;
	@ManyToOne
	@JoinColumn(name = "accountNumber")
	private Account account;

}
