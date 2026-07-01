package com.example.estoque.config;

import com.example.estoque.model.Cliente;
import com.example.estoque.model.EnderecoCliente;
import com.example.estoque.model.Entrega;
import com.example.estoque.model.Fornecedor;
import com.example.estoque.model.Funcionario;
import com.example.estoque.model.ItemPedido;
import com.example.estoque.model.LoginFuncionario;
import com.example.estoque.model.Pedido;
import com.example.estoque.model.Produto;
import com.example.estoque.model.Promocao;
import com.example.estoque.model.TagProduto;
import com.example.estoque.repositories.ClienteRepository;
import com.example.estoque.repositories.EnderecoClienteRepository;
import com.example.estoque.repositories.EntregaRepository;
import com.example.estoque.repositories.FornecedorRepository;
import com.example.estoque.repositories.FuncionarioRepository;
import com.example.estoque.repositories.ItemPedidoRepository;
import com.example.estoque.repositories.LoginFuncionarioRepository;
import com.example.estoque.repositories.PedidoRepository;
import com.example.estoque.repositories.ProdutoRepository;
import com.example.estoque.repositories.PromocaoRepository;
import com.example.estoque.repositories.TagProdutoRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {

    private final FornecedorRepository fornecedorRepository;
    private final ProdutoRepository produtoRepository;
    private final PromocaoRepository promocaoRepository;
    private final TagProdutoRepository tagProdutoRepository;
    private final ClienteRepository clienteRepository;
    private final EnderecoClienteRepository enderecoClienteRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final LoginFuncionarioRepository loginFuncionarioRepository;
    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final EntregaRepository entregaRepository;
    private final PasswordEncoder passwordEncoder;

    public DatabaseSeeder(
            FornecedorRepository fornecedorRepository,
            ProdutoRepository produtoRepository,
            PromocaoRepository promocaoRepository,
            TagProdutoRepository tagProdutoRepository,
            ClienteRepository clienteRepository,
            EnderecoClienteRepository enderecoClienteRepository,
            FuncionarioRepository funcionarioRepository,
            LoginFuncionarioRepository loginFuncionarioRepository,
            PedidoRepository pedidoRepository,
            ItemPedidoRepository itemPedidoRepository,
            EntregaRepository entregaRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.fornecedorRepository = fornecedorRepository;
        this.produtoRepository = produtoRepository;
        this.promocaoRepository = promocaoRepository;
        this.tagProdutoRepository = tagProdutoRepository;
        this.clienteRepository = clienteRepository;
        this.enderecoClienteRepository = enderecoClienteRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.loginFuncionarioRepository = loginFuncionarioRepository;
        this.pedidoRepository = pedidoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
        this.entregaRepository = entregaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (produtoRepository.count() > 0) {
            return;
        }

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome("Eletro Distribuidora Brasil");
        fornecedor.setEmail("contato@eletrobrasil.com");
        fornecedor.setCnpj("12345678000190");
        fornecedor.setTelefone("11999998888");
        fornecedorRepository.save(fornecedor);

        Promocao promocao = new Promocao();
        promocao.setNome("Semana da Cozinha");
        promocao.setDescricao("Desconto especial para produtos de cozinha");
        promocao.setDataInicio(LocalDate.now());
        promocao.setDataFim(LocalDate.now().plusDays(7));
        promocao.setPercentualDesconto(new BigDecimal("12.50"));
        promocaoRepository.save(promocao);

        TagProduto tagCozinha = new TagProduto();
        tagCozinha.setNome("cozinha");

        TagProduto tagPremium = new TagProduto();
        tagPremium.setNome("premium");
        tagProdutoRepository.saveAll(List.of(tagCozinha, tagPremium));

        Produto produto = new Produto();
        produto.setNome("Geladeira Frost Free 400L");
        produto.setMarca("Brastemp");
        produto.setCategoria("Cozinha");
        produto.setVoltagem("220V");
        produto.setPrecoBase(new BigDecimal("3499.90"));
        produto.setFornecedor(fornecedor);
        produto.setPromocoes(List.of(promocao));
        produto.setTags(List.of(tagCozinha, tagPremium));
        produtoRepository.save(produto);

        Cliente cliente = new Cliente();
        cliente.setNome("Mariana Silva");
        cliente.setCpf("12345678901");
        cliente.setTelefone("11988887777");
        cliente.setEmail("mariana.silva@email.com");
        clienteRepository.save(cliente);

        EnderecoCliente endereco = new EnderecoCliente();
        endereco.setLogradouro("Rua das Palmeiras");
        endereco.setNumero("120");
        endereco.setBairro("Centro");
        endereco.setCidade("Sao Paulo");
        endereco.setEstado("SP");
        endereco.setCep("01001000");
        endereco.setCliente(cliente);
        enderecoClienteRepository.save(endereco);

        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Carlos Almeida");
        funcionario.setCpf("98765432100");
        funcionario.setCargo("Vendedor");
        funcionario.setEmail("carlos.almeida@estoque.com");
        funcionario.setDataCriacao(LocalDateTime.now());
        funcionarioRepository.save(funcionario);

        LoginFuncionario loginFuncionario = new LoginFuncionario();
        loginFuncionario.setUsuario("calmeida");
        loginFuncionario.setSenha(passwordEncoder.encode("123456"));
        loginFuncionario.setUltimoLogin(LocalDateTime.now());
        loginFuncionario.setFuncionario(funcionario);
        loginFuncionarioRepository.save(loginFuncionario);

        Pedido pedido = new Pedido();
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setDataAtualizacao(LocalDateTime.now());
        pedido.setStatusPedido("CRIADO");
        pedido.setCliente(cliente);
        pedido.setFuncionario(funcionario);
        pedidoRepository.save(pedido);

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setProduto(produto);
        itemPedido.setPedido(pedido);
        itemPedido.setQuantidade(1);
        itemPedido.setPrecoVenda(new BigDecimal("3299.90"));
        itemPedidoRepository.save(itemPedido);

        Entrega entrega = new Entrega();
        entrega.setCodigoRastreio("BR123456789SP");
        entrega.setDataPrevisao(LocalDate.now().plusDays(5));
        entrega.setStatusEntrega("PENDENTE");
        entrega.setPedido(pedido);
        entrega.setEnderecoCliente(endereco);
        entregaRepository.save(entrega);
    }
}
