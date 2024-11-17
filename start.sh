#!/bin/bash

find ./ -type f -name "*.class" -exec rm -f {} +

javac Principal.java

java Principal;find ./ -type f -name "*.class" -exec rm -f {} +

# inserir mensagens de JOIN e LEAVE do grupo na interface
# tela de configuracao do servidor + porta
# tela para condiguracao de APDU
# tela para configuracao de tema. Light Dark
# Se a conexao TCP tiver caído, o servidor deve iniciar a coneexao com o cliente e enviar a msg do gp?
# Salvar os grupos e suas mansagens ao fechar apliacaao.