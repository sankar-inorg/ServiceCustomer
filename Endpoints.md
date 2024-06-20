Day 3:

POST: http://localhost:8080/customer/signup
{
    "email" : "johny@example.com",
    "firstName" : "John",
    "lastName" : "Bro",
    "password" : "secret123"
    "key": "johny-bro"
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

POST: http://localhost:8080/customer/createEmailVerificationToken/{{customer-id}}

GET: http://localhost:8080/customer/verifyEmailToken/{{token-value}}

POST: http://localhost:8080/customer/verifyEmail/{{token-value}}

POST: http://localhost:8080/customer/createCustomerGroup/{{customer-group-name}}

POST: http://localhost:8080/customer/addCustomerToACustomerGroup/{{customer-id}}/{{customer-group-id}}

1) Customer --> CreateCustomer
2) Authenticate Customer (Sign In)
3) Customer --> Create a Token for Resetting Customer's Password
4) Customer --> Reset Customers Password
5) Customer --> Create a Token for Verifying Customer's Email
6) Customer --> Get Customer by Email Verification Token
7) Customer --> Verify Customer's Email
8) CustomerGroup --> Create Customer Group
9) Customer --> Update Customer by ID
   - Add Customer to the Customer Group 

Steps to use Update APIs using Action:
1) Get the Customer
2) Create an Action List
3) Create an Action
4) Add the Action to the Action List
5) Create the Draft using the Version from Customer and the Action List
6) Pass the JSON Body and Make the API Call
   
Git Commands:

git branch yashwanth main -f: Forcefully reset the 'yashwanth' branch to point to the same commit as the 'main' branch
git push origin main -f: Forcefully push the local changes to the Remote Repo

Day 4:

POST: http://localhost:8080/customer/updateCustomer/{{customer-key}}
{
    "preferredShoeSize" : "38"
}

GET: http://localhost:8080/customer/query-by/{{preferredShoeSize}}

POST: http://localhost:8080/shoppingLists/create
{
    "shoppingListName" : "My Shopping list 2",
    "shoppingListSlug" : "my-shopping-list-2",
    "customerID" : "{{customer-id}}",
    "shoppingListKey" : "my-shopping-list-2",
    "sku" : "Red-35",
    "quantity" : 5
}

POST: http://localhost:8080/shoppingLists/addLineItem
{
    "shoppingListID" : "{{shopping-list-id}}",
    "productID" : "{{product-id}}",
    "variantID" : 4,
    "Quantity" : 5
}

POST: http://localhost:8080/shoppingLists/updateItemQuantity
{
    "shoppingListId" : "{{shopping-list-d}}",
    "lineItemId" : "{{line-item-d}}",
    "quantity" : 10
}

1) Customer --> Update Customer with preferred shoe size field.
2) Customer --> Query Customers by preferred shoe size field.
3) ShoppingList --> Create New Shopping List for Customer.
4) ShoppingList --> Add Items to Shopping List.
5) ShoppingList --> Update Item Qty in Shopping List.
