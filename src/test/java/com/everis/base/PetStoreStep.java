package com.everis.base;

import com.everis.base.models.Order;
import static io.restassured.RestAssured.given;

public class PetStoreStep {

    private final String URL_BASE = "https://petstore.swagger.io/v2";
    private int codigoRespuesta;
    private Order respuestaOrder;
    private String mensajeError;

    // Método para crear una orden (step 1)
    public void crearOrden(int id, int petId, int quantity) {
        Order nuevaOrden = new Order(id, petId, quantity);

        codigoRespuesta = given()
                .baseUri(URL_BASE)
                .contentType("application/json")
                .body(nuevaOrden)
                .when()
                .post("/store/order")
                .then()
                .extract()
                .statusCode();

        // Validar que la creación fue exitosa
        if (codigoRespuesta == 200) {
            respuestaOrder = given()
                    .baseUri(URL_BASE)
                    .when()
                    .get("/store/order/" + id)
                    .as(Order.class);
            System.out.println("ID Creado: " + respuestaOrder.getId());
            System.out.println("PetID Creado: " + respuestaOrder.getPetId());
            System.out.println("Quantity Creado: " + respuestaOrder.getQuantity());
        } else {
            throw new RuntimeException("Error al crear la orden. Código: " + codigoRespuesta);
        }
    }

    public void validarCodigoRespuesta(int codigoEsperado) {
        if (codigoRespuesta != codigoEsperado) {
            throw new AssertionError("Código esperado: " + codigoEsperado + ", Código Obtenido: " + codigoRespuesta);
        }
    }

    public Order obtenerRespuestaOrder() {
        return respuestaOrder;
    }

    // Método para consultar orden
    public void consultarOrden(int id) {
        codigoRespuesta = given()
                .baseUri(URL_BASE)
                .when()
                .get("/store/order/" + id)
                .then()
                .extract()
                .statusCode();

        if (codigoRespuesta == 200) {
            respuestaOrder = given()
                    .baseUri(URL_BASE)
                    .when()
                    .get("/store/order/" + id)
                    .as(Order.class);
        } else if (codigoRespuesta == 404) {
            mensajeError = given()
                    .baseUri(URL_BASE)
                    .when()
                    .get("/store/order/" + id)
                    .then()
                    .extract()
                    .path("message");
        } else {
            throw new RuntimeException("Error al consultar la orden. Código: " + codigoRespuesta);
        }
    }
}
