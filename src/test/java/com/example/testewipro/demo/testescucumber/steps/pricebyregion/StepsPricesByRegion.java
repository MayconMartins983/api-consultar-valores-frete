package com.example.testewipro.demo.testescucumber.steps.pricebyregion;

import com.example.testewipro.demo.testescucumber.CucumberBootstrap;
import com.example.testewipro.demo.dto.PricesRegionRequest;
import com.example.testewipro.demo.dto.PricesRegionResponse;
import com.example.testewipro.demo.testescucumber.steps.clientshttp.ConsultPricesByRegionClient;
import io.cucumber.java.After;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class StepsPricesByRegion extends CucumberBootstrap {

    @Autowired
    private ConsultPricesByRegionClient client;

    @After
    public void after() {
        response = null;
        exception = null;
    }

    private List<PricesRegionResponse> response;
    private Exception exception;
    private Long id;
    private BigDecimal valorToUpdated;

    @Dado("usuario quer buscar os preços de frete das regiões do brasil no end-point {string}")
    public void usuario_quer_buscar_os_preços_de_frete_das_regiões_do_brasil_no_end_point(String string) {
    }

    @Quando("o usuario fizer uma chamada get no end point acima")
    public void quando_cenario_usuario_busca_frete_todas_regioes() {
        response = client.consultFreightAndCep();
    }

    @Entao("ele deve receber uma resposta com cinco regiões e seus respectivos preços de frete:")
    public void entao_cenario_usuario_busca_frete_todas_regioes(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> expectedValues = dataTable.asMap(String.class, String.class);
        List<String> listRegion = response.stream().map(PricesRegionResponse::getRegion).collect(Collectors.toList());

        assertThat(listRegion).isEqualTo(List.of(expectedValues.get("regiao um"),
                expectedValues.get("regiao dois"),
                expectedValues.get("regiao tres"),
                expectedValues.get("regiao quatro"),
                expectedValues.get("regiao cinco")));
    }

    @Dado("que o usuario queira alterar o valor da região com id {string}")
    public void dado_cenario_usuario_alterar_valor_cep(String id) {
        this.id = Long.valueOf(id);
    }

    @Quando("usuario fizer requisição http passando o valor:")
    public void quando_cenario_usuario_alterar_valor_cep(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> expectedValues = dataTable.asMap(String.class, String.class);
        BigDecimal value = new BigDecimal(expectedValues.get("valor novo frete"));
        this.valorToUpdated = value;
        var request = new PricesRegionRequest(value);

        client.updatePriceOfRegion(id, request);
    }

    @Entao("deve retorna status: 200 Ok")
    public void entao_cenario_usuario_alterar_valor_cep() {
        var todosValores = client.consultFreightAndCep();
        var regionOne = todosValores
                .stream()
                .filter(region -> region.getId().equals(id))
                .findFirst();


        assertThat(regionOne.get().getPrice()).isEqualTo(valorToUpdated);

    }
}
