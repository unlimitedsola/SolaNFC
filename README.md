# SolaNFC

_A scriptable Mifare Classic tool._

SolaNFC is a scriptable
[Mifare Classic](https://en.wikipedia.org/wiki/MIFARE#MIFARE_Classic_family)
tool by leveraging
[IntelliJ IDEA](https://jetbrains.com/idea)
and
[Kotlin](https://kotlinlang.org/).
SolaNFC provides you the ability to write concise scripts with code completion and easy execution, allows you to create
your prototype rapidly.

## Available Features

- [x] Block Authentication
- [x] Block Reading/Writing
- [x] Support unlocked cards (Chinese Clone)
- [x] Dumping/Formatting utilities

### Supported Interfaces

- [x] PC/SC

### Supported Card Terminals

- [x] ACR122U

## User Guide

### Prerequisites

- Java Development Kit(JDK) 21 or higher
- IntelliJ IDEA 2024.1 or above

### Installation

1. Clone this repository
2. Import as IntelliJ IDEA project via `build.gralde` file.
3. Wait for IntelliJ IDEA while creating the project structure.
4. Create your script inside `scripts/custom` folder.

### Code Completion

IntelliJ IDEA should provide you with code completion automatically.

### Your first line of code

You can get your current connected tag by invoking the `getCard` function.

```kotlin
val card = getCard()!!
``` 

After your operations are done, you can wait for your tag to disconnect by using `waitDisconnect`, this function blocks
until the tag is disconnected.

### Executing your script

Simply by right-clicking your current editing script inside IntelliJ IDEA and choose `Run/Debug` action.

### Going further

For more examples, check out the sample scripts inside `scripts/sample` folder.
