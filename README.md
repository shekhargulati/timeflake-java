# Timeflake-Java

Timeflake-Java is the Java port of [timeflake](https://github.com/anthonynsimon/timeflake) Python library.

> Timeflake is a 128-bit, roughly-ordered, URL-safe UUID. Inspired by Twitter's Snowflake, Instagram's ID and Firebase's PushID.

## Usage

```
import timeflake.Timeflake;

var r = Timeflake.random()

r.hex();
r.base62();
r.timestamp();
r.uuid();
```

For more refer to the original [library](https://github.com/anthonynsimon/timeflake).