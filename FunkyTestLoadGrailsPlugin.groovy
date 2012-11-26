class FunkyTestLoadGrailsPlugin {
    def version = "0.3.8"
    def grailsVersion = "2.0 > *"
    def title = "Funky Test Load Plugin"
    def author = "Eamonn O'Connell"
    def authorEmail = "twitter:@34m0"
    def description = '''\
Enables functional tests to be used as light load tests.
It is very opinionated, and assumes you are using Geb and Spock - the functional testing tools de jour. 
It will include the necessary dependencies for Geb and Spock. This should be handy for newbies, but a pain for those who want more control.
'''

    def documentation = "https://github.com/halfbaked/grails-funky-test-load"
    def license = "APACHE"
    def issueManagement = [ system: "Github", url: "https://github.com/halfbaked/grails-funky-test-load/issues" ]
    def scm = [ url: "https://github.com/halfbaked/grails-funky-test-load" ]
}
