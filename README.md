# demo
This repo is for test demo

1. service is exposed at 5555 port
    http://localhost:5555/ --> will redirect to swagger ui where we can access end points for different operations.
2. actuator is exposed at 1234 port
    http://localhost:1234/actuator --> shows actuator end points like health, info, metricts n etc...
3. used flyway for db migration scripts, here I have put create and insert script in the version file which executes during startup.
