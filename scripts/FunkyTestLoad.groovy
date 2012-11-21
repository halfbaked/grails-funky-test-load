import org.codehaus.groovy.grails.cli.CommandLineHelper

target(default: "Runs functional tests in parallel inorder to simulate concurrent users") {
  
  def reportsDir = 'target/test-reports/functestload'  
  new File(reportsDir).mkdirs()
	def inputHelper = new CommandLineHelper()

  def target = "http://localhost:8080/"
  def numberOfUsers = 1
  def threads = []
  def browser = "htmlunit"
  def pattern = ""
  def highlights
  
  while(true){
    highlights = [] // reset
    def line
    println ""
		println "Ready to load test funky style"
		println " - Just hit return on the following questions to use defaults"
		println " - Ctrl+c to exit"
		println ""
    println "What is your target [$target]? "
    line = inputHelper.userInput("")?.trim()
		if (line) target = line
		println ""
    println "How many users [$numberOfUsers]?"
    line = inputHelper.userInput("")?.trim()
    if (line) numberOfUsers = new Integer(line)
		println ""
    println "Want a test pattern [$pattern]?"
    line = inputHelper.userInput("")?.trim()
    if (line) pattern = line
    println ""
    println "What browser [$browser]?"
    println "Possibles: [chrome, firefox, htmlunit]"
    line = inputHelper.userInput("")?.trim()
    if (line) browser = line
    println ""

    numberOfUsers.times { threadNum ->
      threads << Thread.start {
        def reportDir = new File(reportsDir + '/' + 'user' + threadNum).absolutePath      
        def cmd = """grails -Dgrails.project.test.reports.dir=${reportDir} test-app -baseUrl="$target" functional: $pattern"""
        if (browser != "htmlunit") cmd += " -Dgeb.env=$browser"
        def builder = new ProcessBuilder(cmd.split(' '))      
        def process = builder.redirectErrorStream(true).start()
        def testsOutput = new BufferedReader(new InputStreamReader(process.in))
        exhaust(testsOutput, "User $threadNum", highlights)      
      }
    }
    threads.each {
        it.join()
    }
    def hr = "="*40
    println """
      $hr
      $hr
      All [$numberOfUsers] user threads  complete
      $hr
      ${highlights}
      $hr
      $hr
    """
  }

}

exhaust = { Reader reader, String prefix, List highlights, boolean noWait = false, Closure stopAt = null ->
	if (noWait && !reader.ready()) {
		return null
	}
	
	def line = reader.readLine()
	while (line != null) {
    def lineWithPrefix = "${prefix}${line}"
		println lineWithPrefix
    if (line.toLowerCase().contains('tests passed') || line.toLowerCase().contains('tests failed')) { 
      highlights << lineWithPrefix
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
