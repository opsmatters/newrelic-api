![opsmatters](https://i.imgur.com/VoLABc1.png)

## How to use the New Relic API
### Table of contents

- [Initialisation](#initialisation)
- [Alerts Channels](#alerts-channels)
- [Alerts Policies](#alerts-policies)
- [Alerts NRQL Conditions](#alerts-nrql-conditions)
- [Alerts Infrastructure Conditions](#alerts-infrastructure-conditions)

### Initialisation

Use the builder provided to obtain a service instance to carry out most New Relic operations:
```
NewRelicApiService api = NewRelicApiService.builder()
    .hostname("api.newrelic.com")
    .port(443)
    .apiKey("<YOUR_API_KEY>")
    .build();
```
Alternatively, if the hostname and port are omitted they default to "api.newrelic.com" and 443 respectively, so this becomes:
```
NewRelicApiService api = NewRelicApiService.builder()
    .apiKey("<YOUR_API_KEY>")
    .build();
```

Similarly, use the builder provided to obtain a service instance to carry out New Relic Infrastructure operations:
```
NewRelicInfraApiService infraApi = NewRelicInfraApiService.builder()
    .hostname("infra-api.newrelic.com")
    .port(443)
    .apiKey("<YOUR_API_KEY>")
    .build();
```
Again, if the hostname and port are omitted they default to "infra-api.newrelic.com" and 443 respectively, so this becomes:
```
NewRelicInfraApiService infraApi = NewRelicInfraApiService.builder()
    .apiKey("<YOUR_API_KEY>")
    .build();
```

### Alerts Channels
To create an email alert channel called "alerts@test.com", first instantiate the channel with an "email" configuration and then pass it to the "create" operation:
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
The alert channel returned includes all the additional fields that were populated by the server on creation eg, "id", "links".

Other operations have also been included for alert channels:
* all(): returns all alert channels for the account.
* get(id): returns the alert channel with the given id.
* delete(id): deletes the alert channel with the given id.

### Alerts Policies
To create an alert policy called "test-policy" with a PER_POLICY rollup strategy, first instantiate the policy and then pass it to the "create" operation:
```
AlertPolicy p = AlertPolicy.builder()
    .name("test-policy")
    .perPolicyIncidentPreference()
    .build();
AlertPolicy policy = api.alertPolicies().create(p).get();
```
The alert policy returned includes all the additional fields that were populated by the server on creation eg, "id", "created_at".

Other operations have also been included for alert policies:
* all(): returns all alert policies for the account.
* get(name): returns all alert policies with the given name.
* get(name,id): returns all alert policies with the given name and id.
* delete(id): deletes the alert policy with the given id.

### Alerts NRQL Conditions
To add a critical NRQL alert condition for average CPU percentage above 80%, instantiate a term and nrql object and then pass them to the "create" operation:
```
Term term = Term.builder()
    .duration(10)
    .criticalPriority()
    .aboveOperator()
    .allTimeFunction()
    .threshold(80)
    .build();
Nrql nrql = Nrql.builder()
    .query("SELECT average(cpuPercent) from SystemSample WHERE hostname like 'ip-%'")
    .sinceValue(3)
    .build();
NrqlAlertCondition c = NrqlAlertCondition.builder()
    .name("high-avg-cpu-used-error")
    .singleValueFunction()
    .addTerm(term)
    .nrql(nrql)
    .enabled(true)
    .build();
NrqlAlertCondition condition = api.nrqlAlertConditions().create(policy.getId(), c).get();
```
The NRQL alert condition returned includes all the additional fields that were populated by the server on creation eg, "id".

Other operations have also been included for NRQL alert conditions:
* all(policyId): returns all NRQL alert conditions for the given policy id.
* get(policyId,id): returns the NRQL alert condition for the given policy id and condition id.
* delete(id): deletes the NRQL alert condition with the given id.

### Alerts Infrastructure Conditions
To add a critical infrastructure alert condition for a host becoming unavailable, instantiate a condition object and then pass it to the "create" operation:
```
InfraAlertCondition c = InfraAlertCondition.builder()
    .policyId(policy.getId())
    .name("host-unavailable-error")
    .hostNotReportingType()
    .whereClause("env=prod")
    .criticalThreshold(new CriticalThreshold(10))
    .enabled(true)
    .build();
InfraAlertCondition condition = infraApi.infraAlertConditions().create(c).get();
```
The infrastructure alert condition returned includes all the additional fields that were populated by the server on creation eg, "id" and "created_at_epoch_millis".

Other operations have also been included for infrastructure alert conditions:
* all(policyId): returns all infrastructure alert conditions for the given policy id.
* get(id): returns the infrastructure alert condition for the given condition id.
* delete(id): deletes the infrastructure alert condition with the given id.

<sub>Copyright (c) 2018 opsmatters</sub>