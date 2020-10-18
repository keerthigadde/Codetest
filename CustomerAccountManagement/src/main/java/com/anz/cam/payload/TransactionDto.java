package com.anz.cam.payload;
/**
 * TransactionDto payload
 * @author Keerthi Gadde
 *
 */
import java.math.BigDecimal;
import java.util.Date;

import com.anz.cam.model.modelutils.Currency;
import com.anz.cam.model.modelutils.TransactionType;
import com.anz.cam.utils.CustomDateDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
	private Long transactionId;
	private BigDecimal amount;
	private Currency currency;
	private TransactionType transType;
	private String description;
	@JsonDeserialize(using = CustomDateDeserialize.class)
	private Date transactionDate;
	private String accountNumber;
	private String accountName;
}
