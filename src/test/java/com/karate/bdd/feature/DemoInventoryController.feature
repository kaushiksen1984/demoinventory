Feature: Test DemoInventoryController for different flows

Background:
	* url baseURL
	* header Content-Type = 'application/json'

Scenario: To test for the retrieval for all Products

	Given path '/productDetails'
	When method get
	Then status 200

Scenario: To test for the retrieval based on Product ID

	Given path '/productDetail'
	And param productid = '1'
	When method get
	Then status 200
	And match response == {"productid":1,"productname":"Samsung TV"}

Scenario: To add Product

	Given path '/addProduct'
	And request { productname: 'Visio TV' }
	When method post
	Then status 201
 	And match response == "New product inserted"
 	
Scenario: To Update Product

	Given path '/updateProduct'
	And request { productid: 5, productname: 'TCL TV' }
	When method put
	Then status 201
 	And match response == "Product updated"
 	