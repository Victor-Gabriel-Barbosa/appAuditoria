-- phpMyAdmin SQL Dump
-- version 5.2.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Tempo de geração: 24/06/2026 às 17:30
-- Versão do servidor: 8.0.46
-- Versão do PHP: 8.2.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `appauditoria`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `auditoria`
--

CREATE TABLE `auditoria` (
  `id` bigint NOT NULL,
  `usuarioCpf` varchar(11) COLLATE utf8mb4_general_ci NOT NULL,
  `tabelaAfetada` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `operacao` enum('INSERT','UPDATE','DELETE','LOGIN') COLLATE utf8mb4_general_ci NOT NULL,
  `registroId` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `dadosAntigos` json DEFAULT NULL,
  `dadosNovos` json DEFAULT NULL,
  `dataHora` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `funcionario`
--

CREATE TABLE `funcionario` (
  `cpf` varchar(11) COLLATE utf8mb4_general_ci NOT NULL,
  `nome` varchar(300) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `telefone` varchar(11) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `funcao` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `perfilId` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Acionadores `funcionario`
--
DELIMITER $$
CREATE TRIGGER `trg_funcionario_after_delete` AFTER DELETE ON `funcionario` FOR EACH ROW BEGIN
    INSERT INTO auditoria (usuarioCpf, tabelaAfetada, operacao, registroId, dadosAntigos, dadosNovos)
    VALUES (
        IFNULL(@usuario_logado_cpf, '00000000000'),
        'funcionario',
        'DELETE',
        OLD.cpf,
        JSON_OBJECT('nome', OLD.nome, 'telefone', OLD.telefone, 'funcao', OLD.funcao),
        NULL
    );
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trg_funcionario_after_insert` AFTER INSERT ON `funcionario` FOR EACH ROW BEGIN
    INSERT INTO auditoria (usuarioCpf, tabelaAfetada, operacao, registroId, dadosAntigos, dadosNovos)
    VALUES (
        IFNULL(@usuario_logado_cpf, '00000000000'),
        'funcionario',
        'INSERT',
        NEW.cpf,
        NULL,
        JSON_OBJECT('nome', NEW.nome, 'telefone', NEW.telefone, 'funcao', NEW.funcao)
    );
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trg_funcionario_after_update` AFTER UPDATE ON `funcionario` FOR EACH ROW BEGIN
    INSERT INTO auditoria (usuarioCpf, tabelaAfetada, operacao, registroId, dadosAntigos, dadosNovos)
    VALUES (
        IFNULL(@usuario_logado_cpf, '00000000000'),
        'funcionario',
        'UPDATE',
        NEW.cpf,
        JSON_OBJECT('nome', OLD.nome, 'telefone', OLD.telefone, 'funcao', OLD.funcao),
        JSON_OBJECT('nome', NEW.nome, 'telefone', NEW.telefone, 'funcao', NEW.funcao)
    );
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura para tabela `gerente`
--

CREATE TABLE `gerente` (
  `cpf` varchar(11) COLLATE utf8mb4_general_ci NOT NULL,
  `nome` varchar(300) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `telefone` varchar(11) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `area` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `perfilId` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Acionadores `gerente`
--
DELIMITER $$
CREATE TRIGGER `trg_gerente_after_delete` AFTER DELETE ON `gerente` FOR EACH ROW BEGIN
    INSERT INTO auditoria (usuarioCpf, tabelaAfetada, operacao, registroId, dadosAntigos, dadosNovos)
    VALUES (
        IFNULL(@usuario_logado_cpf, '00000000000'),
        'gerente',
        'DELETE',
        OLD.cpf,
        JSON_OBJECT('nome', OLD.nome, 'telefone', OLD.telefone, 'area', OLD.area),
        NULL
    );
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trg_gerente_after_insert` AFTER INSERT ON `gerente` FOR EACH ROW BEGIN
    INSERT INTO auditoria (usuarioCpf, tabelaAfetada, operacao, registroId, dadosAntigos, dadosNovos)
    VALUES (
        IFNULL(@usuario_logado_cpf, '00000000000'),
        'gerente',
        'INSERT',
        NEW.cpf,
        NULL,
        JSON_OBJECT('nome', NEW.nome, 'telefone', NEW.telefone, 'area', NEW.area)
    );
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trg_gerente_after_update` AFTER UPDATE ON `gerente` FOR EACH ROW BEGIN
    INSERT INTO auditoria (usuarioCpf, tabelaAfetada, operacao, registroId, dadosAntigos, dadosNovos)
    VALUES (
        IFNULL(@usuario_logado_cpf, '00000000000'),
        'gerente',
        'UPDATE',
        NEW.cpf,
        JSON_OBJECT('nome', OLD.nome, 'telefone', OLD.telefone, 'area', OLD.area),
        JSON_OBJECT('nome', NEW.nome, 'telefone', NEW.telefone, 'area', NEW.area)
    );
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura para tabela `perfil`
--

CREATE TABLE `perfil` (
  `id` int NOT NULL,
  `des` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `produto`
--

CREATE TABLE `produto` (
  `codigo` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  `descricao` text COLLATE utf8mb4_general_ci,
  `valor` decimal(10,2) DEFAULT NULL,
  `estoque` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Acionadores `produto`
--
DELIMITER $$
CREATE TRIGGER `trg_produto_after_delete` AFTER DELETE ON `produto` FOR EACH ROW BEGIN
    INSERT INTO auditoria (usuarioCpf, tabelaAfetada, operacao, registroId, dadosAntigos, dadosNovos)
    VALUES (
        IFNULL(@usuario_logado_cpf, '00000000000'),
        'produto',
        'DELETE',
        OLD.codigo,
        JSON_OBJECT('descricao', OLD.descricao, 'valor', OLD.valor, 'estoque', OLD.estoque),
        NULL
    );
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trg_produto_after_insert` AFTER INSERT ON `produto` FOR EACH ROW BEGIN
    INSERT INTO auditoria (usuarioCpf, tabelaAfetada, operacao, registroId, dadosAntigos, dadosNovos)
    VALUES (
        IFNULL(@usuario_logado_cpf, '00000000000'), -- Fallback caso não venha da aplicação
        'produto',
        'INSERT',
        NEW.codigo,
        NULL,
        JSON_OBJECT('descricao', NEW.descricao, 'valor', NEW.valor, 'estoque', NEW.estoque)
    );
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trg_produto_after_update` AFTER UPDATE ON `produto` FOR EACH ROW BEGIN
    INSERT INTO auditoria (usuarioCpf, tabelaAfetada, operacao, registroId, dadosAntigos, dadosNovos)
    VALUES (
        IFNULL(@usuario_logado_cpf, '00000000000'),
        'produto',
        'UPDATE',
        NEW.codigo,
        JSON_OBJECT('descricao', OLD.descricao, 'valor', OLD.valor, 'estoque', OLD.estoque),
        JSON_OBJECT('descricao', NEW.descricao, 'valor', NEW.valor, 'estoque', NEW.estoque)
    );
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura para tabela `usuario`
--

CREATE TABLE `usuario` (
  `cpf` varchar(11) COLLATE utf8mb4_general_ci NOT NULL,
  `senha` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `perfilId` int DEFAULT NULL,
  `dataCadastro` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Acionadores `usuario`
--
DELIMITER $$
CREATE TRIGGER `trg_usuario_after_insert` AFTER INSERT ON `usuario` FOR EACH ROW BEGIN
    INSERT INTO auditoria (usuarioCpf, tabelaAfetada, operacao, registroId, dadosAntigos, dadosNovos)
    VALUES (
        IFNULL(@usuario_logado_cpf, '00000000000'),
        'usuario',
        'INSERT',
        NEW.cpf,
        NULL,
        JSON_OBJECT('perfilId', NEW.perfilId)
    );
END
$$
DELIMITER ;

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `auditoria`
--
ALTER TABLE `auditoria`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `funcionario`
--
ALTER TABLE `funcionario`
  ADD PRIMARY KEY (`cpf`),
  ADD KEY `perfilID` (`perfilId`);

--
-- Índices de tabela `gerente`
--
ALTER TABLE `gerente`
  ADD PRIMARY KEY (`cpf`),
  ADD KEY `perfilID` (`perfilId`);

--
-- Índices de tabela `perfil`
--
ALTER TABLE `perfil`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `produto`
--
ALTER TABLE `produto`
  ADD PRIMARY KEY (`codigo`);

--
-- Índices de tabela `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`cpf`),
  ADD KEY `perfilID` (`perfilId`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `auditoria`
--
ALTER TABLE `auditoria`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `perfil`
--
ALTER TABLE `perfil`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `funcionario`
--
ALTER TABLE `funcionario`
  ADD CONSTRAINT `funcionario_ibfk_1` FOREIGN KEY (`perfilId`) REFERENCES `perfil` (`id`);

--
-- Restrições para tabelas `gerente`
--
ALTER TABLE `gerente`
  ADD CONSTRAINT `gerente_ibfk_1` FOREIGN KEY (`perfilId`) REFERENCES `perfil` (`id`);

--
-- Restrições para tabelas `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`perfilId`) REFERENCES `perfil` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
