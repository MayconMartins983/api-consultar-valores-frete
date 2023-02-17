#language:pt
Funcionalidade: Buscar valor do frete pelo cep
  Cenario: Usuario busca cep com sucesso
    Dado Que o usuario queira buscar o frete do cep "01001-000"
    Quando ele passar o valor acima no corpo da requisição http pelo Postman por exemplo
    Então ele deve receber os valores:
    | cep | 01001-000 |
    | rua |Praça da Sé |
    | complemento | lado ímpar |
    | bairro | Sé |
    | cidade | São Paulo |
    | estado | SP |

  Cenario: Usuario recebe erro ao buscar cep inválido
    Dado que o usuario queira buscar o frete, por exemplo, do cep "0100-00"
    Quando ele colocar o valor acima no corpo da requisição http pelo Postman por exemplo
    Então ele receberá erro 400 bad request do tipo validationExceptionCustom

  Cenario: Usuario recebe erro ao buscar cep que não existe
    Dado que o usuario queira buscar o frete para o cep "99999-999"
    Quando ele colocar o valor acima no corpo da requisição http pelo Postman
    Então ele receberá erro 400 bad request do tipo ResourceNotFoundException
