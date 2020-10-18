package com.anz.cam.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.anz.cam.model.modelutils.AccountType;
import com.anz.cam.model.modelutils.Currency;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Account Bean.
 * @author Keerthi Gadde
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString(exclude = { "transactions" })
@JsonIgnoreProperties({ "transactions" })
@Entity
@Table(name = "ACCOUNT")
public class Account {
	@Id
	@GeneratedValue(generator = "accountIdGenerator")
	@GenericGenerator(name = "accountIdGenerator", strategy = "com.anz.cam.utils.AccountNumberGenerator")
	private String accountNumber;
	private String customerId;
	@Column(name = "accountName")
	private String accName;
	@Enumerated(EnumType.STRING)
	@Column(name = "accounttype")
	private AccountType accType;
	private BigDecimal openBalance;
	private BigDecimal balance;
	@Enumerated(EnumType.STRING)
	private Currency currency;
	@CreationTimestamp
	private Date creationDate;
	@UpdateTimestamp
	private Date balanceDate;
	@OneToMany(mappedBy = "account", cascade = { CascadeType.PERSIST, CascadeType.REFRESH })
	private List<Transaction> transactions;
}
