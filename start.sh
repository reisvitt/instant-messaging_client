#!/bin/bash

find ./ -type f -name "*.class" -exec rm -f {} +

javac Principal.java

java Principal;find ./ -type f -name "*.class" -exec rm -f {} +


# Se a conexao TCP tiver caído, o cliente deve iniciar a coneexao com o servidor e enviar a msg do gp e vice versa??

# inserir mensagens de JOIN e LEAVE do grupo na interface
# tela de configuracao do servidor + porta
# tela para configuracao de APDU
# tela para configuracao de tema. Light Dark
# Salvar os grupos e suas mansagens ao fechar aplicaçao.