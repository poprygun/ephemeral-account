## Facade service

Fronts both `Cache` and `Real` services.

Run below to apply load to the app
```bash
while true; do sleep 1; curl -I -X GET 'https://facade.cfapps.io/accounts?cif=xyz2';done
```

cf unbind-service facade circuit-console-service
cf delete-service circuit-console-service
cf create-service p-circuit-breaker-dashboard standard circuit-breaker-dashboard
cf bind-service facade circuit-breaker-dashboard
cf restage facade