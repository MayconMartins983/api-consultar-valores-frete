#language:pt
  Funcionalidade: Buscar valores de frete de todas regiões e alterar caso necessário
    Cenario: usuario quer buscar os valores de frete de todas as regiões do brasil
      Dado usuario quer buscar os preços de frete das regiões do brasil no end-point "v1/prices-region"
      Quando o usuario fizer uma chamada get no end point acima
      Então ele deve receber uma resposta com cinco regiões e seus respectivos preços de frete:
      | regiao um | NORTE |
      | regiao dois | NORDESTE |
      | regiao tres | SUL |
      | regiao quatro | SUDESTE |
      | regiao cinco | CENTRO-OESTE |

    Cenario: usuario quer alterar valor do frete de alguma região
      Dado que o usuario queira alterar o valor da região com id "1"
      Quando usuario fizer requisição http passando o valor:
      | valor novo frete | 210.00 |
      Entao deve retorna status: 200 Ok

