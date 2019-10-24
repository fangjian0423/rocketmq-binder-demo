Apps:

```bash
app register --name source-app --type source --uri maven://com.alibaba.cloud:rocketmq-time-source:jar:0.2.2.BUILD-SNAPSHOT
app register --name processor-app --type processor --uri maven://com.alibaba.cloud:rocketmq-time-processor:jar:0.2.2.BUILD-SNAPSHOT
app register --name sink-app --type sink --uri maven://com.alibaba.cloud:rocketmq-time-sink:jar:0.2.2.BUILD-SNAPSHOT
```

Streams:

```bash
stream create --name log-data --definition 'source-app | processor-app | sink-app'
```

Deploys(input it in freetext in spring cloud dataflow dashboard):

```properties
app.source-app.spring.cloud.stream.bindings.output.destination=scdf-log-source
app.processor-app.spring.cloud.stream.bindings.output.destination=scdf-log-processor
app.processor-app.spring.cloud.stream.bindings.input.destination=scdf-log-source
app.processor-app.spring.cloud.stream.bindings.input.group=scdf-log-source-group
app.sink-app.spring.cloud.stream.bindings.input.destination=scdf-log-processor
app.sink-app.spring.cloud.stream.bindings.input.group=scdf-log-processor-group
```
