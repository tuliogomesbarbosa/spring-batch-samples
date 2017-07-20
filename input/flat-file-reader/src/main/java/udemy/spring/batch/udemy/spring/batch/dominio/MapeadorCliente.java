package udemy.spring.batch.udemy.spring.batch.dominio;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class MapeadorCliente implements FieldSetMapper<Cliente> {
    @Override
    public Cliente mapFieldSet(FieldSet fieldSet) throws BindException {
        return new Cliente(fieldSet.readLong("id"),
                fieldSet.readString("primeiroNome"),
                fieldSet.readString("ultimoNome"),
                fieldSet.readDate("dataNascimento", "yyyy-MM-dd HH:mm:ss"));
    }
}
