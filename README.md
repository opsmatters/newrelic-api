![opsmatters](https://i.imgur.com/VoLABc1.png)

# New Relic API 
[![Build Status](https://travis-ci.org/opsmatters/newrelic-api.svg?branch=master)](https://travis-ci.org/opsmatters/newrelic-api)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.opsmatters/newrelic-api/badge.svg?style=blue)](https://maven-badges.herokuapp.com/maven-central/com.opsmatters/newrelic-api)
[![Javadocs](http://www.javadoc.io/badge/com.opsmatters/newrelic-api.svg)](http://www.javadoc.io/doc/com.opsmatters/newrelic-api)

A Java client library for the New Relic Monitoring and Alerting REST APIs built using Jersey and Gson.

The following New Relic APIs are currently supported:

### Alerts v2 API
* Alerts Channels (List, Create, Delete)
* Alerts Policies (List, Create, Update, Delete)
* Alerts Policy Channels (Update, Delete)
* Alerts Conditions (List, Create, Update, Delete)
* Alerts Entity Conditions (List, Add, Remove)
* Alerts NRQL Conditions (List, Create, Update, Delete)
* Alerts External Service Conditions (List, Create, Update, Delete)
* Alerts Plugins Conditions (List, Create, Update, Delete)
* Alerts Synthetics Conditions (List, Create, Update, Delete)
* Alerts Infrastructure Conditions (List, Create, Update, Delete)
* Alerts Events (List)
* Alerts Violations (List)
* Alerts Incidents (List)

### APM API
* Applications (List, Show, Update, Delete, Metric Names, Metric Data)
* Browser Applications (List, Create)

Other APIs can be included on request.

## Prerequisites

A New Relic account with an Admin user.
The user needs to generate an [Admin API Key](https://docs.newrelic.com/docs/apis/rest-api-v2/getting-started/api-keys) 
to provide read-write access via the [New Relic REST APIs](https://api.newrelic.com).
The Admin API Key is referenced in this documentation as the parameter "YOUR_API_KEY".

## Installing

First clone the repository using:
```
>$ git clone https://github.com/opsmatters/newrelic-api.git
>$ cd newrelic-api
```

To compile the source code, run all tests, and generate all artefacts (including sources, javadoc, etc):
```
mvn package -Dnewrelic.apiKey="<YOUR_API_KEY>" 
```

## Running the tests

To execute the unit tests:
```
mvn clean test -Dnewrelic.apiKey="<YOUR_API_KEY>"
```

The following tests are included:

* AlertOperations: Creates several alert channels, an alert policy with several conditions and then deletes them.

## Examples

A detailed set of examples of the usage of the API can be found [here](src/main/java/com/opsmatters/newrelic/api).

## Deployment

The build artefacts are hosted in The Maven Central Repository. 

Add the following dependency to include the artefact within your project:
```
<dependency>
  <groupId>com.opsmatters</groupId>
  <artifactId>newrelic-api</artifactId>
  <version>0.2.0</version>
</dependency>
```

## Built With

* [Jersey](https://jersey.github.io/) - RESTful Web Services in Java
* [Gson](https://github.com/google/gson) - Java serialization/deserialization library
* [Guava](https://github.com/google/guava/wiki) - An open-source set of common libraries for Java
* [Maven](https://maven.apache.org/) - Dependency Management

## Contributing

Please read [CONTRIBUTING.md](https://www.contributor-covenant.org/version/1/4/code-of-conduct.html) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

This project use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/opsmatters/newrelic-api/tags). 

## Authors

* **Gerald Curley** - *Initial work* - [opsmatters](https://github.com/opsmatters)

See also the list of [contributors](https://github.com/opsmatters/newrelic-api/contributors) who participated in this project.

## License

This project is licensed under the terms of the [Apache license 2.0](https://www.apache.org/licenses/LICENSE-2.0.html).

<sub>Copyright (c) 2018 opsmatters</sub>