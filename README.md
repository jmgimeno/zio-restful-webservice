# ZIO Quickstart: Building RESTful Web Service

__NOTE__: This is an _old version_ of the QuickStart RESTful Web Service examples that uses the `zio-http` library from `Dream11`. It also has been migrated from `sbt` to `scala-cli`.

This is the simple quickstart for writing a RESTful ZIO Web Service. You can download and run it very quickly. This will give you an idea of how to write similar apps.

This quickstart shows how to build a simple RESTful web service using ZIO. It uses

- [ZIO HTTP](https://dream11.github.io/zio-http/) for the HTTP server
- [ZIO JSON](https://zio.github.io/zio-json/) for the JSON serialization
- [ZIO Logging](https://zio.github.io/zio-logging/) for integrate logging with slf4j
- [ZIO Config](https://zio.github.io/zio-config/) for loading configuration data

## Running The Example

First, open the console and clone the project using `git` (or you can simply download the project) and then change the directory:

```sh
git clone https://github.com/jmgimeno/zio-restful-webservice.git
cd zio-restful-webservice
```

### Open the project in the editor

To open it in `vscode`,

```sh
scala-cli setup-ide .
code .
```

### Running the project from the command line

Once you are inside the project directory, run the application:

```sh
scala-cli run .
```
