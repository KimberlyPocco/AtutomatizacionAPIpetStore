package com.everis.base.stepDefinitions;
import com.everis.base.PetStoreStep;
import com.everis.base.models.Order;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PetStoreSD {
    @Steps
    PetStoreStep petStore;

    @Given("dado que estoy en la pagina crear orden")
    public void dadoQueEstoyEnLaPaginaCrearOrden() {
        System.out.println("Comprobando que se ejcute los stetps para el escenario de compras");
    }

    @Given("dado que estoy en la pagina consultar orden")
    public void dadoQueEstoyEnLaPaginaConsultarOrden() {
        System.out.println("Comprobando que se ejcute los stetps para el escenario de consultar de orndes");
    }

    // crear una orden
    @When("creo una orden con id {int}, petId {int}, quantity {int}")
    public void creoUnaOrdenConIdIdPetIdPetIdQuantityQuantity(int id, int petId, int quantity) {
        petStore.crearOrden(id, petId, quantity);
    }

    // consultar una orden
    @When("consulto una orden con id {int}")
    public void consultarOrderID(int id) {
        petStore.consultarOrden(id);
    }

    // verificar el codigo de estado de la respuesta
    @Then("el código de estado de la respuesta debe ser {int}")
    public void elCódigoDeEstadoDeLaRespuestaDebeSerCodigo(int codigo) {
        petStore.validarCodigoRespuesta(codigo);
    }

    // el body de respuesta debe tener
    @And("la respuesta debe contener el id {int}, petId {int}, quantity {int}")
    public void laRespuestaDebeContenerElIdIdPetIdPetIdQuantityQuantity(int id, int petId, int quantity) {
        Order order = petStore.obtenerRespuestaOrder();
        assertNotNull(order);
        assertEquals(id, order.getId());
        assertEquals(petId, order.getPetId());
        assertEquals(quantity, order.getQuantity());
    }


}
