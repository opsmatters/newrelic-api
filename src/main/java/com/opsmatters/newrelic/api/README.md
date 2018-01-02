![opsmatters](https://i.imgur.com/VoLABc1.png)

## How to use the New Relic API

To obtain a service instance to carry out most New Relic operations use the builder provided:
```
NewRelicApiService api = NewRelicApiService.builder().hostname("api.newrelic.com").port(443).apiKey("<YOUR_API_KEY>").build();
```
Alternatively, if the hostname and port are not provided, they default to "api.newrelic.com" and 443 respectively, eg.
```
NewRelicApiService api = NewRelicApiService.builder().apiKey("<YOUR_API_KEY>").build();
```

Similarly, to obtain a service instance to carry out New Relic Infrastructure operations use the builder provided:
```
NewRelicInfraApiService api = NewRelicInfraApiService.builder().hostname("infra-api.newrelic.com").port(443).apiKey("<YOUR_API_KEY>").build();
```
Alternatively, if the hostname and port are not provided, they default to "infra-api.newrelic.com" and 443 respectively, eg.
```
NewRelicInfraApiService api = NewRelicInfraApiService.builder().apiKey("<YOUR_API_KEY>").build();
```

<sub>Copyright (c) 2018 opsmatters</sub>