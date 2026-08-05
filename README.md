# appAuditoria

Uma aplicação de auditoria desenvolvida em Java, focada em fornecer uma interface gráfica moderna e persistência de dados em base de dados relacional.

## 📷 Capturas de Tela

**Página Inicial**
> <img width="1917" height="1015" alt="image" src="https://github.com/user-attachments/assets/b709808f-81dd-443a-8f1c-179d5bf065b1" />

**Menu Principal**
> <img width="1916" height="1015" alt="image" src="https://github.com/user-attachments/assets/c7740544-8524-4dc2-bc69-f264e108fb6c" />

**Página de Cadastro de Funcionário**
> <img width="1917" height="1008" alt="image" src="https://github.com/user-attachments/assets/71e76502-3abf-4810-aba2-d92488499cc7" />

**Página de Cadastro de Gerente**
> <img width="1917" height="1015" alt="image" src="https://github.com/user-attachments/assets/225bf6ef-7824-4010-8705-b862588918a7" />

**Página de Cadastro de Produto**
> <img width="1917" height="1011" alt="image" src="https://github.com/user-attachments/assets/f0adc068-7c60-4f66-a662-3fbc29d19ecb" />

**Página de Gerenciamento de Funcionários**
> <img width="1916" height="1015" alt="image" src="https://github.com/user-attachments/assets/9842d866-2717-4aec-b580-b87824a940ff" />

**Página de Gerenciamento de Gerentes**
> <img width="1917" height="1012" alt="image" src="https://github.com/user-attachments/assets/a81210f2-bec9-494e-9972-6b8a07791174" />

**Página de Gerenciamento de Produtos**
> <img width="1917" height="1015" alt="image" src="https://github.com/user-attachments/assets/d4ad503d-036d-4ecb-b8c9-5e72bdf59fcd" />

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

1. Crie uma base de dados no MySQL com o nome `appauditoria`.
2. Importe o ficheiro de estrutura localizado em `sql/appauditoria.sql` para criar as tabelas necessárias.
3. A aplicação está configurada por defeito para ligar com as seguintes credenciais:
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
