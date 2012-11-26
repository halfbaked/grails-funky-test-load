grails.project.work.dir = 'target'
grails.project.source.level = 1.6

grails.project.dependency.resolution = {

	inherits 'global'
	log 'warn'

	repositories {
		grailsCentral()
		mavenLocal()
		mavenCentral()
	}

	dependencies {
		compile 'org.aspectj:aspectjtools:1.6.10'
	}

	plugins {
		build(':release:2.1.0', ':rest-client-builder:1.0.2') {
			export = false
		}
	}
}
