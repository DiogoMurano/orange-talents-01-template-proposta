package br.com.zup.proposal.shared.crypto;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class CryptoConverter implements AttributeConverter<String, String> {

    @Autowired
    private CryptoResolver cryptoResolver;

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return cryptoResolver.encode(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return cryptoResolver.decode(dbData);
    }

}