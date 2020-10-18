package com.anz.cam.payload;
/**
 * AccountDto payload
 * @author Keerthi Gadde
 *
 */
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.anz.cam.model.modelutils.AccountType;
import com.anz.cam.model.modelutils.Currency;
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
public class AccountDto {
	private String accountNumber;
	@NotNull(message = "CustomerId can not be a null")
	@NotBlank(message = "CustomerId can not be a empty.")
	private String customerId;
	private String accName;
	@NotNull
	private AccountType accType;
	private BigDecimal openBalance;
	private BigDecimal balance;
	@NotNull
	private Currency currency;
	@JsonDeserialize(using = CustomDateDeserialize.class)
	private Date balanceDate;
	private String accntUIType;

}
