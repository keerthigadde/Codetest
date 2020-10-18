package com.anz.cam.utils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomDateDeserialize extends JsonDeserializer<Date> {
	@Override
	public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
		SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);

		try {
			return formatter.parse(p.getText());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return ctxt.parseDate(p.getText());
	}
}
