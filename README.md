# appAuditoria

Uma aplicação de auditoria desenvolvida em Java, focada em fornecer uma interface gráfica moderna e persistência de dados em base de dados relacional.

## 🛠 Tecnologias Utilizadas

O projeto foi construído utilizando as seguintes tecnologias e bibliotecas:

* **Java 26:** Linguagem de programação principal do projeto.
* **Maven:** Ferramenta de gestão de dependências e compilação de projetos.
* **MySQL Connector/J (9.4.0):** Driver JDBC para a ligação à base de dados MySQL.
* **FlatLaf & FlatLaf Extras (3.6):** Biblioteca para modernizar a Interface Gráfica do Utilizador (Swing), proporcionando um visual atual e suporte a temas.
* **jSystemThemeDetector (3.9.1):** Permite a deteção automática do tema do sistema operativo (Claro/Escuro) para adaptar a interface.
* **jBCrypt (0.4):** Implementação do algoritmo de hash de palavras-passe OpenBSD Blowfish, garantindo a segurança no armazenamento de credenciais.

## ⚙️ Pré-requisitos

Para executar este projeto localmente, necessita de ter instalado:

* [Java Development Kit (JDK) 26](https://jdk.java.net/) ou superior.
* [Apache Maven](https://maven.apache.org/).
* [MySQL Server](https://dev.mysql.com/downloads/mysql/).

## 🗄️ Configuração da Base de Dados

Antes de iniciar a aplicação, certifique-se de que o seu servidor MySQL está a correr na porta `3306` e execute as seguintes configurações:

1.  Crie uma base de dados com o nome `appauditoria`.
2.  A aplicação está configurada por defeito para ligar com as seguintes credenciais:
    * **URL:** `jdbc:mysql://localhost:3306/appauditoria`
    * **Utilizador:** `root`
    * **Palavra-passe:** `mysql`

*(Nota: Caso utilize credenciais diferentes no seu ambiente local, será necessário alterar estes valores na classe `Conexao.java`).*

## 🚀 Como Executar

1.  Clone este repositório para a sua máquina local.
2.  Abra o terminal na diretoria raiz do projeto (onde se encontra o ficheiro `pom.xml`).
3.  Compile e instale as dependências executando:
    ```bash
    mvn clean install
    ```
4.  Para iniciar a aplicação, execute a classe principal `com.mycompany.appauditoria.AppAuditoria` na sua IDE ou através do Maven:
    ```bash
    mvn exec:java -Dexec.mainClass="com.mycompany.appauditoria.AppAuditoria"
    ```
