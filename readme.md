# 📊 Projeto Tabela de Frequência - IFBA Irecê

Este projeto foi desenvolvido como parte do curso de **Análise e Desenvolvimento de Sistemas (ADS)**. Trata-se de uma API REST construída com **Spring Boot** para automação de cálculos estatísticos de tabelas de frequência (discreta e contínua).

O sistema substitui interfaces antigas (como Swing) por uma interface web moderna, utilizando uma arquitetura em camadas e seguindo as melhores práticas de desenvolvimento Java.

---

## 🛠️ Tecnologias Utilizadas

* **Java 21**: Versão estável mais recente.
* **Spring Boot 3.x**: Framework principal para a API.
* **Maven**: Gerenciador de dependências e build.
* **Lombok**: Para redução de código boilerplate (Getters, Setters, Logs).
* **HTML5/CSS3/JavaScript**: Interface front-end simples e funcional.

---

## 🏗️ Arquitetura do Projeto

O projeto segue o padrão de **Arquitetura em Camadas** para garantir a separação de responsabilidades:

| Camada | Pasta / Pacote | Descrição |
| **Controller** | `br.com.ifba.estatistica.controller` | Porta de entrada da API. Recebe as requisições do navegador. |
| **Service** | `br.com.ifba.estatistica.service` | Contém a "inteligência" do sistema (Regra de Sturges e cálculos). |
| **Entity** | `br.com.ifba.estatistica.entity` | Define a estrutura dos dados (FrequenciaRow). |
| **Front-end** | `src/main/resources/static` | Arquivos `index.html` e `style.css` que o usuário acessa. |

---

## 🧠 Como Funciona o Cálculo

O sistema processa dois tipos de dados:

### 1. Dados Discretos
Contabiliza a frequência absoluta ($fi$) de cada valor individual encontrado na lista enviada.

### 2. Dados Contínuos (Regra de Sturges)
Para dados agrupados em classes, o sistema utiliza a fórmula de Sturges para determinar o número ideal de intervalos ($K$):

$$K = 1 + 3.3 \cdot \log_{10}(n)$$

Onde $n$ é o tamanho da amostra. A partir disso, calcula-se a amplitude do intervalo ($C$) e as frequências relativas e acumuladas.

---

## 🚀 Como Executar o Projeto

### Pré-requisitos
* JDK 21 instalado.
* IDE NetBeans (ou IntelliJ/Eclipse).
* Maven configurado.

### Passo a Passo
1.  **Clonar o repositório:**
    ```bash
    git clone https://github.com/the-kryz/tabelafrequencia.git
    ```
2.  **Compilar o projeto (NetBeans):**
    * Clique com o botão direito no projeto > **Clean and Build**.
    * *Nota: Caso o build falhe nos testes, use o comando: `mvn clean install -DskipTests`.*
3.  **Executar:**
    * Execute a classe `TabelaFrequenciaApplication.java`.
4.  **Acessar:**
    * Abra o navegador em: `http://localhost:8080/index.html`

---

## 🔌 Endpoints da API

A API expõe dois endpoints principais via **POST**:

* `POST /api/frequencia/discreta`: Recebe um JSON (Array de inteiros) e retorna a tabela discreta.
* `POST /api/frequencia/continua`: Recebe um JSON e retorna a tabela com intervalos de classe.

**Exemplo de corpo da requisição (JSON):**
```json
[23,21,19,21,22,19,19,18,37,21,19,20,20,24,19,20,20,20,21,20]
```

---

## ⚠️ Solução de Problemas Comuns

* **Porta 8080 ocupada:** Se o servidor não iniciar, feche o processo Java travado no Windows:
    ```cmd
    netstat -ano | findstr :8080
    taskkill /F /PID <PID_ENCONTRADO>
    ```
* **Erro de Lombok:** Certifique-se de que o processamento de anotações está ativo na sua IDE para que o `@Slf4j` e o `@Data` funcionem.

---

### 👨‍💻 Desenvolvedor
**Paulo Everton Nunes Novais** Estudante de ADS - IFBA Campus Irecê.

