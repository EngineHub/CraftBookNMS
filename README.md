CraftBookNMS
===========

This project is an extension of CraftBook, to provide some fixes that can't be done with the Bukkit/Spigot API.

The following are some of the major fixes:

* Partial fix for Spigot's 1.12 Tile Entity performance regression

Compiling
---------

The project is written for Java 8 and our build process makes use of [Gradle](http://gradle.org). Once installed,
simply run:

    gradlew build

Dependencies are automatically handled by Gradle.

If Spigot BuildTools have never been run, that will need to be done first before compiling. If an error occurs in dependency resolution, re-running
 Spigot BuildTools should be the first attempt at fixing.

Contributing
------------

We happily accept contributions. The best way to do this is to fork CraftBookNMS
on GitHub, add your changes, and then submit a pull request. We'll look at it,
make comments, and merge it into CraftBookNMS if everything works out.

In addition, please ensure your code is compliant with the Google Code
Conventions to keep things neat and readable.

By submitting code, you agree to place to license your code under the 
irrevocable MIT License.

Links
-----

* [CraftBook](https://github.com/EngineHub/CraftBook)
* [IRC channel](http://skq.me/irc/irc.esper.net/sk89q/) (#sk89q on irc.esper.net)
* [Issue tracker](http://youtrack.sk89q.com/issues/CRAFTBOOK)