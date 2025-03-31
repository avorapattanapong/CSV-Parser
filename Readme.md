# LSEG Assignment - CSV Parser

## Description
This is a simple CSV parser that reads a CSV file and stores the data in an in-memory H2 database. The project is split into two parts, the backend and the frontend. The backend is built using Spring Boot and the frontend is built using React.
The frontend is a simple UI that allows the log to upload a CSV file and view the data in a table. The backend is a REST API that allows the frontend to upload the CSV file and manages data.

## Features
- Upload a CSV file
- View the data in a table
- Sort the data by column (?)
- Search the data by column (?)
- Add a warning if row have a duration is longer than the configured threshold

## Things to note
1. Even though the project is setup using h2 in-memory database, the database type can easily be converted to persistent database like MySQL or PostgreSQL by changing the configurations in `application.properties` file.
2. The main file that contains the logic for parsing the CSV file is `CSVParserService.java`. The class takes advantage of Spring's dependency injection to allow for easy swapping of implementations. If a different CSV format is required, a new implementation can be created and injected into the service.
3. The main file that contains processing Logs and storing is `LogService.java`. The service consolidates data by pid and stores the data in the database.


## Assumptions
1. PIDs are unique.
2. No authentication is required to access the API.
3. No authorization is required to access the API.

## How to run the project
1. Clone the repository
2. In the root directory, run `docker-compose up --build -d` to build and run the docker container
3. Go to `http://localhost:3000` to access the frontend
4. Note that due to some performance issues, the page might need to be refreshed a few times to load ui-library

## Things To Improve
1. There are multiple places that can be factored out to reduce code duplication
2. Exceptions can be factored out to a more generic type instead of service related exceptions
3. Add unit tests to frontend
4. React app dockerfile is not production-ready, it is currently setup to run in dev environment
5. Performance may not be up to par
6. React app is created in Nextjs, which I do not have much experience with. With more time, I could optimize the setup and improve the performance, utilizing Nextjs.
7. UI can be cleaned up a little more.
8. Error handling on the UI is missing.
9. Linting should be configured to help maintain code formatting