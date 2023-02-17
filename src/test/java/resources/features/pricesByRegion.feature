#language:pt
  Funcionalidade: Buscar valores de frete de todas regiões e alterar caso necessário
    Cenario: usuario quer buscar os valores de frete de todas as regiões do brasil
      Dado usuario quer buscar os preços de frete das regiões do brasil
      Quando o usuario fizer uma chamada get no end point "v1/prices-region"
      Então ele deve receber uma resposta com cinco regiões e seus respectivos preços de frete:
      | regiao um | regiao norte |
      | regiao dois | regiao nordeste |
      | regiao tres | regiao sul |
      | regiao quatro | regiao sudeste |
      | regiao cinco | regiao centro-oeste |

