package com.anz.cam.model.modelutils;
/**
 * TransactionType Converter
 * @author Keerthi Gadde
 *
 */
import java.util.stream.Stream;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TransactionTypeConverter implements AttributeConverter<TransactionType, String> {
	@Override
	public String convertToDatabaseColumn(TransactionType transType) {
		if (transType == null) {
			return null;
		}

		return transType.getTransactionType();
	}

	@Override
	public TransactionType convertToEntityAttribute(String transType) {
		if (transType == null) {
			return null;
		}

		return Stream.of(TransactionType.values()).filter(act -> act.getTransactionType().equals(transType)).findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}
}
