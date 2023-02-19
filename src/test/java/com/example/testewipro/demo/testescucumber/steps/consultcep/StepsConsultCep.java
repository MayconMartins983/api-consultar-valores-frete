package com.example.testewipro.demo.testescucumber.steps.consultcep;

import com.example.testewipro.demo.testescucumber.CucumberBootstrap;
import com.example.testewipro.demo.dto.ConsultCepRequest;
import com.example.testewipro.demo.dto.ConsultCepResponse;
import com.example.testewipro.demo.exceptions.ResourceNotFoundException;
import com.example.testewipro.demo.exceptions.ValidationExceptionCustom;
import com.example.testewipro.demo.testescucumber.steps.clientshttp.ConsultCepClient;
import com.example.testewipro.demo.testescucumber.steps.clientshttp.ConsultPricesByRegionClient;
import io.cucumber.java.After;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class StepsConsultCep extends CucumberBootstrap {

    @Autowired
    private ConsultCepClient client;

    @After
    public void after() {
        response = null;
        cep = "";
        exception = null;
    }

    private String cep;
    private ConsultCepResponse response;
    private Exception exception;

    @Dado("Que o usuario queira buscar o frete do cep {string}")
    public void dado_cenario_cep_existente(String cep) {
        this.cep = cep;
    }

    @Quando("ele passar o valor acima no corpo da requisição http pelo Postman por exemplo")
    public void quando_cenario_cep_existente() {
        response = client.consultFreightAndCep(new ConsultCepRequest(cep));
    }

    @Então("ele deve receber os valores:")
    public void entao_cenario_cep_existente(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> values = dataTable.asMap(String.class, String.class);

        assertThat(response)
                .extracting("cep", "rua", "complemento", "bairro", "cidade", "estado")
                .containsExactlyInAnyOrder(values.get("cep"), values.get("rua"), values.get("complemento"),
                        values.get("bairro"), values.get("cidade"), values.get("estado"));
        assertThat(response.getFrete()).isInstanceOf(BigDecimal.class);
    }

    @Dado("que o usuario queira buscar o frete, por exemplo, do cep {string}")
    public void dado_cenario_cep_invalido(String cep) {
        this.cep = cep;
    }

    @Quando("ele colocar o valor acima no corpo da requisição http pelo Postman por exemplo")
    public void quando_cenario_cep_invalido() {
        try {
            client.consultFreightAndCep(new ConsultCepRequest(cep));
        } catch (Exception ex) {
            exception = ex;
        }
    }

    @Então("ele receberá erro 400 bad request do tipo validationExceptionCustom")
    public void entao_cenario_cep_invalido() {
        assertThat(exception)
                .isInstanceOf(ValidationExceptionCustom.class);
    }

    @Dado("que o usuario queira buscar o frete para o cep {string}")
    public void dado_cenario_cep_inexistente(String cep) {
        this.cep = cep;
    }

    @Quando("ele colocar o valor acima no corpo da requisição http pelo Postman")
    public void quando_cenario_cep_inexistente() {
        try {
            client.consultFreightAndCep(new ConsultCepRequest(cep));
        } catch (Exception ex) {
            exception = ex;
        }
    }

    @Então("ele receberá erro 404 not found do tipo ResourceNotFoundException")
    public void entao_cenario_cep_inexistente() {
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class);
    }
}
