package com.imlinker.storage.common.converters;

import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;
import com.imlinker.storage.common.encryption.AesCipher;
import com.imlinker.storage.common.encryption.AesCipherHolder;
import com.imlinker.storage.common.model.SecureString;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class SecureStringConverter implements AttributeConverter<SecureString, String> {
    @Override
    public String convertToDatabaseColumn(SecureString secureString) {
        AesCipher cipher = AesCipherHolder.getInstance();

        if (!cipher.getEnable()) {
            return secureString.getValue();
        }

        try {
            return cipher.encrypt(secureString.getValue());
        } catch (Exception e) {
            throw new ApplicationException(ErrorType.INTERNAL_PROCESSING_ERROR, null, e.getCause());
        }
    }

    @Override
    public SecureString convertToEntityAttribute(String s) {
        AesCipher cipher = AesCipherHolder.getInstance();

        if (!cipher.getEnable()) {
            return new SecureString(s);
        }

        try {
            String decryptedString = cipher.decrypt(s);
            return new SecureString(decryptedString);
        } catch (Exception e) {
            throw new ApplicationException(ErrorType.INTERNAL_PROCESSING_ERROR, null, e.getCause());
        }
    }
}
