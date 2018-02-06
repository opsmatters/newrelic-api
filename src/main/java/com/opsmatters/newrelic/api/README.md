![opsmatters](https://i.imgur.com/VoLABc1.png)

## How to use the New Relic API
### Table of contents

- [Initialisation](#initialisation)

#### REST v2 API
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
- [Applications](#applications)
- [Application Hosts](#application-hosts)
- [Application Instances](#application-instances)
- [Browser Applications](#browser-applications)
- [Mobile Applications](#mobile-applications)
- [Key Transactions](#key-transactions)
- [Plugins](#plugins)
- [Plugin Components](#plugin-components)
- [Servers](#servers)
- [Deployments](#deployments)
- [Labels](#labels)
- [Users](#users)
- [Usages](#usages)
- [Dashboards](#insights-dashboards)

#### Synthetics v1/v3 API
- [Monitors](#monitors)
- [Locations](#locations)

#### Plugins v1 API
- [Plugins Metrics](#plugins-metrics)

#### Insights v1 API
- [Query](#insights-query)

#### Partner v2 API
- [Accounts](#partner-accounts)
- [Users](#partner-users)
- [Subscriptions](#partner-subscriptions)

### Initialisation

#### REST API

To obtain a client instance to carry out operations with the New Relic REST API use the builder provided:
```
NewRelicApi api = NewRelicApi.builder()
    .hostname("api.newrelic.com")
    .port(443)
    .apiKey("<YOUR_API_KEY>")
    .build();
```
Alternatively, if the hostname and port are omitted they default to "api.newrelic.com" and 443 respectively, so this becomes:
```
NewRelicApi api = NewRelicApi.builder()
    .apiKey("<YOUR_API_KEY>")
    .build();
```

#### Infrastructure API

To obtain a client instance to carry out operations with the New Relic Infrastructure REST API use the builder provided:
```
NewRelicInfraApi infraApi = NewRelicInfraApi.builder()
    .hostname("infra-api.newrelic.com")
    .port(443)
    .apiKey("<YOUR_API_KEY>")
    .build();
```
Again, if the hostname and port are omitted they default to "infra-api.newrelic.com" and 443 respectively, so this becomes:
```
NewRelicInfraApi infraApi = NewRelicInfraApi.builder()
    .apiKey("<YOUR_API_KEY>")
    .build();
```

#### Synthetics API

To obtain a client instance to carry out operations with the New Relic Synthetics REST API use the builder provided:
```
NewRelicSyntheticsApi syntheticsApi = NewRelicSyntheticsApi.builder()
    .hostname("synthetics.newrelic.com")
    .port(443)
    .apiKey("<YOUR_API_KEY>")
    .build();
```
Again, if the hostname and port are omitted they default to "synthetics.newrelic.com" and 443 respectively, so this becomes:
```
NewRelicSyntheticsApi syntheticsApi = NewRelicSyntheticsApi.builder()
    .apiKey("<YOUR_API_KEY>")
    .build();
```

#### Insights API

To obtain a client instance to carry out operations with the New Relic Insights REST API use the builder provided:
```
NewRelicInsightsApi insightsApi = NewRelicInsightsApi.builder()
    .hostname("insights-api.newrelic.com")
    .port(443)
    .queryKey("<YOUR_QUERY_KEY>")
    .build();
```
Again, if the hostname and port are omitted they default to "insights-api.newrelic.com" and 443 respectively, so this becomes:
```
NewRelicInsightsApi insightsApi = NewRelicInsightsApi.builder()
    .queryKey("<YOUR_QUERY_KEY>")
    .build();
```

#### Plugins API

To obtain a client instance to carry out operations with the New Relic Plugins API use the builder provided:
```
NewRelicPluginsApi pluginsApi = NewRelicPluginsApi.builder()
    .hostname("platform-api.newrelic.com")
    .port(443)
    .licenseKey("<YOUR_LICENSE_KEY>")
    .build();
```
Again, if the hostname and port are omitted they default to "platform-api.newrelic.com" and 443 respectively, so this becomes:
```
NewRelicPluginsApi pluginsApi = NewRelicPluginsApi.builder()
    .licenseKey("<YOUR_LICENSE_KEY>")
    .build();
```

#### Partner API

To obtain a client instance to carry out operations with the New Relic Partner REST API use the builder provided:
```
NewRelicPartnerApi partnerApi = NewRelicPartnerApi.builder()
    .hostname("rpm.newrelic.com")
    .port(443)
    .apiKey("<YOUR_PARTNER_ACCOUNT_KEY>")
    .build();
```
Again, if the hostname and port are omitted they default to "rpm.newrelic.com" and 443 respectively, so this becomes:
```
NewRelicPartnerApi partnerApi = NewRelicPartnerApi.builder()
    .apiKey("<YOUR_PARTNER_ACCOUNT_KEY>")
    .build();
```

### Alerts Channels
To create an email alert channel, first instantiate the channel object and then pass it to the "create" operation:
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
* list(name): returns the alert channels with the given name.
* show(channelId): returns the alert channel with the given id.
* delete(channelId): deletes the alert channel with the given id.

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
To create an alert policy called "my-policy" with a PER_POLICY rollup strategy, first instantiate the policy and then pass it to the "create" operation:
```
AlertPolicy p = AlertPolicy.builder()
    .name("my-policy")
    .perPolicyIncidentPreference()
    .build();

AlertPolicy policy = api.alertPolicies().create(p).get();
```
The alert policy returned includes all the additional fields that were populated by the server on creation eg, "id", "created_at".

Other operations have also been included for alert policies:
* list(): returns all alert policies for the account.
* list(name): returns all alert policies with the given name.
* show(name,policyId): returns the alert policy with the given name and id.
* show(policyId): returns the alert policy with the given id.
* update(policy): updates the alert policy with the given policy details.
* delete(policyId): deletes the alert policy with the given id.

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
    .violationCloseTimer(AlertCondition.ViolationCloseTimerInterval.HOURS_12)
    .addTerm(term)
    .enabled(true)
    .build();

AlertCondition condition = api.alertConditions().create(policy.getId(), c).get();
```
The APM alert condition returned includes all the additional fields that were populated by the server on creation eg, "id".

Other operations have also been included for APM alert conditions:
* list(policyId): returns all APM alert conditions for the given policy id.
* list(policyId,name): returns all APM alert conditions for the given policy id and name.
* show(policyId,conditionId): returns the APM alert condition for the given policy id and condition id.
* delete(conditionId): deletes the APM alert condition with the given id.

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
* show(policyId,conditionId): returns the NRQL alert condition for the given policy id and condition id.
* delete(conditionId): deletes the NRQL alert condition with the given id.

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
* show(policyId,conditionId): returns the external service alert condition for the given policy id and condition id.
* delete(conditionId): deletes the external service alert condition with the given id.

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
    .name("my-metric-error")
    .metric("my-metric")
    .metricDescription("my-metric-description")
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
* show(policyId,conditionId): returns the Plugins alert condition for the given policy id and condition id.
* delete(conditionId): deletes the Plugins alert condition with the given id.

### Alerts Synthetics Conditions
To add a critical Synthetics alert condition for a monitor, instantiate a condition object and then pass it to the "create" operation:
```
SyntheticsAlertCondition c = SyntheticsAlertCondition.builder()
    .name("my-synthetics-error")
    .monitorId("abcde-12345-abcde-12345")
    .enabled(true)
    .build();

SyntheticsAlertCondition condition = api.syntheticsAlertConditions().create(policy.getId(), c).get();
```
The Synthetics alert condition returned includes all the additional fields that were populated by the server on creation eg, "id".

Other operations have also been included for Synthetics alert conditions:
* list(policyId): returns all Synthetics alert conditions for the given policy id.
* show(policyId,conditionId): returns the Synthetics alert condition for the given policy id and condition id.
* delete(conditionId): deletes the Synthetics alert condition with the given id.

### Alerts Infrastructure Conditions
To add a critical infrastructure alert condition for disk utilisation > 80%, instantiate a condition object and then pass it to the "create" operation:
```
InfraAlertCondition c = InfraMetricAlertCondition.builder()
    .policyId(policy.getId())
    .name("disk-space-error")
    .storageEventType()
    .selectValue("diskUsedPercent")
    .criticalThreshold(new AlertThreshold().builder().durationMinutes(10).value(80).allTimeFunction().build())
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
    .criticalThreshold(new AlertThreshold(10))
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
    .criticalThreshold(new AlertThreshold().builder().durationMinutes(10).value(0).build())
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
* show(conditionId): returns the infrastructure alert condition for the given condition id.
* delete(conditionId): deletes the infrastructure alert condition with the given id.

### Alerts Events
To list all events, call the "list" operation with no parameters:
```
Collection<AlertEvent> events = api.alertEvents().list();
```

To list all events matching one or more filters, build the set of filters and pass it to the "list" operation:
```
Map<String,Object> filters = AlertEventService.filters()
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
Map<String,Object> filters = ApplicationService.filters()
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
* show(applicationId): returns the application for the given id.
* delete(applicationId): deletes the application with the given id.
* metricNames(applicationId): returns the metrics and their value names for the given application.
* metricNames(applicationId, name): returns the metrics and their value names for the given application, where the value names match the given name.

### Application Hosts
To list the hosts for an application call the "list" operation with a set of filters:
```
List<String> filters = ApplicationHostService.filters()
    .hostname("my-host")
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
List<String> filters = ApplicationInstanceService.filters()
    .hostname("my-host")
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
    .name("my-browser-app")
    .build();

BrowserApplication application = api.browserApplications().create(a).get();
```
The Browser application returned includes all the additional fields that were populated by the server on creation eg, "id", "browser_monitoring_key".

Other operations have also been included for Browser applications:
* list(): returns all Browser applications.
* show(applicationId): returns the Browser application for the given id.

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
* show(applicationId): returns the mobile application for the given id.
* metricNames(applicationId): returns the metrics and their value names for the given mobile application.
* metricNames(applicationId, name): returns the metrics and their value names for the given mobile application, where the value names match the given name.

### Key Transactions
To list the key transactions call the "list" operation with a set of filters:
```
List<String> filters = KeyTransactionService.filters()
    .name("my-transaction")
    .build();

Collection<KeyTransaction> transactions = api.keyTransactions().list(filters);
```

Other operations have also been included for key transactions:
* show(transactionId): returns the key transaction for the given id.

### Plugins
To list the installed plugins call the "list" operation with a set of filters:
```
List<String> filters = PluginService.filters()
    .guid("12345-54321")
    .build();

Collection<Plugin> plugins = api.plugins().list(filters);
```

Other operations have also been included for plugins:
* show(pluginId): returns the plugin for the given id.

### Plugin Components
To list the components for a plugin call the "list" operation with a set of filters:
```
List<String> filters = PluginComponentService.filters()
    .name("plugin-name")
    .build();

Collection<PluginComponent> components = api.pluginComponents().list(filters);
```
To list the plugin component metrics using one or more parameters, build the parameter list and then pass it to the "metricData" operation:
```
List<String> parameters = MetricParameterBuilder.builder()
    .names("Threads/SummaryState/RUNNABLE/Count")
    .values("call_count")
    .from(System.currentTimeMillis()-(3600*1000L)) // last 60 minutes
    .to(System.currentTimeMillis())
    .summarize(true)
    .build();

MetricData metrics = api.pluginComponents().metricData(componentId, parameters).get();
```

Other operations have also been included for plugin components:
* show(componentId): returns the plugin component for the given id.
* metricNames(componentId): returns the metrics and their value names for the given plugin component.
* metricNames(componentId, name): returns the metrics and their value names for the given plugin component, where the value names match the given name.

### Servers
To update a server's name, instantiate a server object and then pass it to the "update" operation:
```
Server s = Server.builder()
    .id(12345)
    .name("new-server-name")
    .build();

Server server = api.servers().update(s).get();
```
The "id" field needs to be provided as it is used to locate the existing server to update.

To list the servers matching one or more filters, build the filters and then pass it to the "list" operation:
```
Map<String,Object> filters = ServerService.filters()
    .reported(true) // all servers that have reported in the last 10 hours
    .build();

Collection<Server> servers = api.servers().list(filters);
```
To list the server metrics using one or more parameters, build the parameter list and then pass it to the "metricData" operation:
```
List<String> parameters = MetricParameterBuilder.builder()
    .names("System/Memory/Used/bytes")
    .values("average_response_time")
    .from(System.currentTimeMillis()-(3600*1000L)) // last 60 minutes
    .to(System.currentTimeMillis())
    .summarize(true)
    .build();

MetricData metrics = api.servers().metricData(parameters).get();
```

Other operations have also been included for servers:
* list(): returns all servers.
* show(serverId): returns the server for the given id.
* delete(serverId): deletes the server with the given id.
* metricNames(serverId): returns the metrics and their value names for the given server.
* metricNames(serverId, name): returns the metrics and their value names for the given server, where the value names match the given name.

### Deployments
To create an deployment for an application, first instantiate the deployment and then pass it to the "create" operation:
```
Deployment d = Deployment.builder()
    .description("deployment-name")
    .revision("1.0")
    .changelog("some changes")
    .user("me")
    .build();

Deployment deployment = api.deployments().create(applicationId, d).get();
```
The deployment returned includes all the additional fields that were populated by the server on creation eg, "id", "timestamp".

Other operations have also been included for deployments:
* list(applicationId): returns all deployments for the application.
* show(applicationId, deploymentId): returns the deployment with the given id.
* delete(applicationId, deploymentId): deletes the deployment with the given id.

### Labels
To create a label for an application or server, first instantiate the label and then pass it to the "create" operation:
```
Label l = Label.builder()
    .category("Production")
    .name("Main")
    .build();

Label label = api.labels().create(l).get();
```
The label returned includes all the additional fields that were populated by the server on creation eg, "key".

Other operations have also been included for labels:
* list(): returns all labels.
* show(key): returns the label with the given key.
* delete(key): deletes the label with the given key.

### Monitors
To create a simple (ping) monitor for a URL, first instantiate the monitor and then pass it to the "create" operation:
```
SimpleMonitor sm = SimpleMonitor.builder()
    .name("my-simple-monitor")
    .frequency(Monitor.Frequency.MINUTES_60)
    .uri("http://test.com")
    .slaThreshold(1.0)
    .status(Monitor.Status.ENABLED)
    .addLocation("LINODE_EU_WEST_1")
    .validationStringOption("phrase")
    .verifySslOption(true)
    .build();

Monitor monitor = syntheticsApi.monitors().create(sm).get();
```
The monitor returned includes all the additional fields that were populated by the server on creation eg, "id".

Alternatively, to create a scripted monitor, first instantiate the monitor and then pass it to the "create" operation:
```
ScriptBrowserMonitor sbm = ScriptBrowserMonitor.builder()
    .name("my-script-monitor")
    .frequency(Monitor.Frequency.MINUTES_60)
    .slaThreshold(1.0)
    .status(Monitor.Status.ENABLED)
    .addLocation("LINODE_EU_WEST_1")
    .build();

Monitor monitor = syntheticsApi.monitors().create(sbm).get();
```
Next, add a script to the monitor by instantiating the script and then passing it to the "updateScript" operation with the id of the monitor:
```
Script script = Script.builder()
    .scriptText("dmFyIGFzc2VydCA9IHJlcXVpcmUoJ2Fzc2VydCcpOw0KYXNzZXJ0LmVxdWFsKCcxJywgJzEnKTs=")
    .build();

syntheticsApi.monitors().updateScript(monitor.getId(), script).get();
```
Note that the "scriptText" parameter in the payload contains a Base64 encoded version of the script to be added.

Other operations have also been included for monitors:
* list(): returns all monitors.
* list(label): returns all monitors with the given label.
* show(monitorId): returns the monitor with the given id.
* showScript(monitorId): returns the script for the monitor with the given id.
* update(monitor): updates the monitor with the given details (full update).
* patch(monitor): updates the monitor with the given details (partial update).
* delete(monitorId): deletes the monitor with the given id.
* createLabel(monitorId,label): adds the label to the monitor with the given id.
* deleteLabel(monitorId,label): deletes the label from the monitor with the given id.

### Locations
To list the valid locations call the "list" operation:
```
Collection<Location> locations = syntheticsApi.locations().list();
```

### Plugins Metrics
To send a set of metrics to New Relic build the metric component with the required timeslices, and then call the "metricData" operation:
```
MetricTimeslice<Integer> timeslice1 = MetricTimeslice.<Integer> builder()
    .total(50)
    .count(4)
    .min(10)
    .max(15)
    .sumOfSquares(325)
    .build();

MetricTimeslice<Double> timeslice2 = MetricTimeslice.<Double> builder()
    .total(50.1)
    .count(4.1)
    .min(10.1)
    .max(15.1)
    .sumOfSquares(325.1)
    .build();

Component component = Component.builder()
    .name("my-component")
    .guid("com.test.my-plugin")
    .duration(60)
    .addMetric("Component/Database[Queries/First]", 100) // scalar metric
    .addMetric("Component/Database[Queries/Second]", new int[] {25, 2, 10, 15, 325}) // array metric
    .addMetric("Component/Database[Queries/Third]", timeslice1) // int timeslice
    .addMetric("Component/Database[Queries/Fourth]", timeslice2) // float timeslice
    .build();

PluginData data = PluginData.builder()
    .host("my-host")
    .pid(12345)
    .version("1.0")
    .addComponent(component)
    .build();

Status status = pluginsApi.metrics().metricData(data).get();
Assert.assertTrue(status.getStatus().equals("ok"));
```
If the data was sent successfully, a "status" attribute is returned with a value of "ok".

### Users
To list the users call the "list" operation with a set of filters:
```
List<String> filters = UserService.filters()
    .email("me@test.com")
    .build();

Collection<User> users = api.users().list(filters);
```

Other operations have also been included for users:
* show(userId): returns the user for the given id.
* resetPassword(userId): resets the password for the user with the given id.

### Usages
To list the usages for a product call the "list" operation with a set of parameters:
```
Calendar c = Calendar.getInstance();
c.add(Calendar.DATE, -1); // usages from yesterday to today
long startDate = c.getTimeInMillis();
long endDate = System.currentTimeMillis();
UsageData usage = api.usages().list(product, startDate, endDate, true).get();
```

### Insights Query
To execute an Insights query and get the results call the "list" operation:
```
String query = "SELECT average(duration) FROM PageView";
QueryData data = insightsApi.queries().list(accountId, query).get();
```

### Insights Dashboards
To create a dashboard, first instantiate the dashboard and then pass it to the "create" operation:
```
Dashboard d = Dashboard.builder()
    .title("my-policy")
    .version(1)
    .icon(Dashboard.Icon.BAR_CHART)
    .allVisibility()
    .ownerEditable()
    .addFilter("ProcessSample", "commandName")
    .build();

Dashboard dashboard = api.dashboards().create(d).get();
```
The dashboard returned includes all the additional fields that were populated by the server on creation eg, "id", "created_at" and "owner_email".

To list the dashboards matching one or more filters, build the filters and then pass it to the "list" operation:
```
Map<String,Object> filters = DashboardService.filters()
    .title("test-dashboard")
    .build();

Collection<Dashboard> dashboards = api.dashboards().list(filters);
```

To add a new widget to an existing dashboard, first create the chart, add it to the dashboard, and then call the "update" operation.
Below are examples of how to add some common widget types to the dashboard created in the previous example.

#### Adding a Histogram widget
To add a histogram widget to an existing dashboard:
```
dashboard.addWidget(EventChart.builder()
    .visualization(EventChart.Visualization.HISTOGRAM)
    .accountId(accountId)
    .title("event-title")
    .notes("event notes")
    .position(1,1)
    .size(1,1)
    .addNrqlData("SELECT histogram(threadCount,10,20) from ProcessSample SINCE yesterday")
    .build());

dashboard = api.dashboards().update(dashboard).get();
```

#### Adding a Gauge widget
To add a gauge widget to an existing dashboard:
```
dashboard.addWidget(ThresholdEventChart.builder()
    .visualization(ThresholdEventChart.Visualization.GAUGE)
    .title("threshold-title")
    .notes("threshold notes")
    .accountId(accountId)
    .position(1,2)
    .size(1,1)
    .addNrqlData("SELECT average(cpuPercent) from ProcessSample SINCE 10 minutes ago")
    .threshold(Threshold.builder().red(10).yellow(5).build())
    .build());

dashboard = api.dashboards().update(dashboard).get();
```

#### Adding a Pie Chart widget
To add a pie chart widget to an existing dashboard:
```
dashboard.addWidget(FacetChart.builder()
    .visualization(FacetChart.Visualization.FACET_PIE_CHART)
    .title("facet-pie-title")
    .notes("facet pie notes")
    .accountId(accountId)
    .position(2,1)
    .size(2,1)
    .addNrqlData("SELECT count(*) FROM ProcessSample SINCE 1 DAY AGO FACET commandName")
    .drilldownDashboardId(dashboardId)
    .build());

dashboard = api.dashboards().update(dashboard).get();
```

#### Adding a Metric Line Chart widget
To add a metric line chart widget to an existing dashboard:
```
MetricsData data = MetricsData.builder()
    .duration(86400000) // 1 day
    .addEntityId(entityId)
    .addMetric(Metric.builder().name(Metric.Category.APDEX).addValue(Metric.Apdex.SCORE).build())
    .orderBy(Metric.Apdex.SCORE)
    .limit(10)
    .build();

dashboard.addWidget(MetricLineChart.builder()
    .title("metric-line-title")
    .notes("metric line notes")
    .accountId(accountId)
    .position(2,3)
    .size(1,1)
    .addData(data)
    .build());

dashboard = api.dashboards().update(dashboard).get();
```

#### Adding a Breakdown Chart widget
To add a breakdown chart widget to an existing dashboard:
```
MetricsData data = MetricsData.builder()
    .duration(86400000) // 1 day
    .addEntityId(entityId)
    .build();

dashboard.addWidget(BreakdownMetricChart.builder()
    .visualization(BreakdownMetricChart.Visualization.APPLICATION_BREAKDOWN)
    .title("breakdown-title")
    .notes("breakdown notes")
    .accountId(accountId)
    .position(3,1)
    .size(1,1)
    .addData(data)
    .build());

dashboard = api.dashboards().update(dashboard).get();
```

#### Adding a Traffic Light widget
To add a traffic light widget to an existing dashboard:
```
TrafficLight trafficLight = TrafficLight.builder()
    .id("12345")
    .title("cpu-percent")
    .subtitle("maximum")
    .addState(TrafficLightState.builder().wrongType().min(0).max(3).build())
    .addState(TrafficLightState.builder().warningType().min(3).max(7).build())
    .addState(TrafficLightState.builder().okType().min(7).max(10).build())
    .build();

dashboard.addWidget(TrafficLightChart.builder()
    .title("traffic-light-title")
    .notes("traffic light notes")
    .accountId(accountId)
    .position(3,2)
    .size(1,1)
    .addNrqlData("SELECT max(cpuPercent) from ProcessSample SINCE 10 minutes ago")
    .addTrafficLight(trafficLight)
    .build());

dashboard = api.dashboards().update(dashboard).get();
```

#### Adding an Inventory Chart widget
To add an inventory chart widget to an existing dashboard:
```
dashboard.addWidget(InventoryChart.builder()
    .title("inventory-title")
    .notes("inventory notes")
    .accountId(accountId)
    .position(4,1)
    .size(1,1)
    .addSourceData("metadata/system")
    .addFilter("operatingSystem", "linux")
    .build());

dashboard = api.dashboards().update(dashboard).get();
```

#### Adding a Markdown widget
To add a markdown widget to an existing dashboard:
```
dashboard.addWidget(Markdown.builder()
    .title("markdown-title")
    .notes("markdown notes")
    .accountId(accountId)
    .position(4,2)
    .size(2,1)
    .addSourceData("# Dashboard Notes\n\nHere are some notes")
    .build());

dashboard = api.dashboards().update(dashboard).get();
```

Other operations have also been included for dashboards:
* list(): returns all dashboards for the account.
* show(dashboardId): returns the dashboard with the given id.
* update(dashboard): updates the dashboard with the given dashboard details and widgets.
* delete(dashboardId): deletes the dashboard with the given id.

### Partner Accounts
To create a partner account, first instantiate the account with a set of users and subscriptions and then pass it to the "create" operation:
```
PartnerUser user = PartnerUser.builder()
    .firstName("John")
    .lastName("Doe")
    .email("john.doe@test.com")
    .role(User.Role.ADMIN)
    .owner(true)
    .password("XXXXXXXX")
    .build()

PartnerAccount a = PartnerAccount.builder()
    .name("test-account")
    .phoneNumber("0208234567"
    .allowApiAccess(true)
    .addUser(user)
    .addSubscription(ProductSubscription.builder().productId(4).quantity(10).build())
    .addSubscription(ProductSubscription.builder().productId(10).quantity(0).build())
    .build();

PartnerAccount account = partnerApi.accounts().create(partnerId, a).get();
```
The account returned includes all the additional fields that were populated by the server on creation eg, "id", "status".

Other operations have also been included for partner accounts:
* list(partnerId): returns all accounts for the given partner id.
* show(partnerId, accountId): returns the account with the given account id for the given partner id.
* update(partnerId, account): updates the account with the given account details.
* delete(partnerId, accountId): deletes the account with the given id for the given partner id.

### Partner Users
To create a partner user, first instantiate the user and then pass it to the "create" operation:
```
PartnerUser u = PartnerUser.builder()
    .firstName("John")
    .lastName("Smith")
    .email("john.smith@test.com")
    .role(User.Role.USER)
    .owner(false)
    .password("XXXXXXXX")
    .build()

User user = partnerApi.users().create(partnerId, accountId, u).get();
```
The user returned includes all the additional fields that were populated by the server on creation eg, "id".

Other operations have also been included for partner users:
* list(partnerId, accountId): returns all users for the given partner id and account id.
* update(partnerId, accountId, user): updates the user with the given user details.
* delete(partnerId, accountId, userId): deletes the user with the given id for the given partner id and account id.

### Partner Subscriptions
To replace the product subscriptions for a partner account, first instantiate a list of subscriptions and then pass it to the "create" operation:
```
List<ProductSubscription> subscriptions = new ArrayList<ProductSubscription>();
subscriptions.add(ProductSubscription.builder().productId(4).quantity(10).build());
subscriptions.add(ProductSubscription.builder().productId(10).quantity(0).build());
PartnerSubscription subscription = partnerApi.subscriptions().create(partnerId, accountId, subscriptions).get();
```
The subscription returned includes all the additional fields that were populated by the server on creation eg, "id", "status".

Other operations have also been included for partner subscriptions:
* list(partnerId, accountId): returns all subscriptions for the given partner id and account id.
* show(partnerId, accountId, subscriptionId): returns the subscription with the given id for the given partner id and account id.

<sub>Copyright (c) 2018 opsmatters</sub>