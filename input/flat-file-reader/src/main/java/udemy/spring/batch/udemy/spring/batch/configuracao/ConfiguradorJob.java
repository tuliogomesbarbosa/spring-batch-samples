package udemy.spring.batch.udemy.spring.batch.configuracao;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import udemy.spring.batch.udemy.spring.batch.dominio.Cliente;
import udemy.spring.batch.udemy.spring.batch.dominio.MapeadorCliente;

@Configuration
public class ConfiguradorJob {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader leitorDeClientes() {
        /*
         Três passos básicos para leitura de um arquivo "plano" ou tabular:
         1. Ler uma linha do arquivo. O FlatFileItemReader terá essa responsabilidade;
         2. Passar a linha lida para o método LineTokenizer#tokenize() para recuperar o FieldSet;
         3. Passar o FieldSet retornado pelo Tokenizer para o FieldSetMapper. O retorno
         do método FieldSetMapper#mapFieldSet() será o retorno do método ItemReader#read();

         Esses passos são executados internamente no FlatFileItemReader, basta que definamos o
         LineTokenizer e o FieldSetMapper.
         */
        FlatFileItemReader<Cliente> leitor = new FlatFileItemReader<>();
        leitor.setLinesToSkip(1);
        leitor.setResource(new ClassPathResource("/data/cliente.csv"));

        DefaultLineMapper<Cliente> mapeadorDeLinha = new DefaultLineMapper<>();

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(new String[] {"id", "primeiroNome", "ultimoNome", "dataNascimento"});

        mapeadorDeLinha.setLineTokenizer(tokenizer);
        mapeadorDeLinha.setFieldSetMapper(new MapeadorCliente());
        mapeadorDeLinha.afterPropertiesSet();

        leitor.setLineMapper(mapeadorDeLinha);

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
