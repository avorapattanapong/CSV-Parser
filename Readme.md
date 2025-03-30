# LSEG Assignment - CSV Parser

## Description
This is a simple CSV parser that reads a CSV file and stores the data in an in-memory H2 database. The project is split into two parts, the backend and the frontend. The backend is built using Spring Boot and the frontend is built using React.
The frontend is a simple UI that allows the user to upload a CSV file and view the data in a table. The backend is a REST API that allows the frontend to upload the CSV file and manages data.

## Features
- Upload a CSV file
- View the data in a table
- Sort the data by column (?)
- Search the data by column (?)
- Add a warning if row have a duration is is longer than the configured threshold