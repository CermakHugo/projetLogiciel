package ProjetGenieLogiciel.isepval.configs;

import ProjetGenieLogiciel.isepval.models.enums.Mark;
import ProjetGenieLogiciel.isepval.models.enums.UserType;
import org.springframework.core.convert.converter.Converter;


public class StringToEnumMarkConverter implements Converter<String, Mark> {
    @Override
    public Mark convert(String source) {
        try {
            return Mark.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
