Introduction
The Book-store API provides us with opporturnty to create and store different books online.

Base URL
The base URLs for all API endpoints is: 
http://localhost:9090/api/v1/book
http://localhost:9090/api/v1/users


Authentication
Book-store has secured using jwt token, all for api request a user must be registered and authenticated.
a Bearer token is return on registration, this is then passed as "AUTHORIZATION" header for subsequent calls.


responseCode:
all sucesssful responseCode: "000"
all failed responseCode: "900"

response messages:
all successful requets:"success"
all failed request: "Operation failed"
sucessful Registration: "Please Proceed to login"
successful authentication: "Login was Successful"



validation:the follow should be noted
Firstname:must not be empty
Lastname: must not be empty
email:must not be empty
author: must not be empty
title: must not be empty
amount: cannot be null
status: must not be empty


Endpoints

registerUser()
description: create a new user on the server.
http method:POST
url: /register

Requestbody:
{
  "firstname": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "password": "securePassword123",
  "phoneNumber": "+1234567890",
  "address": "123 Main Street, Cityville"
}

Response:
{
    "responseCode": "000",
    "message": "success",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huLmRvZUBleGFtcGxlLmNvbSIsImlhdCI6MTcwNTQzNjg0MiwiZXhwIjoxNzA1NDM4MjgyfQ.Z5G5AD8dpgZTkRfjVpmGYn-uhZgQWMtBfkdctkHNbYU"
}


Requestbody:

authenticate()
description: check for access and priveleges.
http method:POST
url: /authenticate


{
    "username": "john.doe@example.com",
    "password": "securePassword123"
}

Response:

{
    "responseCode": "000",
    "message": "Login was Successful",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huLmRvZUBleGFtcGxlLmNvbSIsImlhdCI6MTcwNTQzNjg4OSwiZXhwIjoxNzA1NDM4MzI5fQ.oq_knoCD-bEpgQkHcVCAKd4AdjW52NszhVQYXvYwe-w"
}



getAllBooks()
description: Get a list of all books.
http method:GET 
url: /get-all-books

Requestbody: none

Response:
  {
            "id": 2,
            "title": "Things fall apart",
            "author": "chinua Achebe",
            "category": {
                "categoryName": "African fiction",
                "description": "an african fictional story"
            },
            "amount": 50.99,
            "status": "Available",
            "createdAt": "2024-01-16T21:30:59.02524",
            "updatedAt": "2024-01-16T21:30:59.025206"
        },
        {
            "id": 1,
            "title": "lord of the rings( the return of the king)",
            "author": "J.R.R Tolkein",
            "category": {
                "categoryName": "fictional Adventure",
                "description": "An adventure of the middle east"
            },
            "amount": 50.99,
            "status": "Available",
            "createdAt": "2024-01-16T21:28:49.219934",
            "updatedAt": "2024-01-16T21:32:26.293756"
        }
    ]
}

CreatBook().
description: Create a new a book.
http method:Post 
url: /create-book

Requestbody: 
{
    "title": "Things fall apart",
    "author": "chinua Achebe",
    "category": {
        "categoryName": "African fiction",
        "description": "an african fictional story"
    },
    "amount": 50.99,
    "status": "Available",
    "dateCreated": "2024-01-16T12:30:00"
}

Response:

{
    "responseCode": "000",
    "message": "success",
    "details": {
        "id": 2,
        "title": "Things fall apart",
        "author": "chinua Achebe",
        "category": {
            "categoryName": "African fiction",
            "description": "an african fictional story"
        },
        "amount": 50.99,
        "status": "Available",
        "createdAt": "2024-01-16T21:30:59.02524",
        "updatedAt": "2024-01-16T21:30:59.025206"
    }
}


UpdateBook().
description: replace an already exist instance on server.
http method:PUT 
url: /book/update-book/{book-id} 
note: {book-id} is pathvariable and should always be added to the url
Requestbody: 
{
    "title": "lord of the rings( the return of the king)",
    "author": "J.R.R Tolkein",
    "category": {
        "categoryName": "fictional Adventure",
        "description": "An adventure of the middle east"
    },
    "amount": 50.99,
    "status": "Available",
    "dateCreated": "2024-01-16T12:30:00"
}
Response:
{
    "responseCode": "00x",
    "message": "Updated successfully",
    "details": {
        "id": 1,
        "title": "lord of the rings( the return of the king)",
        "author": "J.R.R Tolkein",
        "category": {
            "categoryName": "fictional Adventure",
            "description": "An adventure of the middle east"
        },
        "amount": 50.99,
        "status": "Available",
        "createdAt": "2024-01-16T21:28:49.219934",
        "updatedAt": "2024-01-16T21:32:26.293756"
    }
}

DeleteBook()
description: remove a book on the server.
http method:DELETE
note: {book-id} is the id of thebook you want to delete, it is also pathvariable and should always be added to the url
url: /delete-book/{book_id}

Requestbody: none
Response: none 
HTTP STATUS : 200 OK






