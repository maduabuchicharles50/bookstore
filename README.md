#  RESTful Web Service for a Bookstore

This project is a Bookstore Management System and it was built using Docker and the Java SpringBoot Framework. It allows 
a user to manage books, authors and genre .

## Basic Steps to run and test the Project
### 1. Download or Clone the Project from Github [here](https://github.com/maduabuchicharles50/bookstore)
- Inside any folder or directory in your computer, download and unzip the git file containing the project.
- Alternatively, you can clone the project from Github by running the following command `git clone https://github.com/maduabuchicharles50/bookstore.git` in any terminal on your computer.

### 2. Run the Application
**NOTE:** You must have Docker installed on your computer in order to test this project
- If you do not have Docker installed on your computer, you can download Docker from the official Docker website [here](https://www.docker.com/get-started/)
  - Download the file for your Operating System and confirm that Docker was installed successfully by running the command `docker --version ` on your termianl to see the verion of docker that was installed.
- Open your terminal from the directory where you downloaded or cloned the project and enter into the folder named `bookstore`.
- In the above terminal run the following command: `docker compose up --build`.
**NOTE:** Please ensure you are connected to the internet while running this command to enable Docker download the reuired docker image and build the Dockerfile in the project.
- After Docker builds successfully, we should have the Database service listening on `localhost:5432` and the Backend(Springboot) service listening on `localhost:8081`.

### 3. Test the Application
- You can test the application by sending requests to the endpoints on Postman or using the curl command on your terminal.

## API Endpoints
This project comprises of three major entities; Book, Author and Gentre

### 1. Genre
> **POST:** `http://localhost:8081/api/genres`
 - Adds a new genre in the bookstore.
```
Sample Request
{
    "type":"fiction"
}
```
```
Sample Response
{
    "id": 3,
    "type": "fiction"
}
```
> **GET:** `http://localhost:8081/api/genres/{id}`
 - Returns a single Genre with the specified genre id.
```
Sample Response
{
    "id": 3,
    "type": "fiction"
}
```
> **GET ALL:** `http://localhost:8081/api/genres/{id}`
 - Returns aLL Genres with the specified genre with pagination
```
Sample Response
{
    "_embedded": {
        "genreDtoList": [
            {
                "id": 4,
                "type": " non fiction"
            },
            {
                "id": 3,
                "type": "story"
            }
        ]
    },
    "_links": {
        "self": {
            "href": "http://localhost:8081/api/genres?page=0&size=2000&sort=type,asc"
        }
    },
    "page": {
        "size": 2000,
        "totalElements": 2,
        "totalPages": 1,
        "number": 0
    }
}
```
> **PUT:** `http://localhost:8081/api/genres/{id}`
 - Updates the details of the Genre with the specified id. 
```
Sample Request
{
    "type":"story"
}
```
```
Sample Response
{
    "id": 3,
    "type": "story"
}
```
> **DELETE:** `http://localhost:8081/api/genre/{id}`
 - Deletes a Genre profile from the system with id.

### 2. Author 
> **POST:** `http://localhost:8081/api/authors`
 - Adds a new Autor to the bookstore.
```
Sample Request
{
    "name":"Ken ",
    "contact":"08123567832"
}
```
```
Sample Response
{
    "id": 3,
    "name": "Ken ",
    "contact": "08123567832"
}
```
> **PUT:** `http://localhost:8081/api/return/{bookId}/patron/{patronId}`
 - Records the return of a borrowed book by a particular Patron specified by the patronId. **NOTE:** This can only be done by the authenticated Librarian that borrowed the book to the Patron.
```
Sample Response
{
    "id": 1,
    "borrowDate": "2024-04-21",
    "returnDate": "2024-04-21",
    "book": {
        "id": 1,
        "title": "Things fall",
        "author": "Chinua Achebe",
        "isbn": "8900-2920-2729-2828",
        "publicationYear": "2016"
    },
    "patron": {
        "id": 1,
        "name": "Mbonu Charlie",
        "contact": "08132468741"
    },
    "librarian": {
        "id": 3,
        "email": "johnny2@gmail.com",
        "name": "John Prince"
    }
}
```

### 3. Book
- A Book is a single book that contains an Author and a Genre
  
> **GET:** `http://localhost:8081/api/books`
 - Returns the list of books in the bookstore. 
```
Sample Response
{
    "_embedded": {
        "items": [
            {
                "id": 2,
                "title": "The Black boy",
                "author": {
                    "id": 1,
                    "name": "ike",
                    "contact": "08145673890",
                    "createdAt": "2024-07-04T10:23:17.969674",
                    "updatedAt": "2024-07-04T10:24:06.651496"
                },
                "genre": {
                    "id": 4,
                    "type": " non fiction"
                },
                "isbn": "8900-2920-2729-2828",
                "publicationYear": "2016"
            }
        ]
    },
    "_links": {
        "self": {
            "href": "http://localhost:8081/api/books?page=0&size=2000&sort=publicationYear,asc"
        }
    },
    "page": {
        "size": 2000,
        "totalElements": 1,
        "totalPages": 1,
        "number": 0
    }
}
```
> **GET:** `http://localhost:8081/api/books/{id}`
 - Returns a single Book with the specified book id.
```
Sample Response
{
    "id": 2,
    "title": "The Black boy",
    "author": {
        "id": 1,
        "name": "ike",
        "contact": "08145673890",
        "createdAt": "2024-07-04T10:23:17.969674",
        "updatedAt": "2024-07-04T10:24:06.651496"
    },
    "genre": {
        "id": 4,
        "type": " non fiction"
    },
    "isbn": "8900-2920-2729-2828",
    "publicationYear": "2016"
}
```
> **POST:** `http://localhost:8081/api/books`
 - Adds a new Book to the bookstore.
```
Sample Request
{
    "title": "The Black boy",
    "authorId": 1,
    "genreId": 4,
    "isbn": "8900-2920-2729-2828",
    "publicationYear": "2016"
}
```
```
Sample Response
{
    "id": 2,
    "title": "The Black boy",
    "author": {
        "id": 1,
        "name": "ike",
        "contact": "08145673890",
        "createdAt": "2024-07-04T10:23:17.969674",
        "updatedAt": "2024-07-04T10:24:06.651496"
    },
    "genre": {
        "id": 4,
        "type": " non fiction"
    },
    "isbn": "8900-2920-2729-2828",
    "publicationYear": "2016"
}
```
> **PUT:** `http://localhost:8081/api/books/{id}`
 - Updates the details of a Book with the specified id.
```
Sample Request
{
    "title": "The Black boy updated",
    "authorId": 1,
    "genreId": 4,
    "isbn": "8900-2920-2729-2828",
    "publicationYear": "2016"
}
```
```
Sample Response
{
    "id": 2,
    "title": "The Black boy updated",
    "author": {
        "id": 1,
        "name": "ike",
        "contact": "08145673890",
        "createdAt": "2024-07-04T10:23:17.969674",
        "updatedAt": "2024-07-04T10:24:06.651496"
    },
    "genre": {
        "id": 4,
        "type": " non fiction"
    },
    "isbn": "8900-2920-2729-2828",
    "publicationYear": "2016"
}
```
> **DELETE:** `http://localhost:8081/api/books/{id}`
 - Deletes a Book with the specified id from the bookstore.

