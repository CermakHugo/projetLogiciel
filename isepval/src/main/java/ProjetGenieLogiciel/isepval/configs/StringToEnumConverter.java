package ProjetGenieLogiciel.isepval.configs;

import ProjetGenieLogiciel.isepval.models.enums.UserType;
import org.springframework.core.convert.converter.Converter;


public class StringToEnumConverter implements Converter<String, UserType> {
    @Override
    public UserType convert(String source) {
        try {
            return UserType.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
