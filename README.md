# Project Title

A transaction log system using ratpack and redis

## Getting Started

### Dependencies

* Java 17
* Docker

### Installing

* How/where to download your program
* Any modifications needed to be made to files/folders

### Executing program

* To run directly without redis
> ./gradlew run 

* Get Redis Up and running
> docker run -p 6379:6379 --name redis-redisjson redislabs/rejson:latest

* To run directly with Redis
> SET DB=REDIS
> REDIS_URL=redis://localhost:6379
> ./gradlew run

* To run inside docker
> ./gradlew shadowJar
> docker build -t t-log-ratpack:latest .
> docker run --rm -d -p 5050:5050 t-log-ratpack:latest
## Help

Any advise for common problems or issues.
```
command to run if program contains helper info
```

## Authors

Contributors names and contact info

ex. Dominique Pizzie  
ex. [@DomPizzie](https://twitter.com/dompizzie)

## Version History

* 0.2
    * Various bug fixes and optimizations
    * See [commit change]() or See [release history]()
* 0.1
    * Initial Release

## License

This project is licensed under the [NAME HERE] License - see the LICENSE.md file for details

## Acknowledgments

Inspiration, code snippets, etc.
* [awesome-readme](https://github.com/matiassingers/awesome-readme)
* [PurpleBooth](https://gist.github.com/PurpleBooth/109311bb0361f32d87a2)
* [dbader](https://github.com/dbader/readme-template)
* [zenorocha](https://gist.github.com/zenorocha/4526327)
* [fvcproductions](https://gist.github.com/fvcproductions/1bfc2d4aecb01a834b46)