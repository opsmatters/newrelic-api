![opsmatters](https://i.imgur.com/VoLABc1.png)

## How to use the New Relic API
### Table of contents

- [Initialisation](#initialisation)

#### Alerts v2 API
- [Alerts Channels](#alerts-channels)
- [Alerts Policies](#alerts-policies)
- [Alerts Policy Channels](#alerts-policy-channels)
- [Alerts Conditions](#alerts-conditions)
- [Alerts Entity Conditions](#alerts-entity-conditions)
- [Alerts NRQL Conditions](#alerts-nrql-conditions)
- [Alerts External Service Conditions](#alerts-external-service-conditions)
- [Alerts Plugins Conditions](#alerts-plugins-conditions)
- [Alerts Synthetics Conditions](#alerts-synthetics-conditions)
- [Alerts Infrastructure Conditions](#alerts-infrastructure-conditions)
- [Alerts Events](#alerts-events)
- [Alerts Violations](#alerts-violations)
- [Alerts Incidents](#alerts-incidents)

#### APM v2 API
- [Applications](#applications)
- [Application Hosts](#application-hosts)
- [Application Instances](#application-instances)
- [Browser Applications](#browser-applications)
- [Mobile Applications](#mobile-applications)
- [Key Transactions](#key-transactions)
- [Plugins](#plugins)

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
* show(id): returns the alert channel with the given id.
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
* show(name,id): returns the alert policy with the given name and id.
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
To add a critical APM alert condition for application Apdex above 0.7, instantiate a condition object and then pass it to the "create" operation:
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
* show(policyId,id): returns the APM alert condition for the given policy id and condition id.
* delete(id): deletes the APM alert condition with the given id.

### Alerts Entity Conditions
To add an entity to an APM alert condition, call the "add" operation:
```
AlertCondition condition = api.alertEntityConditions().add(condition.getId(), entityId, entityType).get();
```
The APM alert condition returned includes all the additional fields that were updated by the server on creation eg, "entities".

Other operations have also been included for alert entity conditions:
* get(entityId,entityType): returns the APM alert condition for the given entity id.
* remove(conditionId,entityId,entityType): removes the given entity id from the APM alert condition with the given id.

### Alerts NRQL Conditions
To add a critical NRQL alert condition for average CPU percentage above 80%, instantiate a condition object and then pass them to the "create" operation:
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
* show(policyId,id): returns the NRQL alert condition for the given policy id and condition id.
* delete(id): deletes the NRQL alert condition with the given id.

### Alerts External Service Conditions
To add a critical external service alert condition for average response time above 5s, instantiate a condition object and then pass it to the "create" operation:
```
Term term = Term.builder()
    .duration(10)
    .criticalPriority()
    .aboveOperator()
    .allTimeFunction()
    .threshold(5)
    .build();

ExternalServiceAlertCondition c = ApmExternalServiceAlertCondition.builder()
    .name("high-response-time-error")
    .metric(ApmExternalServiceAlertCondition.Metric.RESPONSE_TIME_AVERAGE)
    .externalServiceUrl("example.com")
    .addTerm(term)
    .enabled(true)
    .build();

ExternalServiceAlertCondition condition = api.externalServiceAlertConditions().create(policy.getId(), c).get();
```
The external service alert condition returned includes all the additional fields that were populated by the server on creation eg, "id".

Other operations have also been included for external service alert conditions:
* list(policyId): returns all external service alert conditions for the given policy id.
* show(policyId,id): returns the external service alert condition for the given policy id and condition id.
* delete(id): deletes the external service alert condition with the given id.

### Alerts Plugins Conditions
To add a critical Plugins alert condition for a metric, instantiate a condition object and then pass it to the "create" operation:
```
Term term = Term.builder()
    .duration(10)
    .criticalPriority()
    .aboveOperator()
    .allTimeFunction()
    .threshold(5)
    .build();

PluginId plugin = PluginId.builder()
    .id("12345")
    .guid("12345-12345")
    .build();

PluginsAlertCondition c = PluginsAlertCondition.builder()
    .name("test-metric-error")
    .metric("test-metric")
    .metricDescription("test-metric-description")
    .averageValueFunction()
    .addTerm(term)
    .plugin(plugin)
    .addEntity(54321)
    .enabled(true)
    .build();

PluginsAlertCondition condition = api.pluginsAlertConditions().create(policy.getId(), c).get();
```
The Plugins alert condition returned includes all the additional fields that were populated by the server on creation eg, "id".

Other operations have also been included for Plugins alert conditions:
* list(policyId): returns all Plugins alert conditions for the given policy id.
* show(policyId,id): returns the Plugins alert condition for the given policy id and condition id.
* delete(id): deletes the Plugins alert condition with the given id.

### Alerts Synthetics Conditions
To add a critical Synthetics alert condition for a monitor, instantiate a condition object and then pass it to the "create" operation:
```
SyntheticsAlertCondition c = SyntheticsAlertCondition.builder()
    .name("test-synthetics-error")
    .monitorId("abcde-12345-abcde-12345")
    .enabled(true)
    .build();

SyntheticsAlertCondition condition = api.syntheticsAlertConditions().create(policy.getId(), c).get();
```
The Synthetics alert condition returned includes all the additional fields that were populated by the server on creation eg, "id".

Other operations have also been included for Synthetics alert conditions:
* list(policyId): returns all Synthetics alert conditions for the given policy id.
* show(policyId,id): returns the Synthetics alert condition for the given policy id and condition id.
* delete(id): deletes the Synthetics alert condition with the given id.

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
* show(id): returns the infrastructure alert condition for the given condition id.
* delete(id): deletes the infrastructure alert condition with the given id.

### Alerts Events
To list all events, call the "list" operation with no parameters:
```
Collection<AlertEvent> events = api.alertEvents().list();
```

To list all events matching one or more filters, build the set of filters and pass it to the "list" operation:
```
Map<String,Object> filters = AlertEventOperations.filters()
    .product(Product.APM)
    .entityType(EntityType.APPLICATION)
    .eventType(AlertEvent.EventType.VIOLATION_OPEN)
    .build();
Collection<AlertEvent> events = api.alertEvents().list(filters);
```

### Alerts Violations
To list all violations, call the "list" operation with a date range and a flag to indicate if only open violations are required:
```
Calendar c = Calendar.getInstance();
c.add(Calendar.DATE, -7); // select violations for the last week
long startDate = c.getTimeInMillis();
long endDate = System.currentTimeMillis();
boolean onlyOpen = true;
Collection<AlertViolation> violations = api.alertViolations().list(startDate, endDate, onlyOpen);
```

### Alerts Incidents
To list all incidents, call the "list" operation with a flag to indicate if only open violations are required:
```
boolean onlyOpen = true;
Collection<AlertIncident> incidents = api.alertIncidents().list(onlyOpen);
```

### Applications
To update an application's name and settings, instantiate an application object and then pass it to the "update" operation:
```
Application a = Application.builder()
    .id(12345)
    .name("new-application-name")
    .appApdexThreshold(0.5f)
    .endUserApdexThreshold(7.0f)
    .enableRealUserMonitoring(true)
    .build();

Application application = api.applications().update(a).get();
```
The "id" field needs to be provided as it is used to locate the existing application to update.

To list the applications matching one or more filters, build the filters and then pass it to the "list" operation:
```
Map<String,Object> filters = ApplicationOperations.filters()
    .language("java") // all Java applications
    .build();

Collection<Application> applications = api.applications().list(filters);
```
 To list the application metrics using one or more parameters, build the parameter list and then pass it to the "metricData" operation:
```
List<String> parameters = MetricParameterBuilder.builder()
    .names("EndUser")
    .names("EndUser/Apdex")
    .values("call_count")
    .values("average_response_time")
    .values("score")
    .from(System.currentTimeMillis()-(3600*1000L)) // last 60 minutes
    .to(System.currentTimeMillis())
    .summarize(true)
    .build();

MetricData metrics = api.applications().metricData(parameters).get();
```

Other operations have also been included for applications:
* list(): returns all applications.
* show(id): returns the application for the given id.
* delete(id): deletes the application with the given id.
* metricNames(id): returns the metrics and their value names for the given application.
* metricNames(id, name): returns the metrics and their value names for the given application, where the value names match the given name.

### Application Hosts
To list the hosts for an application call the "list" operation with a set of filters:
```
List<String> filters = ApplicationHostOperations.filters()
    .hostname("test-host")
    .build();

Collection<ApplicationHost> applicationHosts = api.applicationHosts().list(applicationId, filters);
```
 To list the application host metrics using one or more parameters, build the parameter list and then pass it to the "metricData" operation:
```
List<String> parameters = MetricParameterBuilder.builder()
    .names("Threads/SummaryState/RUNNABLE/Count")
    .values("call_count")
    .from(System.currentTimeMillis()-(3600*1000L)) // last 60 minutes
    .to(System.currentTimeMillis())
    .summarize(true)
    .build();

MetricData metrics = api.applicationHosts().metricData(applicationId, hostId, parameters).get();
```

Other operations have also been included for application hosts:
* show(applicationId, hostId): returns the application host for the given id.
* metricNames(applicationId, hostId): returns the metrics and their value names for the given application host.
* metricNames(applicationId, hostId, name): returns the metrics and their value names for the given application host, where the value names match the given name.

### Application Instances
To list the instances for an application call the "list" operation with a set of filters:
```
List<String> filters = ApplicationInstanceOperations.filters()
    .hostname("test-host")
    .build();

Collection<ApplicationInstance> applicationInstances = api.applicationInstances().list(applicationId, filters);
```
 To list the application instance metrics using one or more parameters, build the parameter list and then pass it to the "metricData" operation:
```
List<String> parameters = MetricParameterBuilder.builder()
    .names("Threads/SummaryState/RUNNABLE/Count")
    .values("call_count")
    .from(System.currentTimeMillis()-(3600*1000L)) // last 60 minutes
    .to(System.currentTimeMillis())
    .summarize(true)
    .build();

MetricData metrics = api.applicationInstances().metricData(applicationId, instanceId, parameters).get();
```

Other operations have also been included for application instances:
* show(applicationId, instanceId): returns the application instance for the given id.
* metricNames(applicationId, instanceId): returns the metrics and their value names for the given application instance.
* metricNames(applicationId, instanceId, name): returns the metrics and their value names for the given application instance, where the value names match the given name.

### Browser Applications
To add a browser application, instantiate an application object and then pass it to the "create" operation:
```
BrowserApplication a = BrowserApplication.builder()
    .name("test-browser-app")
    .build();

BrowserApplication application = api.browserApplications().create(a).get();
```
The Browser application returned includes all the additional fields that were populated by the server on creation eg, "id", "browser_monitoring_key".

Other operations have also been included for Browser applications:
* list(): returns all Browser applications.
* show(id): returns the Browser application for the given id.

### Mobile Applications
To list the mobile applications call the "list" operation:
```
Collection<MobileApplication> applications = api.mobileApplications().list();
```
 To list the mobile application metrics using one or more parameters, build the parameter list and then pass it to the "metricData" operation:
```
List<String> parameters = MetricParameterBuilder.builder()
    .names("Mobile/Crash/All")
    .names("Session/Start")
    .values("call_count")
    .from(System.currentTimeMillis()-(3600*1000L)) // last 60 minutes
    .to(System.currentTimeMillis())
    .summarize(true)
    .build();

MetricData metrics = api.mobileApplications().metricData(parameters).get();
```

Other operations have also been included for mobile applications:
* show(id): returns the mobile application for the given id.
* metricNames(id): returns the metrics and their value names for the given mobile application.
* metricNames(id, name): returns the metrics and their value names for the given mobile application, where the value names match the given name.

### Key Transactions
To list the key transactions call the "list" operation with a set of filters:
```
List<String> filters = KeyTransactionOperations.filters()
    .name("test-transaction")
    .build();

Collection<KeyTransaction> transactions = api.keyTransactions().list(filters);
```

Other operations have also been included for key transactions:
* show(id): returns the key transaction for the given id.

### Plugins
To list the installed plugins call the "list" operation with a set of filters:
```
List<String> filters = PluginOperations.filters()
    .guid("test-guid")
    .build();

Collection<Plugin> plugins = api.plugins().list(filters);
```

Other operations have also been included for plugins:
* show(id): returns the plugin for the given id.

<sub>Copyright (c) 2018 opsmatters</sub>