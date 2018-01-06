![opsmatters](https://i.imgur.com/VoLABc1.png)

## How to use the New Relic API
### Table of contents

- [Initialisation](#initialisation)
- [Alerts Channels](#alerts-channels)
- [Alerts Policies](#alerts-policies)
- [Alerts Policy Channels](#alerts-policy-channels)
- [Alerts Conditions](#alerts-conditions)
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
To create an email alert channel called "alerts@test.com", first instantiate the channel object and then pass it to the "create" operation:
```
EmailChannel c = EmailChannel.builder()
    .name("alerts@test.com")
    .recipients("alerts@test.com")
    .includeJsonAttachment(true)
    .build();

AlertChannel channel = api.alertChannels().create(c).get();
```
The alert channel returned includes all the additional fields that were populated by the server on creation eg, "id", "links".

Other operations have also been included for alert channels:
* list(): returns all alert channels for the account.
* get(id): returns the alert channel with the given id.
* delete(id): deletes the alert channel with the given id.

The available channel types are:
* UserChannel
* EmailChannel
* HipChatChannel
* SlackChannel
* CampfireChannel
* OpsGenieChannel
* PagerDutyChannel
* VictorOpsChannel
* xMattersChannel
* WebhookChannel

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
* list(): returns all alert policies for the account.
* list(name): returns all alert policies with the given name.
* get(name,id): returns the alert policy with the given name and id.
* update(policy): updates the alert policy with the given policy details.
* delete(id): deletes the alert policy with the given id.

### Alerts Policy Channels
To add an alert channel to an existing policy, pass the policy id and channel id to the "update" operation:
```
AlertPolicyChannel policyChannel = api.alertPolicyChannels().update(policy.getId(), channel.getId()).get();
```
The alert policy channel returned includes all the additional fields that were populated by the server on update eg, "channel_ids".

Other operations have also been included for alert policy channels:
* delete(policyId, id): deletes the alert channel with the given id from the policy.

### Alerts Conditions
To add a critical APM alert condition for application Apdex above 0.7, instantiate a term object and then pass it to the "create" operation:
```
Term term = Term.builder()
    .duration(Term.Duration.MINUTES_10)
    .criticalPriority()
    .aboveOperator()
    .allTimeFunction()
    .threshold(0.7)
    .build();

AlertCondition c = ApmAppAlertCondition.builder()
    .name("apdex-application")
    .metric(ApmAppAlertCondition.Metric.APDEX)
    .applicationConditionScope()
    .violationCloseTimer(AlertCondition.HOURS_12)
    .addTerm(term)
    .enabled(true)
    .build();

AlertCondition condition = api.alertConditions().create(policy.getId(), c).get();
```
The APM alert condition returned includes all the additional fields that were populated by the server on creation eg, "id".

Other operations have also been included for APM alert conditions:
* list(policyId): returns all APM alert conditions for the given policy id.
* get(policyId,id): returns the APM alert condition for the given policy id and condition id.
* delete(id): deletes the APM alert condition with the given id.

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
* list(policyId): returns all NRQL alert conditions for the given policy id.
* get(policyId,id): returns the NRQL alert condition for the given policy id and condition id.
* delete(id): deletes the NRQL alert condition with the given id.

### Alerts Infrastructure Conditions
To add a critical infrastructure alert condition for disk utilisation > 80%, instantiate a condition object and then pass it to the "create" operation:
```
InfraAlertCondition c = InfraMetricAlertCondition.builder()
    .policyId(policy.getId())
    .name("disk-space-error")
    .storageEventType()
    .selectValue("diskUsedPercent")
    .criticalThreshold(new Threshold().builder().durationMinutes(10).value(80).allTimeFunction().build())
    .aboveComparison()
    .whereClause("env=prod")
    .enabled(true)
    .build();

InfraAlertCondition condition = infraApi.infraAlertConditions().create(c).get();
```
To add a critical infrastructure alert condition for a host becoming unavailable, instantiate a condition object and then pass it to the "create" operation:
```
InfraAlertCondition c = InfraHostNotReportingAlertCondition.builder()
    .policyId(policy.getId())
    .name("host-unavailable-error")
    .criticalThreshold(new Threshold(10))
    .whereClause("env=prod")
    .enabled(true)
    .build();

InfraAlertCondition condition = infraApi.infraAlertConditions().create(c).get();
```
To add a critical infrastructure alert condition for a java process not running, instantiate a condition object and then pass it to the "create" operation:
```
InfraAlertCondition c = InfraProcessRunningAlertCondition.builder()
    .policyId(policy.getId())
    .name("process-not-running-error")
    .criticalThreshold(new Threshold().builder().durationMinutes(10).value(0).build())
    .equalComparison()
    .processWhereClause("(commandName = 'java')")
    .whereClause("env=prod")
    .enabled(true)
    .build();

InfraAlertCondition condition = infraApi.infraAlertConditions().create(c).get();
```
The infrastructure alert condition returned includes all the additional fields that were populated by the server on creation eg, "id" and "created_at_epoch_millis".

Other operations have also been included for infrastructure alert conditions:
* list(policyId): returns all infrastructure alert conditions for the given policy id.
* get(id): returns the infrastructure alert condition for the given condition id.
* delete(id): deletes the infrastructure alert condition with the given id.

<sub>Copyright (c) 2018 opsmatters</sub>