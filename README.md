# paximum-storeproject
A simple springboot-webapi project with unit tests.

## Build and Test

* From the root of the repository, use `db_initialize.sql` to initialize and populate the database.
* Correct the below fields in `src\main\resources\application.properties`.
  * spring.datasource.url=jdbc:mysql://localhost:3306/paximum_project 
  * spring.datasource.username=username
  * spring.datasource.password=password
  
* Run the project. Examples:
  * localhost:8080/api/v1/purchase - post (itemList)
  * localhost:8080/api/v1/list-products - get
  * localhost:8080/api/v1/product/{productId} - get
  
## Dependencies
Dependencies are managed in the `pom.xml` file.
