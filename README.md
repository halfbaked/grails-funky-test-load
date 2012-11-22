grails-funky-test-load
======================

Intro
-----
![multiple chromes](http://halfbaked.github.com/img/multiple-chrome-icons.png) <br/>
Run functional tests as light load tests. Once you install this plugin, your application will be able to run functional tests in parallel, provided you have already done the necessary, to setup your application to run functional tests. If you are not sure what is required checkout [this blog post on setting up functional tests in Grails with Geb and Spock](http://www.34m0.com/2012/11/getting-started-with-grails-functional.html).


Usage
-----
This plugin comes with a script (it's about all it comes with ;) ) which you can use to run your tests. To run your tests run this command like you would any other grails command:

**grails funky-test-load**

This will bring up a console, and will ask you a number of questions. You can answer these questions with suitable values or hit return to accept the default.

Credits
-------
* [Run Grails functional tests in parallel blog post](http://fbflex.wordpress.com/2011/12/01/a-script-to-run-grails-functional-tests-in-parallel/)
* [Grails functional-test-development plugin](https://github.com/alkemist/grails-functional-test-development)



