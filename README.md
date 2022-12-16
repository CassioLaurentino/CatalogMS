#catalog
:clipboard: Catálogo de Produtos

Está é uma API que possibilita a Criação de um produto, Atualização de um produto, Busca de um produto por Código, Lista de produtos, Lista de produtos filtrados por nome, descrição e preço e Deleção de um produto.

:computer: A aplicação está sendo executada na porta 9999, rodando no perfil de 'test' com o banco de dados H2 e contém um seeding de alguns produtos que são inseridos ao iniciar a aplicação.

## Os endpoints existentes na aplicação são:


| Verbo HTTP | Resource path                          | Description                 | Query param             |
| ---------- | -------------------------------------- | --------------------------- | ----------------------- |  
|  POST      |	http://localhost:9999/products				|	Criação de um produto       |                         |
|  PUT	     |	http://localhost:9999/products/{id}		|	Atualização de um produto   |                         |
|  GET	     |	http://localhost:9999/products/{id}		|	Busca de um produto por ID  |                         |
|  GET	     |	http://localhost:9999/products				|	Lista de produtos           |                         |
|  GET	     |	http://localhost:9999/products/search	|	Lista de produtos filtrados | q, min_price, max_price |
|  DELETE    |	http://localhost:9999/products/{id}		|	Deleção de um produto       |                         |


## Formato
  O formato esperado de um produto é:
  ```
    {
      "name": "string",
      "description": "string",
      "price": <preco>
    } 
  ```
  
  
## Iniciando essa aplicação
```
 git clone https://github.com/cwilliam98/cwcatalog.git
```
```
 cd  cwcatalog/backend
```
```
 mvn install
```
```
 mvn spring-boot:run  
```
