# android-nmea-parser

Light-weight Android Java library for NMEA sentences parsing


## Supported sentences

* GPRMC, GNRMC v4.10
* GPGGA, GNGGA
* GPGSV
* GPGSA

## Fork Changes

* Abstract the sentence prefix -- instead of just `GP`, `GN` and other 2-letter codes are allowed.
* Fixed parsing of GNMRC v4.10 with the extra field 14 (not used).
* Fixed parsing of GNGGA with negative alt and separation fields.


## NMEA Parser

Flow parser build on top of the [BasicNMEAParser](src/main/java/com/github/petr_s/nmea/basic/BasicNMEAParser.java)
that maps raw NMEA data to useful Android objects such as [Location](https://developer.android.com/reference/android/location/Location.html) and [GpsSatellite](https://developer.android.com/reference/android/location/GpsSatellite.html)

### Location parsing

To get an Android Location object you have to parse both RMC and GGA with the same time.


```java
NMEAHandler handler = new NMEAHandler() {
    ...
    @Override
    public void onLocation(Location location) {

    }
    ...
};
NMEAParser parser = new NMEAParser(handler);
parser.parse("$GPRMC,163407.000,A,5004.7485,N,01423.8956,E,0.04,36.97,180416,,*38");
parser.parse("$GPGGA,163407.000,5004.7485,N,01423.8956,E,1,07,1.7,285.7,M,45.5,M,,0000*5F");
```


### Satellites parsing

To get a list of gps satellites you have to parse all of GSVs and at least one GSA sentence.
Since [Android GpsSatellite class](https://developer.android.com/reference/android/location/GpsSatellite.html) is inaccessible (only trough reflection),
 the package level [GpsSatellite](src/main/java/com/github/petr_s/nmea/GpsSatellite.java) is introduced.

```java
NMEAHandler handler = new NMEAHandler() {
    ...
    @Override
    public void onSatellites(List<GpsSatellite> satellites) {

    }
    ...
};
NMEAParser parser = new NMEAParser(handler);
parser.parse("$GPGSV,3,1,11,29,86,273,30,25,60,110,38,31,52,278,47,02,28,050,39*7D");
parser.parse("$GPGSV,3,2,11,12,23,110,34,26,18,295,29,21,17,190,30,05,11,092,25*72");
parser.parse("$GPGSV,3,3,11,14,02,232,13,23,02,346,12,20,01,135,13*48");
parser.parse("$GPGSA,A,3,25,02,26,05,29,31,21,12,,,,,1.6,1.0,1.3*3B");
```

If you don't need all methods there's also an [Adapter](src/main/java/com/github/petr_s/nmea/NMEAAdapter.java).


## Basic NMEA Parser

Flow parser that allows you to access raw NMEA data:

```java
BasicNMEAHandler handler = new BasicNMEAHandler() {
    ...
    @Override
    public void onRMC(long date, long time, double latitude, double longitude, float speed, float direction) {
    }
    ...
};
BasicNMEAParser parser = new BasicNMEAParser(handler);
parser.parse("$GPRMC,163407.000,A,5004.7485,N,01423.8956,E,0.04,36.97,180416,,*38");
```

If you don't need all methods there's also an [Adapter](src/main/java/com/github/petr_s/nmea/basic/BasicNMEAAdapter.java).


## Gradle Usage

This fork is not exported to Maven Central like the original was.

To use it, get the source via a Git Submodule:

```shell
$ git submodule add "https://github.com/ralfoide/android-nmea-parser" AndroidNmeaParser
```

`settings.gradle.kts`:
```
include(":AndroidNmeaParser")
```

`build.gradle.kts`:
```
dependencies {
    implementation(project(":AndroidNmeaParser"))
}
```

~~
