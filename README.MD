# Transactional and async

Demo of the @Async and @Transactional - Both Error case and Working case

## Installation

Open project in IntelliJ and it should load spring boot app config automatically 

otherwise, run spring boot app via

```bash
./gradlew bootRun
```

## Usage

### Case 1

Run the transaction and async method inside:

```
start transaction
   |
   |---> async method start
   |<--- async method end
   |
end transaction
```

Request:
```bash
curl --request GET \
  --url http://localhost:8080/transactional/person/1
```

### Case 2

Run async method and start a transaction (getting an exception):

```
start async
   |
   |---> start transaction (exception due to TM being on another thread)
   | ....
   |<--- 
   |
end async
```

Request:
```bash
curl --request GET \
  --url http://localhost:8080/exception/transactional/person/1
```

## License
[MIT](https://choosealicense.com/licenses/mit/)