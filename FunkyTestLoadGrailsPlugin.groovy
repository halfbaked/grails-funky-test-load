class FunkyTestLoadGrailsPlugin {
    def version = "0.3.8"
    def grailsVersion = "2.0 > *"
    def title = "Funky Test Load Plugin"
    def author = "Eamonn O'Connell"
    def authorEmail = "twitter:@34m0"
    def description = '''\
Enables functional tests to be used as light load tests. Once you install this plugin, your application will 
be able to run functional tests in parallel, provided you have already done the necessary, to setup your 
application to run functional tests.
'''

    def documentation = "https://github.com/halfbaked/grails-funky-test-load"
    def license = "APACHE"
    def issueManagement = [ system: "Github", url: "https://github.com/halfbaked/grails-funky-test-load/issues" ]
    def scm = [ url: "https://github.com/halfbaked/grails-funky-test-load" ]
}
