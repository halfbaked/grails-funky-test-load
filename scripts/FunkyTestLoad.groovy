import org.codehaus.groovy.grails.cli.CommandLineHelper


target(default: "Runs functional tests in parallel inorder to simulate concurrent users") {

  def reportsDir = 'target/test-reports/functestload'  
  new File(reportsDir).mkdirs()
	def inputHelper = new CommandLineHelper()
  def threads = []
  def highlights

  def target = "http://localhost:8080/"
  def numberOfUsers = 1
  def browser = "htmlunit"
  def pattern = ""
  
  while(true){
    highlights = [] // reset
    def line
    println ""
		println "Ready to load test funky style"
		println " - Just hit return on the following questions to use defaults"
		println " - Ctrl+c to exit"
		println ""
    def askForTarget = true
    while(askForTarget) {
      println "What is your target [$target]? "
      line = inputHelper.userInput("")?.trim()
      if(!line || validateUrl(line)) {
        if (line) target = line
        askForTarget = false
      }
    }
		//println ""
    println "How many users [$numberOfUsers]?"
    line = inputHelper.userInput("")?.trim()
    if (line) numberOfUsers = new Integer(line)
		//println ""
    println "Want a test pattern [$pattern]?"
    line = inputHelper.userInput("")?.trim()
    if (line) pattern = line
    //println ""
    println "What browser [$browser]?"
    println "Possibles: [chrome, firefox, htmlunit]"
    line = inputHelper.userInput("")?.trim()
    if (line) browser = line

    println """

      (             ____
           )    .;'`    `';,
          (    //          \\\\
           ,_ ||  _..-n-.._ ||
          (c)\\||-"'-.....-"-;|
           \\  ;'             Y
            \\_/`              \\
            ;                  ;
            |                  |
            |                  |
       jgs   '.__          __.'
              ``'''''''''''``
     The time is now ${new Date().format("HH:mm:ss")}.     
     This can take a while. Put the kettle on. Relax.
     
    """
    
    numberOfUsers.times { threadNum ->
      threads << Thread.start {
        def logPrefix = "User $threadNum"
        def reportDir = new File(reportsDir + '/' + 'user' + threadNum).absolutePath      
        def cmd = """grails -Dgrails.project.test.reports.dir=${reportDir} test-app -baseUrl="$target" functional: $pattern"""
        if (browser != "htmlunit") cmd += " -Dgeb.env=$browser"
        def builder = new ProcessBuilder(cmd.split(' '))      
        println "$logPrefix| $cmd"
        def process = builder.redirectErrorStream(true).start()
        def testsOutput = new BufferedReader(new InputStreamReader(process.in))
        exhaust(testsOutput, logPrefix, highlights)      
      }
    }
    threads.each {
        it.join()
    }
    def hr = "="*40
    println hr
    println hr     
    println "The time is now ${new Date().format("HH:mm:ss")}" 
    println "All [$numberOfUsers] user threads  complete"
    if (highlights){ 
      println hr
      highlights.each { println it }
    }
    println hr
    println hr    
  }

}

exhaust = { Reader reader, String prefix, List highlights, boolean noWait = false, Closure stopAt = null ->
	if (noWait && !reader.ready()) {
		return null
	}
	def verbose = false
	def line = reader.readLine()
	while (line != null) {
    def lineWithPrefix = "${prefix}${line}"
    if (line.toLowerCase().contains('tests passed') || line.toLowerCase().contains('tests failed')) { 
      highlights << lineWithPrefix
    }
    if (verbose == true) { println lineWithPrefix }
    else if (line.toLowerCase().contains('error')) {
      println lineWithPrefix
    }
		if (stopAt) {
			def stopped = stopAt(line)
			if (stopped) {
				return stopped
			}
		}
		
		if (noWait && !reader.ready()) {
			return null
		}
		
		line = reader.readLine()
	}
}

validateUrl = { String url ->
  try { 
    new URL(url) 
    if (!url.endsWith('/')) {
      println "Invalid url [$url]. Url needs to end with /"  
      return false
    }
    return true 
  } catch (MalformedURLException e) { 
    println "Invalid url [$url]."
    return false 
  } 
}

