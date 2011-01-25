define 'sukrupa' do
	compile.with Dir[_("lib/*.jar")],
                 Dir[_("lib/db/*.jar")],
	             Dir[_("lib/jetty/*.jar")],
	             Dir[_("lib/jsp/*.jar")],
	             Dir[_("lib/production/*.jar")],
	             Dir[_("lib/spring/*.jar")]
	compile.into "web/WEB-INF/classes"

	test.with Dir[_("lib/test/*.jar")]
	test.compile.into "target/test"

	package :war, :file=>_('target/sukrupa.war')

    #TODO: fork off webserver
	run.using :main => "org.sukrupa.web.WebServer"
end
