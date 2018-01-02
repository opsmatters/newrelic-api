![opsmatters](https://i.imgur.com/VoLABc1.png)

## How to use the New Relic API
### Table of contents

- [Initialisation](#initialisation)
- [Alert Channels](#alert-channels)
- [Alert Policies](#alert-policies)

### Initialisation

To obtain a service instance to carry out most New Relic operations use the builder provided:
```
NewRelicApiService api = NewRelicApiService.builder()
    .hostname("api.newrelic.com")
    .port(443)
    .apiKey("<YOUR_API_KEY>")
    .build();
```
Alternatively, if the hostname and port are not provided they default to "api.newrelic.com" and 443 respectively, so this becomes:
```
NewRelicApiService api = NewRelicApiService.builder()
    .apiKey("<YOUR_API_KEY>")
    .build();
```

Similarly, to obtain a service instance to carry out New Relic Infrastructure operations use the builder provided:
```
NewRelicInfraApiService infraApi = NewRelicInfraApiService.builder()
    .hostname("infra-api.newrelic.com")
    .port(443)
    .apiKey("<YOUR_API_KEY>")
    .build();
```
Again, if the hostname and port are not provided they default to "infra-api.newrelic.com" and 443 respectively, so this becomes:
```
NewRelicInfraApiService infraApi = NewRelicInfraApiService.builder()
    .apiKey("<YOUR_API_KEY>")
    .build();
```

### Alert Channels
To create an email alert channel called "alerts@test.com", first instantiate the channel with an "email" configuration and then pass it to the "create" operation::
```
EmailConfiguration configuration = EmailConfiguration.builder()
    .recipients("alerts@test.com")
    .includeJsonAttachment(true)
    .build();
AlertChannel c = AlertChannel.builder()
    .name("alerts@test.com")
    .configuration(configuration)
    .build();
AlertChannel channel = api.alertChannels().create(c).get();
```
The alert channel object returned has all available fields populated by the server eg, "id", "links".

Other operations have also been included for alert channels:
* all(): returns all alert channels for the account.
* get(id): returns the alert channel with the given id.
* delete(id): deletes the alert channel with the given id.

### Alert Policies
To create an alert policy called "test-policy" with a PER_POLICY rollup strategy, first instantiate the policy and then pass it to the "create" operation::
```
AlertPolicy p = AlertPolicy.builder()
    .name("test-policy")
    .perPolicyIncidentPreference()
    .build();
AlertPolicy policy = api.alertPolicies().create(p).get();
```
The alert policy object returned has all available fields populated by the server eg, "id", "created_at".

Other operations have also been included for alert policies:
* all(): returns all alert policies for the account.
* get(name): returns all alert policies with the given name.
* get(name,id): returns all alert policies with the given name and id.
* delete(id): deletes the alert policy with the given id.

<sub>Copyright (c) 2018 opsmatters</sub>