# About the project

*Description:*

- Supplier CRUD, developed using SpringBoot (Java) and AngularJS.

# Prerequisites

- Maven. Download link: https://www-us.apache.org/dist/maven/maven-3/3.6.0/binaries/apache-maven-3.6.0-bin.tar.gz
- Git. Download link: https://git-scm.com/download/win

# Downloading the repository

Open the Git Bash inside the directory on which you want to download the repository, and execute the following command:
```
$ git clone https://github.com/luis4129/supplier-challenge-api.git
```

# Building the .jar

If the previous Git Bash is still open, execute the following statement to enter the cloned repository directory:

```
$ cd supplier-challenge
```

Otherwisde, just open another Git Bash at the cloned repository directory.

After that, just execute the following command to generate the `.jar` file (the integration test will alse be executed):

```
$ mvn package
```

# Manually executing the integration tests (Optional)

Just run the following command:
```
$ mvn test
```

# Running the .jar

Run the following statement to enter the `.jar` directory:

```
$ cd target
```

Now, all that's left is to run the `.jar` file with the statement below:

```
$ java -jar supplier-challenge-0.0.1-SNAPSHOT.jar
```

# Done!
Just open the URL below, it should be up and running:
http://localhost:8080/
