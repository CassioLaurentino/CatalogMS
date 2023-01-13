# Framework Brasil session-manager-ms  

### Acesso Swagger
Pra acessar o swagger do microserviço localmente use o seguinte path `/api`

Exemplo local: `http://localhost:8080/api`
  Para validação da sessão, o Session Manager receberá o BSessionId, o user_id, o product_id (opcional), o id da web_view que está sendo acessada.

      O Session Manager consultará no MongoDB se a sessão existe através do BSessionId e user_id informado. Ao recuperar a sessão, deverá validar se o product_id está entre os produtos do usuário que estão no array products fa sessão.

      Caso a validação acima tenha sido feita com sucesso, o Session Manager atualizará a sessão com as informações referente a web view que está sendo acessada (adicionará o id da webview e o product_id posicionado no campo “webviews” que tem dentro da sessão), e retornará em seguida o código HTTP 200 ao APIM.

      Caso a sessão não exista ou a validação não tenha sido concluída com sucesso, o Session Manager deverá retornará código HTTP 401 ao APIM.

      O swagger desse método para validação, pode ser encontrado no link https://wikicorp.telefonica.com.br/pages/viewpage.action?pageId=288317128. 

### Exception Handler
  A partir da versão 3.2 do Spring, a anotação @ControllerAdvice é a melhor solução para um componente que controla as exceções, e somente deve ser usada no escopo da classe.

    Adicionando um handler:
      - Dentro da classe GlobalExceptionHandler criar um novo método para gerenciar uma nova exceção.
      - Utilizar a anotação @ExceptionHandler, juntamente com a classe de exception a ser mantida.
      - O método deve retornar uma ResponseEntity<CustomMessage> com uma mensagem de erro e o HttpStatus.

        Exemplo: 
          @ExceptionHandler(value = {Exception.class})
          public ResponseEntity<CustomMessage> HandleGenericException(Exception e, WebRequest req) {
            CustomMessage message = new CustomMessage(new Date(), e.getMessage());
            log.error("Internal Server Error, see logs! ", req.getDescription(false));
            return new ResponseEntity<CustomMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
          }

    Utilizando o handler:
      - Durante a implementação de sua demanda é possível utilizar na declaração do método, desta forma "throws Exception". Porém, o uso indicado é em um block try/catch.

        Exemplo:
        catch (ForbiddenException exception) {
            throw new ForbiddenException(
                "Session ID not found or Invalid"
            );
        } catch (Exception exception) {
            throw new BadRequestException();
        }
