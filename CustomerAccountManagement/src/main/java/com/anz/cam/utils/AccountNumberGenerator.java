package com.anz.cam.utils;
/**
 * AccountNumberGenerator is a class to generatate unique accountnumbers 
 * @author Keerthi Gadde
 *
 */
import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.query.spi.QueryImplementor;

public class AccountNumberGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		String number = NumberGeneratorUtil.accountNumberGenerator();
		QueryImplementor<?> aQuery = session.createQuery("select count(*) from Account where accountNumber=?1");
		aQuery.setParameter(1, number);
		long countryCount = (Long) aQuery.getSingleResult();
		while (countryCount > 0) {
			number = NumberGeneratorUtil.accountNumberGenerator();
		}
		return number;
	}
}