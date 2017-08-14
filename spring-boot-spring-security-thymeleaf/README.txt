Building

1)

To build this project you will need Maven 2. You can get it at:

 http://maven.apache.org

Clean and install products:

 mvn clean install


Run as spring boot application:

 spring-boot:run

Once started, the application should be available at:

 http://localhost:8080/


2) For users information you have to create localdb with json-sever having data for users such as:


{
  "users": [
    { "id": 1, "name": "test", "lastName": "test", "login":"user", "password":"user", "email":"nf.mariem@gmail.com","phoneNumber":"8888888","address":"test Address","roles":[{"id":1,"role":"ROLE_USER"}]},
	{ "id": 1, "name": "testadmin", "lastName": "testadmin", "login":"admin", "password":"admin", "email":"nf.mariem@gmail.com","phoneNumber":"8888888","address":"test Address","roles":[{"id":1,"role":"ROLE_ADMIN"}]}
  ],
  "roles": [
    { "id": 1, "role": "ROLE_ADMIN"},
	{ "id": 1, "role": "ROLE_USER"}
  ]
}