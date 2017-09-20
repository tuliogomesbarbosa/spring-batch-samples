package udemy.spring.batch.configuracao;

import com.sun.deploy.util.SessionState;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.xstream.XStreamMarshaller;
import udemy.spring.batch.dominio.Cliente;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConfiguradorJob {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public StaxEventItemReader<Cliente> leitorDeClientes() {
        XStreamMarshaller clienteMarshaller = new XStreamMarshaller();
        Map<String, Class> aliases = new HashMap<>();
        aliases.put("cliente", Cliente.class);
        clienteMarshaller.setAliases(aliases);

        StaxEventItemReader<Cliente> leitor = new StaxEventItemReader<>();
        leitor.setResource(new ClassPathResource("/data/clientes.xml"));
        leitor.setFragmentRootElementName("cliente");
        leitor.setUnmarshaller(clienteMarshaller);

        return leitor;
    }

    @Bean
    public ItemWriter<Cliente> escritorDeClientes() {
        return clientes -> {
            for (Cliente cliente : clientes) {
                System.out.println(cliente.toString());
            }
        };
    }

    @Bean
    public Step step() {
        return stepBuilderFactory.get("passo1")
                .<Cliente, Cliente>chunk(10)
                .reader(leitorDeClientes())
                .writer(escritorDeClientes())
                .build();
    }

    @Bean
    public Job job() {
        return jobBuilderFactory.get("tarefa1")
                .start(step())
                .build();
    }
}
