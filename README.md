# bci challenge

Proyecto gradle, bajo framework spring boot y con pruebas unitarias en spock 

Importar el proyecto como un proyecto gradle en intelliJ y a traves de este ejecutar el proyecto o correr las pruebas. 
 
Services path 
  
  ## login  
  
  enpoint: /bci/api/auth/login
  
  metodo: get
  
  headers
	
	Authorization: {token}
  
  
  ## registrar usuario 
  
  enpoint: /bci/api/auth/sign-up
  
  metodo: post
  
  ejemplo body:
  
	{
		"name": "prueba",
		"email": "prueba@prueba.com",
		"password": "a2asfGfdfdf4",
		"phones": [
					{
						"number": 2242424,
						"cityCode": 1,
						"countryCode": "+57"
					}
		]
	}
	

  
