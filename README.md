grails-funky-test-load
======================

Intro
-----
Run functional tests as light load tests. Once you install this plugin, your application will be able to run Geb+Spock functional tests in parallel.
It is very opinionated, and assumes you are using Geb and Spock - the functional testing tools de jour. It will include the necessary dependencies for Geb and Spock. This should be handy for newbies, but might be pain for those who want more control!


Usage
-----
This plugin comes with a script (it's about all it comes with ;) ) which you can use to run your tests. To run your tests run this command like you would any other grails command:

grails funky-test-load

This will bring up a console, and will ask you a number of questions. You can answer these questions with suitable values or hit return to accept the default.

Note
----
To use Chrome you have to install a chrome driver natively on your machine. For more details out the "Install Chrome Driver" in [this blog post](http://programming34m0.blogspot.fr/2012/11/getting-started-with-grails-functional.html)


Credits
-------
* [Run Grails functional tests in parallel blog post](http://fbflex.wordpress.com/2011/12/01/a-script-to-run-grails-functional-tests-in-parallel/)
* [Grails functional-test-development plugin](https://github.com/alkemist/grails-functional-test-development)


