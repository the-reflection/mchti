
package org.reflection.service;

import java.math.BigInteger;
import javax.persistence.EntityManager;
import org.reflection.model.com.AbstractEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Service;

@Service("dataConverterFactory")
public class DataConverterFactory implements ConverterFactory<String, AbstractEntity> {

    private final EntityManager em;
   
   @Autowired
    public DataConverterFactory(EntityManager em) {
        this.em = em;
    }

    @Override
    public <T extends AbstractEntity> Converter<String, T> getConverter(Class<T> targetType) {
        return new GenericConverter(targetType);
    }

    private final class GenericConverter<T extends AbstractEntity> implements Converter<String, T> {

        private final Class<T> targetType;

        public GenericConverter(Class<T> targetType) {
            this.targetType = targetType;
        }

        @Override
        public T convert(String text) {
            if (text != null && !text.isEmpty() && !text.equalsIgnoreCase("-1")) {//Ignore - to be determined later
                try {
                    return (T) em.find(targetType, new BigInteger(text.trim()));
                } catch (Exception e) {
                }
            }
            return null;
        }
    }
}
