# ---------------------------------------------------------------------------
# Imports
# ---------------------------------------------------------------------------

#require 'buildr/resolver'

MAIN_BUNDLE      = 'src/main/resources/Bundle.properties'


# Buildr needs THIS_VERSION to be a string.
# VERSION          = File.open(MAIN_BUNDLE) do |f|
  #f.readlines.select {|s| s =~ /^api\.version/}.map {|s| s.chomp.sub(/^.*=/, '')}
#end[0]


PROJECT = 'parsley'
VERSION = '1.0.0'
THIS_VERSION     = "#{VERSION}"
THIS_POM         = "target/#{PROJECT}-#{VERSION}.pom"
THIS_GROUP = "mats"
COPYRIGHT = "Ljunggren Consulting"

# This artifact
ARTIFACT         = "org.clapper:#{PROJECT}:jar:#{VERSION}"


# Specify Maven 2.0 remote repositories here, like this:
repositories.remote << "http://repo1.maven.org/maven2"
repositories.release_to = "/Library/Tomcat/webapps/"

JUNIT = 'junit:junit:jar:4.11'
SERVLET_API = 'javax.servlet:servlet-api:jar:2.5'
COMMONS_LOGGING = 'commons-logging:commons-logging:jar:1.1.1'
CUCUMBER_JUNIT = 'info.cukes:cucumber-junit:jar:1.1.5'
CUCUMBER_JAVA = 'info.cukes:cucumber-java:jar:1.1.5'
CUCUMBER_CORE = 'info.cukes:cucumber-core:jar:1.1.5'
CUCUMBER_DEPS = 'info.cukes:cucumber-jvm-deps:jar:1.0.3'
GHERKIN = 'info.cukes:gherkin:jar:2.12.2'
XMLPULL = 'xmlpull:xmlpull:jar:1.1.3.1'
DOM4J = 'dom4j:dom4j:jar:1.6.1'
JDOM = 'org.jdom:jdom-legacy:jar:1.1.3'
WOODSTOX = 'org.codehaus.woodstox:wstx-lgpl:jar:3.2.6'
CGLIB = 'cglib:cglib:jar:3.1'
XOM = 'xom:xom:jar:1.2.5'
STAX = 'stax:stax:jar:1.2.0_rc2-dev'
CUCUMBER_REPORTING = 'net.masterthought:cucumber-reporting:jar:0.0.23'


Project.local_task :tomcat
Project.local_task :deploy

desc "The Parsley project"

define "parsley" do

  project.version = THIS_VERSION
  project.group = THIS_GROUP
  manifest["Implementation-Vendor"] = COPYRIGHT
  compile.with SERVLET_API, COMMONS_LOGGING, CUCUMBER_JUNIT, CUCUMBER_JAVA, CUCUMBER_DEPS, CUCUMBER_CORE, GHERKIN, XMLPULL, DOM4J, JDOM, WOODSTOX, CGLIB, XOM, STAX, JUNIT, CUCUMBER_REPORTING
  resources
  test.compile.with # Add classpath dependencies
  #test.exclude 'cucumber.*'

  test.resources
  package(:war)
  task :tomcat => :package do
    system '/Library/Tomcat/bin/shutdown.sh'
    system 'rm -rf /Library/Tomcat/webapps/parsley*'
    system 'cp target/*.war /Library/Tomcat/webapps/'
    system '/Library/Tomcat/bin/catalina.sh start'
  end
  package(:war)
  task :deploy => :package do
    system 'cp -r * /Users/matsljunggren/Cloud/OpenShift/jbossas1'
    end
end

# ---------------------------------------------------------------------------
# Utility Functions
# ---------------------------------------------------------------------------

def msg(s)
  $stderr.puts "*** #{s}"
end

# ---------------------------------------------------------------------------
# Gross and ugly hacks
# ---------------------------------------------------------------------------

# #Create a POM that has dependencies in it. Uses the buildr/resolver gem.
# def make_pom
# mkdir_p File.dirname(THIS_POM)
#  deps = Buildr::Resolver.resolve(DEPS)
#  Buildr::Resolver.write_pom(ARTIFACT, THIS_POM)
# end

# module Buildr

#   #Local hack job to override Buildr's default POM generation, to include
#   #dependencies in the POM.

#  module Package
#    alias :old_package :package
#    def package(*args)
#      old_package *args
#      make_pom
#     end

#   end

#  module ActsAsArtifact

#    def pom_xml
#      make_pom
#      File.open(THIS_POM).readlines.join('')
#    end
#   end
# end
