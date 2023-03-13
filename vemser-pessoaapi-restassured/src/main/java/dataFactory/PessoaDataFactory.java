package dataFactory;

import model.PessoaModel;
import net.datafaker.Faker;
import org.apache.commons.lang3.StringUtils;
import service.PessoaService;

import java.util.regex.*;

import java.text.SimpleDateFormat;

public class PessoaDataFactory {
    private static PessoaService pessoaService = new PessoaService();
    private static Faker faker = new Faker();

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static PessoaModel pessoaValida(){
        return novaPessoa();
    }
    public static PessoaModel pessoaComNomeEmBranco(){
        PessoaModel pessComNomeEmBranco = pessoaValida();
        pessComNomeEmBranco.setNome(StringUtils.EMPTY);
        return pessComNomeEmBranco;
    }
    private static PessoaModel novaPessoa(){
        PessoaModel pessoa = new PessoaModel();
        pessoa.setNome(faker.name().nameWithMiddle());
        pessoa.setDataNascimento(dateFormat.format(faker.date().birthday()));
        pessoa.setCpf(faker.cpf().valid(false));
        pessoa.setEmail(pessoa.getNome().replaceAll("[^a-zA-Z0-9]", "") + "@" + faker.internet().domainName());

        return pessoa;
    }

    public static String cpfValidaFromAPI(){
        String cpfPessoaFromAPI =
        pessoaService.buscarTodasPessoas()
        .then()
                .extract()
                .path("content[0].cpf");
        return cpfPessoaFromAPI;
    }

    public static String cpfInvalido(){
        String cpf = "12398745e35";
        return cpf;
    }

    public static String nomeValidoFromAPI(){
        String nomePessoaFromAPI =
        pessoaService.buscarTodasPessoas()
        .then()
                .extract()
                .path("content[0].nome");
        return nomePessoaFromAPI;
    }

    public static String nomeInvalido(){
        String nome = "Adr0a1d0";
        return nome;
    }

    public static Integer idPessoaValidoFromAPI(){
        Integer idPessoaFromAPI =
        pessoaService.buscarTodasPessoas()
        .then()
                .extract()
                .path("content[0].idPessoa");
        return idPessoaFromAPI;
    }

    public static Integer idPessoaInvalido(){
        Integer idPessoa = Integer.MAX_VALUE;
        return idPessoa;
    }
}
