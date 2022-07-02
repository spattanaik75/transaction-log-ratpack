# TRANSACTION LOG

A transaction log system using ratpack and redis

## Getting Started

### Dependencies

* Java 17
* Docker

### How to Run

* To run directly without redis
```shell
./gradlew run
```

* Get Redis Up and running
```shell
docker run -p 6379:6379 --name redis-redisjson redislabs/rejson:latest
```

* To run directly with Redis
```shell
REDIS_URL=redis://localhost:6379
./gradlew run
```

* To run inside docker
```shell
./gradlew shadowJar
docker build -t t-log-ratpack:latest .
docker run --rm -d -p 5050:5050 t-log-ratpack:latest
```

### Usage

* POST /login
```shell
curl --location --request POST 'localhost:5050/login'
```
* GET /balance
```shell
curl --location --request GET 'localhost:5050/balance' \
--header 'Authorization: PASTE_YOUR_TOKEN_HERE'
```
* GET /transactions
```shell
curl --location --request GET 'localhost:5050/transactions' \
--header 'Authorization: PASTE_YOUR_TOKEN_HERE'
```
* POST /spend
```shell
curl --location --request POST 'localhost:5050/spend' \
--header 'Authorization: PASTE_YOUR_TOKEN_HERE' \
--header 'Content-Type: application/json' \
--data-raw '{
   "date": "2007-12-03T10:17:33",
   "description": "Something-test1",
   "amount": 999.00,
   "currency": "ZAR"

}'
```
## Questions
* How long did you spend on the coding test? What would you add to your solution if you spent more time on it? If you didn't spend much time on the coding test then use this as an opportunity to explain what you would add.
  * 1 day. Took half a day to go through ratpack documentation. and rest coding the solution 
  * I could add tests using Spock as testing framework 
  * Guice for DI
  * And some mechanism to imitate @ControllerAdvice from springboot
* What was the most useful feature that was added to Java 8? Please include a snippet of code that shows how you've used it.
  * Functional Programming
  * Streams
* What is your favourite framework / library / package that you love but couldn't use in the task? What do you like about it so much?
  * Springboot(Java) 
    * have almost all production ready features
    * ageing well
  * Python(pandas)
    * very efficient datastructures
* What great new thing you learnt about in the past year and what are you looking forward to learn more about over the next year?
  * learning new things almost everyday. Currently teaching myself AWS.
* How would you track down a performance issue in production? Have you ever had to do this? Can you add anything to your implementation to help with this?
  * First we need to start isolate individual systems to identify the bottleneck
  * We will need a lot of runtime metrics also.
  * Its also recommended to have a checklist of very common performance issues: e.g.
  * Common DB issues 
    * CPU usage
    * Connection throttling
    * Full Table Scans
  * To be honest there is no particular method to do it. 
  * We can also make sure we provide some tooling alongwith our app. such as, system metrics, debug logs etc. 
* How would you improve the APIs that you just used?
  * still in the figuring out phase. I need to figure out how Blocking calls work properly. 
  * So far used a mixure of blocking / non blocking methods. 
* Please describe yourself in JSON format.
```json
{
  "name": "Soumya",
  "lastname": "Pattanaik",
  "age": 30,
  "nationality": "Indian",
  "livesin": "South Africa",
  "passions": [
    "Programming",
    "Learning",
    "Helping others"
  ],
  "interests": [
    "AI/ML",
    "Financial Markets",
    "Travel"
  ],

  "dreams": [
    "To make this world a better place!"
  ]
}
```
* What is the meaning of life? 
  * So many things comes to my mind, to summarize "just make the world a better place. Make everyone around you happier than they are. Thats my job"

    And 2 lines from Afterlife:
    “Happiness is amazing. It’s so amazing, it doesn’t matter if it’s yours or not.” — Anne
    “Good people do things for other people. That’s it. The end.” — Anne
  
## Thanks for your time, hope to hear from you soon!