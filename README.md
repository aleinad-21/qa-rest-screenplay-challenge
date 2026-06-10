# qa-rest-screenplay-challenge

API automation project using:

- Java 21
- Gradle
- Serenity BDD
- Screenplay Pattern
- JUnit 5
- REST Assured

---

## Configure Environment Variable

Before running the project, you must set your Go REST token as an environment variable in https://gorest.co.in/consumer/login.

### Mac/Linux

```bash
export GOREST_TOKEN="your_token_here"
or run the tests directly using the generated token, e.g.: GOREST_TOKEN="your_token_here" ./gradlew clean test