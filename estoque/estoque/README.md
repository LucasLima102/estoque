# Sistema de Estoque

O sistema gerencia o estoque e as vendas de uma loja de eletrodomesticos, controlando fornecedores, produtos, clientes, funcionarios, pedidos, itens de pedido, entregas, promocoes e tags de produtos.

## Como testar a aplicacao

### 1. Verificar o Java

Antes de iniciar, confira se o Java esta instalado e se o `JAVA_HOME` esta configurado corretamente:

```bash
java -version
```

O projeto esta configurado para Java 21.

### 2. Iniciar a aplicacao

Na pasta raiz do projeto, execute:

```bash
./mvnw spring-boot:run
```

No Windows, use:

```bash
mvnw.cmd spring-boot:run
```

Por padrao, a aplicacao sera iniciada em:

```text
http://localhost:8080
```

### 3. Acessar o H2 Console

Com a aplicacao rodando, abra no navegador:

```text
http://localhost:8080/h2-console
```

Use os seguintes dados de conexao:

```text
JDBC URL: jdbc:h2:mem:estoque
User Name: sa
Password: sa
```

O campo `Password` deve ficar vazio.

### 4. Conferir as tabelas

Depois de conectar no H2 Console, as tabelas devem aparecer no painel lateral. Exemplos:

```text
CLIENTES
FORNECEDORES
PRODUTOS
PEDIDOS
ITENS_PEDIDO
ENTREGAS
PROMOCOES
TAGS_PRODUTO
PRODUTOS_PROMOCOES
PRODUTOS_TAGS
```

### 5. Consultar os dados iniciais

A aplicacao possui uma carga inicial executada automaticamente ao iniciar. Para testar, rode consultas como:

```sql
SELECT * FROM CLIENTES;
SELECT * FROM PRODUTOS;
SELECT * FROM PEDIDOS;
SELECT * FROM ENTREGAS;
SELECT * FROM PROMOCOES;
SELECT * FROM TAGS_PRODUTO;
```

Se os registros aparecerem, a carga inicial foi executada corretamente.

## Observacao

O banco H2 usado no projeto esta em memoria. Isso significa que os dados sao recriados sempre que a aplicacao e iniciada novamente.
