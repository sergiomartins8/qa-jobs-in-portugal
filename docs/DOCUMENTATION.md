## Documentation

#### Table of Contents

[Understand the Architecture](#understand-the-architecture)

[How do I execute the project?](#how-do-i-execute-the-project)
  * [Custom Execution Options](#custom-execution-options)
  * [Examples](#examples)
  
[Contribute](#contribute)

## Understand the Architecture

The project architecture is pretty straight forward. There are mainly three packages:
1. `pages` map a stateless webpage for a single website.
1. `jobs` applies the state (ie. page navigation) to the current page in order to extract jobs on a single website page.
1. `utils` set of util methods that contain the ability to model objects, write those to markdown files and so on.

![](img/architecture.png)

## How do I execute the project?

1. Set gitlab ssh-keys (https://docs.gitlab.com/ee/ssh/)
1. `git clone git@github.com:sergiomartins8/qa-jobs-in-portugal.git`
1. Using Intellij, enable `annotation processing` under preferences, and install `lombok` plugin
1. Open terminal and execute `$ mvn install`

> You'll need maven installed on your machine

#### Custom Execution Options

This project is built with Selenide (https://selenide.org/) under the hood. Thus, execution options mainly come from selenide itself.

#### Examples
Choose your favorite browser (_default:_ Chrome)
```shell script
$ mvn install -Dselenide.browser=Firefox
```

Execute in headless mode (_default:_ false)
```shell script
$ mvn install -Dselenide.headless=true
```

## Contribute

For contribution check the [contribution](CONTRIBUTING.md) guide.

First contribution? _No problemo._ Check out this amazing [project](https://github.com/firstcontributions/first-contributions) to help you out.
