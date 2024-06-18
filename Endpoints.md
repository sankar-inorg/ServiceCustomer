POST: http://localhost:8080/customer/signup
{
    "email" : "johny@example.com",
    "firstName" : "John",
    "lastName" : "Bro",
    "password" : "secret123"
}

POST: http://localhost:8080/customer/signin
{
    "email" : "johny@example.com",
    "password" : "secret123"
}

POST: http://localhost:8080/customer/createResetPasswordToken/{{customer-id}}

POST: http://localhost:8080/customer/resetPassword
{
    "token": "{{token-value}}",
    "newPassword": "Hello1234"
}
