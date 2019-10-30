# Ticket Reservation System

## About
This is a ticket reservation system that can reserve tickets and check seat availability of a user specified origin and destination city for a given number of seats.

## How To Build Run
1. Build and install service-library and cache-library jars using mevan.
2. Add availability-service and reservation-service services to Tomcat server and start the server.
3. Run ticket-client.

## Design Details
### Architecture
- Application is consist of two microserives separated based on their functionality (checking and reparation) communicating via HTTP messages.
- Two servlet endpoints are provided in each service to to handle requests.
- Availability checking and updating remaining seats are done by availability-service.
- Reservation, seat allocation and seat number tracking is done in reservation-service.
- Two separate databases (here in memory caches) connected to each service to access data.
- Two decoupled services running on separate processes provide more availability and maintainability.
- Client application can send request to check availability and reserve tickets.
### Flow
#### Availability Checking
1. Client sends get request to get data from the service.
2. Request is received, validity checked and processed by the service.
3. Database is accessed by the service and data is received for availability checking.
4. Response is created and send as the response to the get request.

#### Reservation
1. Client sends post request to get and update data of the service.
2. Request is received, validity checked and processed by the service.
3. Request is send for availability service.
4. If the seat is available reservation service will create a post request update database.
5. Database is accessed by the service and data is updated.
6. Seat reservation database updated according to the request.
4. Response is created and send as the response to the post request.

#### Data Storage Mechanism
- All the complex data elements broken into smaller unique elements identified as unit data elements.
- These are easy to handle and maintain.
- When large compacted data is updating all respective unit elements are updating individually, same when getting data unit elements are aggregate to make the complex data needed.
- As a plus point they are act as unique keys for the databases.

### Security 
- Securing rest API with https
- Credentials for admins
- Use OAuth provide authorization over HTTP with access tokens instead of credentials

## Improvements
- Temporary hold requested seats for a small amount of time in availability checking until the user is deciding so the next user can get less amount of remaining seats.
- Temporary holding will not block reads like the current implementation.
- Add vehicle class with seats counts and making separate database entries for unit elements of separate vehicles so the use can reserve seats across vehicles.
- Populate price maps and seat counts reading from csv files.
- Add improved logging and log file dumping for server services.

## Unit Tests
- Added unit tests for servlet testing and database testing.
- Run mevan test to deploy tests.