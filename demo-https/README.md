## Demo Project for enabling https connection

#### Creating self signed certificate
	keytool -genkeypair -alias shamsherahmed -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore shamsherahmed.p12 -validity 3650
###### The format used for the keystore. for JKS, set it as JKS
server.ssl.key-store-type=PKCS12
###### The path to the keystore containing the certificate
server.ssl.key-store=classpath:keystore/shamsherahmed.p12
###### The password used to generate the certificate
server.ssl.key-store-password=use the same password which we added during certificate creation
###### The alias mapped to the certificate
server.ssl.key-alias=javadevjournal
###### Run Spring Boot on HTTPS only
server.port=8443