Feature: Gestión de ordenes de la tienda

  @CrearOrden
  Scenario Outline: Crear una nueva orden de tienda
    Given dado que estoy en la pagina crear orden
    When creo una orden con id <id>, petId <petId>, quantity <quantity>
    Then el código de estado de la respuesta debe ser <codigo>
    And la respuesta debe contener el id <id>, petId <petId>, quantity <quantity>
    Examples:
      | id | petId | quantity | codigo |
      | 5  | 2     | 3        | 200    |
      | 6  | 3     | 1        | 200    |
      | 7  | 3     | 1        | 200    |
      | 8  | 3     | 1        | 200    |

  @ConsultarOrden
  Scenario Outline: Consultar orden de tienda creada
    Given dado que estoy en la pagina consultar orden
    When consulto una orden con id <id>
    Then el código de estado de la respuesta debe ser <codigo>

    Examples:
      | id    | codigo |
      | 1956  | 404    |
      | 2     | 200    |
      | 3     | 200    |
      | 22563 | 404      |


