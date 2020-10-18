Follow the instructions mentioned in attached file for testing and running application(screen shot attached in word document)
Instructions to run the code:
1.	Run Spring Boot application and it will run on port no: 8081
2.	In-memory database is used and it can be found at : http://localhost:8081/console
Console User name : sa  Password : password
3.	Two users are created with encrypted passwords which can be found on data.sql or on H2 database.
4.	All URL’s are secured and need token to access them. Postman URL is used to get access token.
Authorization settings in Postman:
 
Form parameters to get token:
 
5.	Copy the access token from the response.
6.	Creation of account 
URL : http://localhost:8081/accounts
Method: POST
Header: Authorization bearer copied token
 

Body: 

 
{
    "accType": "CURRENT",
     "customerId":"2",
    "openBalance": 100.51,
    "currency":"SGD",
    "balanceDate":"12-10-2020", 
    "balance":12000.6
}
7.	GET REQUEST for Accounts: http://localhost:8081/accounts
Without passing page parameters
 


8.GET REQUEST for Accounts: http://localhost:8081/accounts
You can pass pagination parameters also
 
 9 .Creation of transaction.
URL: http://localhost:8081/transactions
Method: POST
Header: Authorization bearer copied token
Body
{
    "accountNumber": "5963313131",
    "transType": "Debit",
    "amount": 2000.51,
    "currency":"SGD",
    "description":"test"
}

Follow the instructions mentioned in the Accounts sections above for reference.
10. Getting Transactions for account number
Method : GET
 
